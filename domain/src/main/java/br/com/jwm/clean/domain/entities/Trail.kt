package br.com.jwm.clean.domain.entities

data class Trail(
    val id: Int,
    val title: String,
    val description: String,
    val trailCode: String,
    val iconUrl: String?
)