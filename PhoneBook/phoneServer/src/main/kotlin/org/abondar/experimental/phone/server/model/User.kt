package org.abondar.experimental.phone.server.model

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object Users : IntIdTable("ph_user"){
    var username = varchar("username",255)
    var password = varchar("password",255)
}

class UserEntity (id: EntityID<Int>): IntEntity(id){
    companion object : IntEntityClass<UserEntity>(Users)

    var username by Users.username
    var password by Users.password

    override fun toString(): String {
        return "UserEntity(username='$username')"
    }

    fun toUser() = User(id.value,username,password)

}

data class User(
    val id: Int,
    val userName: String,
    val password: String
)
