plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("maven-publish") // <-- maven-publish plugin
}

android {
    namespace = "app.qcart.deeplink" // 🔧 Set your SDK package namespace here
    compileSdk = 35

    defaultConfig {
        minSdk = 27
        targetSdk = 35
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(kotlin("stdlib"))
}

publishing {
    publications {
        release(MavenPublication) {
            groupId = "app.qcart"             // Your Maven group ID
            artifactId = "deeplink-sdk"       // Your SDK’s artifact name
            version = "1.0.0"                 // Your SDK version

            afterEvaluate {
                from(components.release)      // Publish the release build of your library
            }

            pom {
                name.set("QCart Deeplink SDK")
                description.set("SDK for handling deep links in QCart apps")
                url.set("https://yourdomain.com/sdk")  // Your SDK/project homepage
                
                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                developers {
                    developer {
                        id.set("yourid")
                        name.set("Your Name")
                        email.set("you@example.com")
                    }
                }
                scm {
                    connection.set("scm:git:git://github.com/yourusername/yourrepo.git")
                    developerConnection.set("scm:git:ssh://github.com/yourusername/yourrepo.git")
                    url.set("https://github.com/yourusername/yourrepo")
                }
            }
        }
    }

    repositories {
        // For local testing — publishes to your local Maven repo at ~/.m2/repository
        mavenLocal()
        
        // You can add your remote Maven repository here later (Sonatype, GitHub Packages, etc.)
        // Example:
        // maven {
        //     url = uri("https://your.maven.repo/url")
        //     credentials {
        //         username = "..."
        //         password = "..."
        //     }
        // }
    }
}