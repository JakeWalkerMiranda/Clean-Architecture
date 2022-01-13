package br.com.jwm.clean.domain.usescases

import br.com.jwm.clean.domain.entities.Trail
import br.com.jwm.clean.domain.repositories.MainRepository
import br.com.jwm.clean.domain.usescases.shared.NoParams
import br.com.jwm.clean.domain.usescases.shared.UseCase

class GetTrailsUseCase(
    private val mainRepository: MainRepository
) : UseCase<NoParams, List<Trail>> {

    override suspend fun invoke(params: NoParams): List<Trail> =
        mainRepository.getTrails()
}