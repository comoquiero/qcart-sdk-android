plugins {
    id("io.github.gradle-nexus.publish-plugin") version "1.3.0"
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}

// Nexus publishing only runs at root for Maven Central
nexusPublishing {
    repositories {
        sonatype {
            username.set(findProperty("centralUsername") as String? ?: "")
            password.set(findProperty("centralPassword") as String? ?: "")
        }
    }
}