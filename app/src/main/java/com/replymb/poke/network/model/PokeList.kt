package com.replymb.poke.network.model

import com.squareup.moshi.Json

class PokeList : Paged() {
    @field:Json(name = "results") var results: List<Poke> ? = listOf()
}