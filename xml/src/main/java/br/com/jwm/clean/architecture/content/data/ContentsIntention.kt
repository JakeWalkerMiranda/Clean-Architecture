package br.com.jwm.clean.architecture.content.data

sealed class ContentsIntention {

    data class GetContents(
        val trailId: Int,
        val trailTitle: String
    ) : ContentsIntention()

    object Pop : ContentsIntention()
}