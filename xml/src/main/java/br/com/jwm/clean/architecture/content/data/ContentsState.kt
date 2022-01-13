package br.com.jwm.clean.architecture.content.data

data class ContentsState(
    val trailTitle: String = "",
    val contents: List<Content> = emptyList()
) {
    data class Content(
        val id: Int = 0,
        val title: String = "",
        val contentType: String = "",
        val contentUrl: String,
        val description: String = "",
        val imageUrl: String? = null
    )
}