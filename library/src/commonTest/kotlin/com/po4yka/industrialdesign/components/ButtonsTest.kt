package com.po4yka.industrialdesign.components

import androidx.compose.material3.Text
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.runComposeUiTest
import com.po4yka.industrialdesign.IndustrialTheme
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalTestApi::class)
class ButtonsTest {
    @Test
    fun primary_button_emits_click() = runComposeUiTest {
        var clicks = 0
        setContent {
            IndustrialTheme {
                IndustrialPrimaryButton(onClick = { clicks++ }) { Text("SUBMIT") }
            }
        }
        onNodeWithText("SUBMIT").assertHasClickAction().performClick()
        assertEquals(1, clicks)
    }

    @Test
    fun disabled_button_swallows_click() = runComposeUiTest {
        var clicks = 0
        setContent {
            IndustrialTheme {
                IndustrialPrimaryButton(onClick = { clicks++ }, enabled = false) {
                    Text("DISABLED")
                }
            }
        }
        onNodeWithText("DISABLED").performClick()
        assertEquals(0, clicks)
    }
}
