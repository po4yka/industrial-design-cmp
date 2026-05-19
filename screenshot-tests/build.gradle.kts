// screenshot-tests — Roborazzi screenshot regression tests.
// Discovers every @Preview in :preview-desktop via ComposablePreviewScanner and
// captures golden PNGs with Robolectric Native Graphics mode.
//
// Record goldens : ./gradlew :screenshot-tests:recordRoborazziDebug
// Verify in CI   : ./gradlew :screenshot-tests:verifyRoborazziDebug
//
// Robolectric is pinned to SDK 35 (sdk=35 in robolectric.properties and @Config)
// because SDK 36 requires JDK 21 and this project targets JDK 17.

import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    // AGP 9 ships built-in Kotlin support — no `kotlin("android")` plugin needed
    // (it errors with "plugin no longer required for Kotlin support since AGP 9.0").
    // AGP is on the classpath via :library's android-kmp-library alias at the root
    // level, so id() form (no version) reuses the resolved version.
    id("com.android.library")
    id("org.jetbrains.kotlin.plugin.compose")
    alias(libs.plugins.roborazzi)
}

android {
    namespace = "com.po4yka.industrialdesign.screenshots"
    compileSdk = 36

    defaultConfig {
        minSdk = 27
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
            isReturnDefaultValues = true
        }
    }

    // Share the preview source set from :preview-desktop so we don't duplicate files.
    // The previews import IndustrialTheme from :library (available via testImplementation)
    // and androidx.compose.ui.tooling.preview.Preview (available from compose-bom).
    sourceSets {
        getByName("test") {
            kotlin.srcDir("../preview-desktop/src/main/kotlin")
        }
    }

    buildFeatures {
        compose = true
    }
}

kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_17)
    }
}

dependencies {
    implementation(project(":library"))

    // Jetpack Compose via BOM — versions align with CMP 1.11.0 which maps to
    // Compose UI 1.8.x / the 2025.05 BOM family.
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.material3)
    implementation(libs.compose.foundation)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.activity.compose)

    testImplementation(libs.junit4)
    testImplementation(libs.robolectric)
    testImplementation(platform(libs.compose.bom))
    testImplementation(libs.compose.ui.test.junit4)
    testImplementation(libs.roborazzi.compose)
    testImplementation(libs.roborazzi.junit.rule)
    testImplementation(libs.roborazzi.compose.preview.scanner.support)
    testImplementation(libs.preview.scanner.android)
}
