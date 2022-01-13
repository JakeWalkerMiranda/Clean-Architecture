package br.com.jwm.clean.architecture.trails.data

data class TrailsState(
    val trails: List<Trail> = emptyList()
) {
    data class Trail(
        val id: Int = 0,
        val title: String = "",
        val iconUrl: String? = null
    )
}