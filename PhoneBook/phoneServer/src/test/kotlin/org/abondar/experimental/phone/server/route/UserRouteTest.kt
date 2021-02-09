package org.abondar.experimental.phone.server.route


import com.google.gson.Gson
import io.ktor.http.*
import io.ktor.server.testing.*
import org.junit.jupiter.api.Test
import io.ktor.util.*

import io.ktor.server.testing.setBody
import org.abondar.experimental.phone.server.model.User
import org.abondar.experimental.phone.server.appModule


import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class UserRouteTest {

    @KtorExperimentalAPI
    @Test
    fun createUserTest() = withTestApplication({
        appModule(true)
    }) {

        val gson = Gson()
        val user = User(0,"test","test")
        val body =  gson.toJson(user)
        val call= handleRequest(HttpMethod.Post, "/user") {
           addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            setBody(body)
        }

        val status = call.response.status()?.value
        val resp = gson.fromJson(call.response.content,User::class.java)

        assertTrue { call.requestHandled }
        assertEquals(201,status)
        assertEquals(user.username,resp.username)

    }

    @KtorExperimentalAPI
    @Test
    fun findUserTest() = withTestApplication({
        appModule(true)
    }) {

        val gson = Gson()
        val user = User(0,"test","test")
        val body =  gson.toJson(user)
        var call= handleRequest(HttpMethod.Post, "/user") {
            addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            setBody(body)
        }

        val resp = gson.fromJson(call.response.content,User::class.java)

        call= handleRequest(HttpMethod.Get, "/user/"+resp.id) {
            addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
        }

        val found = gson.fromJson(call.response.content,User::class.java)
        val status = call.response.status()?.value


        assertTrue { call.requestHandled }
        assertEquals(302,status)
        assertEquals(resp.id,found.id)


    }

    @KtorExperimentalAPI
    @Test
    fun findUserNotFoundTest() = withTestApplication({
        appModule(true)
    }) {

        val call= handleRequest(HttpMethod.Get, "/user/7") {
            addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
        }

        val status = call.response.status()?.value

        assertFalse{ call.requestHandled }
        assertEquals(404,status)

    }

    @KtorExperimentalAPI
    @Test
    fun findUserBadRequestTest() = withTestApplication({
        appModule(true)
    }) {

        val call= handleRequest(HttpMethod.Get, "/user/null") {
            addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
        }

        val status = call.response.status()?.value
        assertTrue{ call.requestHandled }
        assertEquals(400,status)

    }


    @KtorExperimentalAPI
    @Test
    fun deleteUserTest() = withTestApplication({
        appModule(true)
    }) {

        val gson = Gson()
        val user = User(0,"test","test")
        val body =  gson.toJson(user)
        var call= handleRequest(HttpMethod.Post, "/user") {
            addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            setBody(body)
        }

        val resp = gson.fromJson(call.response.content,User::class.java)

        call= handleRequest(HttpMethod.Delete, "/user/"+resp.id) {
            addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
        }

        val status = call.response.status()?.value


        assertTrue { call.requestHandled }
        assertEquals(200,status)

    }

}
