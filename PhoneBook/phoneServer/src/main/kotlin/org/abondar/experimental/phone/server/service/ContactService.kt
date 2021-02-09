package org.abondar.experimental.phone.server.org.abondar.experimental.phone.server.service

import org.abondar.experimental.phone.server.model.Contact
import org.abondar.experimental.phone.server.model.ContactEntity
import org.abondar.experimental.phone.server.model.Contacts
import org.abondar.experimental.phone.server.model.UserEntity


import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

class ContactService() {

    fun createContact(contact: Contact,userId: Int) = transaction{
        ContactEntity.new {

                this.firstName=contact.firstName
                this.lastName=contact.lastName
                this.phone=contact.phone
                this.email=contact.email
                this.user = UserEntity[userId]
            }

    }

    fun getContactById(id: Int) = transaction {
        ContactEntity[id].toContact()
    }

    fun deleteContact(id: Int) = transaction {
        ContactEntity[id].delete()
    }

    fun updateContact(contact: Contact) = transaction{
        val ce = ContactEntity[contact.id]

        ce.email = contact.email
        ce.firstName = contact.firstName
        ce.lastName = contact.lastName
        ce.phone = contact.phone

    }

    fun updateAndMergedContacts(contact: Contact,mergedIds: List<Int> ) = transaction {
        val ce = ContactEntity[contact.id]
        ce.email = contact.email
        ce.firstName = contact.firstName
        ce.lastName = contact.lastName
        ce.phone = contact.phone

        ContactEntity.forIds(mergedIds).forEach {
            it.delete()
        }
    }
}
