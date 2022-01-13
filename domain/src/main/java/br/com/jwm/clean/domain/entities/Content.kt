package br.com.jwm.clean.domain.entities

data class Content(
    val id: Int,
    val title: String,
    val description : String,
    val contentType: String,
    val contentUrl: String,
    val imageUrl: String?
)