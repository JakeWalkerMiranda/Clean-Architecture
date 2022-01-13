package br.com.jwm.clean.data.repositories

import br.com.jwm.clean.data.datasources.MainDataSource
import br.com.jwm.clean.data.mappers.toEntity
import br.com.jwm.clean.domain.entities.Content
import br.com.jwm.clean.domain.entities.Trail
import br.com.jwm.clean.domain.repositories.MainRepository

class MainRepositoryImpl(
    private val mainDataSource: MainDataSource
) : MainRepository {

    override suspend fun getTrails(): List<Trail> =
        mainDataSource.getTrails().map { trailModel -> trailModel.toEntity() }

    override suspend fun getContents(id: Int): List<Content> =
        mainDataSource.getContents(id = id).map { contentModel -> contentModel.toEntity() }
}