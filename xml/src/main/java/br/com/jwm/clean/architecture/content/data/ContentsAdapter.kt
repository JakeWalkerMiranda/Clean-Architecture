package br.com.jwm.clean.architecture.content.data

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.jwm.clean.architecture.R
import br.com.jwm.clean.architecture.databinding.ContentItemBinding
import coil.load
import coil.transform.RoundedCornersTransformation

class ContentsAdapter : ListAdapter<ContentsState.Content, ContentsAdapter.ViewHolder>(
    DIFF_CALLBACK
) {
    lateinit var onContentClick: (url: String) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ContentItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class ViewHolder(
        private val binding: ContentItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(content: ContentsState.Content) {
            binding.apply {
                title.text = content.title
                contentType.text = content.contentType
                description.text = content.description

                image.load(content.imageUrl) {
                    placeholder(R.drawable.bg_content_placeholder)
                    transformations(RoundedCornersTransformation(CONTENT_RADIUS))
                }

                root.setOnClickListener { onContentClick(content.contentUrl) }
            }
        }
    }

    private companion object {
        const val CONTENT_RADIUS = 6F

        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ContentsState.Content>() {
            override fun areItemsTheSame(
                oldItem: ContentsState.Content,
                newItem: ContentsState.Content
            ): Boolean = oldItem == newItem

            override fun areContentsTheSame(
                oldItem: ContentsState.Content,
                newItem: ContentsState.Content
            ): Boolean = oldItem == newItem
        }
    }
}