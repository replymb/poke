package com.demo.telepassdigitalandroid.dataflow.states

import com.demo.telepassdigitalandroid.network.model.PokeDetail
import io.uniflow.core.flow.data.UIState

open class PokeDetailStates : UIState() {
    object LoadingPokeDetail : PokeDetailStates()
    object ErrorPokeDetail : PokeDetailStates()

    data class PokeDetailState(val data: PokeDetail): PokeDetailStates()
}