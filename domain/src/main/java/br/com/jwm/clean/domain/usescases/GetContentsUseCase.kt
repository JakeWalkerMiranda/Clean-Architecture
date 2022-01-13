package br.com.jwm.clean.domain.usescases

import br.com.jwm.clean.domain.entities.Content
import br.com.jwm.clean.domain.repositories.MainRepository
import br.com.jwm.clean.domain.usescases.shared.UseCase

class GetContentsUseCase(
    private val mainRepository: MainRepository
) : UseCase<GetContentsUseCase.Params, List<Content>> {

    override suspend fun invoke(params: Params): List<Content> =
        mainRepository.getContents(id = params.id)

    data class Params(val id: Int)
}