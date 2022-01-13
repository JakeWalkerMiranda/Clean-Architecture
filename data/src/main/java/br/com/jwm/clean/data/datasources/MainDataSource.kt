package br.com.jwm.clean.data.datasources

import br.com.jwm.clean.data.models.ContentModel
import br.com.jwm.clean.data.models.TrailModel

interface MainDataSource {

    suspend fun getTrails(): List<TrailModel>

    suspend fun getContents(id: Int): List<ContentModel>
}