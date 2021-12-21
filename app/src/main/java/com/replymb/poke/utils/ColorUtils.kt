package com.replymb.poke.utils

import androidx.annotation.ColorRes
import com.replymb.poke.R

object ColorUtils {
    private val typeColor = mapOf(
        Pair("bug", R.color.poke_type_bug),
        Pair("dark", R.color.poke_type_dark),
        Pair("dark", R.color.poke_type_dark),
        Pair("electric", R.color.poke_type_electric),
        Pair("fairy", R.color.poke_type_fairy),
        Pair("fighting", R.color.poke_type_fighting),
        Pair("fire", R.color.poke_type_fire),
        Pair("flying", R.color.poke_type_flying),
        Pair("ghost", R.color.poke_type_ghost),
        Pair("grass", R.color.poke_type_grass),
        Pair("ground", R.color.poke_type_ground),
        Pair("ice", R.color.poke_type_ice),
        Pair("normal", R.color.poke_type_normal),
        Pair("poison", R.color.poke_type_poison),
        Pair("psychic", R.color.poke_type_psychic),
        Pair("rock", R.color.poke_type_rock),
        Pair("shadow", R.color.poke_type_shadow),
        Pair("steel", R.color.poke_type_steel),
        Pair("water", R.color.poke_type_water),
        Pair("unknown", R.color.poke_type_unknown)
    )

    @ColorRes fun forType(type: String?): Int{
        return typeColor[type]?:R.color.poke_type_unknown
    }
}