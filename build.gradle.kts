plugins {
    kotlin("jvm") version "2.4.21"
    application
}

repositories {
    mavenCentral()
}

kotlin {

    jvmToolchain(26)
}

application {
    mainClass = "MainKt"
}