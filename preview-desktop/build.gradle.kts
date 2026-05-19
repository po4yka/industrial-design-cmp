// preview-desktop — internal tooling module, never published.
// Applies ee.schimke.composeai.preview so AI agents and reviewers can render
// every @Preview in this module to PNG via `./gradlew :preview-desktop:renderAllPreviews`.
// Uses the Compose Multiplatform Desktop backend (Skiko / ImageComposeScene),
// so no Android / Robolectric setup is required.
//
// CMP 1.11 deprecated the `compose.<target>: String` accessors in plain `dependencies {}`
// blocks (the KMP `sourceSets.<x>.dependencies { }` DSL is preferred). The
// upstream compose-ai-tools `samples/cmp/build.gradle.kts` ships the same suppression.
@file:Suppress("DEPRECATION")

plugins {
    // KGP is already on the classpath via :library / :demo's kotlin-multiplatform
    // alias at root level, so `kotlin("jvm")` resolves without a version reference
    // (specifying one triggers Gradle's "plugin already on classpath" error).
    kotlin("jvm")
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.composeai.preview)
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    implementation(compose.desktop.currentOs)
    implementation(compose.material3)
    implementation(compose.foundation)
    implementation(compose.ui)
    implementation(compose.uiTooling)
    // org.jetbrains.compose.ui.tooling.preview.Preview annotation.
    implementation(compose.components.uiToolingPreview)
    implementation(project(":library"))
}
