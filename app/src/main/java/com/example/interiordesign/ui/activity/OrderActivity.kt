package com.example.interiordesign.ui.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.interiordesign.R
import com.example.interiordesign.adapter.UserAdapter
import com.example.interiordesign.databinding.ActivityOrderBinding

class OrderActivity : AppCompatActivity() {
    lateinit var binding: ActivityOrderBinding
    lateinit var adapter: UserAdapter

    val icons = arrayOf(
        R.drawable.baseline_home_24,
        R.drawable.baseline_explore_24,
        R.drawable.baseline_info_24,
        R.drawable.baseline_person_24,
        R.drawable.baseline_logout_24,
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_order)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}