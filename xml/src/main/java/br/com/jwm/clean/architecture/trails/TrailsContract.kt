package br.com.jwm.clean.architecture.trails

import br.com.jwm.clean.infra.presenter.mvi.StateViewModel
import br.com.jwm.clean.architecture.trails.data.TrailsIntention
import br.com.jwm.clean.architecture.trails.data.TrailsState

interface TrailsContract {
    interface TrailsViewModel : StateViewModel<TrailsState, TrailsIntention>
}