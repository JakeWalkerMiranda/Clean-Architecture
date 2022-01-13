package br.com.jwm.clean.architecture.trails

import br.com.jwm.clean.infra.presenter.navigator.Navigator
import br.com.jwm.clean.architecture.trails.data.TrailsAdapter
import br.com.jwm.clean.architecture.trails.data.TrailsState
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val trailsModule = module {
    viewModel {
        TrailsViewModel(
            getTrailsUseCase = get(),
            dispatchersProvider = get(),
            initialState = TrailsState(),
            navigator = get<Navigator>()
        )
    }

    factory { TrailsAdapter() }
}