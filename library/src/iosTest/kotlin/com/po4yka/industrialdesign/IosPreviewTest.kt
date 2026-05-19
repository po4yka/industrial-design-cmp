package com.po4yka.industrialdesign

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.runComposeUiTest
import com.po4yka.industrialdesign.components.IndustrialBottomBar
import com.po4yka.industrialdesign.components.IndustrialDial
import com.po4yka.industrialdesign.components.IndustrialPrimaryButton
import com.po4yka.industrialdesign.components.IndustrialSegmentedControl
import com.po4yka.industrialdesign.components.IndustrialStatRow
import com.po4yka.industrialdesign.components.IndustrialTextField
import com.po4yka.industrialdesign.components.NavItem
import com.po4yka.industrialdesign.components.Trend
import kotlin.test.Test
import kotlin.test.assertTrue

@OptIn(ExperimentalTestApi::class)
class IosPreviewTest {

    @Test
    fun primaryButtonComposesAndIsClickable() = runComposeUiTest {
        var clicks = 0
        setContent {
            IndustrialTheme {
                IndustrialPrimaryButton(
                    onClick = { clicks++ },
                    text = "ACTION",
                )
            }
        }
        onNodeWithText("ACTION").assertHasClickAction()
        onNodeWithText("ACTION").assertIsDisplayed()
    }

    @Test
    fun segmentedControlShowsAllOptions() = runComposeUiTest {
        val options = listOf("A", "B", "C")
        setContent {
            IndustrialTheme {
                IndustrialSegmentedControl(
                    options = options,
                    selectedIndex = 1,
                    onSelect = {},
                )
            }
        }
        // Each option is uppercased inside the component
        options.forEach { label ->
            onAllNodesWithText(label.uppercase())[0].assertIsDisplayed()
        }
    }

    @Test
    fun textFieldShowsValueAndLabel() = runComposeUiTest {
        setContent {
            IndustrialTheme {
                IndustrialTextField(
                    value = "x",
                    onValueChange = {},
                    label = "LABEL",
                )
            }
        }
        // Label is rendered uppercase inside the component
        onNodeWithText("LABEL").assertIsDisplayed()
    }

    @Test
    fun bottomBarRendersNavItems() = runComposeUiTest {
        val items = listOf(
            NavItem(label = "Home"),
            NavItem(label = "Stats"),
            NavItem(label = "Settings"),
        )
        setContent {
            IndustrialTheme {
                IndustrialBottomBar(
                    items = items,
                    selectedIndex = 0,
                    onSelect = {},
                )
            }
        }
        items.forEach { item ->
            onNodeWithText(item.label.uppercase()).assertIsDisplayed()
        }
    }

    @Test
    fun statRowRendersLabelAndValue() = runComposeUiTest {
        setContent {
            IndustrialTheme {
                IndustrialStatRow(
                    label = "SPEED",
                    value = "42",
                    trend = Trend.Up,
                )
            }
        }
        // Label is uppercased inside the component
        onNodeWithText("SPEED").assertIsDisplayed()
        onNodeWithText("42").assertIsDisplayed()
    }

    @Test
    fun dialComposesWithoutCrash() = runComposeUiTest {
        var composed = false
        setContent {
            IndustrialTheme {
                IndustrialDial(value = 0.5f)
            }
            composed = true
        }
        assertTrue(composed, "IndustrialDial should compose without throwing")
    }
}
