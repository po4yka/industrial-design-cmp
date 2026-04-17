package com.po4yka.industrialdesign.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.po4yka.industrialdesign.IndustrialTokens

/**
 * Flat surface container. Zero elevation, optional 1dp `outlineVariant` border, `Radius.Card` (16dp).
 * `raised = true` swaps `surface` for `surfaceVariant`. Defaults to 16dp inner padding.
 * Becomes clickable when `onClick` is non-null.
 */
@Composable
fun IndustrialCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    border: Boolean = true,
    raised: Boolean = false,
    content: @Composable ColumnScope.() -> Unit,
) {
    val shape = RoundedCornerShape(IndustrialTokens.Radius.Card)
    val containerColor = if (raised) {
        MaterialTheme.colorScheme.surfaceVariant
    } else {
        MaterialTheme.colorScheme.surface
    }
    val borderStroke = if (border) {
        BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
    } else {
        null
    }
    val colors = CardDefaults.cardColors(containerColor = containerColor)
    val elevation = CardDefaults.cardElevation(
        defaultElevation = 0.dp,
        pressedElevation = 0.dp,
        focusedElevation = 0.dp,
        hoveredElevation = 0.dp,
        draggedElevation = 0.dp,
        disabledElevation = 0.dp,
    )

    if (onClick != null) {
        Card(
            onClick = onClick,
            modifier = modifier,
            shape = shape,
            colors = colors,
            elevation = elevation,
            border = borderStroke,
        ) {
            Column(modifier = Modifier.padding(16.dp), content = content)
        }
    } else {
        Card(
            modifier = modifier,
            shape = shape,
            colors = colors,
            elevation = elevation,
            border = borderStroke,
        ) {
            Column(modifier = Modifier.padding(16.dp), content = content)
        }
    }
}
