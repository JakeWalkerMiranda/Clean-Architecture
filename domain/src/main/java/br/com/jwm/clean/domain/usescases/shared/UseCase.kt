package br.com.jwm.clean.domain.usescases.shared

interface UseCase<ParamsType, ReturnType> {
    suspend operator fun invoke(params: ParamsType): ReturnType
}