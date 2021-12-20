package com.demo.telepassdigitalandroid.network.model

import com.squareup.moshi.Json

class PokeList : Paged() {
    @field:Json(name = "results") var results: List<Poke> ? = listOf()
}