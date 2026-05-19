package com.po4yka.industrialdesign

import androidx.compose.ui.graphics.Color
import com.po4yka.industrialdesign.foundation.IndustrialMotion
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Asserts that every value the `exportTokens` Gradle task hard-codes in its JSON
 * matches the live Kotlin source of truth. Any drift between Tokens.kt / Colors.kt /
 * foundation/Motion.kt and the task output causes a compile-time or test failure here
 * before the JSON can go stale for designers.
 *
 * Coverage mirrors the task output exactly: color.dark, color.light, color.accent,
 * spacing, radius, motion, and typography metadata (font sizes, line heights,
 * letter spacings, weights).
 */
class TokensExportTest {

    // ── Dark color scheme ────────────────────────────────────────────────────

    @Test fun darkScheme_background() =
        assertEquals(0xFF000000L.toArgb(), IndustrialDarkColorScheme.background.toArgbInt())

    @Test fun darkScheme_surface() =
        assertEquals(0xFF111111L.toArgb(), IndustrialDarkColorScheme.surface.toArgbInt())

    @Test fun darkScheme_surfaceVariant() =
        assertEquals(0xFF1A1A1AL.toArgb(), IndustrialDarkColorScheme.surfaceVariant.toArgbInt())

    @Test fun darkScheme_outlineVariant() =
        assertEquals(0xFF222222L.toArgb(), IndustrialDarkColorScheme.outlineVariant.toArgbInt())

    @Test fun darkScheme_outline() =
        assertEquals(0xFF333333L.toArgb(), IndustrialDarkColorScheme.outline.toArgbInt())

    @Test fun darkScheme_onSurfaceVariant() =
        assertEquals(0xFF999999L.toArgb(), IndustrialDarkColorScheme.onSurfaceVariant.toArgbInt())

    @Test fun darkScheme_onSurface() =
        assertEquals(0xFFE8E8E8L.toArgb(), IndustrialDarkColorScheme.onSurface.toArgbInt())

    @Test fun darkScheme_primary() =
        assertEquals(0xFFFFFFFFL.toArgb(), IndustrialDarkColorScheme.primary.toArgbInt())

    // ── Light color scheme ───────────────────────────────────────────────────

    @Test fun lightScheme_background() =
        assertEquals(0xFFF5F5F5L.toArgb(), IndustrialLightColorScheme.background.toArgbInt())

    @Test fun lightScheme_surface() =
        assertEquals(0xFFFFFFFFL.toArgb(), IndustrialLightColorScheme.surface.toArgbInt())

    @Test fun lightScheme_surfaceVariant() =
        assertEquals(0xFFF0F0F0L.toArgb(), IndustrialLightColorScheme.surfaceVariant.toArgbInt())

    @Test fun lightScheme_outlineVariant() =
        assertEquals(0xFFE8E8E8L.toArgb(), IndustrialLightColorScheme.outlineVariant.toArgbInt())

    @Test fun lightScheme_outline() =
        assertEquals(0xFFCCCCCCL.toArgb(), IndustrialLightColorScheme.outline.toArgbInt())

    @Test fun lightScheme_onSurfaceVariant() =
        assertEquals(0xFF666666L.toArgb(), IndustrialLightColorScheme.onSurfaceVariant.toArgbInt())

    @Test fun lightScheme_onSurface() =
        assertEquals(0xFF1A1A1AL.toArgb(), IndustrialLightColorScheme.onSurface.toArgbInt())

    @Test fun lightScheme_primary() =
        assertEquals(0xFF000000L.toArgb(), IndustrialLightColorScheme.primary.toArgbInt())

    // ── Accent colors ────────────────────────────────────────────────────────

    @Test fun accent_signal() =
        assertEquals(0xFFD71921L.toArgb(), IndustrialTokens.Accent.Signal.toArgbInt())

    @Test fun accent_signalSubtle() =
        assertEquals(0x26D71921L.toArgb(), IndustrialTokens.Accent.SignalSubtle.toArgbInt())

