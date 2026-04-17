package com.po4yka.industrialdesign.foundation

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Accessibility helpers for the industrial design system. The design target
 * is 3:1 non-text contrast (WCAG non-text contrast) and a 44dp minimum
 * interactive touch target across all components.
 */

object IndustrialA11y {
    val MinTouchTargetSize: Dp = 44.dp
}

/**
 * Enforces the 44dp minimum touch target on any interactive composable.
 * Pair with `Modifier.clickable { }` (or equivalent) for the hit area.
 */
fun Modifier.minTouchTarget(): Modifier =
    this.then(Modifier.defaultMinSize(minWidth = 44.dp, minHeight = 44.dp))

/**
 * Applies industrial semantics in a single call. Each argument is optional
 * and only written when non-null, so callers can mix and match attributes.
 */
fun Modifier.industrialSemantics(
    label: String? = null,
    role: Role? = null,
    stateDescription: String? = null,
): Modifier = this.semantics {
    if (label != null) this.contentDescription = label
    if (role != null) this.role = role
    if (stateDescription != null) this.stateDescription = stateDescription
}

/**
 * Current font scale clamped to a safe range for layout math. Callers can
 * use the returned factor to pre-flight spacing adjustments before drawing.
 */
@Composable
fun calibrateForFontScale(): Float =
    LocalDensity.current.fontScale.coerceIn(0.85f, 2.0f)
