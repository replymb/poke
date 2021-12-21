package com.replymb.poke.network.model

import com.squareup.moshi.Json

data class Named(@field:Json(name = "name") val name: String ? = null, @field:Json(name = "url") val url: String ? = null)