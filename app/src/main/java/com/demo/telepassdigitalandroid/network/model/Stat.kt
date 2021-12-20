package com.demo.telepassdigitalandroid.network.model

import com.squareup.moshi.Json

class Stat {
    @field:Json(name = "base_stat") val baseStat: Int ? = null
    @field:Json(name = "effort") val effort: Int ? = null
    @field:Json(name = "stat") val statName: StatName ? = null
}

data class StatName(@field:Json(name = "name") val name: String ? = null)