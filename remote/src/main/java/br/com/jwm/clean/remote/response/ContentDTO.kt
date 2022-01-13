package br.com.jwm.clean.remote.response

import com.squareup.moshi.Json

data class ContentDTO(
    @Json(name = "id") val id: Int,
    @Json(name = "title") val title: String,
    @Json(name = "description") val description: String,
    @Json(name = "contentType") val contentType: String,
    @Json(name = "contentUrl") val contentUrl: String,
    @Json(name = "imageUrl") val imageUrl: String?
)