package com.replymb.poke.dataflow.states

import com.replymb.poke.network.model.PokeDetail
import io.uniflow.core.flow.data.UIState

open class PokeDetailStates : UIState() {
    object LoadingPokeDetail : PokeDetailStates()
    object ErrorPokeDetail : PokeDetailStates()

    data class PokeDetailState(val data: PokeDetail): PokeDetailStates()
}