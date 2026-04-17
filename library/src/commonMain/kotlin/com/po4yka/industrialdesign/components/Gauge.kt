package com.po4yka.industrialdesign.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin

private const val RadialStartAngle = 135f
private const val RadialSweep = 270f
private const val DialStartAngle = 180f
private const val DialSweep = 180f
private val DegToRad: Float = (PI / 180.0).toFloat()

/**
 * Circular gauge. 270° background arc, foreground arc for value fraction, tick marks around outside.
 * Center shows numeric readout; optional ALL CAPS label renders below.
 */
@Composable
fun IndustrialRadialGauge(
    value: Float,
    modifier: Modifier = Modifier,
    range: ClosedFloatingPointRange<Float> = 0f..1f,
    ticks: Int = 10,
    arcWidth: Dp = 2.dp,
    trackColor: Color = MaterialTheme.colorScheme.outlineVariant,
    progressColor: Color = MaterialTheme.colorScheme.primary,
    label: String? = null,
) {
    val span = (range.endInclusive - range.start).coerceAtLeast(0.0001f)
    val fraction = ((value - range.start) / span).coerceIn(0f, 1f)
    val density = LocalDensity.current
    val strokePx = with(density) { arcWidth.toPx() }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f),
            contentAlignment = Alignment.Center,
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                val diameter = min(size.width, size.height)
                val outerPadding = strokePx * 3f
                val arcW = diameter - outerPadding * 2f
                val arcSize = Size(arcW, arcW)
                val topLeft = Offset(
                    (size.width - arcW) / 2f,
                    (size.height - arcW) / 2f,
                )
                drawArc(
                    color = trackColor,
                    startAngle = RadialStartAngle,
                    sweepAngle = RadialSweep,
                    useCenter = false,
                    topLeft = topLeft,
                    size = arcSize,
                    style = Stroke(width = strokePx),
                )
                drawArc(
                    color = progressColor,
                    startAngle = RadialStartAngle,
                    sweepAngle = RadialSweep * fraction,
                    useCenter = false,
                    topLeft = topLeft,
                    size = arcSize,
                    style = Stroke(width = strokePx),
                )
                val cx = size.width / 2f
                val cy = size.height / 2f
                val radius = arcW / 2f
                val innerRadius = radius + strokePx
                val outerRadius = radius + strokePx * 2.5f
                val tickCount = ticks.coerceAtLeast(2)
                for (i in 0..tickCount) {
                    val t = i.toFloat() / tickCount
                    val angleRad = (RadialStartAngle + RadialSweep * t) * DegToRad
                    drawLine(
                        color = trackColor,
                        start = Offset(
                            cx + cos(angleRad) * innerRadius,
                            cy + sin(angleRad) * innerRadius,
                        ),
                        end = Offset(
                            cx + cos(angleRad) * outerRadius,
                            cy + sin(angleRad) * outerRadius,
                        ),
                        strokeWidth = strokePx,
                    )
                }
            }
            Text(
                text = formatValue(value),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(horizontal = 8.dp),
            )
        }
        if (label != null) {
            Text(
                text = label.uppercase(),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}

/**
 * Thin-stroke semicircular dial with needle and tick marks. Use for directional readouts.
 */
@Composable
fun IndustrialDial(
    value: Float,
    modifier: Modifier = Modifier,
    min: Float = 0f,
    max: Float = 1f,
    label: String? = null,
    arcWidth: Dp = 2.dp,
    ticks: Int = 10,
    trackColor: Color = MaterialTheme.colorScheme.outlineVariant,
    needleColor: Color = MaterialTheme.colorScheme.primary,
) {
    val span = (max - min).coerceAtLeast(0.0001f)
    val fraction = ((value - min) / span).coerceIn(0f, 1f)
    val density = LocalDensity.current
    val strokePx = with(density) { arcWidth.toPx() }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(2f),
        ) {
            val outerPadding = strokePx * 3f
            val arcW = (size.width - outerPadding * 2f).coerceAtLeast(0f)
            val arcSize = Size(arcW, arcW)
            val topLeft = Offset(
                (size.width - arcW) / 2f,
                size.height - arcW / 2f - outerPadding / 2f,
            )
            drawArc(
                color = trackColor,
                startAngle = DialStartAngle,
                sweepAngle = DialSweep,
                useCenter = false,
                topLeft = topLeft,
                size = arcSize,
                style = Stroke(width = strokePx),
            )

            val cx = topLeft.x + arcW / 2f
            val cy = topLeft.y + arcW / 2f
            val radius = arcW / 2f
            val innerRadius = radius + strokePx
            val outerRadius = radius + strokePx * 2.5f
            val tickCount = ticks.coerceAtLeast(2)
            for (i in 0..tickCount) {
                val t = i.toFloat() / tickCount
                val angleRad = (DialStartAngle + DialSweep * t) * DegToRad
                drawLine(
                    color = trackColor,
                    start = Offset(
                        cx + cos(angleRad) * innerRadius,
                        cy + sin(angleRad) * innerRadius,
                    ),
                    end = Offset(
                        cx + cos(angleRad) * outerRadius,
                        cy + sin(angleRad) * outerRadius,
                    ),
                    strokeWidth = strokePx,
                )
            }

            val needleAngleRad = (DialStartAngle + DialSweep * fraction) * DegToRad
            val needleLength = radius - strokePx
            drawLine(
                color = needleColor,
                start = Offset(cx, cy),
                end = Offset(
                    cx + cos(needleAngleRad) * needleLength,
                    cy + sin(needleAngleRad) * needleLength,
                ),
                strokeWidth = strokePx * 1.5f,
            )
            drawCircle(
                color = needleColor,
                radius = strokePx * 1.5f,
                center = Offset(cx, cy),
            )
        }
        Text(
            text = formatValue(value),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
        )
        if (label != null) {
            Text(
                text = label.uppercase(),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}

private fun formatValue(value: Float): String {
    val rounded = (value * 100f).toInt() / 100f
    return if (rounded == rounded.toInt().toFloat()) {
        rounded.toInt().toString()
    } else {
        rounded.toString()
    }
}
