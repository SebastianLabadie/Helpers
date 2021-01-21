package com.example.firmadigital

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        var lat=-34.8943943
        var long=-56.1830574
        var zoom = 16
        // Add a marker in Sydney and move the camera
        //val centerPoint = LatLng(lat, long)
        addMarkers()
        val boundsBuilder = LatLngBounds.builder()
            .include(PERTH)
            .include(ADELAIDE)
            .include(MELBOURNE)
            .include(SYDNEY)
            .include(DARWIN)
            .include(BRISBANE)

        /*val markerOptions = MarkerOptions()
        markerOptions.position(centerPoint)
        markerOptions.title("Platzi Conf 2019")

        markerOptions.icon(bitmapDescriptorFromVector(this,R.drawable.ic_baseline_location_on_24))

        mMap.addMarker(markerOptions)*/
        mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(boundsBuilder.build(), zoom))


    }

    private fun bitmapDescriptorFromVector(context: Context, vectorResId: Int): BitmapDescriptor? {
        return ContextCompat.getDrawable(context, vectorResId)?.run {
            setBounds(0, 0, intrinsicWidth, intrinsicHeight)
            val bitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888)
            draw(Canvas(bitmap))
            BitmapDescriptorFactory.fromBitmap(bitmap)
        }
    }

    private fun addMarkers() {
        mMap.addMarker(
            MarkerOptions()
                .position(BRISBANE)
                .title("Brisbane")
        )
        mMap.addMarker(
            MarkerOptions()
                .position(MELBOURNE)
                .title("Melbourne")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
        )
        mMap.addMarker(
            MarkerOptions()
                .position(SYDNEY)
                .title("Sydney")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
        )
        mMap.addMarker(
            MarkerOptions()
                .position(ADELAIDE)
                .title("Adelaide")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))
        )
        mMap.addMarker(
            MarkerOptions()
                .position(PERTH)
                .title("Perth")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA))
        )
    }

    companion object {
        private val BRISBANE = LatLng(-27.47093, 153.0235)
        private val MELBOURNE = LatLng(-37.81319, 144.96298)
        private val SYDNEY = LatLng(-33.87365, 151.20689)
        private val ADELAIDE = LatLng(-34.92873, 138.59995)
        private val PERTH = LatLng(-31.952854, 115.857342)
        private val DARWIN = LatLng(-12.459501, 130.839915)

        /**
         * A Polygon with five points in the Norther Territory, Australia.
         */
        private val POLYGON = arrayOf(
            LatLng(-18.000328, 130.473633), LatLng(-16.173880, 135.087891),
            LatLng(-19.663970, 137.724609), LatLng(-23.202307, 135.395508),
            LatLng(-19.705347, 129.550781)
        )
    }
}