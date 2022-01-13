package br.com.jwm.clean.remote.response

import com.squareup.moshi.Json

data class TrailDTO(
    @Json(name = "id") val id: Int,
    @Json(name = "title") val title: String,
    @Json(name = "description") val description: String,
    @Json(name = "trailCode") val trailCode: String,
    @Json(name = "iconUrl") val iconUrl: String?
)