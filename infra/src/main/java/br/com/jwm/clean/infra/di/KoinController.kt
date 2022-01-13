package br.com.jwm.clean.infra.di

import android.content.Context
import androidx.startup.Initializer
import br.com.jwm.clean.infra.di.modules.domainModule
import br.com.jwm.clean.infra.di.modules.networkModule
import br.com.jwm.clean.infra.di.modules.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinApplication
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

class KoinInitializer : Initializer<KoinApplication> {

    override fun create(context: Context): KoinApplication =
        startKoin {
            androidContext(context)
            loadKoinModules(
                listOf(
                    networkModule,
                    domainModule,
                    uiModule
                )
            )
        }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}