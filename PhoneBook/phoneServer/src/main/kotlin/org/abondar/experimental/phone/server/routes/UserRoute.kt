package org.abondar.experimental.phone.server.org.abondar.experimental.phone.server.routes

import io.ktor.application.call
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.abondar.experimental.phone.server.model.User
import org.abondar.experimental.phone.server.org.abondar.experimental.phone.server.service.UserService
import org.abondar.experimental.phone.server.conf.serviceDI
import org.kodein.di.instance

fun Route.users(){
    val  userService by serviceDI.instance<UserService>()

    post("user"){
        val userReq = call.receive<User>()
        val userResp = userService.createUser(userReq).toUser()
        call.respond(HttpStatusCode.Created,userResp)
    }

    get("user/{id}"){
        val userId = call.parameters["id"]?.toIntOrNull() ?: throw NotFoundException()
        val user = userService.getUserById(userId)
        call.respond(HttpStatusCode.Found,user)
    }

    delete("user/{id}"){
        val userId = call.parameters["id"]?.toIntOrNull() ?: throw NotFoundException()
        userService.deleteUser(userId)
        call.respond(HttpStatusCode.OK)
    }

    put("user"){
        val userReq = call.receive<User>()
        userService.updatePassword(userReq)
        call.respond(HttpStatusCode.OK)
    }
}
