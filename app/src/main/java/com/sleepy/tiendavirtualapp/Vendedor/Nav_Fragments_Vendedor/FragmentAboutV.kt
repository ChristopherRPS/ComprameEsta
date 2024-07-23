package com.sleepy.tiendavirtualapp.Vendedor.Nav_Fragments_Vendedor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.sleepy.tiendavirtualapp.R

class FragmentAboutV : Fragment(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_about_v, container, false)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        return view
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val companyLocation = LatLng(-12.119308, -77.030820) // Coordenadas de la dirección especificada
        mMap.addMarker(MarkerOptions().position(companyLocation).title("Empresa"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(companyLocation, 15f))
    }
}
