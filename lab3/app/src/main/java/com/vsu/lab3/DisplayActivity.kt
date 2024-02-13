package com.vsu.lab3

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class DisplayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)

        val userName = intent.getStringExtra("user_name")
        val textViewName = findViewById<TextView>(R.id.textViewName)
        textViewName.text = "Привет, $userName!"
    }
}
