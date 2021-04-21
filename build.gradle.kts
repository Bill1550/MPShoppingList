buildscript {
    repositories {
        gradlePluginPortal()
        jcenter()
        google()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.Kotlin.main}")
        classpath("com.android.tools.build:gradle:4.1.1")
    }
}

group = "com.loneoakech.tests"
version = "1.0-SNAPSHOT"

allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
    }
}