package com.phonedeveloper.apicorporalabs.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.phonedeveloper.apicorporalabs.R
import com.phonedeveloper.apicorporalabs.core.base.BaseActivity
import com.phonedeveloper.apicorporalabs.databinding.ActivityMainBinding
import com.phonedeveloper.apicorporalabs.ui.articles.HomeNewsViewModel
import com.phonedeveloper.apicorporalabs.ui.detail.DetailViewModelFactory
import com.phonedeveloper.apicorporalabs.ui.viewmodel.NewsViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject
import kotlin.time.ExperimentalTime

@ExperimentalTime
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    companion object {
        fun intentBuilder(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    lateinit var viewModel: HomeNewsViewModel

    @Inject
    lateinit var factory: NewsViewModelFactory

    @Inject
    lateinit var factoryDetail: DetailViewModelFactory

    private lateinit var appBarConfiguration: AppBarConfiguration

//    Para hacer uso de complemento de Navegación
//    private val navController by lazy {
//        (supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment) /*as NavHostFragment*/
//            .navController
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, factory)
            .get(HomeNewsViewModel::class.java)
    }

    override fun onActivityCreated() {

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

}