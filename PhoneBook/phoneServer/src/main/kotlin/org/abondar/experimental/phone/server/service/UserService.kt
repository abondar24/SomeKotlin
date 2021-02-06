package org.abondar.experimental.phone.server.org.abondar.experimental.phone.server.service

import org.abondar.experimental.phone.server.model.User
import org.abondar.experimental.phone.server.model.UserEntity
import org.jetbrains.exposed.sql.transactions.transaction
import org.mindrot.jbcrypt.BCrypt

class UserService {

    fun createUser(user: User) = transaction {
        UserEntity.new {
            this.username = user.username
            this.password = BCrypt.hashpw(user.password,BCrypt.gensalt())
        }
    }

    fun getUserById(id: Int) = transaction {
        UserEntity[id].toUser()
    }

    fun deleteUser(id: Int) = transaction {
        UserEntity[id].delete()
    }

}
