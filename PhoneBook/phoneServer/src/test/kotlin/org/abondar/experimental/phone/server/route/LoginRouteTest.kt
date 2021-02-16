package org.abondar.experimental.phone.server.route

import com.google.gson.Gson
import io.ktor.http.*
import io.ktor.server.testing.*
import io.ktor.sessions.*
import io.ktor.util.*
import org.abondar.experimental.phone.server.appModule
import org.abondar.experimental.phone.server.model.User
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

class LoginRouteTest {

    @KtorExperimentalAPI
    @Test
    fun loginUserTest(): Unit = withTestApplication({
        appModule(true)
    }) {

        val gson = Gson()
        val usr = createUser(gson)


        val body =  gson.toJson(usr)
        val call= handleRequest(HttpMethod.Post, "/login") {
            addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            setBody(body)
        }

        val status = call.response.status()?.value
        val resp = gson.fromJson(call.response.content, String::class.java)
        val session = call.sessions.get("Phone_Session")

        print(resp)
        assertEquals(200,status)
        assertTrue { resp.isNotEmpty() }
        assertNotNull(session)
    }


    @KtorExperimentalAPI
    @Test
    fun loginUserNotFoundTest() = withTestApplication({
        appModule(true)
    }) {

        val gson = Gson()
        val usr = User(700,"test","test")


        val body =  gson.toJson(usr)
        val call= handleRequest(HttpMethod.Post, "/login") {
            addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            setBody(body)
        }

        val status = call.response.status()?.value

        assertEquals(404,status)

    }


    @KtorExperimentalAPI
    @Test
    fun loginUserWrongPasswordTest() = withTestApplication({
        appModule(true)
    }) {

        val gson = Gson()
        val user = createUser(gson)

        val usr = User(user.id,"test","test1")


        val body =  gson.toJson(usr)
        val call= handleRequest(HttpMethod.Post, "/login") {
            addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            setBody(body)
        }

        val status = call.response.status()?.value

        assertEquals(403,status)

    }

    @KtorExperimentalAPI
    @Test
    fun logoutUserTest() = withTestApplication({
        appModule(true)
    }) {

        val gson = Gson()
        val user = createUser(gson)

        val body =  gson.toJson(user)
        val call= handleRequest(HttpMethod.Get, "/logout/${user.id}") {
            addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            setBody(body)
        }

        val status = call.response.status()?.value
        val session = call.sessions.get("Phone_Session")

        assertEquals(200,status)
        assertNull(session)
    }


    @KtorExperimentalAPI
    private fun createUser(gson: Gson):User = withTestApplication({
        appModule(true)
    }) {

        val user = User(0,"test","test")
        val body =  gson.toJson(user)
        val call= handleRequest(HttpMethod.Post, "/user") {
            addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            setBody(body)
        }

        val resp = gson.fromJson(call.response.content,User::class.java)
        val res = User(resp.id,"test","test")
        res
    }
}
