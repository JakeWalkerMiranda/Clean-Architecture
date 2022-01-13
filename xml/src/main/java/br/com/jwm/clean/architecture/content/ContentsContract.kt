package br.com.jwm.clean.architecture.content

import br.com.jwm.clean.infra.presenter.mvi.StateViewModel
import br.com.jwm.clean.architecture.content.data.ContentsIntention
import br.com.jwm.clean.architecture.content.data.ContentsState

interface ContentsContract {
    interface ContentsViewModel : StateViewModel<ContentsState, ContentsIntention>
}