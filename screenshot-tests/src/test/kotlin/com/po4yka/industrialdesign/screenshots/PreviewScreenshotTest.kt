package com.po4yka.industrialdesign.screenshots

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onRoot
import com.github.takahirom.roborazzi.RoborazziRule
import com.github.takahirom.roborazzi.RobolectricDeviceQualifiers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.ParameterizedRobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import sergio.sastre.composable.preview.scanner.android.AndroidComposablePreviewScanner
import sergio.sastre.composable.preview.scanner.android.AndroidPreviewInfo
import sergio.sastre.composable.preview.scanner.core.preview.ComposablePreview

/**
 * Screenshot regression tests for every `@Preview` composable in `:preview-desktop`.
 *
 * Run `./gradlew :screenshot-tests:recordRoborazziDebug` to record initial goldens.
 * Run `./gradlew :screenshot-tests:verifyRoborazziDebug` to compare against them (CI).
 *
 * Goldens are stored at `screenshot-tests/src/test/snapshots/` and must be committed.
 * Pixel diffs are uploaded as build artifacts on CI failure.
 */
@RunWith(ParameterizedRobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = RobolectricDeviceQualifiers.Pixel7Pro, sdk = [35])
class PreviewScreenshotTest(
    private val preview: ComposablePreview<AndroidPreviewInfo>,
) {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @get:Rule
    val roborazziRule = RoborazziRule(
        composeRule = composeTestRule,
        captureRoot = composeTestRule.onRoot(),
        options = RoborazziRule.Options(
            // Default is CaptureType.None, which leaves the rule passive and
            // makes verify a 0/0 no-op. LastImage captures the rendered root
            // once setContent settles, which is what each @Test below produces.
            captureType = RoborazziRule.CaptureType.LastImage(),
            outputDirectoryPath = "src/test/snapshots",
            outputFileProvider = { description, dir, fileExtension ->
                java.io.File(
                    dir,
                    "${preview.declaringClass}.${preview.methodName}.$fileExtension",
                )
            },
        ),
    )

    @Test
    fun snapshot() {
        composeTestRule.setContent {
            preview()
        }
    }

    companion object {
        @JvmStatic
        @ParameterizedRobolectricTestRunner.Parameters(name = "{0}")
        fun previews(): List<ComposablePreview<AndroidPreviewInfo>> =
            AndroidComposablePreviewScanner()
                .scanPackageTrees("com.po4yka.industrialdesign.preview")
                .getPreviews()
    }
}
