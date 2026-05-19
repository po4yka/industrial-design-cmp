package com.po4yka.industrialdesign.preview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.po4yka.industrialdesign.IndustrialTokens
import com.po4yka.industrialdesign.components.IndustrialDestructiveButton
import com.po4yka.industrialdesign.components.IndustrialGhostButton
import com.po4yka.industrialdesign.components.IndustrialPrimaryButton
import com.po4yka.industrialdesign.components.IndustrialSecondaryButton
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun IndustrialPrimaryButtonPreview() = PreviewHost {
    IndustrialPrimaryButton(onClick = {}, text = "Primary")
}

@Preview
@Composable
fun IndustrialPrimaryButtonDisabledPreview() = PreviewHost {
    IndustrialPrimaryButton(onClick = {}, text = "Primary", enabled = false)
}

@Preview
@Composable
fun IndustrialSecondaryButtonPreview() = PreviewHost {
    IndustrialSecondaryButton(onClick = {}, text = "Secondary")
}

@Preview
@Composable
fun IndustrialSecondaryButtonDisabledPreview() = PreviewHost {
    IndustrialSecondaryButton(onClick = {}, text = "Secondary", enabled = false)
}

@Preview
@Composable
fun IndustrialGhostButtonPreview() = PreviewHost {
    IndustrialGhostButton(onClick = {}, text = "Ghost")
}

@Preview
@Composable
fun IndustrialGhostButtonDisabledPreview() = PreviewHost {
    IndustrialGhostButton(onClick = {}, text = "Ghost", enabled = false)
}

@Preview
@Composable
fun IndustrialDestructiveButtonPreview() = PreviewHost {
    IndustrialDestructiveButton(onClick = {}, text = "Destructive")
}

@Preview
@Composable
fun IndustrialDestructiveButtonDisabledPreview() = PreviewHost {
    IndustrialDestructiveButton(onClick = {}, text = "Destructive", enabled = false)
}

@Preview
@Composable
fun IndustrialButtonGroupPreview() = PreviewHost {
    Column {
        Row(horizontalArrangement = Arrangement.spacedBy(IndustrialTokens.Spacing.sm)) {
            IndustrialPrimaryButton(onClick = {}, text = "Primary")
            IndustrialSecondaryButton(onClick = {}, text = "Secondary")
        }
        Spacer(Modifier.height(IndustrialTokens.Spacing.sm))
        Row(horizontalArrangement = Arrangement.spacedBy(IndustrialTokens.Spacing.sm)) {
            IndustrialGhostButton(onClick = {}, text = "Ghost")
            IndustrialDestructiveButton(onClick = {}, text = "Destructive")
        }
        Spacer(Modifier.height(IndustrialTokens.Spacing.md))
        Text(
            text = "DISABLED",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Spacer(Modifier.height(IndustrialTokens.Spacing.sm))
        Row(horizontalArrangement = Arrangement.spacedBy(IndustrialTokens.Spacing.sm)) {
            IndustrialPrimaryButton(onClick = {}, text = "Primary", enabled = false)
            IndustrialSecondaryButton(onClick = {}, text = "Secondary", enabled = false)
        }
    }
}

@Preview
@Composable
fun IndustrialButtonGroupLightPreview() = PreviewHost(darkTheme = false) {
    Row(horizontalArrangement = Arrangement.spacedBy(IndustrialTokens.Spacing.sm)) {
        IndustrialPrimaryButton(onClick = {}, text = "Primary")
        IndustrialSecondaryButton(onClick = {}, text = "Secondary")
        IndustrialGhostButton(onClick = {}, text = "Ghost")
        IndustrialDestructiveButton(onClick = {}, text = "Destructive")
    }
}
