package com.alphabit.dhiyodha.Dashboard

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.alphabit.dhiyodha.BabyToys.BabyToysActivity
import com.alphabit.dhiyodha.Cart.CartActivity
import com.alphabit.dhiyodha.Electronics.ElectronicsActivity
import com.alphabit.dhiyodha.GroceriesPets.GroceriesPetsActivity
import com.alphabit.dhiyodha.HealthBeauty.HealthBeautyActivity
import com.alphabit.dhiyodha.HomeLifeStyle.HomeLifestyleActivity
import com.alphabit.dhiyodha.ManFashion.ManActivity
import com.alphabit.dhiyodha.Medicine.MedicineActivity
import com.alphabit.dhiyodha.MyAccount.MyAccountActivity
import com.alphabit.dhiyodha.R
import com.alphabit.dhiyodha.ScanProduct.ScanProductActivity
import com.alphabit.dhiyodha.SportsOutDoor.SportsOutdoorActivity
import com.alphabit.dhiyodha.Wishlist.WishlistActivity
import com.alphabit.dhiyodha.WomanFashion.WomanFashionActivity
import com.alphabit.dhiyodha.databinding.ActivityDashboardBinding
import com.alphabit.dhiyodha.databinding.RowDashboardBannerBinding
import com.alphabit.dhiyodha.databinding.RowDashboardCouponBannerBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar
import org.imaginativeworld.whynotimagecarousel.ImageCarousel
import org.imaginativeworld.whynotimagecarousel.listener.CarouselListener
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem
import org.imaginativeworld.whynotimagecarousel.utils.setImage
import java.io.IOException
import java.util.Locale


class DashboardActivity : AppCompatActivity() {

    companion object {
        const val CAMERA_REQUEST_RESULT = 1
        val TAG = DashboardActivity::class.java.simpleName
    }

    private lateinit var binding: ActivityDashboardBinding
    private lateinit var categoriesAdapter: DashboardCategoriesAdapter
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fusedLocationClient =
            LocationServices.getFusedLocationProviderClient(this@DashboardActivity)

        setUpClickListener()
        setCategoriesAdapter()
        setUpBanner()
        setUpBannerForCoupon()
        setUpBannerForSecondCoupon()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setUpClickListener() {
        binding.toolbar.ivCart.setOnClickListener {
            val intent = Intent(this@DashboardActivity, CartActivity::class.java)
            startActivity(intent)
        }

        binding.toolbar.ivWishlist.setOnClickListener {
            val intent = Intent(this@DashboardActivity, WishlistActivity::class.java)
            startActivity(intent)
        }

        binding.toolbar.ivAccount.setOnClickListener {
            val intent = Intent(this@DashboardActivity, MyAccountActivity::class.java)
            startActivity(intent)
        }

        binding.toolbar.ivDrawer.setOnClickListener {
            if (binding.mDrawerLayout.isDrawerOpen(binding.navDrawer)) {
                binding.mDrawerLayout.closeDrawer(binding.navDrawer)
            } else if (!binding.mDrawerLayout.isDrawerOpen(binding.navDrawer)) {
                binding.mDrawerLayout.openDrawer(binding.navDrawer)
                setDrawerClickListener()
            }
        }

        binding.tvChangeAddress.setOnClickListener {
            checkLocationPermission()
        }

        binding.imgScanProduct.setOnClickListener {
            if (!isCameraPermissionGiven()) {
                requestPermissions(
                    arrayOf(Manifest.permission.CAMERA),
                    CAMERA_REQUEST_RESULT
                )
            } else {
                val intent = Intent(this@DashboardActivity, ScanProductActivity::class.java)
                startActivity(intent)
            }
        }
    }

