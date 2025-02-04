plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

apply(plugin = "kotlin-kapt") // Apply kapt directly to avoid conflict
apply(plugin = "kotlin-parcelize")  // Apply parcelize manually

android {
    namespace = "com.alphabit.dhiyodha"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.alphabit.dhiyodha"
        minSdk = 24
        //noinspection EditedTargetSdkVersion
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.databinding.runtime)
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.activity)
    implementation(libs.firebase.auth)
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.fragment.ktx)

    //text size
    implementation(libs.sdp.android)
    implementation(libs.ssp.android)

    //View binding
    implementation(libs.butterknife)
    implementation(libs.androidx.legacy.support.v4)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.lifecycle.extensions)
    implementation(libs.monitor)
    implementation(libs.androidx.junit.ktx)

    //circular image
    implementation(libs.circleimageview)
    implementation(libs.roundedimageview)

    //recyclerview
    implementation(libs.androidx.recyclerview)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.logging.interceptor)

    // Gson
    implementation(libs.gson)

    // lottie animation
    implementation(libs.lottie)

    implementation(libs.elasticviews)

    implementation(libs.permissionx)

    implementation(libs.kotlinx.coroutines.core)

    //Seekbar
    implementation(libs.circularseekbar)

    implementation(libs.ucropnedit)
    implementation(libs.dexter)

    // Auto scroll viewpager
    implementation(libs.loopingviewpager)

    //banner  slide
    implementation(libs.whynotimagecarousel)
}