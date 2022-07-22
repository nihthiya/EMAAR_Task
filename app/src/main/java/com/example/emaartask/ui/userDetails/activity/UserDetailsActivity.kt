package com.example.emaartask.ui.userDetails.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.emaartask.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)
    }
}