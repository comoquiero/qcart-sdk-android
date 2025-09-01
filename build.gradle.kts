plugins {
    id("com.android.library") version "8.2.1"
    id("org.jetbrains.kotlin.android") version "1.9.10"
    id("maven-publish")
    id("signing")
    id("io.github.gradle-nexus.publish-plugin") version "1.2.0"
}

repositories {
    google()
    mavenCentral()
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

// publishing {
//     publications {
//         create<MavenPublication>("release") {
//             groupId = "app.qcart"
//             artifactId = "deeplink-sdk"
//             version = "1.0.0"

//             afterEvaluate {
//                 from(components["release"])
//             }
//         }
//     }

//     repositories {
//         mavenLocal() // local Maven repository
//     }
// }

// publishing {
//     publications {
//         create<MavenPublication>("release") {
//             groupId = "app.qcart"
//             artifactId = "deeplink-sdk"
//             version = "1.0.0"

//             afterEvaluate { from(components["release"]) }

//             pom {
//                 name.set("QCart Deeplink SDK")
//                 description.set("SDK for handling deep links in QCart apps")
//                 url.set("https://yourdomain.com/sdk")

//                 licenses {
//                     license {
//                         name.set("The Apache License, Version 2.0")
//                         url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
//                     }
//                 }

//                 developers {
//                     developer {
//                         id.set("yourid")
//                         name.set("Your Name")
//                         email.set("you@example.com")
//                     }
//                 }

//                 scm {
//                     connection.set("scm:git:git://github.com/yourusername/yourrepo.git")
//                     developerConnection.set("scm:git:ssh://github.com/yourusername/yourrepo.git")
//                     url.set("https://github.com/yourusername/yourrepo")
//                 }
//             }
//         }
//     }

//     repositories {
//         maven {
//             name = "OSSRH"
//             url = uri("https://oss.sonatype.org/service/local/staging/deploy/maven2/")
//             credentials {
//                 username = findProperty("ossrhUsername") as String
//                 password = findProperty("ossrhPassword") as String
//             }
//         }
//     }
// }

// signing {
//     useGpgCmd() // or useInMemoryPgpKeys(...)
//     sign(publishing.publications["release"])
// }

nexusPublishing {
    repositories {
        sonatype {
            // username.set(findProperty("centralUsername") as String)
            // password.set(findProperty("centralPassword") as String)
            username.set("DaGsgm")
            password.set("0dpUq0jyX2lMyyyIVh34Zq9ReZ0ih8TOE")
        }
    }
}

publishing {
    publications {
        create<MavenPublication>("release") {
            groupId = "app.qcart"
            artifactId = "deeplink-sdk"
            version = "1.0.1"

            // afterEvaluate {
            //     from(components["release"])
            // }

            // Use the release AAR and POM manually
            artifact("$buildDir/outputs/aar/${project.name}-release.aar")

            pom {
                name.set("QCart Deeplink SDK")
                description.set("An SDK for handling deeplinks in QCart apps")
                url.set("https://github.com/comoquiero/qcart")

                licenses {
                    license {
                        name.set("Apache-2.0 License")
                        url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }

                scm {
                    connection.set("scm:git:https://github.com/comoquiero/qcart.git")
                    developerConnection.set("scm:git:https://github.com/comoquiero/qcart.git")
                    url.set("https://github.com/comoquiero/qcart")
                }

                developers {
                    developer {
                        id.set("DaGsgm")
                        name.set("Oscar Gardiazabal")
                        email.set("oscar@qcart.app")
                    }
                }
            }
        }
    }

    // repositories {
    //     maven {
    //         name = "OSSRH"
    //         url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
    //         credentials {
    //             username = findProperty("ossrhUsername") as String? ?: ""
    //             password = findProperty("ossrhPassword") as String? ?: ""
    //         }
    //     }
    // }
    // repositories {
    //     maven {
    //         // name = "MavenCentral"
    //         // url = uri("https://ossrh-staging-api.central.sonatype.com/service/local/staging/deploy/maven2/")
    //         name = "CentralPortal"
    //         url = uri("https://central.sonatype.com/api/v1/publisher/deploy")
    //         credentials {
    //             // username = findProperty("centralPortalUsername") as String
    //             // password = findProperty("centralPortalPassword") as String
    //             username = "DaGsgm"
    //             password = "0dpUq0jyX2lMyyyIVh34Zq9ReZ0ih8TOE"
    //         }
    //     }
    // }
}

// signing {
//     sign(publishing.publications["release"])
// }
// signing {
//     // Only sign when publishing to remote, not local
//     val isLocal = gradle.startParameter.taskNames.any { it.contains("MavenLocal") }
//     if (!isLocal) {
//         useGpgCmd() // or useInMemoryPgpKeys(...)
//         sign(publishing.publications["release"])
//     }
// }
signing {
    useGpgCmd() // Uses your local GPG installation and key
    // useGpgCmd("06A92E9C4AE27832A938F759B69D72D95FB4382F") // Uses your local GPG installation and key
    sign(publishing.publications["release"])
}