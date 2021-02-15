package org.abondar.experimental.phone.server.org.abondar.experimental.phone.server.service

import com.auth0.jwt.JWT
import org.abondar.experimental.phone.server.model.User
import org.abondar.experimental.phone.server.org.abondar.experimental.phone.server.conf.AuthConfig.alg
import org.abondar.experimental.phone.server.org.abondar.experimental.phone.server.conf.AuthConfig.jwtAudience
import org.abondar.experimental.phone.server.org.abondar.experimental.phone.server.conf.AuthConfig.jwtIssuer
import org.abondar.experimental.phone.server.org.abondar.experimental.phone.server.conf.AuthConfig.validTime
import org.mindrot.jbcrypt.BCrypt
import java.util.*
import javax.naming.AuthenticationException

class LoginService(private val userService: UserService) {

    fun loginUser(user: User): String{
          if (!verifyUser(user)){
              throw AuthenticationException("Wrong credentials")
         }

        return generateToken(user)
    }

    fun verifyUser(user: User): Boolean{
        val res = userService.getUserById(user.id)
       return BCrypt.checkpw(user.password,res.password)
    }

    fun logoutUser(userId: Int){
        userService.getUserById(userId)
    }

    private fun generateToken(user: User): String = JWT.create()
        .withSubject("Authentication")
        .withIssuer(jwtIssuer)
        .withAudience(jwtAudience)
        .withClaim("name", user.username)
        .withClaim("password", user.password)
        .withExpiresAt(getExpiration())
        .sign(alg)


    private fun getExpiration() = Date(System.currentTimeMillis() + validTime)


}
