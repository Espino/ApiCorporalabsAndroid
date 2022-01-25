package com.phonedeveloper.apicorporalabs.core.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<ViewModel : BaseViewModel<*>, VBinding : ViewBinding> :
    HiltFragment() {

    lateinit var secureContext: Context
    open var useSharedViewModel: Boolean = false

    protected lateinit var binding: VBinding
    protected abstract fun getViewBinding(): VBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        setHasOptionsMenu(true)
        return binding!!.root
    }

    private fun init() {
        binding = getViewBinding()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUser()
        setupViews()
        setupListeners()
        setupData()
        setupObservers()
        if (savedInstanceState == null) // we fetch data only when the page is not recreated
            fetchData()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        secureContext = context
    }

    /**
     * this method is a callback used to setup your observers
     * and called after @link #setupViews()
     */

    protected open fun setupUser() {}

    protected open fun setupViews() {}

    protected open fun setupListeners() {}

    protected open fun setupData() {}

    /**
     * this method is a callback used to fetching data
     * and called after @link #setupObservers()
     * this callback will be called automatic when you first load the fragment
     */
    protected open fun setupObservers() {}

    protected open fun fetchData() {}

    override fun onDestroy() {
        super.onDestroy()
//        _binding = null
    }
}