    // Permission
    private fun isCameraPermissionGiven(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (isCameraPermissionGiven()) {
            val intent = Intent(this@DashboardActivity, ScanProductActivity::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(
                this,
                "Camera permission is needed to run this application!",
                Toast.LENGTH_SHORT
            ).show()
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.CAMERA
                )
            ) {
                val intent = Intent()
                intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                intent.data = Uri.fromParts("package", this.packageName, null)
                startActivity(intent)
            }
        }
    }


    private fun checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(
                this@DashboardActivity,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestLocationPermission()
        } else {
            // Permission is already granted
            getUserLocation()
        }
    }

    private fun requestLocationPermission() {
        requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                getUserLocation()
            } else {
                Snackbar.make(
                    binding.lnrLocation, String.format(
                        String.format(
                            //"By disabling notification permission, you'll not get any future updates notifications",
                            "Location are disabled, please turn on location to get current address",
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

    private fun getAddressFromLocation(latitude: Double, longitude: Double) {
        try {
            val geocoder = Geocoder(this, Locale.getDefault())
            val addresses: List<Address>? = geocoder.getFromLocation(latitude, longitude, 1)

            if (!addresses.isNullOrEmpty()) {
                val address: Address = addresses[0]
                val addressText =
                    "${address.getAddressLine(0)}, ${address.locality}, ${address.countryName}"
                binding.tvAddress.text = addressText
            } else {
                Toast.makeText(this, "No address found", Toast.LENGTH_SHORT).show()
            }
        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(this, "Geocoder service not available", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setDrawerClickListener() {
        binding.tvWomanFashion.setOnClickListener {
            binding.mDrawerLayout.closeDrawer(binding.navDrawer)
            val intent = Intent(this@DashboardActivity, WomanFashionActivity::class.java)
            startActivity(intent)
        }
        binding.tvManFashion.setOnClickListener {
            binding.mDrawerLayout.closeDrawer(binding.navDrawer)
            val intent = Intent(this@DashboardActivity, ManActivity::class.java)
            startActivity(intent)
        }
        binding.tvElectronics.setOnClickListener {
            binding.mDrawerLayout.closeDrawer(binding.navDrawer)
            val intent = Intent(this@DashboardActivity, ElectronicsActivity::class.java)
            startActivity(intent)
        }
        binding.tvHomeLifeStyle.setOnClickListener {
            binding.mDrawerLayout.closeDrawer(binding.navDrawer)
            val intent = Intent(this@DashboardActivity, HomeLifestyleActivity::class.java)
            startActivity(intent)
        }
        binding.tvMedicine.setOnClickListener {
            binding.mDrawerLayout.closeDrawer(binding.navDrawer)
            val intent = Intent(this@DashboardActivity, MedicineActivity::class.java)
            startActivity(intent)
        }
        binding.tvSports.setOnClickListener {
            binding.mDrawerLayout.closeDrawer(binding.navDrawer)
            val intent = Intent(this@DashboardActivity, SportsOutdoorActivity::class.java)
            startActivity(intent)
        }
        binding.tvBabyToys.setOnClickListener {
            binding.mDrawerLayout.closeDrawer(binding.navDrawer)
            val intent = Intent(this@DashboardActivity, BabyToysActivity::class.java)
            startActivity(intent)
        }
        binding.tvGroceries.setOnClickListener {
            binding.mDrawerLayout.closeDrawer(binding.navDrawer)
            val intent = Intent(this@DashboardActivity, GroceriesPetsActivity::class.java)
            startActivity(intent)
        }
        binding.tvHealthBeauty.setOnClickListener {
            binding.mDrawerLayout.closeDrawer(binding.navDrawer)
            val intent = Intent(this@DashboardActivity, HealthBeautyActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setUpBannerForCoupon() {
        val carousel: ImageCarousel = binding.imageCarouselViewCoupon
        carousel.registerLifecycle(lifecycle)

        binding.imageCarouselViewCoupon.carouselListener = object : CarouselListener {
            override fun onCreateViewHolder(
                layoutInflater: LayoutInflater, parent: ViewGroup
            ): ViewBinding {
                return RowDashboardCouponBannerBinding.inflate(
                    layoutInflater, parent, false
                )
            }

            override fun onBindViewHolder(
                binding: ViewBinding, item: CarouselItem, position: Int
            ) {
                val currentBinding = binding as RowDashboardCouponBannerBinding
                currentBinding.imageView.apply {
                    scaleType = ImageView.ScaleType.FIT_XY
                    setImage(item, R.drawable.ic_coupon)
                }
            }
        }

        for (i in 0 until 10) {
            carousel.addData(CarouselItem(i))
        }
    }

    private fun setUpBannerForSecondCoupon() {
        val carousel: ImageCarousel = binding.imageCarouselViewCouponSecond
        carousel.registerLifecycle(lifecycle)

        binding.imageCarouselViewCouponSecond.carouselListener = object : CarouselListener {

            override fun onCreateViewHolder(
                layoutInflater: LayoutInflater, parent: ViewGroup
            ): ViewBinding {
                return RowDashboardCouponBannerBinding.inflate(
                    layoutInflater, parent, false
                )
            }

            override fun onBindViewHolder(
                binding: ViewBinding, item: CarouselItem, position: Int
            ) {
                val currentBinding = binding as RowDashboardCouponBannerBinding
                currentBinding.imageView.apply {
                    scaleType = ImageView.ScaleType.FIT_XY
                    setImage(item, com.alphabit.dhiyodha.R.drawable.ic_coupon)
                }
            }
        }

        for (i in 0 until 10) {
            carousel.addData(CarouselItem(i))
        }
    }

    private fun setUpBanner() {
        val carousel: ImageCarousel = binding.imageCarouselView
        carousel.registerLifecycle(lifecycle)

        binding.imageCarouselView.carouselListener = object : CarouselListener {
            override fun onCreateViewHolder(
                layoutInflater: LayoutInflater, parent: ViewGroup
            ): ViewBinding {
                return RowDashboardBannerBinding.inflate(
                    layoutInflater, parent, false
                )
            }

            override fun onBindViewHolder(
                binding: ViewBinding, item: CarouselItem, position: Int
            ) {
                val currentBinding = binding as RowDashboardBannerBinding
                currentBinding.imageView.apply {
                    scaleType = ImageView.ScaleType.FIT_XY
                    setImage(item, R.drawable.ic_dashboard_banner)
                }
            }
        }

        for (i in 0 until 10) {
            carousel.addData(CarouselItem(i))
        }
    }

    private fun setCategoriesAdapter() {
        categoriesAdapter = DashboardCategoriesAdapter()
        layoutManager = LinearLayoutManager(this@DashboardActivity, RecyclerView.HORIZONTAL, false)
        binding.rcvCategories.adapter = categoriesAdapter
        binding.rcvCategories.layoutManager = layoutManager
    }
}