package com.example.emaartask.utils

import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.emaartask.R
import java.text.SimpleDateFormat
import java.util.*

class Utils {
    companion object {
        fun formatDate(currentDate: String?): String {
            val formattedDate: String
            var spf = SimpleDateFormat("yyyy-M-dd'T'HH:mm:ss", Locale.US)
            val newDate: Date = spf.parse(currentDate)
            spf = SimpleDateFormat("MMMM dd, yyyy", Locale.US)
            formattedDate = spf.format(newDate)
            return formattedDate
        }

        fun initToolbarWithTitle(
            activity: AppCompatActivity,
            toolbar: Toolbar,
            textView: TextView,
            title: String?
        ) {
            try {
                activity.setSupportActionBar(toolbar)
                if (title!!.isNotEmpty()) {
                    textView.text = title
                }
                Objects.requireNonNull(activity.supportActionBar)?.setHomeButtonEnabled(false)
                activity.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
                activity.supportActionBar!!.setDisplayShowTitleEnabled(false)
                toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_ios_24)

                toolbar.setNavigationOnClickListener {
                    activity.onBackPressed()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }


        }
    }


}