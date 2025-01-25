package com.example.interiordesign.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.interiordesign.R
import com.example.interiordesign.databinding.ActivityNavigationBinding
import com.example.interiordesign.ui.fragment.AboutFragment
import com.example.interiordesign.ui.fragment.AccountFragment
import com.example.interiordesign.ui.fragment.ExploreFragment
import com.example.interiordesign.ui.fragment.HomeFragment
import com.example.interiordesign.ui.fragment.LogoutFragment

class NavigationActivity : AppCompatActivity() {
    lateinit var binding: ActivityNavigationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationView.setOnItemSelectedListener {menu ->
            when(menu.itemId){
                R.id.navHome -> replaceFragment(HomeFragment())
                R.id.navExplore -> replaceFragment(ExploreFragment())
                R.id.navAbout -> replaceFragment(AboutFragment())
                R.id.navAccount -> replaceFragment(AccountFragment())
                R.id.navLogout -> replaceFragment(LogoutFragment())
                else ->{
                    val intent = Intent(this, OrderActivity::class.java)
                    startActivity(intent)
                }
            }
            true
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.frameNavigation, fragment)
        fragmentTransaction.commit()

    }
}