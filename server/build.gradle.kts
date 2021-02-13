plugins {
//    application
    kotlin("jvm")
    application
}

group = "com.loneoakech.tests"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation( project(":shared") )
    implementation(kotlin("stdlib"))
    testImplementation("junit", "junit", "4.12")
}
