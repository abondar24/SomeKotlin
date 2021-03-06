package org.abondar.experimental.phone.server

import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.routing
import io.ktor.sessions.*
import io.ktor.util.KtorExperimentalAPI
import org.abondar.experimental.phone.server.conf.initDB
import org.abondar.experimental.phone.server.conf.initTestDB
import org.abondar.experimental.phone.server.conf.serviceDI
import org.abondar.experimental.phone.server.org.abondar.experimental.phone.server.conf.AuthConfig
import org.abondar.experimental.phone.server.org.abondar.experimental.phone.server.conf.AuthConfig.jwtAudience
import org.abondar.experimental.phone.server.org.abondar.experimental.phone.server.conf.AuthConfig.jwtHeader
import org.abondar.experimental.phone.server.org.abondar.experimental.phone.server.conf.AuthConfig.jwtIssuer
import org.abondar.experimental.phone.server.org.abondar.experimental.phone.server.conf.AuthConfig.jwtRealm
import org.abondar.experimental.phone.server.org.abondar.experimental.phone.server.conf.AuthConfig.loginPath
import org.abondar.experimental.phone.server.org.abondar.experimental.phone.server.model.LoginSession
import org.abondar.experimental.phone.server.routes.apiRoute
import org.jetbrains.exposed.dao.exceptions.EntityNotFoundException
import org.kodein.di.ktor.di
import java.lang.NumberFormatException
import java.text.DateFormat
import javax.naming.AuthenticationException


fun main(args: Array<String>): Unit  = io.ktor.server.netty.EngineMain.main(args)




@KtorExperimentalAPI
@Suppress("unused")
@JvmOverloads
fun Application.appModule(testing: Boolean = false){

    if (testing){
        initTestDB()
    } else {
        initDB()
        jwtIssuer = environment.config.property("ktor.jwt.domain").getString()
        jwtAudience = environment.config.property("ktor.jwt.audience").getString()
        jwtRealm = environment.config.property("ktor.jwt.realm").getString()

    }

   install(ContentNegotiation) {
       gson {
           setPrettyPrinting()
           setDateFormat(DateFormat.LONG)
       }
   }

   install(CallLogging)

   install(StatusPages){
       exception<EntityNotFoundException>{
           call.respond(HttpStatusCode.NotFound)
       }

       exception<NotFoundException>{
           call.respond(HttpStatusCode.BadRequest)
       }

       exception<NumberFormatException>{
           call.respond(HttpStatusCode.BadRequest)
       }

       exception<NullPointerException>{
           call.respond(HttpStatusCode.BadRequest)
       }

       exception<AuthenticationException>{
           call.respond(HttpStatusCode.Forbidden)
       }
   }

    install(Sessions){
        cookie<LoginSession>("Phone_Session")
    }

    install(Authentication){
        jwt ("jwt"){
            verifier(AuthConfig.verifier)
            skipWhen { call -> call.sessions.get<LoginSession>() != null }
            skipWhen { call -> call.request.path() == loginPath }
            skipWhen { call -> call.request.path() == "/user" && call.request.httpMethod== HttpMethod.Post}
            skipWhen { testing }

            validate { credential ->
                if (credential.payload.audience.contains(jwtAudience)) JWTPrincipal(credential.payload) else null
            }
        }
    }

   di {
     serviceDI
    }

   routing {
       apiRoute()
   }

}
