package com.example.davaleba_18.data

import com.squareup.moshi.Json

data class ApiResponse(
    val page: Int,
    @Json(name = "per_page")
    val perPage: Int,
    val total: Int,
    @Json(name = "total_pages")
    val totalPages: Int,
    val data: List<User>
)