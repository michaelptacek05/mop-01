plugins {
    kotlin("jvm") version "2.4.21"
    id("org.jetbrains.kotlin.plugin.compose") version "2.4.21"
    application
}

repositories {
    mavenCentral()
    google()
}

kotlin {
    jvmToolchain(26)
}

application {
    mainClass = "MainKt"
}

dependencies {
    implementation("org.jetbrains.compose.runtime:runtime:1.12.2")
    implementation("org.jetbrains.compose.desktop:desktop-jvm-macos-arm64:1.12.2")
}