package com.po4yka.industrialdesign.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.runComposeUiTest
import com.po4yka.industrialdesign.IndustrialTheme
import kotlin.test.Test

/**
 * Smoke tests for the signature segmented progress bar — dark + light + overflow paths
 * render without crashing. Visual fidelity is enforced by screenshot tests once those
 * are wired into the Paparazzi / Roborazzi Android path.
 */
@OptIn(ExperimentalTestApi::class)
class SegmentedProgressTest {
    @Test
    fun renders_in_dark() = runComposeUiTest {
        setContent {
            IndustrialTheme(darkTheme = true) {
                IndustrialSegmentedProgressBar(progress = 0.5f, modifier = Modifier.fillMaxWidth())
            }
        }
        onRoot().assertExists()
    }

    @Test
    fun renders_in_light() = runComposeUiTest {
        setContent {
            IndustrialTheme(darkTheme = false) {
                IndustrialSegmentedProgressBar(progress = 0.5f, modifier = Modifier.fillMaxWidth())
            }
        }
        onRoot().assertExists()
    }

    @Test
    fun handles_overflow_progress() = runComposeUiTest {
        setContent {
            IndustrialTheme(darkTheme = true) {
                IndustrialSegmentedProgressBar(progress = 1.5f, modifier = Modifier.fillMaxWidth())
            }
        }
        onRoot().assertExists()
    }

    @Test
    fun handles_negative_progress() = runComposeUiTest {
        setContent {
            IndustrialTheme(darkTheme = true) {
                IndustrialSegmentedProgressBar(progress = -0.1f, modifier = Modifier.fillMaxWidth())
            }
        }
        onRoot().assertExists()
    }
}
