package com.vocaai.covid19.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vocaai.covid19.R

class LearnMoreActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learn_more)

        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close)
    }
}
