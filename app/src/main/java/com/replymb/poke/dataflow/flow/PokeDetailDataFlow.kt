package com.replymb.poke.dataflow.flow

import android.util.Log
import com.replymb.poke.dataflow.states.PokeDetailStates
import com.replymb.poke.repository.PokeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.uniflow.android.AndroidDataFlow
import javax.inject.Inject

@HiltViewModel
class PokeDetailDataFlow @Inject constructor(private val pokeRepository: PokeRepository) : AndroidDataFlow() {


    fun loadDetail(url: String) = action {
        setState(PokeDetailStates.LoadingPokeDetail)
        val pokes = kotlin.runCatching { pokeRepository.getPokeDetail(url) }.onFailure {
            Log.e("POKE", "Error loading detail for $url", it)
        }.getOrNull()

        setState {
            pokes?.let { PokeDetailStates.PokeDetailState(pokes) }
                ?: PokeDetailStates.ErrorPokeDetail
        }
    }

}