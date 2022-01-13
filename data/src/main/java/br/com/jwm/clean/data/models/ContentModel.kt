package br.com.jwm.clean.data.models

data class ContentModel(
    val id: Int,
    val title: String,
    val description : String,
    val contentType: String,
    val contentUrl: String,
    val imageUrl: String?
)