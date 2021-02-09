package org.abondar.experimental.phone.server.model

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ReferenceOption


object Contacts : IntIdTable("ph_contact"){
    var firstName = varchar("first_name",255)
    var lastName = varchar("last_name",255)
    var phone = varchar("phone",4096)
    var email = varchar("email",4096)
    var user = reference("user",Users,onDelete = ReferenceOption.CASCADE)
}

class ContactEntity (id: EntityID<Int>): IntEntity(id){
    companion object : IntEntityClass<ContactEntity>(Contacts)

    var firstName by Contacts.firstName
    var lastName by Contacts.lastName
    var phone by Contacts.phone
    var email by Contacts.email
    var user by UserEntity referencedOn Contacts.user


    fun toContact() = Contact(id.value,firstName,lastName,phone,email)

}


data class Contact(
    val id: Int,

    val firstName: String,
    val lastName: String,
    val phone: String,
    val email: String
)
