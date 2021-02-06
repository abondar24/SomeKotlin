package org.abondar.experimental.phone.server.org.abondar.experimental.phone.server.service

import org.abondar.experimental.phone.server.model.User
import org.abondar.experimental.phone.server.service.serviceDI
import org.kodein.di.instance
import org.mindrot.jbcrypt.BCrypt

class LoginService(val userService: UserService) {
 //   private val  userService by serviceDI.instance<UserService>()

    fun loginUser(user: User): Boolean{
          val res = userService.getUserById(user.id)

          return  BCrypt.checkpw(user.password, res.password)
    }
}
