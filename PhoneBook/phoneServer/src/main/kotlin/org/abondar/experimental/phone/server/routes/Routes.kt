package org.abondar.experimental.phone.server.routes

import io.ktor.routing.Routing
import io.ktor.routing.route
import org.abondar.experimental.phone.server.org.abondar.experimental.phone.server.routes.users


fun Routing.apiRoute(){
    route("/"){
         users()
    }

}

