package br.com.jwm.clean.architecture.content

import android.util.Log
import br.com.jwm.clean.architecture.content.data.ContentsIntention
import br.com.jwm.clean.architecture.content.data.ContentsState
import br.com.jwm.clean.domain.usescases.GetContentsUseCase
import br.com.jwm.clean.infra.dispatchers.DispatchersProvider
import br.com.jwm.clean.infra.presenter.mvi.StateViewModelImpl
import br.com.jwm.clean.infra.presenter.navigator.NavigatorRouter

class ContentsViewModel(
    private val getContentsUseCase: GetContentsUseCase,
    private val navigator: NavigatorRouter,
    dispatchersProvider: DispatchersProvider,
    initialState: ContentsState
) : StateViewModelImpl<ContentsState, ContentsIntention>(
    dispatchersProvider = dispatchersProvider,
    initialState = initialState
), ContentsContract.ContentsViewModel {

    override suspend fun handleIntentions(intention: ContentsIntention) {
        when (intention) {
            is ContentsIntention.GetContents -> getContents(intention.trailId, intention.trailTitle)
            is ContentsIntention.Pop -> pop()
        }
    }

    private suspend fun getContents(trailId: Int, trailTitle: String) {
        runCatching { getContentsUseCase(GetContentsUseCase.Params(id = trailId)) }
            .onSuccess { contents ->
                val contentsState = contents.map { content ->
                    ContentsState.Content(
                        id = content.id,
                        title = content.title,
                        contentType = content.contentType,
                        description = content.description,
                        imageUrl = content.imageUrl,
                        contentUrl = content.contentUrl
                    )
                }
                updateState { copy(trailTitle = trailTitle, contents = contentsState) }
            }
            .onFailure { exception -> Log.d(TAG, "getTrails: $exception") }
    }

    private suspend fun pop() { navigator.pop() }
}

const val TAG = "ContentsViewModel"