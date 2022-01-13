package br.com.jwm.clean.domain.repositories

import br.com.jwm.clean.domain.entities.Content
import br.com.jwm.clean.domain.entities.Trail

interface MainRepository {

    suspend fun getTrails(): List<Trail>

    suspend fun getContents(id: Int): List<Content>
}