package br.com.jwm.clean.architecture.trails.data

sealed class TrailsIntention {

    object GetTrails : TrailsIntention()

    data class NavigateToContent(
        val trailId: Int,
        val trailTitle: String
    ) : TrailsIntention()
}