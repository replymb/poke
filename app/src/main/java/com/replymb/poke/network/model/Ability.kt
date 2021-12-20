package com.replymb.poke.network.model

import com.squareup.moshi.Json

class Ability {
    @field:Json(name= "ability") val ability: AbilityName ? = null
    @field:Json(name = "is_hidden") val hidden: Boolean ? = null
    @field:Json(name = "slot") val slot: Int ? = null
}

data class AbilityName(@field:Json(name = "name") val name: String ? = null)