package com.example.excersices

import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject

val client = OkHttpClient()
val apiKey = "Client-ID 2k7FU31RiDWg5u6mJ2SRgg-ljyb6Y_5RiOPmICzaisg"

fun getRandomPhoto(): Unsplash {

    val request = Request.Builder()
        .url("https://api.unsplash.com/photos/random")
        .get()
        .addHeader("accept", "application/json")
        .addHeader("Authorization", apiKey)
        .build()

    val response = client.newCall(request).execute()
    val body = response.body?.string()

    return Unsplash(body ?: "")
}
