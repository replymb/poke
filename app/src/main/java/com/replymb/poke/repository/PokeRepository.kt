package com.replymb.poke.repository

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.replymb.poke.network.IPokeService
import com.replymb.poke.network.model.Poke

class PokeRepository(private val pokeService: IPokeService): PagingSource<Int, Poke>() {

    suspend fun getPokeList(offset: Int, limit: Int = PAGE_SIZE) = pokeService.getList(offset, limit).body()

    suspend fun getPokeDetail(url: String) = pokeService.getDetail(url).body()

    override fun getRefreshKey(state: PagingState<Int, Poke>): Int? {
        return state.anchorPosition?.let { extractOffset(state.closestItemToPosition(it)?.url) }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Poke> {
        val response = params.key?.let {
            kotlin.runCatching { getPokeList(it, params.loadSize) }.getOrNull()
        }

        return LoadResult.Page(
            data = response?.results.orEmpty(),
            prevKey = extractOffset(response?.previous),
            nextKey = extractOffset(response?.next),
            itemsBefore = if (params.placeholdersEnabled) params.key?:0 else LoadResult.Page.COUNT_UNDEFINED,
            itemsAfter = if (params.placeholdersEnabled) response?.count?.minus(params.key?:0)?.minus(params.loadSize)?:10 else LoadResult.Page.COUNT_UNDEFINED
        )
    }

    private fun extractOffset(url: String?): Int ? {
        return url?.let { Uri.parse(it).getQueryParameter("offset")?.toInt() }
    }

    companion object {
        const val PAGE_SIZE : Int = 20
    }

}