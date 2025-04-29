package com.alphabit.dhiyodha.Cart

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alphabit.dhiyodha.BottomSheets.AddAddressBottomSheet
import com.alphabit.dhiyodha.R
import com.alphabit.dhiyodha.databinding.ActivityCartBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar
import java.io.IOException
import java.util.Locale

class CartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCartBinding
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var cartItemAdapter: CartItemAdapter
    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this@CartActivity)

        setUpToolBar()
        setUpClickListener()
        setCartItemAdapter()
    }

    private fun setCartItemAdapter() {
        cartItemAdapter = CartItemAdapter()
        layoutManager = LinearLayoutManager(this@CartActivity, RecyclerView.VERTICAL, false)
        binding.rcvCartDetails.adapter = cartItemAdapter
        binding.rcvCartDetails.layoutManager = layoutManager
    }

    private fun setUpClickListener() {
        binding.tvSelectAddress.setOnClickListener {
            showAddressBottomSheet()
        }
        binding.tvChangeCode.setOnClickListener {
            checkLocationPermission()
        }
    }

    private fun showAddressBottomSheet() {
        val bottomSheet = AddAddressBottomSheet()
        bottomSheet.show(supportFragmentManager, AddAddressBottomSheet::class.java.name)
    }

    private fun setUpToolBar() {
        binding.toolbar.tvTitle.text = "Cart"
        binding.toolbar.ivBack.setOnClickListener {
            finish()
        }
    }

    private fun checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(
                this@CartActivity,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestLocationPermission()
        } else {
            // Permission is already granted
            getUserLocation()
        }
    }

    @SuppressLint("MissingPermission")
    private fun getUserLocation() {
        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            location?.let {
                getAddressFromLocation(it.latitude, it.longitude)
            } ?: run {
                Toast.makeText(this, "Unable to get location", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun requestLocationPermission() {
        requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }

    private fun getAddressFromLocation(latitude: Double, longitude: Double) {
        try {
            val geocoder = Geocoder(this, Locale.getDefault())
            val addresses: List<Address>? = geocoder.getFromLocation(latitude, longitude, 1)

            if (!addresses.isNullOrEmpty()) {
                val address: Address = addresses[0]
                binding.tvPinCode.text = address.postalCode
            } else {
                Toast.makeText(this, "No address found", Toast.LENGTH_SHORT).show()
            }
        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(this, "Geocoder service not available", Toast.LENGTH_SHORT).show()
        }
    }

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                getUserLocation()
            } else {
                Snackbar.make(
                    binding.cnsBottom, String.format(
                        String.format(
                            "Location are disabled, please turn on location to get current pincode",
                            getString(R.string.app_name)
                        )
                    ), 2000 //2sec
                ).setAction("Enable") {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        val settingsIntent: Intent =
                            Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                .putExtra(
                                    Settings.EXTRA_APP_PACKAGE, packageName
                                )
                        startActivity(settingsIntent)
                    }
                }.setBackgroundTint(resources.getColor(R.color.midnightBlue))
                    .setActionTextColor(resources.getColor(R.color.white)).show()
            }
        }
}