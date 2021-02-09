package org.abondar.experimental.phone.server.service

import org.abondar.experimental.phone.server.conf.initTestDB
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
        val user = User(0,"test","test")
        val usr = userService.createUser(user).toUser()

        val contact = Contact(0,"test","test","test","")
        val res = contactService.createContact(contact,usr.id).toContact()

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
        val user = User(0,"test","test")
        val usr = userService.createUser(user).toUser()

        val contact = Contact(0,"test","test","test","")
        val ct = contactService.createContact(contact,usr.id).toContact()

        val res = contactService.getContactById(ct.id)
        assertEquals(ct.id,res.id)

    }

    @Test
    fun deleteContactTest(){
        initTestDB()
        val user = User(0,"test","test")
        val usr = userService.createUser(user).toUser()

        val contact = Contact(0,"test","test","test","")
        val ct = contactService.createContact(contact,usr.id).toContact()

        contactService.deleteContact(ct.id)

        assertThrows<EntityNotFoundException> { contactService.getContactById(ct.id) }

    }

    @Test
    fun updateContactTest(){
        initTestDB()
        val user = User(0,"test","test")
        val usr = userService.createUser(user).toUser()

        val contact = Contact(0,"test","test","test","")
        val ct = contactService.createContact(contact,usr.id).toContact()

        val upd = Contact(ct.id,ct.firstName,ct.lastName,ct.phone,"email")
        contactService.updateContact(upd)

        val res = contactService.getContactById(upd.id)
        assertEquals(upd.email,res.email)

    }


    @Test
    fun updateMergeContactTest(){
        initTestDB()
        val user = User(0,"test","test")
        val usr = userService.createUser(user).toUser()

        val contact = Contact(0,"test","test","test","")
        val ct = contactService.createContact(contact,usr.id).toContact()

        val contact1 = Contact(0,"test","test","test","")
        val ct1 = contactService.createContact(contact1,usr.id).toContact()

        val upd = Contact(ct.id,ct.firstName,ct.lastName,ct.phone,"email")
        contactService.updateAndMergedContacts(upd, listOf(ct1.id))

        val res = contactService.getContactById(upd.id)
        assertEquals(upd.email,res.email)

        assertThrows<EntityNotFoundException> { contactService.getContactById(ct1.id) }
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
}
