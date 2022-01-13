package br.com.jwm.clean.infra.presenter.navigator

import androidx.annotation.IdRes
import androidx.core.os.bundleOf
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import br.com.jwm.clean.infra.dispatchers.DispatchersProvider
import kotlinx.coroutines.withContext

interface NavigatorBinder {
    fun bind(navController: NavController, lifecycleScope: LifecycleCoroutineScope)
    fun unbind()
}

interface NavigatorRouter {
    suspend fun navigate(directions: NavDirections)

    suspend fun navigate(@IdRes destinationId: Int)
    suspend fun <T> navigate(@IdRes destinationId: Int, argument: Pair<String, T>)

    suspend fun replace(@IdRes destinationId: Int)
    suspend fun <T> replace(@IdRes destinationId: Int, argument: Pair<String, T>)

    suspend fun pop()
    suspend fun pop(destinationId: Int?, inclusive: Boolean)

    suspend fun <T> pop(argument: Pair<String, T>)
    suspend fun <T> pop(destinationId: Int? = null, inclusive: Boolean = false, argument: Pair<String, T>)

    suspend fun <T> sendArgumentToBackStackEntry(argument: Pair<String, T>)
}

interface Navigator : NavigatorBinder, NavigatorRouter

class NavigatorImpl(
    private val dispatchersProvider: DispatchersProvider
) : Navigator {

    private var navController: NavController? = null

    override fun bind(navController: NavController, lifecycleScope: LifecycleCoroutineScope) {
        this.navController = navController
    }

    override fun unbind() {
        this.navController = null
    }

    override suspend fun navigate(directions: NavDirections) {
        withContext(dispatchersProvider.ui) {
            navController?.navigate(directions)
        }
    }

    override suspend fun navigate(@IdRes destinationId: Int) {
        withContext(dispatchersProvider.ui) {
            navController?.navigate(destinationId)
        }
    }

    override suspend fun <T> navigate(destinationId: Int, argument: Pair<String, T>) {
        withContext(dispatchersProvider.ui) {
            navController?.navigate(destinationId, bundleOf(argument))
        }
    }

    override suspend fun replace(destinationId: Int) {
        pop()
        navigate(destinationId)
    }

    override suspend fun <T> replace(destinationId: Int, argument: Pair<String, T>) {
        pop()
        navigate(destinationId, argument)
    }

    override suspend fun pop() {
        pop(null, false)
    }

    override suspend fun pop(destinationId: Int?, inclusive: Boolean) {
        withContext(dispatchersProvider.ui) {
            if (destinationId != null)
                navController?.popBackStack(destinationId, inclusive)
            else {
                navController?.popBackStack()
            }
        }
    }

    override suspend fun <T> pop(
        argument: Pair<String, T>
    ) {
        withContext(dispatchersProvider.ui) {
            sendArgumentToBackStackEntry(argument)
            pop()
        }
    }

    override suspend fun <T> pop(
        destinationId: Int?,
        inclusive: Boolean,
        argument: Pair<String, T>
    ) {
        withContext(dispatchersProvider.ui) {
            sendArgumentToBackStackEntry(argument)
            pop(destinationId, inclusive)
        }
    }

    override suspend fun <T> sendArgumentToBackStackEntry(argument: Pair<String, T>) {
        withContext(dispatchersProvider.ui) {
            argument.let {
                navController?.previousBackStackEntry?.savedStateHandle?.set(
                    argument.first,
                    argument.second
                )
            }
        }
    }
}