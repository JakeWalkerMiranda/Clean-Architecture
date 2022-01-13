package br.com.jwm.clean.infra.dispatchers

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface DispatchersProvider {
    val ui: CoroutineDispatcher
    val io: CoroutineDispatcher
}

class AppDispatchersProvider : DispatchersProvider {
    override val ui: CoroutineDispatcher = Dispatchers.Main
    override val io: CoroutineDispatcher = Dispatchers.IO
}