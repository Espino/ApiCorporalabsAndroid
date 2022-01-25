package com.phonedeveloper.apicorporalabs.core.base

import android.os.Bundle
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<BindingType : ViewBinding> :
    HiltActivity() {

    lateinit var binding: BindingType
    abstract fun onActivityCreated()

    abstract fun getViewBinding(): BindingType

    var onBackPress: (() -> Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)
        onActivityCreated()
    }

    override fun onBackPressed() {
        if (onBackPress != null) {
            onBackPress!!()
        } else {
            super.onBackPressed()
        }
    }
}