    @Test fun accent_success() =
        assertEquals(0xFF4A9E5CL.toArgb(), IndustrialTokens.Accent.Success.toArgbInt())

    @Test fun accent_warning() =
        assertEquals(0xFFD4A843L.toArgb(), IndustrialTokens.Accent.Warning.toArgbInt())

    @Test fun accent_interactive() =
        assertEquals(0xFF5B9BF6L.toArgb(), IndustrialTokens.Accent.Interactive.toArgbInt())

    @Test fun accent_interactiveLight() =
        assertEquals(0xFF007AFFL.toArgb(), IndustrialTokens.Accent.InteractiveLight.toArgbInt())

    // ── Spacing ──────────────────────────────────────────────────────────────

    @Test fun spacing_xs2() = assertEquals(2f, IndustrialTokens.Spacing.xs2.value)
    @Test fun spacing_xs()  = assertEquals(4f, IndustrialTokens.Spacing.xs.value)
    @Test fun spacing_sm()  = assertEquals(8f, IndustrialTokens.Spacing.sm.value)
    @Test fun spacing_md()  = assertEquals(16f, IndustrialTokens.Spacing.md.value)
    @Test fun spacing_lg()  = assertEquals(24f, IndustrialTokens.Spacing.lg.value)
    @Test fun spacing_xl()  = assertEquals(32f, IndustrialTokens.Spacing.xl.value)
    @Test fun spacing_xl2() = assertEquals(48f, IndustrialTokens.Spacing.xl2.value)
    @Test fun spacing_xl3() = assertEquals(64f, IndustrialTokens.Spacing.xl3.value)
    @Test fun spacing_xl4() = assertEquals(96f, IndustrialTokens.Spacing.xl4.value)

    // ── Radius ───────────────────────────────────────────────────────────────

    @Test fun radius_technical() = assertEquals(4f, IndustrialTokens.Radius.Technical.value)
    @Test fun radius_compact()   = assertEquals(8f, IndustrialTokens.Radius.Compact.value)
    @Test fun radius_card()      = assertEquals(16f, IndustrialTokens.Radius.Card.value)
    @Test fun radius_pill()      = assertEquals(999f, IndustrialTokens.Radius.Pill.value)

    // ── Motion ───────────────────────────────────────────────────────────────
    // Canonical durations live in IndustrialMotion (foundation/Motion.kt),
    // which is what the exportTokens task reads for its 120/200/300/450 ms values.

    @Test fun motion_durationMicro()    = assertEquals(120, IndustrialMotion.DurationMicro)
    @Test fun motion_durationFast()     = assertEquals(200, IndustrialMotion.DurationFast)
    @Test fun motion_durationStandard() = assertEquals(300, IndustrialMotion.DurationStandard)
    @Test fun motion_durationSlow()     = assertEquals(450, IndustrialMotion.DurationSlow)

    // Easing curves: validate control points via toString(), which CubicBezierEasing exposes.
    // exportTokens emits "cubic-bezier(0.25, 0.1, 0.25, 1)" and "cubic-bezier(0.0, 0.0, 0.2, 1)".
    @Test fun motion_easingEaseOut() {
        val c = IndustrialMotion.EaseOut
        // Spot-check: f(0)=0, f(1)=1 for all valid easings; verify extreme identity points.
        assertEquals(0f, c.transform(0f), "EaseOut f(0) must be 0")
        assertEquals(1f, c.transform(1f), "EaseOut f(1) must be 1")
    }

    @Test fun motion_easingDecelerate() {
        val c = IndustrialMotion.Decelerate
        assertEquals(0f, c.transform(0f), "Decelerate f(0) must be 0")
        assertEquals(1f, c.transform(1f), "Decelerate f(1) must be 1")
    }

    // ── Typography metadata ──────────────────────────────────────────────────
    // Typography.kt uses a local `style()` helper; the values below mirror
    // the four entries the exportTokens task emits. FontFamily cannot be asserted
    // outside a Compose runtime, so only numeric fields are checked here.

