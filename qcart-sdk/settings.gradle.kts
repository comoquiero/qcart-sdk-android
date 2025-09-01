pluginManagement {
    repositories {
        gradlePluginPortal()  // for Kotlin plugins
        google()              // for Android Gradle plugin
        mavenCentral()
        maven { setUrl("https://jitpack.io") } // 👈 allow JitPack plugins if needed
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { setUrl("https://jitpack.io") } // 👈 allow JitPack dependencies
    }
}

rootProject.name = "deeplink-sdk"

// 👇 include your modules here
include(":qcart-sdk")
include(":app")   // optional, only if you keep a sample app