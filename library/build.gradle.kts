import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.kmp.library)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.compose.compiler)
    `maven-publish`
}

group = "com.github.po4yka.industrial-design-cmp"
version = "0.1.0"

kotlin {
    compilerOptions {
        freeCompilerArgs.add("-Xexpect-actual-classes")
    }

    androidLibrary {
        namespace = "com.po4yka.industrialdesign"
        compileSdk = 36
        minSdk = 27

        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "IndustrialDesign"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(compose.ui)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.components.resources)
        }
        commonTest.dependencies {
            implementation(kotlin("test"))
            @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
            implementation(compose.uiTest)
        }
    }
}

compose.resources {
    publicResClass = true
    packageOfResClass = "com.po4yka.industrialdesign.resources"
}

// --- Token export ------------------------------------------------------------
// Emits a Style Dictionary-compatible tokens.json for designers / Figma Tokens
// round-tripping. Run: `./gradlew :library:exportTokens`. Output:
// library/build/tokens/tokens.json. Keep in sync with Tokens.kt + Colors.kt.
tasks.register("exportTokens") {
    group = "industrial-design"
    description = "Export design tokens to build/tokens/tokens.json (Style Dictionary format)."

    val outputFile = layout.buildDirectory.file("tokens/tokens.json")
    outputs.file(outputFile)

    doLast {
        val json = buildString {
            append("{\n")
            append("  \"color\": {\n")
            append("    \"dark\": {\n")
            append("      \"background\": { \"value\": \"#000000\", \"type\": \"color\" },\n")
            append("      \"surface\": { \"value\": \"#111111\", \"type\": \"color\" },\n")
            append("      \"surfaceVariant\": { \"value\": \"#1A1A1A\", \"type\": \"color\" },\n")
            append("      \"outlineVariant\": { \"value\": \"#222222\", \"type\": \"color\" },\n")
            append("      \"outline\": { \"value\": \"#333333\", \"type\": \"color\" },\n")
            append("      \"onSurfaceVariant\": { \"value\": \"#999999\", \"type\": \"color\" },\n")
            append("      \"onSurface\": { \"value\": \"#E8E8E8\", \"type\": \"color\" },\n")
            append("      \"primary\": { \"value\": \"#FFFFFF\", \"type\": \"color\" }\n")
            append("    },\n")
            append("    \"light\": {\n")
            append("      \"background\": { \"value\": \"#F5F5F5\", \"type\": \"color\" },\n")
            append("      \"surface\": { \"value\": \"#FFFFFF\", \"type\": \"color\" },\n")
            append("      \"surfaceVariant\": { \"value\": \"#F0F0F0\", \"type\": \"color\" },\n")
            append("      \"outlineVariant\": { \"value\": \"#E8E8E8\", \"type\": \"color\" },\n")
            append("      \"outline\": { \"value\": \"#CCCCCC\", \"type\": \"color\" },\n")
            append("      \"onSurfaceVariant\": { \"value\": \"#666666\", \"type\": \"color\" },\n")
            append("      \"onSurface\": { \"value\": \"#1A1A1A\", \"type\": \"color\" },\n")
            append("      \"primary\": { \"value\": \"#000000\", \"type\": \"color\" }\n")
            append("    },\n")
            append("    \"accent\": {\n")
            append("      \"signal\": { \"value\": \"#D71921\", \"type\": \"color\" },\n")
            append("      \"signalSubtle\": { \"value\": \"#26D71921\", \"type\": \"color\" },\n")
            append("      \"success\": { \"value\": \"#4A9E5C\", \"type\": \"color\" },\n")
            append("      \"warning\": { \"value\": \"#D4A843\", \"type\": \"color\" },\n")
            append("      \"interactive\": { \"value\": \"#5B9BF6\", \"type\": \"color\" },\n")
            append("      \"interactiveLight\": { \"value\": \"#007AFF\", \"type\": \"color\" }\n")
            append("    }\n")
            append("  },\n")
            append("  \"spacing\": {\n")
            append("    \"xs2\": { \"value\": \"2px\", \"type\": \"dimension\" },\n")
            append("    \"xs\": { \"value\": \"4px\", \"type\": \"dimension\" },\n")
            append("    \"sm\": { \"value\": \"8px\", \"type\": \"dimension\" },\n")
            append("    \"md\": { \"value\": \"16px\", \"type\": \"dimension\" },\n")
            append("    \"lg\": { \"value\": \"24px\", \"type\": \"dimension\" },\n")
            append("    \"xl\": { \"value\": \"32px\", \"type\": \"dimension\" },\n")
            append("    \"xl2\": { \"value\": \"48px\", \"type\": \"dimension\" },\n")
            append("    \"xl3\": { \"value\": \"64px\", \"type\": \"dimension\" },\n")
            append("    \"xl4\": { \"value\": \"96px\", \"type\": \"dimension\" }\n")
            append("  },\n")
            append("  \"radius\": {\n")
            append("    \"technical\": { \"value\": \"4px\", \"type\": \"dimension\" },\n")
            append("    \"compact\": { \"value\": \"8px\", \"type\": \"dimension\" },\n")
            append("    \"card\": { \"value\": \"16px\", \"type\": \"dimension\" },\n")
            append("    \"pill\": { \"value\": \"999px\", \"type\": \"dimension\" }\n")
            append("  },\n")
            append("  \"motion\": {\n")
            append("    \"durationMicro\": { \"value\": \"120ms\", \"type\": \"duration\" },\n")
            append("    \"durationFast\": { \"value\": \"200ms\", \"type\": \"duration\" },\n")
            append("    \"durationStandard\": { \"value\": \"300ms\", \"type\": \"duration\" },\n")
            append("    \"durationSlow\": { \"value\": \"450ms\", \"type\": \"duration\" },\n")
            append("    \"easingEaseOut\": { \"value\": \"cubic-bezier(0.25, 0.1, 0.25, 1)\", \"type\": \"cubicBezier\" },\n")
            append("    \"easingDecelerate\": { \"value\": \"cubic-bezier(0.0, 0.0, 0.2, 1)\", \"type\": \"cubicBezier\" }\n")
            append("  },\n")
            append("  \"typography\": {\n")
            append("    \"displayLarge\": { \"value\": { \"fontFamily\": \"Doto\", \"fontSize\": \"72px\", \"lineHeight\": \"72px\", \"letterSpacing\": \"-0.03em\" }, \"type\": \"typography\" },\n")
            append("    \"headlineLarge\": { \"value\": { \"fontFamily\": \"Space Grotesk\", \"fontSize\": \"24px\", \"lineHeight\": \"29px\", \"letterSpacing\": \"-0.01em\", \"fontWeight\": 500 }, \"type\": \"typography\" },\n")
            append("    \"bodyLarge\": { \"value\": { \"fontFamily\": \"Space Grotesk\", \"fontSize\": \"16px\", \"lineHeight\": \"24px\", \"fontWeight\": 400 }, \"type\": \"typography\" },\n")
            append("    \"labelLarge\": { \"value\": { \"fontFamily\": \"Space Mono\", \"fontSize\": \"13px\", \"lineHeight\": \"16px\", \"letterSpacing\": \"0.06em\", \"fontWeight\": 400 }, \"type\": \"typography\" }\n")
            append("  }\n")
            append("}\n")
        }

        val file = outputFile.get().asFile
        file.parentFile.mkdirs()
        file.writeText(json)
        logger.lifecycle("Exported tokens → ${file.absolutePath}")
    }
}
