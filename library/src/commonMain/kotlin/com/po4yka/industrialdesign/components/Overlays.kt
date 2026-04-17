package com.po4yka.industrialdesign.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.po4yka.industrialdesign.IndustrialTokens

/**
 * Status kind for [IndustrialInlineStatus] — picks the bracketed text color.
 */
enum class StatusKind { Info, Success, Error, Warning, Loading }

/**
 * Modal dialog. Surface + 1dp outline border, 16dp radius, 24dp padding, constrained to [maxWidth].
 * Optional title row with headlineSmall text and a `[ X ]` ghost close button.
 */
@Composable
fun IndustrialDialog(
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    title: String? = null,
    maxWidth: Dp = 480.dp,
    content: @Composable ColumnScope.() -> Unit,
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false),
    ) {
        Surface(
            modifier = modifier.widthIn(max = maxWidth),
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 0.dp,
            shadowElevation = 0.dp,
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
            shape = RoundedCornerShape(IndustrialTokens.Radius.Card),
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                if (title != null) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = title,
                            style = MaterialTheme.typography.headlineSmall,
                            color = MaterialTheme.colorScheme.onSurface,
                        )
                        IndustrialGhostButton(
                            onClick = onDismiss,
                            text = "[ X ]",
                        )
                    }
                }
                content()
            }
        }
    }
}

/**
 * Modal bottom sheet. Surface bg, 2dp handle bar, 16dp top corner radius.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IndustrialBottomSheet(
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
) {
    val sheetState = rememberModalBottomSheetState()
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        modifier = modifier,
        sheetState = sheetState,
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface,
        shape = RoundedCornerShape(
            topStart = IndustrialTokens.Radius.Card,
            topEnd = IndustrialTokens.Radius.Card,
            bottomStart = 0.dp,
            bottomEnd = 0.dp,
        ),
        tonalElevation = 0.dp,
        dragHandle = {
            Box(
                modifier = Modifier
                    .padding(top = 12.dp)
                    .size(width = 32.dp, height = 2.dp)
                    .background(MaterialTheme.colorScheme.onSurfaceVariant),
            )
        },
    ) {
        Column(content = content)
    }
}

/**
 * Flat dropdown menu. `surfaceVariant` background via modifier override; consumers place
 * `DropdownMenuItem`s inside with a 44dp min-height for touch targets.
 */
@Composable
fun IndustrialDropdownMenu(
    expanded: Boolean,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismiss,
        modifier = modifier.background(MaterialTheme.colorScheme.surfaceVariant),
        content = content,
    )
}

/**
 * Inline bracketed status readout. Replaces toasts — renders `[TEXT]` in monospace labelLarge.
 */
@Composable
fun IndustrialInlineStatus(
    text: String,
    modifier: Modifier = Modifier,
    kind: StatusKind = StatusKind.Info,
) {
    val color: Color = when (kind) {
        StatusKind.Info -> MaterialTheme.colorScheme.onSurfaceVariant
        StatusKind.Success -> IndustrialTokens.Accent.Success
        StatusKind.Error -> MaterialTheme.colorScheme.error
        StatusKind.Warning -> IndustrialTokens.Accent.Warning
        StatusKind.Loading -> MaterialTheme.colorScheme.onSurface
    }
    val display = when (kind) {
        StatusKind.Loading -> "[${text.uppercase()}…]"
        else -> "[${text.uppercase()}]"
    }
    Text(
        text = display,
        style = MaterialTheme.typography.labelLarge,
        color = color,
        modifier = modifier,
    )
}
