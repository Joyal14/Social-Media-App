package com.example.socialmediaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.socialmediaapp.databinding.ActivityMainBinding
import com.example.socialmediaapp.fragments.FragmentHome
import com.example.socialmediaapp.fragments.FragmentPost
import com.example.socialmediaapp.fragments.FragmentProfile
import com.example.socialmediaapp.fragments.FragmentSearch

class MainActivity : AppCompatActivity() {

    private val fragbinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(fragbinding.root)

        fragbinding.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {

                R.id.botNavHome -> setFragmentToContainer(FragmentHome())

                R.id.botNavSearch -> setFragmentToContainer(FragmentSearch())

                R.id.botNavPost -> setFragmentToContainer(FragmentPost())

                R.id.botNavProfile -> setFragmentToContainer(FragmentProfile())

                else -> setFragmentToContainer(FragmentHome())
            }
            true
        }
    }

    private fun setFragmentToContainer(fragment: Fragment ) {

        supportFragmentManager.beginTransaction()
            .replace(fragbinding.container.id, fragment)
//            //.setCustomAnimations(enter, exit, popEnter, popExit)
            .commit()
    }
}
