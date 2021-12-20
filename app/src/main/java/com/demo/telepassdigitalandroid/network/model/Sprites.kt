package com.demo.telepassdigitalandroid.network.model

import com.squareup.moshi.Json

class Sprites {
    @field:Json(name = "other") val other: SpritesOther ? = null
}

class SpritesOther {
    @field:Json(name = "dream_world") val dreamWorld: Arts ? = null
    @field:Json(name = "home") val home: Arts ? = null
    @field:Json(name = "official-artwork") val artwork: Arts ? = null
}

class Arts {
    @field:Json(name = "front_default") val front: String ? = null
    @field:Json(name = "front_female") val female: String ? = null
    @field:Json(name = "front_shiny") val shiny: String ? = null
    @field:Json(name = "front_shiny_female") val shinyFemale: String ? = null
}