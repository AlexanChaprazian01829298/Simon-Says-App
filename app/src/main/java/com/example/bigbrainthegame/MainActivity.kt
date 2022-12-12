package com.example.bigbrainthegame


import android.content.Context
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.Global.putInt
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.example.bigbrainthegame.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


/***********************************************
Authors: Alexan Chaprazian, Krestina Beshara
Date: 12/12/22
Purpose: Created a childrens game to boost the ability to memorize by implementing a
version of simon says
What Learned: learned how to use shared preferenfeces to display the high score even after the
app is done running. Also learned how to add music to the app while the game is being played.
Sources of Help: https://www.youtube.com/watch?v=NPYaUnGX6kw&ab_channel=MunirHoque
https://developer.android.com/training/data-storage/shared-preferences
https://www.youtube.com/watch?v=I-T_A9tnhgQ&ab_channel=Mr.Kaiser
Time Spent (Hours): 74 hours
 ***********************************************/
/*
Mobile App Development I -- COMP.4630 Honor Statement
COMP.4630 Project F22 - Dr. Lin
The practice of good ethical behavior is essential for
maintaining good order in the classroom, providing an
enriching learning experience for students, and training as
a practicing computing professional upon graduation. This
practice is manifested in the University's Academic
Integrity policy. Students are expected to strictly avoid
academic dishonesty and adhere to the Academic Integrity
policy as outlined in the course catalog. Violations will
be dealt with as outlined therein. All programming
assignments in this class are to be done by the student
alone unless otherwise specified. No outside help is
permitted except the instructor and approved tutors.
I certify that the work submitted with this assignment is
mine and was generated in a manner consistent with this
document, the course academic policy on the course website
on Blackboard, and the UMass Lowell academic code.
Date:12/12/22
Names:Alexan Chaprazian & Krestina Beshara
*/

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setSupportActionBar(binding.toolbar)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val builder = AppBarConfiguration.Builder(navController.graph)
        val appBarConfiguration = builder.build()
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)

        val bottomNavView = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomNavView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        val navController = findNavController(R.id.nav_host_fragment)
        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }
}