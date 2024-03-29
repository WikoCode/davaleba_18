package com.example.davaleba_18.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.davaleba_18.UserPagingSource

class UsersViewModel : ViewModel() {
    val userList = Pager(
        PagingConfig(
            pageSize = 6,
            prefetchDistance = 3,
            initialLoadSize = 12,
            maxSize = 60,
            enablePlaceholders = false
        )
    ) {
        UserPagingSource()
    }.flow.cachedIn(viewModelScope)
}