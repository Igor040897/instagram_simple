package com.example.miniinstagram.ui.home

import android.net.Uri
import android.view.ViewGroup
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.miniinstagram.R
import com.example.miniinstagram.data.models.Post
import com.example.miniinstagram.databinding.ItemImageBinding
import com.example.miniinstagram.databinding.ItemPostBinding
import com.example.miniinstagram.inflateView
import com.example.miniinstagram.setImage
import me.relex.recyclerpager.SnapPageScrollListener

class PostAdapter : RecyclerView.Adapter<PostAdapter.PostItemVH>() {

    private var items: ArrayList<Post> = ArrayList()

    fun setItems(posts: List<Post>) {
        items = ArrayList(posts)
        notifyDataSetChanged()
//        val diffCallback =
//            PostDiffCallback(
//                items,
//                products
//            )
//        val diffResult = DiffUtil.calculateDiff(diffCallback)
//        items.clear()
//        items.addAll(posts)
//        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostItemVH {
        val binding = ItemPostBinding.bind(parent.inflateView(R.layout.item_post))
        binding.viewModel = ItemPostViewModel()
        return PostItemVH(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: PostItemVH, position: Int) = holder.bind(items[position])

    inner class PostItemVH(private val binding: ItemPostBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val imagesAdapter = ImagesAdapter()

        init {
            binding.apply {

                imageRecyclerView.adapter = imagesAdapter
//                indicator.setBackgroundColor(root.context.resources.getColor(android.R.color.black))

                val pagerSnapHelper = PagerSnapHelper()
                pagerSnapHelper.attachToRecyclerView(imageRecyclerView)

                imageRecyclerView.addOnScrollListener(object : SnapPageScrollListener() {
                    override fun onPageSelected(position: Int) {
                        indicator.animatePageSelected(position)
                    }

                    override fun onPageScrolled(
                        position: Int, positionOffset: Float,
                        positionOffsetPixels: Int
                    ) {
                        //todo refactoring and check logic
                        if (positionOffsetPixels == 0) {
                            imageRecyclerView.scrollToPosition(position)
                        }
                    }
                })
            }
        }

        fun bind(item: Post) {
            binding.indicator.createIndicators(itemCount + 1, 0)
            binding.viewModel?.start(item, binding.root.context)
            imagesAdapter.setItems(item.images)
        }
    }

    inner class ImagesAdapter : RecyclerView.Adapter<ImagesAdapter.ImageItemVH>() {

        private var items: ArrayList<String> = ArrayList()

        fun setItems(images: List<String>) {
            items = ArrayList(images)
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageItemVH {
            val binding = ItemImageBinding.bind(parent.inflateView(R.layout.item_image))
            return ImageItemVH(binding)
        }

        override fun getItemCount() = items.size

        override fun onBindViewHolder(holder: ImageItemVH, position: Int) =
            holder.bind(items[position])

        inner class ImageItemVH(private val binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root) {
            fun bind(imageUrl: String) {
                val parse = Uri.parse(imageUrl)
                binding.imageView.setImage(parse)
            }
        }
    }
}