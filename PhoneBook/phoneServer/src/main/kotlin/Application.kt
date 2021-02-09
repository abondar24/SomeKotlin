package org.abondar.experimental.phone.server

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.routing
import io.ktor.util.KtorExperimentalAPI
import org.abondar.experimental.phone.server.conf.initDB
import org.abondar.experimental.phone.server.conf.initTestDB
import org.abondar.experimental.phone.server.routes.apiRoute
import org.abondar.experimental.phone.server.service.serviceDI
import org.jetbrains.exposed.dao.exceptions.EntityNotFoundException
import org.kodein.di.ktor.di
import java.text.DateFormat


fun main(args: Array<String>): Unit  = io.ktor.server.netty.EngineMain.main(args)


@KtorExperimentalAPI
@Suppress("unused")
@JvmOverloads
fun Application.appModule(testing: Boolean = false){
    if (testing){
        initTestDB()
    } else {
        initDB()

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
   }

   di {
     serviceDI
    }

   routing {
       apiRoute()
   }

}
