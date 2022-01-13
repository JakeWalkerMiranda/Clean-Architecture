package br.com.jwm.clean.infra.di.modules

import br.com.jwm.clean.infra.presenter.navigator.Navigator
import br.com.jwm.clean.infra.presenter.navigator.NavigatorImpl
import br.com.jwm.clean.infra.dispatchers.AppDispatchersProvider
import br.com.jwm.clean.infra.dispatchers.DispatchersProvider
import org.koin.dsl.module

val uiModule = module {

     single<DispatchersProvider> { AppDispatchersProvider() }
     single<Navigator> { NavigatorImpl(dispatchersProvider = get()) }
}