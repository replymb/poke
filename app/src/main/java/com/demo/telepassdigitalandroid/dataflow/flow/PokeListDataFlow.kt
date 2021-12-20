package com.demo.telepassdigitalandroid.dataflow.flow

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.demo.telepassdigitalandroid.network.model.Poke
import com.demo.telepassdigitalandroid.repository.PokeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.uniflow.android.AndroidDataFlow
import javax.inject.Inject

@HiltViewModel
class PokeListDataFlow @Inject constructor(private val pokeRepository: PokeRepository) : AndroidDataFlow() {

    var listPager: Pager<Int, Poke>? = Pager(
        config = PagingConfig(PokeRepository.PAGE_SIZE, enablePlaceholders = true, initialLoadSize = 1),
        initialKey = 0,
        pagingSourceFactory = { pokeRepository },
    )

}