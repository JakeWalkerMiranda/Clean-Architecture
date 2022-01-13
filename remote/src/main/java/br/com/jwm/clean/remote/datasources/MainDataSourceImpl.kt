package br.com.jwm.clean.remote.datasources

import br.com.jwm.clean.data.datasources.MainDataSource
import br.com.jwm.clean.data.models.ContentModel
import br.com.jwm.clean.data.models.TrailModel
import br.com.jwm.clean.remote.mappers.toModel
import br.com.jwm.clean.remote.service.MainService

class MainDataSourceImpl(
    private val mainService: MainService
) : MainDataSource {

    override suspend fun getTrails(): List<TrailModel> =
        mainService.getTrails().map { trailDTO -> trailDTO.toModel() }

    override suspend fun getContents(id: Int): List<ContentModel> =
        mainService.getContents(id = id).map { contentDTO -> contentDTO.toModel() }
}