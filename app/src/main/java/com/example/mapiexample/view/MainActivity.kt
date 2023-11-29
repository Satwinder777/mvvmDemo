package com.example.mapiexample.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.mapiexample.R
import com.example.mapiexample.databinding.ActivityMainBinding
import com.example.mapiexample.repository.PhotosRepository
import com.example.mapiexample.retrofit.RetrofitHelper
import com.example.mapiexample.viewmodel.PhotosViewModel
import com.example.mapiexample.viewmodel.PhotosViewModelFactory

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    lateinit var drawerLayout: DrawerLayout
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    lateinit var viewModel: PhotosViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setSupportActionBar(binding.toolbar)

        navController = findNavController(R.id.nav_host_fragment)


        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.nav_account),
            binding.myDrawerLayout
        )

//        setupActionBar(navController,appBarConfiguration)
//        setupNavigationMenu(navController)

//        binding.myDrawerLayout.setupWithNavController(navController)
//        binding.mynav.setupWithNavController(navController)




        drawerLayout = binding.myDrawerLayout
        actionBarDrawerToggle = ActionBarDrawerToggle(this,drawerLayout,R.string.nav_open,R.string.nav_close)

        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        var repo = PhotosRepository(RetrofitHelper.service)
        viewModel = ViewModelProvider(this,PhotosViewModelFactory(repo)).get(PhotosViewModel::class.java)


        binding.mynav.setNavigationItemSelectedListener { menuItem ->
            when(menuItem.itemId){
                R.id.nav_account -> {
                    Toast.makeText(this, "Account", Toast.LENGTH_SHORT).show()

                    drawerLayout.closeDrawers()
                    navController.navigate(R.id.myAccountFragment)
                    true
                }
                R.id.nav_settings -> {
                    Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show()
                    drawerLayout.closeDrawers()
                    navController.navigate(R.id.settingFragment)

                    true
                }
                else -> {
                    Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show()
                    drawerLayout.closeDrawers()
                    navController.navigate(R.id.logoutFragment)

                    true
                }
            }
        }
        viewModel.photosList.observe(this, Observer {
            it.forEach {
                Log.e("PhotosId", "onCreate: ${it.id}")
            }
        })
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (actionBarDrawerToggle.onOptionsItemSelected(item)){
            true
        }
    else{
             return super.onOptionsItemSelected(item)
        }
    }



    private fun setupActionBar(navController: NavController, appBarConfig: AppBarConfiguration) {
        setupActionBarWithNavController(navController, appBarConfig)
    }

//    private fun setupNavigationMenu(navController: NavController) {
//        binding.myDrawerLayout.setupWithNavController(navController)
//    }

}