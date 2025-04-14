plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-parcelize")
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "ru.mygames.gym_xml"
    compileSdk = 35
    buildFeatures{
        viewBinding = true
    }

    defaultConfig {
        applicationId = "ru.mygames.gym_xml"
        minSdk = 24
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation (libs.picasso)

    // Retrofit для сетевых запросов
    implementation (libs.retrofit2.retrofit)
    implementation (libs.converter.gson)
    implementation(libs.androidx.credentials)
    implementation(libs.androidx.credentials.play.services.auth)
    implementation(libs.googleid)
    implementation(libs.firebase.auth)
    implementation(libs.firebase.database.ktx)


    // Glide для загрузки изображений
    annotationProcessor (libs.compiler)

    // Glide для изображений
    implementation (libs.glide.v4160)

    // Lifecycle
    implementation (libs.androidx.lifecycle.viewmodel.ktx.v262)
    implementation (libs.androidx.lifecycle.runtime.ktx)
    // Для воспроизведения видео
    implementation (libs.core)
    implementation(libs.volley)
    implementation(libs.androidx.core.ktx)
    implementation (libs.androidx.fragment.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.glide)
}