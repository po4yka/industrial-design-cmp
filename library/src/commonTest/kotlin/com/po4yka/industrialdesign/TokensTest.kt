package com.po4yka.industrialdesign

import androidx.compose.ui.unit.dp
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Regression tests for token values. Changing any of these should be a deliberate decision
 * with a CHANGELOG entry and token-export re-run.
 */
class TokensTest {
    @Test
    fun spacing_scale_is_stable() {
        assertEquals(2.dp, IndustrialTokens.Spacing.xs2)
        assertEquals(4.dp, IndustrialTokens.Spacing.xs)
        assertEquals(8.dp, IndustrialTokens.Spacing.sm)
        assertEquals(16.dp, IndustrialTokens.Spacing.md)
        assertEquals(24.dp, IndustrialTokens.Spacing.lg)
        assertEquals(32.dp, IndustrialTokens.Spacing.xl)
        assertEquals(48.dp, IndustrialTokens.Spacing.xl2)
        assertEquals(64.dp, IndustrialTokens.Spacing.xl3)
        assertEquals(96.dp, IndustrialTokens.Spacing.xl4)
    }

    @Test
    fun radius_presets_are_stable() {
        assertEquals(4.dp, IndustrialTokens.Radius.Technical)
        assertEquals(8.dp, IndustrialTokens.Radius.Compact)
        assertEquals(16.dp, IndustrialTokens.Radius.Card)
        assertEquals(999.dp, IndustrialTokens.Radius.Pill)
    }

    @Test
    fun motion_durations_are_bounded() {
        assertTrue(IndustrialTokens.Motion.MicroDurationMillis in 100..250)
        assertTrue(IndustrialTokens.Motion.TransitionDurationMillis in 200..400)
    }

    @Test
    fun signal_color_is_red() {
        val signal = IndustrialTokens.Accent.Signal
        assertTrue(signal.red > 0.8f, "signal red channel too low: ${signal.red}")
        assertTrue(signal.green < 0.3f, "signal green channel too high: ${signal.green}")
        assertTrue(signal.blue < 0.3f, "signal blue channel too high: ${signal.blue}")
    }
}
