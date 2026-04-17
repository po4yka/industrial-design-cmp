package com.po4yka.industrialdesign.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.po4yka.industrialdesign.IndustrialTokens

/**
 * Tag corner style. `Pill` uses the fully rounded token, `Technical` uses
 * the 4dp technical radius for a mechanical readout feel.
 */
enum class TagShape { Pill, Technical }

/**
 * Industrial tag / chip. Border-only, no fill. Active swaps the border and
 * text to `primary`. When `onClick` is non-null the tag becomes clickable.
 */
@Composable
fun IndustrialTag(
    text: String,
    modifier: Modifier = Modifier,
    active: Boolean = false,
    shape: TagShape = TagShape.Pill,
    onClick: (() -> Unit)? = null,
) {
    val borderColor = if (active) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline
    val textColor = if (active) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
    val cornerRadius = when (shape) {
        TagShape.Pill -> IndustrialTokens.Radius.Pill
        TagShape.Technical -> IndustrialTokens.Radius.Technical
    }
    val roundedShape = RoundedCornerShape(cornerRadius)
    val clickableModifier = if (onClick != null) {
        Modifier.clip(roundedShape).clickable(onClick = onClick)
    } else {
        Modifier
    }
    Box(
        modifier = modifier
            .then(clickableModifier)
            .border(BorderStroke(1.dp, borderColor), roundedShape)
            .padding(horizontal = 12.dp, vertical = 4.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text.uppercase(),
            style = MaterialTheme.typography.labelSmall,
            color = textColor,
        )
    }
}
