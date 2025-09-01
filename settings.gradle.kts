pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    plugins {
        id("com.android.application") version "8.3.0"
        id("com.android.library") version "8.3.0"
        kotlin("android") version "1.9.0"
        id("io.github.gradle-nexus.publish-plugin") version "1.3.0"
    }
}

rootProject.name = "qcart-sdk-android"
include(":qcart-sdk")
include(":app")