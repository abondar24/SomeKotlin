package org.abondar.experimental.phone.server.org.abondar.experimental.phone.server.routes

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.abondar.experimental.phone.server.model.Contact
import org.abondar.experimental.phone.server.org.abondar.experimental.phone.server.service.ContactService
import org.abondar.experimental.phone.server.service.serviceDI
import org.kodein.di.instance

fun Route.contacts(){
    val  contactService by serviceDI.instance<ContactService>()


    post("contact/{userId}"){
        val ctReq = call.receive<Contact>()
        val userId = call.parameters["userId"]?.toIntOrNull() ?: throw NotFoundException()
        val ctResp = contactService.createContact(ctReq,userId).toContact()
        call.respond(HttpStatusCode.Created,ctResp)
    }

    get("contact/{id}"){
        val ctId = call.parameters["id"]?.toIntOrNull() ?: throw NotFoundException()
        val ct = contactService.getContactById(ctId)
        call.respond(HttpStatusCode.Found,ct)
    }

    delete("contact/{id}"){
        val ctId = call.parameters["id"]?.toIntOrNull() ?: throw NotFoundException()
        contactService.deleteContact(ctId)
        call.respond(HttpStatusCode.OK)
    }

    put ("contact"){
        val ctReq = call.receive<Contact>()
        contactService.updateContact(ctReq)
        call.respond(HttpStatusCode.OK)
    }

    put ("contact/merge"){
        val ctReq = call.receive<Contact>()
        val params = call.request.queryParameters
        val ids = params.getAll("id")?.map { it.toInt() }


        contactService.updateAndMergedContacts(ctReq,ids!!)
        call.respond(HttpStatusCode.OK)
    }



}
