val sdkMode: String = project.findProperty("sdkMode") as? String ?: "local"

plugins {
    id("com.android.application") version "8.3.0"
    kotlin("android") version "1.9.0"
}

android {
    namespace = "com.example.qcarttestapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.qcarttestapp"
        minSdk = 27
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions { jvmTarget = "17" }

    buildFeatures { compose = true }
    composeOptions { kotlinCompilerExtensionVersion = "1.5.0" }
}

dependencies {
    when (sdkMode) {
        "local" -> implementation(project(":qcart-sdk"))
        "maven" -> implementation("app.qcart:deeplink-sdk:1.0.0")
        "jitpack" -> implementation("com.github.comoquiero:qcart-sdk-android:1.0.3")
        // "jitpack" -> implementation("com.github.comoquiero:qcart-sdk-android:main-SNAPSHOT")
        // "jitpack" -> implementation("com.github.comoquiero:qcart-sdk-android:7d6adbb")
    }

    // Jetpack Compose
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation("androidx.compose.ui:ui:1.5.0")
    implementation("androidx.compose.material3:material3:1.3.1")
    implementation("com.appsamurai.storyly:storyly:4.16.2")

    implementation("com.google.android.material:material:1.9.0")
}