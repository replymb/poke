package com.replymb.poke.network.model

import com.squareup.moshi.Json

data class Stat(
    @field:Json(name = "base_stat") val baseStat: Int ? = null,
    @field:Json(name = "effort") val effort: Int ? = null,
    @field:Json(name = "stat") val statName: Named ? = null
)
