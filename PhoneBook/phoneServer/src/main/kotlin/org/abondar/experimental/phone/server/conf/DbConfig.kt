package org.abondar.experimental.phone.server.conf

import com.typesafe.config.ConfigFactory
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.application.Application
import io.ktor.config.*
import io.ktor.util.KtorExperimentalAPI
import org.abondar.experimental.phone.server.model.Contacts
import org.abondar.experimental.phone.server.model.Users
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.slf4j.LoggerFactory



@KtorExperimentalAPI
fun Application.initDB(){
    val conf = HoconApplicationConfig(ConfigFactory.load())

    val dataSource = hikary(conf)
    Database.connect(dataSource)
    createTables()
    LoggerFactory.getLogger(Application::class.simpleName).info("Initialized Database")
}


private fun hikary(conf : HoconApplicationConfig) : HikariDataSource{
    val dbConf = HikariConfig()

    dbConf.driverClassName = conf.property("dataSource.className").getString()
    dbConf.username=conf.property("dataSource.user").getString()
    dbConf.password=conf.property("dataSource.password").getString()
    dbConf.jdbcUrl=conf.property("dataSource.url").getString()
    dbConf.maximumPoolSize=conf.property("dataSource.maxPoolSize").getString().toInt()
    dbConf.isAutoCommit=conf.property("dataSource.autoCommit").getString().toBoolean()
    dbConf.transactionIsolation=conf.property("dataSource.transactionIsolation").getString()


    dbConf.validate()

    return HikariDataSource(dbConf)
}

private fun createTables() = transaction {
    SchemaUtils.create(Users,Contacts)
}
