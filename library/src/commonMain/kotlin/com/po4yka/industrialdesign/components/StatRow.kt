package com.po4yka.industrialdesign.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * Direction indicator for `IndustrialStatRow` / `IndustrialHierarchicalRow`.
 */
enum class Trend { Up, Down, Flat }

private fun Trend.glyph(): String = when (this) {
    Trend.Up -> "↑"
    Trend.Down -> "↓"
    Trend.Flat -> "→"
}

/**
 * Single-line key/value row. Label left in `labelSmall` ALL CAPS `onSurfaceVariant`;
 * right cluster = value (`titleMedium`, `status` color), optional `unit` (`labelMedium`),
 * optional `trend` chevron tinted to the status color. 12dp vertical padding.
 */
@Composable
fun IndustrialStatRow(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
    status: Color = MaterialTheme.colorScheme.onSurface,
    unit: String? = null,
    trend: Trend? = null,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = label.uppercase(),
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = value,
                style = MaterialTheme.typography.titleMedium,
                color = status,
            )
            if (unit != null) {
                Text(
                    text = unit,
                    style = MaterialTheme.typography.labelMedium,
                    color = status,
                )
            }
            if (trend != null) {
                Text(
                    text = trend.glyph(),
                    style = MaterialTheme.typography.titleMedium,
                    color = status,
                )
            }
        }
    }
}

/**
 * Indented stat row. Each `level` adds 16dp of leading padding — indentation is the hierarchy,
 * no tree lines, no expand affordance. Otherwise identical to `IndustrialStatRow`.
 */
@Composable
fun IndustrialHierarchicalRow(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
    level: Int = 0,
    status: Color = MaterialTheme.colorScheme.onSurface,
    unit: String? = null,
    trend: Trend? = null,
) {
    val indent = (level.coerceAtLeast(0) * 16).dp
    IndustrialStatRow(
        label = label,
        value = value,
        modifier = modifier.padding(start = indent),
        status = status,
        unit = unit,
        trend = trend,
    )
}
