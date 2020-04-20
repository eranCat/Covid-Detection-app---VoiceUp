package com.vocaai.covid19.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vocaai.covid19.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        record_voice_btn.setOnClickListener { recordVoice() }
        learn_more_btn.setOnClickListener { openLearnMore() }
    }

    private fun openLearnMore() {
        val intent = Intent(this, LearnMoreActivity::class.java)
        startActivity(intent)
    }

    private fun recordVoice() {
        val intent = Intent(this,FormActivity::class.java)
        startActivity(intent)
    }
}
