package com.phonedeveloper.apicorporalabs.ui.detail

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.doOnNextLayout
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.transition.MaterialArcMotion
import com.google.android.material.transition.MaterialContainerTransform
import com.phonedeveloper.apicorporalabs.R
import com.phonedeveloper.apicorporalabs.core.base.BaseFragment
import com.phonedeveloper.apicorporalabs.databinding.FragmentDetailBinding
import com.phonedeveloper.apicorporalabs.ui.main.MainActivity
import com.phonedeveloper.apicorporalabs.ui.viewmodel.withFactory
import com.phonedeveloper.domain.model.Article
import com.phonedeveloper.domain.utils.ResultDataResource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlin.time.ExperimentalTime

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
@ExperimentalTime
@ExperimentalCoroutinesApi
class DetailFragment : BaseFragment<DetailViewModel, FragmentDetailBinding>() {

    override fun getViewBinding() = FragmentDetailBinding.inflate(layoutInflater)

    private val viewModel: DetailViewModel by viewModels { withFactory((activity as MainActivity).factoryDetail) }
    private val navArgs by navArgs<DetailFragmentArgs>()
    private lateinit var article: Article
    private var isLoading = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        prepareSharedElementTransition()
        postponeEnterTransition()
        return super.onCreateView(inflater, container, savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.doOnNextLayout {
            (it.parent as? ViewGroup)?.doOnPreDraw {
                startPostponedEnterTransition()
            }
        }

    }

    private fun setupActionBar() {
        (activity as? AppCompatActivity)?.let {
            it.supportActionBar?.setDisplayHomeAsUpEnabled(false)
            it.supportActionBar?.title = article.title
            setHasOptionsMenu(true)
        }
    }

    override fun setupViews() {
        article = navArgs.article
        setupActionBar()
        with(binding) {
            article?.let { article ->
                if (!article.media.isNullOrEmpty()) {
                    article.media!![0]?.let {
                        if (!it.mediaMetadata.isNullOrEmpty()) {
                            it.mediaMetadata?.get(1)?.url?.let {
                                Glide.with(binding.detailCover.context)
                                    .load(it)
                                    .apply(
                                        RequestOptions()
                                            .placeholder(R.drawable.loading_animation)
                                            .error(R.drawable.ic_broken_image)
                                    )
                                    .into(binding.detailCover)
//                                    // Get Property from navigation component arguments
                                binding.detailCover.transitionName = article.title

                            }
                        }
                    }
                }
                includedContentDetail.detailTitle.text = article.title
                includedContentDetail.detailDescription.text = article.abstract
            }
        }
    }

    private fun prepareSharedElementTransition() {
        val pathMotion = MaterialArcMotion()
        sharedElementEnterTransition = MaterialContainerTransform()
            .apply {
                setPathMotion(pathMotion)
                duration = 500
                scrimColor = Color.TRANSPARENT
            }
    }

    override fun setupObservers() {
        if (article.title.isNullOrEmpty()) {
            viewModel.getDetailArticle(article)
            viewDetail()
        }
    }

    private fun viewDetail() {
        viewModel.articleDetail.observe(viewLifecycleOwner, { response ->
            when (response) {
                is ResultDataResource.Success -> {
                    hideProgressBar()
                    response.data?.let { article ->
                        if (!article.media.isNullOrEmpty()) {
                            article.media!![0]?.let {
                                if (!it.mediaMetadata.isNullOrEmpty()) {
                                    it.mediaMetadata?.get(1)?.url?.let {
                                        Glide.with(binding.detailCover.context)
                                            .load(it)
                                            .apply(
                                                RequestOptions()
                                                    .placeholder(R.drawable.loading_animation)
                                                    .error(R.drawable.ic_broken_image)
                                            )
                                            .into(binding.detailCover)
//                                    // Get Property from navigation component arguments
                                        binding.detailCover.transitionName = article.title

                                    }
                                }
                            }
                        }

                        binding.includedContentDetail.detailTitle.text = article.title
                        binding.includedContentDetail.detailDescription.text = article.abstract
                    }
                }
                is ResultDataResource.ErrorMessage -> {
                    hideProgressBar()
                    response.messageError?.let {
                        Toast.makeText(activity, "An error occurred : $it", Toast.LENGTH_LONG)
                            .show()
                        Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
                    }
                }

                is ResultDataResource.Loading -> {
                    showProgressBar()
                }
                else -> hideProgressBar()

            }
        })
    }


    private fun showProgressBar() {
        isLoading = true
        binding.includedContentDetail.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        isLoading = false
        binding.includedContentDetail.progressBar.visibility = View.GONE
        findNavController().popBackStack()
    }

}