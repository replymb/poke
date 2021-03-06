package com.replymb.poke.network.model

import com.squareup.moshi.Json

data class PokeDetail (
    @field:Json(name = "base_experience") val baseExperience: Int ? = null,
    @field:Json(name = "name") val name: String ? = null,
    @field:Json(name = "order") val order: Int ? = null,
    @field:Json(name = "weight") val weight: Int ? = null,
    @field:Json(name = "id") val id: Int ? = null,
    @field:Json(name = "height") val height: Int ? = null,
    @field:Json(name = "abilities") val abilities: List<Ability> ? = null,
    @field:Json(name = "sprites") val sprites: Sprites ? = null,
    @field:Json(name = "stats") val stats: List<Stat> ? = null,
    @field:Json(name = "species") val species: Named ? = null,
    @field:Json(name = "types") val types: List<Type> ? = null
)