package com.po4yka.industrialdesign.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Signature segmented progress bar. Row of square-end blocks separated by [gap].
 * Size presets documented via default params: Hero 16–20dp, Standard 8–12dp, Compact 4–6dp.
 * For overflow (progress > 1) caller should pass signal red as [fillColor]; the bar visually fills.
 */
@Composable
fun IndustrialSegmentedProgressBar(
    progress: Float,
    modifier: Modifier = Modifier,
    segmentCount: Int = 20,
    height: Dp = 12.dp,
    fillColor: Color = MaterialTheme.colorScheme.primary,
    emptyColor: Color = MaterialTheme.colorScheme.outlineVariant,
    gap: Dp = 2.dp,
) {
    val filled = (segmentCount * progress).toInt().coerceIn(0, segmentCount)
    Row(
        modifier = modifier.height(height),
        horizontalArrangement = Arrangement.spacedBy(gap),
    ) {
        repeat(segmentCount) { index ->
            val color = if (index < filled) fillColor else emptyColor
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(color),
            )
        }
    }
}

/**
 * Companion readout for [IndustrialSegmentedProgressBar]. Place ABOVE the bar.
 * Label left in ALL CAPS labelSmall; value + unit right in titleMedium + labelMedium.
 */
@Composable
fun IndustrialProgressReadout(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
    unit: String? = null,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = label.uppercase(),
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Text(
                text = value,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
            )
            if (unit != null) {
                Text(
                    text = unit,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
    }
}

/**
 * Thin continuous linear progress bar. Fallback for contexts where segmented discretisation is noise.
 */
@Composable
fun IndustrialLinearProgress(
    progress: Float,
    modifier: Modifier = Modifier,
    height: Dp = 2.dp,
    color: Color = MaterialTheme.colorScheme.primary,
) {
    val clamped = progress.coerceIn(0f, 1f)
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .background(MaterialTheme.colorScheme.outlineVariant),
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(clamped)
                .background(color),
        )
    }
}
