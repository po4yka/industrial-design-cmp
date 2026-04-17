package com.po4yka.industrialdesign.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.po4yka.industrialdesign.IndustrialTokens

/**
 * Column definition for [IndustrialTable]. `mono = true` switches the cell
 * style to Space Mono (`labelLarge`) for numeric readouts.
 */
data class TableColumn(
    val header: String,
    val alignment: Alignment.Horizontal = Alignment.Start,
    val weight: Float = 1f,
    val mono: Boolean = false,
)

/**
 * Industrial data table. Header is separated by a 1dp outline border, rows
 * by outlineVariant horizontal dividers. The optional active row gains a
 * `surfaceVariant` background and a 2dp tertiary left accent strip.
 */
@Composable
fun IndustrialTable(
    columns: List<TableColumn>,
    rows: List<List<String>>,
    modifier: Modifier = Modifier,
    activeRowIndex: Int? = null,
    onRowClick: ((Int) -> Unit)? = null,
) {
    val outline = MaterialTheme.colorScheme.outline
    val outlineVariant = MaterialTheme.colorScheme.outlineVariant
    val accent = MaterialTheme.colorScheme.tertiary
    val surfaceVariant = MaterialTheme.colorScheme.surfaceVariant
    val onSurfaceVariant = MaterialTheme.colorScheme.onSurfaceVariant
    val onSurface = MaterialTheme.colorScheme.onSurface

    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = IndustrialTokens.Spacing.md, vertical = IndustrialTokens.Spacing.sm + IndustrialTokens.Spacing.xs),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            columns.forEach { column ->
                Box(
                    modifier = Modifier.weight(column.weight),
                    contentAlignment = column.alignment.toContentAlignment(),
                ) {
                    Text(
                        text = column.header.uppercase(),
                        style = MaterialTheme.typography.labelSmall,
                        color = onSurfaceVariant,
                    )
                }
            }
        }
        HorizontalDivider(thickness = 1.dp, color = outline)
        rows.forEachIndexed { rowIndex, rowCells ->
            val isActive = rowIndex == activeRowIndex
            val rowBackground = if (isActive) surfaceVariant else Color.Transparent
            val rowClickModifier = if (onRowClick != null) {
                Modifier.clickable { onRowClick(rowIndex) }
            } else {
                Modifier
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min)
                    .background(rowBackground)
                    .then(rowClickModifier),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier = Modifier
                        .width(2.dp)
                        .fillMaxHeight()
                        .background(if (isActive) accent else Color.Transparent),
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = IndustrialTokens.Spacing.md, vertical = IndustrialTokens.Spacing.sm + IndustrialTokens.Spacing.xs),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    columns.forEachIndexed { colIndex, column ->
                        val cellText = rowCells.getOrNull(colIndex).orEmpty()
                        Box(
                            modifier = Modifier.weight(column.weight),
                            contentAlignment = column.alignment.toContentAlignment(),
                        ) {
                            Text(
                                text = cellText,
                                style = if (column.mono) {
                                    MaterialTheme.typography.labelLarge
                                } else {
                                    MaterialTheme.typography.bodyMedium
                                },
                                color = onSurface,
                            )
                        }
                    }
                }
            }
            if (rowIndex < rows.lastIndex) {
                HorizontalDivider(thickness = 1.dp, color = outlineVariant)
            }
        }
    }
}

private fun Alignment.Horizontal.toContentAlignment(): Alignment = when (this) {
    Alignment.End -> Alignment.CenterEnd
    Alignment.CenterHorizontally -> Alignment.Center
    else -> Alignment.CenterStart
}
