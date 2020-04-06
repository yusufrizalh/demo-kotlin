package id.yusufrizalh.mygooglemaps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // tambahkan marker di Inixindo Surabaya
        val ixsby = LatLng(-7.323649, 112.7409258)
        mMap.addMarker(MarkerOptions().position(ixsby).title("Marker di IxSby"))

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ixsby, 16f))

        // untuk menampilkan fitur zoom in / out
        mMap.uiSettings.isZoomControlsEnabled = true
    }

    fun onMarkerClick(p0: Marker?) = false
}
