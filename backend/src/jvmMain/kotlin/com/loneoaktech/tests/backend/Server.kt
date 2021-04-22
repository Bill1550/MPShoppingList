package com.loneoaktech.tests.backend

import com.loneoaktech.tests.shared.domain.model.ShoppingListItem
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

fun main(args: Array<String>) = io.ktor.server.netty.EngineMain.main(args)



fun Application.module( testing: Boolean = false ) {

    install( ContentNegotiation ) {
        json(
            Json {
                encodeDefaults = true
                prettyPrint = true
            }
        )
    }

    install( DefaultHeaders ){
        header("X-Engine", "Ktor")
    }

    routing {
        get("/") {
            call.respondText(
                "Hello from backend",
                ContentType.Text.Html
            )
        }

        route("/api") {
            get("/list") {
                call.respond(
                    listOf(
                        ShoppingListItem("stuff", 42, "get the good ones"),
                        ShoppingListItem("other stuff", 2, "get the cheep ones")
                    )
                )
            }

            post( "/add" ) {
                println( call.receive<ShoppingListItem>() )
                call.respond(HttpStatusCode.OK)
            }
        }
    }
}