package com.po4yka.industrialdesign.components

import androidx.compose.foundation.Canvas
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Uniformly-spaced dot grid for density/heat visualizations. Per-cell alpha
 * derives from [values]`(row, col)` clamped to `[0f, 1f]`. No color gradients.
 */
@Composable
fun IndustrialDotGrid(
    rows: Int,
    cols: Int,
    values: (row: Int, col: Int) -> Float,
    modifier: Modifier = Modifier,
    dotSize: Dp = 6.dp,
    gap: Dp = 4.dp,
    color: Color = MaterialTheme.colorScheme.primary,
) {
    val density = LocalDensity.current
    val dotPx = with(density) { dotSize.toPx() }
    val gapPx = with(density) { gap.toPx() }
    val radius = dotPx / 2f
    val stride = dotPx + gapPx

    Canvas(modifier = modifier) {
        val totalWidth = cols * dotPx + (cols - 1).coerceAtLeast(0) * gapPx
        val totalHeight = rows * dotPx + (rows - 1).coerceAtLeast(0) * gapPx
        val offsetX = (size.width - totalWidth) / 2f
        val offsetY = (size.height - totalHeight) / 2f

        for (r in 0 until rows) {
            for (c in 0 until cols) {
                val alpha = values(r, c).coerceIn(0f, 1f)
                val cx = offsetX + c * stride + radius
                val cy = offsetY + r * stride + radius
                drawCircle(
                    color = color.copy(alpha = alpha),
                    radius = radius,
                    center = Offset(cx, cy),
                )
            }
        }
    }
}
