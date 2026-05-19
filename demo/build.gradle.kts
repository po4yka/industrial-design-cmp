import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.kmp.library)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.compose.compiler)
}

group = "com.po4yka.industrialdesign.demo"
version = "0.1.0"

kotlin {
    compilerOptions {
        freeCompilerArgs.add("-Xexpect-actual-classes")
    }

    androidLibrary {
        namespace = "com.po4yka.industrialdesign.demo"
        compileSdk = 36
        minSdk = 27

        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }

    listOf(
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Demo"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(project(":library"))
            implementation(libs.compose.mp.ui)
            implementation(libs.compose.mp.foundation)
            implementation(libs.compose.mp.material3)
            implementation(libs.compose.mp.components.resources)
        }
        androidMain.dependencies {
            implementation(libs.compose.mp.ui.tooling)
        }
    }
}
