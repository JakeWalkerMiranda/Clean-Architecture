package br.com.jwm.clean.architecture.content

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.jwm.clean.architecture.R
import br.com.jwm.clean.architecture.content.data.ContentsAdapter
import br.com.jwm.clean.architecture.content.data.ContentsIntention
import br.com.jwm.clean.architecture.databinding.ContentsFragmentBinding
import br.com.jwm.clean.infra.presenter.extensions.lifecycleBinding
import br.com.jwm.clean.infra.presenter.extensions.watch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class ContentsFragment : Fragment(R.layout.contents_fragment) {
    private val binding by lifecycleBinding(ContentsFragmentBinding::bind)
    private val viewModel: ContentsContract.ContentsViewModel by viewModel<ContentsViewModel>()
    private val adapter: ContentsAdapter by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadKoinModules(contentsModule)

        val args by navArgs<ContentsFragmentArgs>()

        bindInputs()
        bindOutputs()

        viewModel.publish(
            ContentsIntention.GetContents(
                trailId = args.trailId,
                trailTitle = args.trailTitle
            )
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        unloadKoinModules(contentsModule)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun bindOutputs() = with(viewModel) {
        watch(state) { state ->
            binding.title.text = state.trailTitle

            adapter.submitList(state.contents)
            adapter.notifyDataSetChanged()
        }
    }

    private fun bindInputs() = with(binding) {
        contents.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        contents.adapter = adapter.also { adapter ->
            adapter.onContentClick = { contentUrl ->
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(contentUrl)
                intent.setPackage(YOUTUBE_PACKAGE)
                startActivity(intent)
            }
        }
    }

    companion object {
        const val YOUTUBE_PACKAGE = "com.google.android.youtube"
    }
}