package com.po4yka.industrialdesign.foundation

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Minimal stroke-first icon primitives. Consumers integrating a larger pack
 * should follow the stroke width and size tokens here and avoid filled
 * glyph icons; Lucide outline and Phosphor light are compatible third-party
 * sets.
 */

object IndustrialIconSpec {
    val StrokeWidthThin: Dp = 1.dp
    val StrokeWidthDefault: Dp = 1.5.dp
    val StrokeWidthBold: Dp = 2.dp

    val SizeSm: Dp = 16.dp
    val SizeMd: Dp = 20.dp
    val SizeLg: Dp = 24.dp
    val SizeXl: Dp = 32.dp
}

/** Direction for [IndustrialArrowIcon]. Renders as a chevron glyph. */
enum class ArrowDirection { Up, Down, Left, Right }

/** Chevron arrow drawn as two strokes with round caps for glyph legibility. */
@Composable
fun IndustrialArrowIcon(
    direction: ArrowDirection,
    modifier: Modifier = Modifier,
    strokeWidth: Dp = IndustrialIconSpec.StrokeWidthDefault,
    color: Color = LocalContentColor.current,
) {
    Canvas(modifier = modifier.size(IndustrialIconSpec.SizeMd)) {
        val w = size.width
        val h = size.height
        val px = strokeWidth.toPx()
        val stroke = Stroke(width = px, cap = StrokeCap.Round)

        val path = Path()
        when (direction) {
            ArrowDirection.Up -> {
                path.moveTo(w * 0.25f, h * 0.6f)
                path.lineTo(w * 0.5f, h * 0.35f)
                path.lineTo(w * 0.75f, h * 0.6f)
            }
            ArrowDirection.Down -> {
                path.moveTo(w * 0.25f, h * 0.4f)
                path.lineTo(w * 0.5f, h * 0.65f)
                path.lineTo(w * 0.75f, h * 0.4f)
            }
            ArrowDirection.Left -> {
                path.moveTo(w * 0.6f, h * 0.25f)
                path.lineTo(w * 0.35f, h * 0.5f)
                path.lineTo(w * 0.6f, h * 0.75f)
            }
            ArrowDirection.Right -> {
                path.moveTo(w * 0.4f, h * 0.25f)
                path.lineTo(w * 0.65f, h * 0.5f)
                path.lineTo(w * 0.4f, h * 0.75f)
            }
        }
        drawPath(path = path, color = color, style = stroke)
    }
}

/** Close `X` glyph with square end caps to match chart stroke style. */
@Composable
fun IndustrialCloseIcon(
    modifier: Modifier = Modifier,
    strokeWidth: Dp = IndustrialIconSpec.StrokeWidthDefault,
    color: Color = LocalContentColor.current,
) {
    Canvas(modifier = modifier.size(IndustrialIconSpec.SizeMd)) {
        val w = size.width
        val h = size.height
        val px = strokeWidth.toPx()
        val stroke = Stroke(width = px, cap = StrokeCap.Square)

        drawLine(
            color = color,
            start = Offset(w * 0.25f, h * 0.25f),
            end = Offset(w * 0.75f, h * 0.75f),
            strokeWidth = stroke.width,
            cap = stroke.cap,
        )
        drawLine(
            color = color,
            start = Offset(w * 0.75f, h * 0.25f),
            end = Offset(w * 0.25f, h * 0.75f),
            strokeWidth = stroke.width,
            cap = stroke.cap,
        )
    }
}

/** Check-mark glyph with round end caps. */
@Composable
fun IndustrialCheckIcon(
    modifier: Modifier = Modifier,
    strokeWidth: Dp = IndustrialIconSpec.StrokeWidthDefault,
    color: Color = LocalContentColor.current,
) {
    Canvas(modifier = modifier.size(IndustrialIconSpec.SizeMd)) {
        val w = size.width
        val h = size.height
        val px = strokeWidth.toPx()
        val stroke = Stroke(width = px, cap = StrokeCap.Round)

        val path = Path()
        path.moveTo(w * 0.22f, h * 0.54f)
        path.lineTo(w * 0.42f, h * 0.72f)
        path.lineTo(w * 0.78f, h * 0.32f)
        drawPath(path = path, color = color, style = stroke)
    }
}

/** Filled dot used as an active navigation or status indicator. */
@Composable
fun IndustrialDotIcon(
    modifier: Modifier = Modifier,
    size: Dp = 8.dp,
    color: Color = LocalContentColor.current,
) {
    Canvas(modifier = modifier.size(size)) {
        val radius = this.size.minDimension / 2f
        drawCircle(
            color = color,
            radius = radius,
            center = Offset(this.size.width / 2f, this.size.height / 2f),
        )
    }
}
