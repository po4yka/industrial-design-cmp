package com.po4yka.industrialdesign.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.po4yka.industrialdesign.IndustrialTokens

/**
 * Container corner style for [IndustrialSegmentedControl]. `Pill` is fully
 * rounded, `Compact` uses the 8dp radius for denser desktop toolbars.
 */
enum class ControlShape { Pill, Compact }

/**
 * Industrial segmented control. Active segment inverts to `primary` /
 * `onPrimary`; inactive segments are transparent with `onSurfaceVariant`
 * text. Color transitions animate over 200ms with the industrial easing.
 */
@Composable
fun IndustrialSegmentedControl(
    options: List<String>,
    selectedIndex: Int,
    onSelect: (Int) -> Unit,
    modifier: Modifier = Modifier,
    shape: ControlShape = ControlShape.Pill,
) {
    val cornerRadius = when (shape) {
        ControlShape.Pill -> IndustrialTokens.Radius.Pill
        ControlShape.Compact -> IndustrialTokens.Radius.Compact
    }
    val roundedShape = RoundedCornerShape(cornerRadius)
    Row(
        modifier = modifier
            .height(40.dp)
            .clip(roundedShape)
            .border(1.dp, MaterialTheme.colorScheme.outline, roundedShape),
    ) {
        options.forEachIndexed { index, option ->
            val isActive = index == selectedIndex
            val targetBg = if (isActive) MaterialTheme.colorScheme.primary else Color.Transparent
            val targetFg = if (isActive) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant
            val bgColor by animateColorAsState(
                targetValue = targetBg,
                animationSpec = tween(
                    durationMillis = IndustrialTokens.Motion.MicroDurationMillis,
                    easing = IndustrialTokens.Motion.Easing,
                ),
            )
            val fgColor by animateColorAsState(
                targetValue = targetFg,
                animationSpec = tween(
                    durationMillis = IndustrialTokens.Motion.MicroDurationMillis,
                    easing = IndustrialTokens.Motion.Easing,
                ),
            )
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(bgColor)
                    .clickable { onSelect(index) }
                    .padding(horizontal = IndustrialTokens.Spacing.md),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = option.uppercase(),
                    style = MaterialTheme.typography.labelMedium,
                    color = fgColor,
                )
            }
        }
    }
}
