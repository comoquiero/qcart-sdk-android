plugins {
    id("com.android.library")
    kotlin("android")
    id("maven-publish")
    id("signing")
}

android {
    namespace = "app.qcart.deeplink"
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

// Only configure publishing for release tasks (skip debug/local builds)
afterEvaluate {
    if (!gradle.startParameter.taskNames.any { it.contains("assembleDebug") }) {
        publishing {
            publications {
                create<MavenPublication>("release") {
                    groupId = "app.qcart"
                    artifactId = "deeplink-sdk"
                    version = "1.0.0"
                    artifact("$buildDir/outputs/aar/${project.name}-release.aar")

                    pom {
                        name.set("QCart Deeplink SDK")
                        description.set("SDK for handling deeplinks in QCart apps")
                        url.set("https://github.com/comoquiero/qcart")
                        licenses {
                            license {
                                name.set("Apache-2.0 License")
                                url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
                            }
                        }
                        developers {
                            developer {
                                id.set("DaGsgm")
                                name.set("Oscar Gardiazabal")
                                email.set("oscar@qcart.app")
                            }
                        }
                        scm {
                            connection.set("scm:git:https://github.com/comoquiero/qcart.git")
                            developerConnection.set("scm:git:https://github.com/comoquiero/qcart.git")
                            url.set("https://github.com/comoquiero/qcart")
                        }
                    }
                }
            }

            repositories {
                mavenLocal() // for MavenLocal testing
            }
        }

        signing {
            useGpgCmd()
            sign(publishing.publications["release"])
        }
    }
}