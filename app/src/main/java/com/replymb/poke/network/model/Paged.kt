package com.replymb.poke.network.model

import com.squareup.moshi.Json

open class Paged {
    @field:Json(name = "count") val count: Int ? = null
    @field:Json(name = "next") val next: String ? = null
    @field:Json(name = "previous") val previous: String ? = null
}