package com.chi.test.level1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import com.chi.test.level1.R.id.button

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<Button>(button)
        val text = findViewById<TextView>(R.id.textView)
        viewModel.counter.observe(this) {
            if (it!=null) text.text = it.toString()
        }
        button.setOnClickListener {
           supportFragmentManager.beginTransaction().replace(R.id.container, BlankFragment()).commit()
        }
    }
}