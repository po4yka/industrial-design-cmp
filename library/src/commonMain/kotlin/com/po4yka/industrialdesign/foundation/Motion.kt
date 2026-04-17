package com.po4yka.industrialdesign.foundation

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf

/**
 * Motion primitives for the industrial design system. Ease-out curves and
 * short, mechanical durations only. Spring and bounce are explicitly excluded.
 */

object IndustrialMotion {
    val EaseOut: Easing = CubicBezierEasing(0.25f, 0.1f, 0.25f, 1f)
    val Linear: Easing = LinearEasing
    val Decelerate: Easing = CubicBezierEasing(0.0f, 0.0f, 0.2f, 1f)

    const val DurationMicro: Int = 120
    const val DurationFast: Int = 200
    const val DurationStandard: Int = 300
    const val DurationSlow: Int = 450
}

/**
 * Convenience factory for a standard industrial tween. Prefer this over
 * raw `tween(...)` so timing and easing stay consistent across components.
 */
fun <T> industrialTween(
    durationMillis: Int = IndustrialMotion.DurationStandard,
    easing: Easing = IndustrialMotion.EaseOut,
): TweenSpec<T> = tween(durationMillis = durationMillis, easing = easing)

/**
 * Whether motion should be reduced for the current UI. Defaults to `false`
 * in commonMain; platform layers may supply real OS-level detection later
 * via [LocalReducedMotion].
 */
@Composable
fun rememberReducedMotion(): Boolean = false

/**
 * Composition local for forcing reduced motion on a subtree. Composables
 * should read this and skip animations or collapse durations when `true`.
 */
val LocalReducedMotion = staticCompositionLocalOf { false }
