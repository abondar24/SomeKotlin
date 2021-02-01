package org.abondar.experimental.phone.server.model

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ReferenceOption

object Contacts : IntIdTable("ph_contact"){
    var firstName = varchar("firstName",255)
    var lastName = varchar("lastName",255)
    var phone = varchar("phone",255)
    var email = varchar("email",255)
    var userId = integer("user_id")
        .uniqueIndex()
        .references(Users.id,onDelete = ReferenceOption.CASCADE)
}

class ContactEntity (id: EntityID<Int>): IntEntity(id){
    companion object : IntEntityClass<ContactEntity>(Contacts)

    var userId by Contacts.userId
    var firstName by Contacts.firstName
    var lastName by Contacts.lastName
    var phone by Contacts.phone
    var email by Contacts.email


    fun toContact() = Contact(id.value,userId,firstName,lastName,phone,email)

}


data class Contact(
    val id: Int,
    val userId: Int,
    val firstName: String,
    val lastName: String,
    val phone: String,
    val email: String
)
