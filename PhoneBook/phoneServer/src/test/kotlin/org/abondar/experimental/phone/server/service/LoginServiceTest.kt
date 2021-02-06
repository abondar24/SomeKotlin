package org.abondar.experimental.phone.server.service

import org.abondar.experimental.phone.server.conf.initTestDB
import org.abondar.experimental.phone.server.model.User
import org.abondar.experimental.phone.server.org.abondar.experimental.phone.server.service.LoginService
import org.abondar.experimental.phone.server.org.abondar.experimental.phone.server.service.UserService
import org.jetbrains.exposed.dao.exceptions.EntityNotFoundException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.kodein.di.instance
import org.kodein.di.newInstance
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class LoginServiceTest {

    private val  userService by serviceDI.instance<UserService>()
    private val  loginService by serviceDI.newInstance { LoginService(instance()) }

    @Test
    fun loginServiceTest(){
        initTestDB()
        var user = User(0,"test","test")
        user = userService.createUser(user).toUser()
        val usr = User(user.id,username = user.username,"test")

        val res = loginService.loginUser(usr)
        assertTrue { res }
    }

    @Test
    fun loginServiceUserNotFoundTest(){
        initTestDB()
        val user = User(0,"test","test")
        assertThrows<EntityNotFoundException> { loginService.loginUser(user) }
    }

    @Test
    fun loginServicePasswordNotMatchesTest(){
        initTestDB()
        var user = User(0,"test","test")
        user = userService.createUser(user).toUser()

        val usr = User(user.id,username = user.username,"newPass")
        val res = loginService.loginUser(usr)

        assertFalse { res }
    }
}
