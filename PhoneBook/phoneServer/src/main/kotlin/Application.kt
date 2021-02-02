package org.abondar.experimental.phone.server

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.routing
import io.ktor.util.KtorExperimentalAPI
import org.abondar.experimental.phone.server.conf.initDB
import org.abondar.experimental.phone.server.routes.apiRoute
import org.abondar.experimental.phone.server.service.bindServices
import org.jetbrains.exposed.dao.exceptions.EntityNotFoundException
import org.kodein.di.ktor.di


fun main(args: Array<String>): Unit  = io.ktor.server.netty.EngineMain.main(args)


@KtorExperimentalAPI
@Suppress("unused")
@JvmOverloads
fun Application.module(testing: Boolean = false){
   initDB()

   install(ContentNegotiation) {
       gson {  }
   }

   install(CallLogging)

   install(StatusPages){
       exception<EntityNotFoundException>{
           call.respond(HttpStatusCode.NotFound)
       }
   }

   di {
      bindServices()
    }

   routing {
       apiRoute()
   }

}
