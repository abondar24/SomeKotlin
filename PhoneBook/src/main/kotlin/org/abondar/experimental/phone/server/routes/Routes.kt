package org.abondar.experimental.phone.server.routes

import io.ktor.auth.*
import io.ktor.routing.Routing
import io.ktor.routing.route
import org.abondar.experimental.phone.server.org.abondar.experimental.phone.server.routes.contacts
import org.abondar.experimental.phone.server.org.abondar.experimental.phone.server.routes.login
import org.abondar.experimental.phone.server.org.abondar.experimental.phone.server.routes.users


fun Routing.apiRoute(){
    route("/"){
         authenticate ("jwt"){
             users()
             contacts()
             login()
         }

    }

}

