package org.abondar.experimental.phone.server.service


import org.junit.jupiter.api.Test
import org.abondar.experimental.phone.server.conf.initTestDB
import org.abondar.experimental.phone.server.model.User
import org.abondar.experimental.phone.server.org.abondar.experimental.phone.server.service.UserService
import org.jetbrains.exposed.dao.exceptions.EntityNotFoundException
import org.junit.jupiter.api.assertThrows
import org.kodein.di.instance
import kotlin.test.assertEquals
import kotlin.test.assertTrue

//TODO: refactor data generation
class UserServiceTest {

    private val  userService by serviceDI.instance<UserService>()


    @Test
    fun createUserTest(){
          initTestDB()
          val user = User(0,"test","test")
          val res = userService.createUser(user).toUser()

          assertTrue {res.id>0}
    }

    @Test
    fun getUserByIdTest(){
        initTestDB()
        val user = User(0,"test","test")
        val usr = userService.createUser(user).toUser()

        val res = userService.getUserById(usr.id)

        assertEquals(usr.id,res.id)
    }

    @Test
    fun deleteTest(){
        initTestDB()
        val user = User(0,"test","test")
        var usr = userService.createUser(user).toUser()
        usr = userService.getUserById(usr.id)

        userService.deleteUser(usr.id)
        assertThrows<EntityNotFoundException> { userService.getUserById(usr.id) }

    }
}
