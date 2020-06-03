package com.example.miniinstagram.ui.home

import android.content.Context
import android.widget.Toast
import com.example.miniinstagram.R
import com.example.miniinstagram.databinding.FragmentHomeBinding
import com.example.miniinstagram.ui.base.BaseFragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class HomeFragment : BaseFragment<FragmentHomeBinding>(), PostActionListener {

    override val contentLayoutId = R.layout.fragment_home

    @Inject
    lateinit var homeViewModel: HomeViewModel

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun setupBinding(binding: FragmentHomeBinding) {
        super.setupBinding(binding)
        binding.viewModel = homeViewModel
        homeViewModel.adapter.postActionListener = this
    }

    override fun onAvatarClick(nickname: String) {
        Toast.makeText(context, "Move to user profile", Toast.LENGTH_SHORT).show()
    }

    override fun onNickNameClick(nickname: String) {
        Toast.makeText(context, "Move to user profile", Toast.LENGTH_SHORT).show()
    }

    override fun onPhotoPlaceClick(place: String) {
        Toast.makeText(context, "Move to places posts", Toast.LENGTH_SHORT).show()
    }

    override fun onOptionsClick(postId: Long) {
        Toast.makeText(context, "show menu with action post", Toast.LENGTH_SHORT).show()
    }

    override fun onLikeClick(postId: Long) {
        Toast.makeText(context, "like post", Toast.LENGTH_SHORT).show()
    }

    override fun onCommentClick(postId: Long) {
        Toast.makeText(context, "show comment", Toast.LENGTH_SHORT).show()
    }

    override fun onSendClick(postId: Long) {
        Toast.makeText(context, "send post to", Toast.LENGTH_SHORT).show()
    }

    override fun onBookmarkClick(postId: Long) {
        Toast.makeText(context, "save post in bookmark", Toast.LENGTH_SHORT).show()
    }

    override fun onLinkClick(link: String) {
        Toast.makeText(context, "move to link $link", Toast.LENGTH_SHORT).show()
    }
}