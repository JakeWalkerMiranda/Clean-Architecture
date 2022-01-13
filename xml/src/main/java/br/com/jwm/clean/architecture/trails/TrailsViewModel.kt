package br.com.jwm.clean.architecture.trails

import android.util.Log
import br.com.jwm.clean.architecture.trails.data.TrailsIntention
import br.com.jwm.clean.architecture.trails.data.TrailsState
import br.com.jwm.clean.domain.usescases.GetTrailsUseCase
import br.com.jwm.clean.domain.usescases.shared.NoParams
import br.com.jwm.clean.infra.dispatchers.DispatchersProvider
import br.com.jwm.clean.infra.presenter.mvi.StateViewModelImpl
import br.com.jwm.clean.infra.presenter.navigator.NavigatorRouter

class TrailsViewModel(
    private val getTrailsUseCase: GetTrailsUseCase,
    private val navigator: NavigatorRouter,
    dispatchersProvider: DispatchersProvider,
    initialState: TrailsState
) : StateViewModelImpl<TrailsState, TrailsIntention>(
    dispatchersProvider = dispatchersProvider,
    initialState = initialState
), TrailsContract.TrailsViewModel {

    override suspend fun handleIntentions(intention: TrailsIntention) {
        when (intention) {
            is TrailsIntention.GetTrails -> getTrails()
            is TrailsIntention.NavigateToContent ->
                navigateToContent(trailId = intention.trailId, trailTitle = intention.trailTitle)
        }
    }

    private suspend fun getTrails() {
        runCatching { getTrailsUseCase(NoParams) }
            .onSuccess { trails ->
                val trailsState = trails.map { trail ->
                    TrailsState.Trail(
                        id = trail.id,
                        title = trail.title,
                        iconUrl = trail.iconUrl
                    )
                }
                updateState { copy(trails = trailsState) }
            }
            .onFailure { exception -> Log.d(TAG, "getTrails: $exception") }
    }

    private suspend fun navigateToContent(trailId: Int, trailTitle: String) {
        navigator.navigate(TrailsFragmentDirections.actionTrailsToContents(trailId, trailTitle))
    }
}

private const val TAG = "TrailsViewModel"