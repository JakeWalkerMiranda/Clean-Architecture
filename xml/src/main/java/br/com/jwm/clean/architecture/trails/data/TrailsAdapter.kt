package br.com.jwm.clean.architecture.trails.data


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.jwm.clean.architecture.R
import br.com.jwm.clean.architecture.databinding.TrailItemBinding
import coil.load

class TrailsAdapter : ListAdapter<TrailsState.Trail, TrailsAdapter.ViewHolder>(DIFF_CALLBACK) {
    lateinit var onTrailClick: (trailId: Int, trailTitle: String) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        TrailItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class ViewHolder(
        private val binding: TrailItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(trail: TrailsState.Trail) {
            binding.apply {
                title.text = trail.title

                if (trail.iconUrl.isNullOrEmpty()) {
                    image.load(R.drawable.ic_medal)
                } else {
                    image.load(trail.iconUrl)
                }

                root.setOnClickListener { onTrailClick(trail.id, trail.title) }
            }
        }
    }

    private companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TrailsState.Trail>() {
            override fun areItemsTheSame(
                oldItem: TrailsState.Trail,
                newItem: TrailsState.Trail
            ): Boolean = oldItem == newItem

            override fun areContentsTheSame(
                oldItem: TrailsState.Trail,
                newItem: TrailsState.Trail
            ): Boolean = oldItem == newItem
        }
    }
}



