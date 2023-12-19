package com.example.davaleba_18

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.davaleba_18.api.ApiClient.apiService
import com.example.davaleba_18.data.User

class UserPagingSource : PagingSource<Int, User>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        val pageNumber = params.key ?: 1
        return try {
            val response = apiService.getUsers(pageNumber)
            if (response.isSuccessful && response.body() != null) {
                val data = response.body()!!.data
                val nextPageNumber = if (data.isEmpty()) null else pageNumber + 1
                LoadResult.Page(data, prevKey = if (pageNumber == 1) null else pageNumber - 1, nextKey = nextPageNumber)
            } else {
                LoadResult.Error(Exception("API call failed with error: ${response.code()}"))
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }


    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
