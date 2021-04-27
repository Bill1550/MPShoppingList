import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    kotlin( "plugin.serialization" ) version (Versions.Kotlin.main)
    id("com.android.library")
}

group = "com.loneoaktech.tests.shared"
version = "1.0-SNAPSHOT"

kotlin {
    android()

    jvm {
        compilations["main"].kotlinOptions {
            jvmTarget = JavaVersion.VERSION_1_8.toString() //"1.8"
        }
        testRuns["test"].executionTask.configure {
            useJUnit()
        }
//        withJava()
    }

    ios {
        binaries {
            framework {
                baseName = "shared"
            }
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api( "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Kotlin.coroutines}" )
                api("org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.Kotlin.serialization}" )
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("com.google.android.material:material:1.2.1")
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.1")
            }
        }

        val jvmMain by getting {
            dependencies {
            }
        }

        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
            }
        }

        val iosMain by getting
        val iosTest by getting

    }
}

android {
    compileSdkVersion(Versions.compileSdk)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(Versions.minSdk)
        targetSdkVersion(Versions.targetSdk)
    }
}