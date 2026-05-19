package com.po4yka.industrialdesign.preview

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.po4yka.industrialdesign.IndustrialTokens
import com.po4yka.industrialdesign.components.IndustrialLinearProgress
import com.po4yka.industrialdesign.components.IndustrialProgressReadout
import com.po4yka.industrialdesign.components.IndustrialSegmentedProgressBar
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun IndustrialSegmentedProgressBarHeroPreview() = PreviewHost {
    IndustrialSegmentedProgressBar(
        progress = 0.65f,
        modifier = Modifier.fillMaxWidth(),
        height = 20.dp,
    )
}

@Preview
@Composable
fun IndustrialSegmentedProgressBarStandardPreview() = PreviewHost {
    IndustrialSegmentedProgressBar(
        progress = 0.40f,
        modifier = Modifier.fillMaxWidth(),
        height = 12.dp,
    )
}

@Preview
@Composable
fun IndustrialSegmentedProgressBarCompactPreview() = PreviewHost {
    IndustrialSegmentedProgressBar(
        progress = 0.18f,
        modifier = Modifier.fillMaxWidth(),
        height = 6.dp,
    )
}

@Preview
@Composable
fun IndustrialSegmentedProgressBarOverflowPreview() = PreviewHost {
    IndustrialSegmentedProgressBar(
        progress = 1.2f,
        modifier = Modifier.fillMaxWidth(),
        height = 12.dp,
        fillColor = IndustrialTokens.Accent.Signal,
    )
}

@Preview
@Composable
fun IndustrialProgressReadoutPreview() = PreviewHost {
    IndustrialProgressReadout(label = "Hero", value = "65", unit = "%")
}

@Preview
@Composable
fun IndustrialLinearProgressPreview() = PreviewHost {
    IndustrialLinearProgress(
        progress = 0.42f,
        modifier = Modifier.fillMaxWidth(),
    )
}

@Preview
@Composable
fun IndustrialProgressOverviewPreview() = PreviewHost {
    Column {
        IndustrialProgressReadout(label = "Hero", value = "65", unit = "%")
        Spacer(Modifier.height(IndustrialTokens.Spacing.xs))
        IndustrialSegmentedProgressBar(
            progress = 0.65f,
            modifier = Modifier.fillMaxWidth(),
            height = 20.dp,
        )
        Spacer(Modifier.height(IndustrialTokens.Spacing.md))
        IndustrialProgressReadout(label = "Standard", value = "40", unit = "%")
        Spacer(Modifier.height(IndustrialTokens.Spacing.xs))
        IndustrialSegmentedProgressBar(
            progress = 0.40f,
            modifier = Modifier.fillMaxWidth(),
            height = 12.dp,
        )
        Spacer(Modifier.height(IndustrialTokens.Spacing.md))
        IndustrialProgressReadout(label = "Compact", value = "18", unit = "%")
        Spacer(Modifier.height(IndustrialTokens.Spacing.xs))
        IndustrialSegmentedProgressBar(
            progress = 0.18f,
            modifier = Modifier.fillMaxWidth(),
            height = 6.dp,
        )
        Spacer(Modifier.height(IndustrialTokens.Spacing.md))
        IndustrialLinearProgress(
            progress = 0.42f,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
