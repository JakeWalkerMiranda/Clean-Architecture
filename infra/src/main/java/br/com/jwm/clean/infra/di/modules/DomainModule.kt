package br.com.jwm.clean.infra.di.modules
import br.com.jwm.clean.domain.usescases.GetContentsUseCase
import br.com.jwm.clean.domain.usescases.GetTrailsUseCase
import org.koin.dsl.module

val domainModule = module {
    single { GetTrailsUseCase(mainRepository = get()) }
    single { GetContentsUseCase(mainRepository = get()) }
}