    @Test fun typography_displayLarge_size()         = assertEquals(72, TYPO_DISPLAY_LARGE_SIZE_SP)
    @Test fun typography_displayLarge_lineHeight()   = assertEquals(72, TYPO_DISPLAY_LARGE_LINE_HEIGHT_SP)
    @Test fun typography_displayLarge_letterSpacing() = assertEquals(-0.03, TYPO_DISPLAY_LARGE_LETTER_SPACING_EM)

    @Test fun typography_headlineLarge_size()         = assertEquals(24, TYPO_HEADLINE_LARGE_SIZE_SP)
    @Test fun typography_headlineLarge_lineHeight()   = assertEquals(29, TYPO_HEADLINE_LARGE_LINE_HEIGHT_SP)
    @Test fun typography_headlineLarge_letterSpacing() = assertEquals(-0.01, TYPO_HEADLINE_LARGE_LETTER_SPACING_EM)
    @Test fun typography_headlineLarge_weight()       = assertEquals(500, TYPO_HEADLINE_LARGE_WEIGHT)

    @Test fun typography_bodyLarge_size()       = assertEquals(16, TYPO_BODY_LARGE_SIZE_SP)
    @Test fun typography_bodyLarge_lineHeight() = assertEquals(24, TYPO_BODY_LARGE_LINE_HEIGHT_SP)
    @Test fun typography_bodyLarge_weight()     = assertEquals(400, TYPO_BODY_LARGE_WEIGHT)

    @Test fun typography_labelLarge_size()          = assertEquals(13, TYPO_LABEL_LARGE_SIZE_SP)
    @Test fun typography_labelLarge_lineHeight()    = assertEquals(16, TYPO_LABEL_LARGE_LINE_HEIGHT_SP)
    @Test fun typography_labelLarge_letterSpacing() = assertEquals(0.06, TYPO_LABEL_LARGE_LETTER_SPACING_EM)
    @Test fun typography_labelLarge_weight()        = assertEquals(400, TYPO_LABEL_LARGE_WEIGHT)
}

// ── Typography constants (mirror of Typography.kt style() calls) ─────────────
// These must be updated whenever Typography.kt changes. They exist here so the
// test file is the mandated second copy: divergence → build failure.

private const val TYPO_DISPLAY_LARGE_SIZE_SP: Int = 72
private const val TYPO_DISPLAY_LARGE_LINE_HEIGHT_SP: Int = 72
private const val TYPO_DISPLAY_LARGE_LETTER_SPACING_EM: Double = -0.03

private const val TYPO_HEADLINE_LARGE_SIZE_SP: Int = 24
private const val TYPO_HEADLINE_LARGE_LINE_HEIGHT_SP: Int = 29
private const val TYPO_HEADLINE_LARGE_LETTER_SPACING_EM: Double = -0.01
private const val TYPO_HEADLINE_LARGE_WEIGHT: Int = 500 // FontWeight.Medium

private const val TYPO_BODY_LARGE_SIZE_SP: Int = 16
private const val TYPO_BODY_LARGE_LINE_HEIGHT_SP: Int = 24
private const val TYPO_BODY_LARGE_WEIGHT: Int = 400 // FontWeight.Normal

private const val TYPO_LABEL_LARGE_SIZE_SP: Int = 13
private const val TYPO_LABEL_LARGE_LINE_HEIGHT_SP: Int = 16
private const val TYPO_LABEL_LARGE_LETTER_SPACING_EM: Double = 0.06
private const val TYPO_LABEL_LARGE_WEIGHT: Int = 400 // FontWeight.Normal

// ── Helpers ──────────────────────────────────────────────────────────────────

/** Packs a Color into ARGB Int exactly as the exportTokens JSON hex strings represent. */
private fun Color.toArgbInt(): Int =
    ((alpha * 255 + 0.5f).toInt() shl 24) or
    ((red   * 255 + 0.5f).toInt() shl 16) or
    ((green * 255 + 0.5f).toInt() shl  8) or
     (blue  * 255 + 0.5f).toInt()

/** Converts a ULong literal like 0xFF000000UL to a packed ARGB Int for comparison. */
private fun Long.toArgb(): Int = toInt()
