package org.abondar.experimental.phone.server.route

import com.google.gson.Gson
import io.ktor.http.*
import io.ktor.server.testing.*
import io.ktor.util.*
import org.abondar.experimental.phone.server.appModule
import org.abondar.experimental.phone.server.model.Contact
import org.abondar.experimental.phone.server.model.User
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ContactRouteTest {

    @KtorExperimentalAPI
    @Test
    fun createContactTest() = withTestApplication({
        appModule(true)
    }) {

        val gson = Gson()
        val usrId=createUser(gson)

        val contact = Contact(0,"test","test","test","")
        val body =  gson.toJson(contact)
        val call= handleRequest(HttpMethod.Post, "/contact/$usrId") {
            addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            setBody(body)
        }

        val status = call.response.status()?.value
        val resp = gson.fromJson(call.response.content, Contact::class.java)

        assertTrue { call.requestHandled }
        assertEquals(201,status)
        assertEquals(contact.firstName,resp.firstName)

    }

    @KtorExperimentalAPI
    @Test
    fun findContactTest() = withTestApplication({
        appModule(true)
    }) {

        val gson = Gson()
        val usrId=createUser(gson)
        val ctId = createContact(gson,usrId)

        val call= handleRequest(HttpMethod.Get, "/contact/$ctId") {
            addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
        }

        val status = call.response.status()?.value
        val resp = gson.fromJson(call.response.content, Contact::class.java)

        assertTrue { call.requestHandled }
        assertEquals(302,status)
        assertEquals(ctId,resp.id)

    }


    @KtorExperimentalAPI
    @Test
    fun findContactNotFoundTest() = withTestApplication({
        appModule(true)
    }) {

        val call= handleRequest(HttpMethod.Get, "/contact/7") {
            addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
        }

        val status = call.response.status()?.value

        assertFalse { call.requestHandled }
        assertEquals(404,status)


    }

    @KtorExperimentalAPI
    @Test
    fun findContactBadTest() = withTestApplication({
        appModule(true)
    }) {

        val gson = Gson()

        val call= handleRequest(HttpMethod.Get, "/contact/null") {
            addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
        }

        val status = call.response.status()?.value

        assertTrue { call.requestHandled }
        assertEquals(400,status)


    }


    @KtorExperimentalAPI
    @Test
    fun deleteContactTest() = withTestApplication({
        appModule(true)
    }) {

        val gson = Gson()
        val usrId=createUser(gson)
        val ctId = createContact(gson,usrId)

        val call= handleRequest(HttpMethod.Delete, "/contact/$ctId") {
            addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
        }

        val status = call.response.status()?.value

        assertTrue { call.requestHandled }
        assertEquals(200,status)

    }


    @KtorExperimentalAPI
    @Test
    fun updateContactTest() = withTestApplication({
        appModule(true)
    }) {

        val gson = Gson()
        val usrId=createUser(gson)
        val ctId = createContact(gson,usrId)

        val upd = Contact(ctId,"test1","test","test","")
        val body =  gson.toJson(upd)

        val call= handleRequest(HttpMethod.Put, "/contact") {
            addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            setBody(body)
        }

        val status = call.response.status()?.value

        assertTrue { call.requestHandled }
        assertEquals(200,status)

    }

    @KtorExperimentalAPI
    @Test
    fun updateMergeContactTest() = withTestApplication({
        appModule(true)
    }) {

        val gson = Gson()
        val usrId=createUser(gson)
        val ctId = createContact(gson,usrId)
        val ctId1 = createContact(gson,usrId)
        val ctId2 = createContact(gson,usrId)


        val upd = Contact(ctId,"test","test","test","")
        val body =  gson.toJson(upd)

        val call= handleRequest(HttpMethod.Put, "/contact/merge?id=$ctId1&id=$ctId2") {
            addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            setBody(body)
        }

        val status = call.response.status()?.value

        assertTrue { call.requestHandled }
        assertEquals(200,status)

    }

    @KtorExperimentalAPI
    @Test
    fun updateMergeBadContactTest() = withTestApplication({
        appModule(true)
    }) {

        val gson = Gson()
        val usrId=createUser(gson)
        val ctId = createContact(gson,usrId)


        val upd = Contact(ctId,"test","test","test","")
        val body =  gson.toJson(upd)

        val call= handleRequest(HttpMethod.Put, "/contact/merge?id=salo") {
            addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            setBody(body)
        }

        val status = call.response.status()?.value

        assertTrue{ call.requestHandled }
        assertEquals(400,status)

    }

    @KtorExperimentalAPI
    @Test
    fun updateMergeNullTest() = withTestApplication({
        appModule(true)
    }) {

        val gson = Gson()
        val usrId=createUser(gson)
        val ctId = createContact(gson,usrId)


        val upd = Contact(ctId,"test","test","test","")
        val body =  gson.toJson(upd)

        val call= handleRequest(HttpMethod.Put, "/contact/merge") {
            addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            setBody(body)
        }

        val status = call.response.status()?.value

        assertTrue{ call.requestHandled }
        assertEquals(400,status)

    }

    @KtorExperimentalAPI
    private fun createUser(gson: Gson):Int = withTestApplication({
        appModule(true)
    }){
        val user = User(0,"test","test")
        val body =  gson.toJson(user)
         val call = handleRequest(HttpMethod.Post, "/user") {
            addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            setBody(body)
        }

         val resp = gson.fromJson(call.response.content,User::class.java)
         resp.id
    }

    @KtorExperimentalAPI
    fun createContact(gson: Gson,userId: Int):Int = withTestApplication({
        appModule(true)
    }) {

        val contact = Contact(0,"test","test","test","")
        val body =  gson.toJson(contact)
        val call= handleRequest(HttpMethod.Post, "/contact/$userId") {
            addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            setBody(body)
        }

        val resp = gson.fromJson(call.response.content, Contact::class.java)
        resp.id

    }
}
