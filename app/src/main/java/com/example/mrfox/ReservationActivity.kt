package com.example.mrfox

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.reservation.*

class ReservationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.reservation)

    novobranska.setOnClickListener {
        val pobocka = "Novobranská"
        val openURL = Intent(Intent.ACTION_VIEW)
        openURL.data = Uri.parse("https://mr-fox.reservio.com/")
        startActivity(openURL)
        println("I will reserve $pobocka")
    }

    aztower.setOnClickListener {
        val pobocka = "AZ Tower"
        val openURL = Intent(Intent.ACTION_VIEW)
        openURL.data = Uri.parse("https://mr-fox2.reservio.com/")
        startActivity(openURL)
        println("I will reserve $pobocka")
    }

    backButton.setOnClickListener {
        finish()
    }
    }
}