package com.example.miniinstagram.ui.home

import android.net.Uri
import android.text.method.LinkMovementMethod
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

    var postActionListener: PostActionListener? = null
    private var items: ArrayList<Post> = ArrayList()

    fun setItems(posts: List<Post>) {
        items = ArrayList(posts)
        notifyDataSetChanged()
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
        private var post: Post? = null

        init {
            binding.apply {
                binding.descriptionTextView.movementMethod = LinkMovementMethod.getInstance()
                imageRecyclerView.adapter = imagesAdapter
                val pagerSnapHelper = PagerSnapHelper()
                pagerSnapHelper.attachToRecyclerView(imageRecyclerView)
            }
            setupListeners()
        }

        private fun setupListeners() {
            postActionListener?.apply {
                binding.apply {
                    avatarImageView.setOnClickListener {
                        post?.apply {
                            onAvatarClick(nickName)
                        }
                    }
                    nickNameTextView.setOnClickListener {
                        post?.apply {
                            onNickNameClick(nickName)
                        }
                    }
                    photoPlaceTextView.setOnClickListener {
                        post?.apply {
                            onPhotoPlaceClick(photoPlace)
                        }
                    }
                    optionsImageView.setOnClickListener {
                        post?.apply {
                            onOptionsClick(id)
                        }
                    }
                    likeImageView.setOnClickListener {
                        post?.apply {
                            onLikeClick(id)
                        }
                    }
                    commentImageView.setOnClickListener {
                        post?.apply {
                            onCommentClick(id)
                        }
                    }
                    sendImageView.setOnClickListener {
                        post?.apply {
                            onSendClick(id)
                        }
                    }
                    bookmarkImageView.setOnClickListener {
                        post?.apply {
                            onBookmarkClick(id)
                        }
                    }
                    viewModel?.linkCommand?.observeForever {
                        onLinkClick(it)
                    }
                }
            }

            binding.apply {
                imageRecyclerView.addOnScrollListener(object : SnapPageScrollListener() {
                    override fun onPageSelected(position: Int) {
                        indicator.animatePageSelected(position)
                    }

                    override fun onPageScrolled(
                        position: Int, positionOffset: Float,
                        positionOffsetPixels: Int
                    ) {
                        if (positionOffsetPixels == 0) {
                            imageRecyclerView.scrollToPosition(position)
                        }
                    }
                })
            }
        }

        fun bind(item: Post) {
            post = item
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

        inner class ImageItemVH(private val binding: ItemImageBinding) :
            RecyclerView.ViewHolder(binding.root) {
            fun bind(imageUrl: String) {
                val parse = Uri.parse(imageUrl)
                binding.imageView.setImage(parse)
            }
        }
    }
}