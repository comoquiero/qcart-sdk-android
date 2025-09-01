plugins {
    // Apply nexus plugin here at the root
    id("io.github.gradle-nexus.publish-plugin") version "1.3.0"
}

// Detect if we are building on JitPack
val isJitpackBuild = System.getenv("JITPACK") != null

// Configure Nexus publishing only if not on JitPack
if (!isJitpackBuild) {
    nexusPublishing {
        repositories {
            sonatype {
                username.set(findProperty("centralUsername") as String? ?: "")
                password.set(findProperty("centralPassword") as String? ?: "")
            }
        }
    }
}

// Skip signing when building on JitPack
allprojects {
    tasks.withType<Sign>().configureEach {
        onlyIf { !isJitpackBuild }
    }
}