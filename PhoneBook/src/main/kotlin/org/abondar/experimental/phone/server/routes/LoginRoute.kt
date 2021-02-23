package org.abondar.experimental.phone.server.org.abondar.experimental.phone.server.routes

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.sessions.*
import org.abondar.experimental.phone.server.model.User
import org.abondar.experimental.phone.server.org.abondar.experimental.phone.server.model.LoginSession
import org.abondar.experimental.phone.server.org.abondar.experimental.phone.server.service.LoginService
import org.abondar.experimental.phone.server.conf.serviceDI
import org.kodein.di.instance
import org.kodein.di.newInstance

fun Route.login(){
    val loginService by serviceDI.newInstance { LoginService(instance()) }

    post("login"){
        val userReq = call.receive<User>()
        val token = loginService.loginUser(userReq)

        call.sessions.set(LoginSession(userReq.username,1))
        call.respond(HttpStatusCode.OK,token)
    }


    get("logout/{id}"){
        val userId = call.parameters["id"]?.toIntOrNull() ?: throw NotFoundException()
        loginService.logoutUser(userId)

        call.sessions.clear<LoginSession>()
        call.respond(HttpStatusCode.OK)
    }
}
