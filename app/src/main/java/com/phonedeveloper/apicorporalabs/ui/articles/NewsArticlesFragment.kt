package com.phonedeveloper.apicorporalabs.ui.articles

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.phonedeveloper.apicorporalabs.R
import com.phonedeveloper.apicorporalabs.core.base.BaseFragment
import com.phonedeveloper.apicorporalabs.databinding.FragmentNewsArticlesBinding
import com.phonedeveloper.apicorporalabs.ui.adapter.ApiLoadStateAdapter
import com.phonedeveloper.apicorporalabs.ui.adapter.ListAdapter
import com.phonedeveloper.apicorporalabs.ui.main.MainActivity
import com.phonedeveloper.domain.model.Article
import com.phonedeveloper.domain.utils.Constants
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.ExperimentalTime

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@ExperimentalTime
@ExperimentalCoroutinesApi
@ExperimentalPagingApi
class NewsArticlesFragment : BaseFragment<HomeNewsViewModel, FragmentNewsArticlesBinding>() {

    override fun getViewBinding() = FragmentNewsArticlesBinding.inflate(layoutInflater)

    lateinit var viewModel: HomeNewsViewModel
    private var newsArticleJob: Job? = null

    @Inject
    lateinit var listAdapter: ListAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
//    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = (activity as MainActivity).viewModel
        super.onViewCreated(view, savedInstanceState)
        setupActionBar()
    }

    private fun setupActionBar() {
        (activity as? AppCompatActivity)?.let {
            it.supportActionBar?.title = getString(R.string.app_name)
            setHasOptionsMenu(true)
        }
    }

    override fun setupViews() {
        createRecyclerView()
        setSwipeRefresh(binding)
    }

    private fun createRecyclerView() {
        listAdapter = ListAdapter()
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            listAdapter.withLoadStateHeaderAndFooter(
                header = ApiLoadStateAdapter { listAdapter.retry() },
                footer = ApiLoadStateAdapter { listAdapter.retry() },
            )
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
            setHasFixedSize(true)
            adapter = listAdapter
        }
    }

    override fun setupListeners() {
        listAdapter.setOnItemClickListener { article -> onClicked(article) }
        binding.swipeContainer.setOnClickListener { listAdapter.retry() }
    }

    private fun setSwipeRefresh(binding: FragmentNewsArticlesBinding) {
        binding.swipeContainer.setColorSchemeColors(
            ContextCompat.getColor(requireContext(), R.color.black)
        )

        binding.swipeContainer.setOnRefreshListener {
//            viewModel.refresh(pages, 100)
            viewModel.onManualRefresh()
//            viewModel.refreshIU(forceUpdate = true)
            // we can turn off refresh icon here because we have our own progressBar indicator
            binding.swipeContainer.isRefreshing = false
        }
    }

    override fun setupObservers() {
        getListHeroesJob("")
    }

    private fun getListHeroesJob(params: String) {
        newsArticleJob?.cancel()
        newsArticleJob = lifecycleScope.launch {
//            viewModel.getHeroesPaging().distinctUntilChanged().collectLatest {
            viewModel.getArticlesPaging(params).collectLatest { response ->
                listAdapter.submitData(response)
            }
        }
    }

    private fun onClicked(article: Article) {
        val bundle = Bundle().apply {
            putSerializable(Constants.DETAIL_ARG_KEY, article)
        }
        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}