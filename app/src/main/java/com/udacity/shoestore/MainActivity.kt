package com.udacity.shoestore

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.databinding.ActivityMainBinding
import timber.log.Timber


class MainActivity : AppCompatActivity() {

    //    lateinit var viewModel : MainViewModel
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    private var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.plant(Timber.DebugTree())

        // Set dataBinding
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

//        viewModel =  ViewModelProvider(this).get(MainViewModel::class.java)

        // Set the navController
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.myNavHostFragment) as NavHostFragment
        navController = navHostFragment.navController

        // Set appBarConfiguration
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.loginFragment,
                R.id.shoeListingFragment
            )
        )

        // Set the navController on destinationChanged listener,
        // to show menu only when in Shoe List screen
        navController.addOnDestinationChangedListener { nc: NavController, nd: NavDestination, _: Bundle? ->
            menu?.findItem(R.id.loginFragment)?.isVisible = nd.id == R.id.shoeListingFragment
        }

        // Setup appBarConfiguration with navController
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
    }

    // Set Navigation up
    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }

    // Create menu for logout
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.shoe_listing_menu, menu)
        this.menu = menu
        return super.onCreateOptionsMenu(menu)
    }

    // When interacting with action bar and/or menu, go to proper destination
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        if (item.itemId == R.id.loginFragment) {
            navController.popBackStack(
                R.id.shoeListingFragment, true
            )
        }
        return NavigationUI.onNavDestinationSelected(item, navController)
                || super.onOptionsItemSelected(item)
    }


}
