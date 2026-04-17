package com.po4yka.industrialdesign.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * One bar slice in [IndustrialBarChart].
 */
data class BarDatum(val label: String, val value: Float)

/**
 * Vertical bar chart. Each column is empty-above / filled-below, square ends, 2dp gaps.
 * Labels render ALL CAPS labelSmall below each column when [showLabels] is true.
 */
@Composable
fun IndustrialBarChart(
    data: List<BarDatum>,
    modifier: Modifier = Modifier,
    max: Float = data.maxOfOrNull { it.value } ?: 1f,
    barColor: Color = MaterialTheme.colorScheme.primary,
    emptyColor: Color = MaterialTheme.colorScheme.outlineVariant,
    showLabels: Boolean = true,
    barHeight: Dp = 120.dp,
) {
    val safeMax = if (max <= 0f) 1f else max
    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(barHeight),
            horizontalArrangement = Arrangement.spacedBy(2.dp),
        ) {
            data.forEach { datum ->
                val fraction = (datum.value / safeMax).coerceIn(0f, 1f)
                val emptyWeight = (1f - fraction)
                val filledWeight = fraction
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                ) {
                    if (emptyWeight > 0f) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(emptyWeight)
                                .background(emptyColor),
                        )
                    }
                    if (filledWeight > 0f) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(filledWeight)
                                .background(barColor),
                        )
                    }
                }
            }
        }
        if (showLabels) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(2.dp),
            ) {
                data.forEach { datum ->
                    Box(
                        modifier = Modifier.weight(1f),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = datum.label.uppercase(),
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                }
            }
        }
    }
}
