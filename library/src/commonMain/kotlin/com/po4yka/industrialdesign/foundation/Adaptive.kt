package com.po4yka.industrialdesign.foundation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Adaptive layout primitives: window size classes, density multipliers, and
 * the composition local that drives spacing scaling across the UI.
 */

/** Width bucket matching Material 3 breakpoints (600dp, 840dp). */
enum class IndustrialWidthClass { Compact, Medium, Expanded }

/** Height bucket using 480dp and 900dp as the split points. */
enum class IndustrialHeightClass { Compact, Medium, Expanded }

/** Computed window dimensions in Dp along with their bucket classification. */
data class IndustrialWindowSize(
    val width: Dp,
    val height: Dp,
    val widthClass: IndustrialWidthClass,
    val heightClass: IndustrialHeightClass,
)

/**
 * Reads current window dimensions from [LocalWindowInfo] and returns a
 * remembered [IndustrialWindowSize] that recomputes only when size changes.
 */
@Composable
fun rememberIndustrialWindowSize(): IndustrialWindowSize {
    val density = LocalDensity.current
    val containerSize = LocalWindowInfo.current.containerSize
    return remember(containerSize, density) {
        val widthDp: Dp
        val heightDp: Dp
        with(density) {
            widthDp = containerSize.width.toDp()
            heightDp = containerSize.height.toDp()
        }
        val widthClass = when {
            widthDp < 600.dp -> IndustrialWidthClass.Compact
            widthDp < 840.dp -> IndustrialWidthClass.Medium
            else -> IndustrialWidthClass.Expanded
        }
        val heightClass = when {
            heightDp < 480.dp -> IndustrialHeightClass.Compact
            heightDp < 900.dp -> IndustrialHeightClass.Medium
            else -> IndustrialHeightClass.Expanded
        }
        IndustrialWindowSize(widthDp, heightDp, widthClass, heightClass)
    }
}

/**
 * Spacing multipliers applied to `IndustrialTokens.Spacing` at use sites.
 * `Compact` tightens layouts for data-dense screens; `Comfortable` loosens
 * them for touch-first or accessibility contexts.
 */
object IndustrialDensity {
    val Compact: Float = 0.875f
    val Default: Float = 1.0f
    val Comfortable: Float = 1.125f
}

/** Composition local carrying the active density multiplier for a subtree. */
val LocalIndustrialDensity = staticCompositionLocalOf { IndustrialDensity.Default }
