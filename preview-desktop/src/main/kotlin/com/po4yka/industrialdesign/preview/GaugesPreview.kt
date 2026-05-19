package com.po4yka.industrialdesign.preview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.po4yka.industrialdesign.IndustrialTokens
import com.po4yka.industrialdesign.components.IndustrialDial
import com.po4yka.industrialdesign.components.IndustrialRadialGauge
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun IndustrialRadialGaugePreview() = PreviewHost {
    IndustrialRadialGauge(
        value = 0.65f,
        modifier = Modifier.size(160.dp),
        label = "Load",
    )
}

@Preview
@Composable
fun IndustrialRadialGaugeFullPreview() = PreviewHost {
    IndustrialRadialGauge(
        value = 1f,
        modifier = Modifier.size(160.dp),
        label = "Max",
    )
}

@Preview
@Composable
fun IndustrialDialPreview() = PreviewHost {
    IndustrialDial(
        value = 0.65f,
        modifier = Modifier.size(160.dp),
        label = "Flow",
    )
}

@Preview
@Composable
fun IndustrialDialLowPreview() = PreviewHost {
    IndustrialDial(
        value = 0.15f,
        modifier = Modifier.size(160.dp),
        label = "Idle",
    )
}

@Preview
@Composable
fun IndustrialGaugesOverviewPreview() = PreviewHost {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(IndustrialTokens.Spacing.md),
    ) {
        IndustrialRadialGauge(
            value = 0.65f,
            modifier = Modifier.size(160.dp),
            label = "Load",
        )
        IndustrialDial(
            value = 0.65f,
            modifier = Modifier.size(160.dp),
            label = "Flow",
        )
    }
}
