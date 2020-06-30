package com.example.mrfox

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import com.example.mrfox.helpers.GetServicesValues.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.reservation.*
import kotlin.coroutines.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        reserveButton.setOnClickListener {
            val intent = Intent(this, ReservationActivity::class.java)
            startActivity(intent)
        }

        contactButton.setOnClickListener {
            val intent = Intent(this, ContactActivity::class.java)
            startActivity(intent)
        }

        priceButton.setOnClickListener {
            val text = Toast.makeText(this, "Je to jeste rozmrdany", Toast.LENGTH_LONG)
            text.setGravity(Gravity.BOTTOM,Gravity.CENTER,Gravity.CENTER)
            text.show()
        }

        instaButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://www.instagram.com/mrfoxbrno/")
            startActivity(intent)
        }

        fbButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("fb://page/970617766284294")
            startActivity(intent)
        }

        webButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://www.mrfox.cz")
            startActivity(intent)
        }
    }

    class GetFromThread() : Thread() {

        override fun run() {
            println(GetValuesFromElements())
        }

        operator fun get(d: String) : ArrayList<String> {
            return GetValuesFromElements()
        }

    }
}