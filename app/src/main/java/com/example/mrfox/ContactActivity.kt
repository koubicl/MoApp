package com.example.mrfox

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.contacts.*
import kotlinx.android.synthetic.main.reservation.*
import kotlinx.android.synthetic.main.reservation.backButton

class ContactActivity : AppCompatActivity(), OnMapReadyCallback {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.contacts)
        if (getString(R.string.google_maps_key).isEmpty()) {
            Toast.makeText(this, "Add your own API key in MapWithMarker/app/secure.properties as MAPS_API_KEY=YOUR_API_KEY", Toast.LENGTH_LONG).show()
        }

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.getMapAsync(this)

        backButton.setOnClickListener {
            finish()
        }

        callText.setOnClickListener{
            val number = "+420" + callText.text
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$number"))
            startActivity(intent)
        }
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        googleMap?.apply {
            val novobranska = LatLng(49.1932450, 16.6129436)
            val aztower = LatLng(49.1758086, 16.6057297)
            val center = LatLng(49.1864,16.609)

            setMapStyle(MapStyleOptions.loadRawResourceStyle(this@ContactActivity, R.raw.style_json))

            addMarker(
                MarkerOptions()
                    .position(novobranska)
                    .title("MR FOX Novobranská")
            )
            addMarker(
                MarkerOptions()
                    .position(aztower)
                    .title("MR FOX AZ Tower")
            )
            moveCamera(CameraUpdateFactory.newLatLngZoom(center, 12.7f))
        }
    }
}
