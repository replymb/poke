package com.demo.telepassdigitalandroid.network.model

import com.squareup.moshi.Json

class Poke {
    @field:Json(name = "name") val name: String ? = null
    @field:Json(name = "url") val url: String ? = null
    val num: Int?
    get() = extractNumber(url)

    private fun extractNumber(url: String?): Int? {
        //https://pokeapi.co/api/v2/pokemon/1/
        return url?.let { Regex(".*/(\\d+)/").find(it)?.groupValues?.get(1)?.toInt() }
    }
}