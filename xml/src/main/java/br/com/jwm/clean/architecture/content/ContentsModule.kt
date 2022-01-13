package br.com.jwm.clean.architecture.content

import br.com.jwm.clean.infra.presenter.navigator.Navigator
import br.com.jwm.clean.architecture.content.data.ContentsAdapter
import br.com.jwm.clean.architecture.content.data.ContentsState
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val contentsModule = module {
    viewModel {
        ContentsViewModel(
            getContentsUseCase = get(),
            dispatchersProvider = get(),
            initialState = ContentsState(),
            navigator = get<Navigator>()
        )
    }

    factory { ContentsAdapter() }
}