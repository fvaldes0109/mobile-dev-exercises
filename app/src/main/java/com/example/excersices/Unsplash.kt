package com.example.excersices

import org.json.JSONObject


data class Unsplash(
    val url: String,
    var location: String,
    val city: String,
    val country: String,
    val width: Int,
    val height: Int,
    val camera: String,
    val aperture: String,
    val focalLength: String,
    val iso: Int,
    val downloads: Int,
    val likes: Int,
    val user: String
) {
    constructor(body: String) : this(
        JSONObject(body).getJSONObject("urls").getString("regular"),
        JSONObject(body).getJSONObject("location").getString("name"),
        JSONObject(body).getJSONObject("location").getString("city"),
        JSONObject(body).getJSONObject("location").getString("country"),
        JSONObject(body).getInt("width"),
        JSONObject(body).getInt("height"),
        JSONObject(body).getJSONObject("exif").getString("model"),
        JSONObject(body).getJSONObject("exif").getString("aperture"),
        JSONObject(body).getJSONObject("exif").getString("focal_length"),
        if(JSONObject(body).getJSONObject("exif").has("iso")) JSONObject(body).getJSONObject("exif").getInt("iso") else 0,
        JSONObject(body).getInt("downloads"),
        JSONObject(body).getInt("likes"),
        JSONObject(body).getJSONObject("user").getString("name")
    )
}