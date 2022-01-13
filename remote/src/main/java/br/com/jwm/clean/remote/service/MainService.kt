package br.com.jwm.clean.remote.service

import br.com.jwm.clean.remote.response.ContentDTO
import br.com.jwm.clean.remote.response.TrailDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface MainService {

    @GET("api/v1/trails")
    suspend fun getTrails(): List<TrailDTO>

    @GET("api/v1/trails/{id}/contents")
    suspend fun getContents(
        @Path("id") id: Int
    ): List<ContentDTO>
}