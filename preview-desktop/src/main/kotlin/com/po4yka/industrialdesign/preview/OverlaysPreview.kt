package com.po4yka.industrialdesign.preview

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.po4yka.industrialdesign.IndustrialTokens
import com.po4yka.industrialdesign.components.IndustrialDestructiveButton
import com.po4yka.industrialdesign.components.IndustrialGhostButton
import com.po4yka.industrialdesign.components.IndustrialInlineStatus
import com.po4yka.industrialdesign.components.IndustrialPrimaryButton
import com.po4yka.industrialdesign.components.StatusKind
import androidx.compose.ui.tooling.preview.Preview

// IndustrialDialog / BottomSheet / DropdownMenu attach to platform Window or popup
// primitives that ImageComposeScene does not host. These previews replicate the
// production surface styling (same Surface + border + radius + spacing) so PNG
// baselines accurately reflect what users see when those composables open.

@Preview
@Composable
fun IndustrialDialogContentPreview() = PreviewHost {
    Surface(
        modifier = Modifier.widthIn(max = 480.dp),
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
        shape = RoundedCornerShape(IndustrialTokens.Radius.Card),
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Confirm",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                IndustrialGhostButton(onClick = {}, text = "[ X ]")
            }
            Text(
                text = "Shut down line A?",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
            )
            Row(horizontalArrangement = Arrangement.spacedBy(IndustrialTokens.Spacing.sm)) {
                IndustrialGhostButton(onClick = {}, text = "Cancel")
                IndustrialDestructiveButton(onClick = {}, text = "Shutdown")
            }
        }
    }
}

@Preview
@Composable
fun IndustrialBottomSheetContentPreview() = PreviewHost {
    Surface(
        color = MaterialTheme.colorScheme.surface,
        shape = RoundedCornerShape(
            topStart = IndustrialTokens.Radius.Card,
            topEnd = IndustrialTokens.Radius.Card,
            bottomStart = 0.dp,
            bottomEnd = 0.dp,
        ),
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 12.dp)
                    .size(width = 32.dp, height = 2.dp)
                    .background(MaterialTheme.colorScheme.onSurfaceVariant),
            )
            Column(modifier = Modifier.padding(24.dp)) {
                Text(
                    text = "SHEET TITLE",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                Spacer(Modifier.height(IndustrialTokens.Spacing.sm))
                Text(
                    text = "Bottom sheet content area with surface background.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Spacer(Modifier.height(IndustrialTokens.Spacing.md))
                IndustrialPrimaryButton(onClick = {}, text = "Dismiss")
            }
        }
    }
}

@Preview
@Composable
fun IndustrialDropdownMenuContentPreview() = PreviewHost {
    Column(modifier = Modifier.background(MaterialTheme.colorScheme.surfaceVariant)) {
        DropdownMenuItem(text = { Text("OPTION A") }, onClick = {})
        DropdownMenuItem(text = { Text("OPTION B") }, onClick = {})
        DropdownMenuItem(text = { Text("OPTION C") }, onClick = {})
    }
}

@Preview
@Composable
fun IndustrialInlineStatusInfoPreview() = PreviewHost {
    IndustrialInlineStatus(text = "Info", kind = StatusKind.Info)
}

@Preview
@Composable
fun IndustrialInlineStatusSuccessPreview() = PreviewHost {
    IndustrialInlineStatus(text = "Success", kind = StatusKind.Success)
}

@Preview
@Composable
fun IndustrialInlineStatusErrorPreview() = PreviewHost {
    IndustrialInlineStatus(text = "Error", kind = StatusKind.Error)
}

@Preview
@Composable
fun IndustrialInlineStatusWarningPreview() = PreviewHost {
    IndustrialInlineStatus(text = "Warning", kind = StatusKind.Warning)
}

@Preview
@Composable
fun IndustrialInlineStatusLoadingPreview() = PreviewHost {
    IndustrialInlineStatus(text = "Loading", kind = StatusKind.Loading)
}

@Preview
@Composable
fun IndustrialInlineStatusOverviewPreview() = PreviewHost {
    Column {
        Row(horizontalArrangement = Arrangement.spacedBy(IndustrialTokens.Spacing.sm)) {
            IndustrialInlineStatus(text = "Info", kind = StatusKind.Info)
            IndustrialInlineStatus(text = "Success", kind = StatusKind.Success)
            IndustrialInlineStatus(text = "Error", kind = StatusKind.Error)
        }
        Spacer(Modifier.height(IndustrialTokens.Spacing.sm))
        Row(horizontalArrangement = Arrangement.spacedBy(IndustrialTokens.Spacing.sm)) {
            IndustrialInlineStatus(text = "Warning", kind = StatusKind.Warning)
            IndustrialInlineStatus(text = "Loading", kind = StatusKind.Loading)
        }
    }
}
