package org.abondar.experimental.phone.server.service

import org.abondar.experimental.phone.server.conf.initTestDB
import org.abondar.experimental.phone.server.conf.serviceDI
import org.abondar.experimental.phone.server.model.Contact
import org.abondar.experimental.phone.server.model.User
import org.abondar.experimental.phone.server.org.abondar.experimental.phone.server.service.ContactService
import org.abondar.experimental.phone.server.org.abondar.experimental.phone.server.service.UserService
import org.jetbrains.exposed.dao.exceptions.EntityNotFoundException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.kodein.di.instance
import kotlin.test.assertEquals
import kotlin.test.assertTrue


class ContactServiceTest {
    private val contactService by serviceDI.instance<ContactService>()
    private val userService by serviceDI.instance<UserService>()


    @Test
    fun createContactTest(){
        initTestDB()
        val id = createUser()

        val contact = Contact(0,"test","test","test","")
        val res = contactService.createContact(contact,id).toContact()

        assertTrue {res.id>0}
    }

    @Test
    fun createContactUserNotFoundTest(){
        initTestDB()

        val contact = Contact(0,"test","test","test","")
        assertThrows<EntityNotFoundException> { contactService.createContact(contact,0)}

    }

    @Test
    fun getContactTest(){
        initTestDB()
        val usrId = createUser()
        val ctId = createContact(usrId)

        val res = contactService.getContactById(ctId)
        assertEquals(ctId,res.id)

    }

    @Test
    fun deleteContactTest(){
        initTestDB()
        val usrId = createUser()
        val ctId = createContact(usrId)

        contactService.deleteContact(ctId)

        assertThrows<EntityNotFoundException> { contactService.getContactById(ctId) }

    }

    @Test
    fun updateContactTest(){
        initTestDB()
        val usrId = createUser()
        val ctId = createContact(usrId)

        val upd = Contact(ctId,"test","test","test","email")
        contactService.updateContact(upd)

        val res = contactService.getContactById(upd.id)
        assertEquals(upd.email,res.email)

    }


    @Test
    fun updateMergeContactTest(){
        initTestDB()
        val usrId = createUser()
        val ctId = createContact(usrId)
        val ctId1 = createContact(usrId)

        val upd = Contact(ctId,"test","test","test","email")
        contactService.updateAndMergedContacts(upd, listOf(ctId1))

        val res = contactService.getContactById(upd.id)
        assertEquals(upd.email,res.email)

        assertThrows<EntityNotFoundException> { contactService.getContactById(ctId1) }
    }

    @Test
    fun deleteUserCascadeTest(){
        initTestDB()
        val user = User(0,"test","test")
        val usr = userService.createUser(user).toUser()

        val contact = Contact(0,"test","test","test","")
        val ct = contactService.createContact(contact,usr.id).toContact()

        userService.deleteUser(usr.id)
        assertThrows<EntityNotFoundException> { userService.getUserById(usr.id) }
        assertThrows<EntityNotFoundException> { contactService.getContactById(ct.id) }

    }


    private fun createUser():Int {
        val user = User(0,"test","test")
        val usr = userService.createUser(user).toUser()

        return usr.id
    }


    private fun createContact(userId: Int):Int{

        val contact = Contact(0,"test","test","test","")
        val res = contactService.createContact(contact,userId).toContact()

        return res.id
    }
}
