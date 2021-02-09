package org.abondar.experimental.phone.server.org.abondar.experimental.phone.server.service

import org.abondar.experimental.phone.server.model.User
import org.mindrot.jbcrypt.BCrypt

class LoginService(private val userService: UserService) {

    fun loginUser(user: User): Boolean{
          val res = userService.getUserById(user.id)

          return  BCrypt.checkpw(user.password, res.password)
    }
}
