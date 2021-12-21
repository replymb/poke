package com.replymb.poke.network.model

import com.squareup.moshi.Json

data class Type (
    @field:Json(name = "slot") val slot: Int ? = null,
    @field:Json(name = "type") val type: Named ? = null
)
