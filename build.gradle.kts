plugins {
    id("io.github.gradle-nexus.publish-plugin") version "1.3.0"
}

allprojects {
    repositories {
        google()
        mavenCentral()
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