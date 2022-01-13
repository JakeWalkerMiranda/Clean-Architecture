package br.com.jwm.clean.architecture.trails

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import br.com.jwm.clean.architecture.R
import br.com.jwm.clean.architecture.databinding.TrailsFragmentBinding
import br.com.jwm.clean.architecture.trails.data.TrailsAdapter
import br.com.jwm.clean.architecture.trails.data.TrailsIntention
import br.com.jwm.clean.infra.presenter.extensions.lifecycleBinding
import br.com.jwm.clean.infra.presenter.extensions.watch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class TrailsFragment : Fragment(R.layout.trails_fragment) {
    private val binding by lifecycleBinding(TrailsFragmentBinding::bind)
    private val viewModel: TrailsContract.TrailsViewModel by viewModel<TrailsViewModel>()
    private val adapter: TrailsAdapter by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadKoinModules(trailsModule)
        bindInputs()
        bindOutputs()
        viewModel.publish(TrailsIntention.GetTrails)
    }

    override fun onDestroy() {
        super.onDestroy()
        unloadKoinModules(trailsModule)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun bindOutputs() = with(viewModel) {
        watch(state) { state ->
            adapter.submitList(state.trails)
            adapter.notifyDataSetChanged()
        }
    }

    private fun bindInputs() = with(binding){
        recycler.layoutManager = GridLayoutManager(context, 2)
        recycler.adapter = adapter.also { adapter ->
            adapter.onTrailClick = { trailId, trailTitle ->
                viewModel.publish(
                    TrailsIntention.NavigateToContent(trailId = trailId, trailTitle = trailTitle)
                )
            }
        }
    }
}