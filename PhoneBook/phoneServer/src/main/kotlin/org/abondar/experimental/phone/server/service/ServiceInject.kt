package org.abondar.experimental.phone.server.service

import org.abondar.experimental.phone.server.org.abondar.experimental.phone.server.service.UserService
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton

val serviceDI = DI{
     bind<UserService>() with singleton { UserService() }
}
