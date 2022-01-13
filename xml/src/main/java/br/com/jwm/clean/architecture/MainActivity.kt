package br.com.jwm.clean.architecture

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import br.com.jwm.clean.infra.presenter.navigator.Navigator
import br.com.jwm.clean.infra.presenter.navigator.NavigatorBinder
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val navigator: NavigatorBinder by inject<Navigator>()

    private val navController: NavController by lazy {
        findNavController(R.id.nav_host_fragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigator.bind(navController, lifecycleScope)
    }

    override fun onDestroy() {
        super.onDestroy()
        navigator.unbind()
    }
}