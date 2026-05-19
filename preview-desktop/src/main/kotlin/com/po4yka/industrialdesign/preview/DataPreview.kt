package com.po4yka.industrialdesign.preview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.po4yka.industrialdesign.IndustrialTokens
import com.po4yka.industrialdesign.components.BarDatum
import com.po4yka.industrialdesign.components.IndustrialBarChart
import com.po4yka.industrialdesign.components.IndustrialCard
import com.po4yka.industrialdesign.components.IndustrialDotGrid
import com.po4yka.industrialdesign.components.IndustrialHierarchicalRow
import com.po4yka.industrialdesign.components.IndustrialStatRow
import com.po4yka.industrialdesign.components.IndustrialTable
import com.po4yka.industrialdesign.components.IndustrialWidget
import com.po4yka.industrialdesign.components.TableColumn
import com.po4yka.industrialdesign.components.Trend
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun IndustrialCardPlainPreview() = PreviewHost {
    IndustrialCard {
        Text(
            text = "PLAIN CARD",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Spacer(Modifier.height(IndustrialTokens.Spacing.sm))
        Text(
            text = "Flat surface with outlineVariant border.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
        )
    }
}

@Preview
@Composable
fun IndustrialCardRaisedPreview() = PreviewHost {
    IndustrialCard(raised = true) {
        Text(
            text = "RAISED CARD",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Spacer(Modifier.height(IndustrialTokens.Spacing.sm))
        Text(
            text = "surfaceVariant container color.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
        )
    }
}

@Preview
@Composable
fun IndustrialStatRowPreview() = PreviewHost {
    Column {
        IndustrialStatRow(
            label = "RPM",
            value = "3 220",
            unit = "rpm",
            status = IndustrialTokens.Accent.Success,
            trend = Trend.Up,
        )
        IndustrialStatRow(
            label = "Temp",
            value = "87",
            unit = "°C",
            status = IndustrialTokens.Accent.Warning,
            trend = Trend.Flat,
        )
        IndustrialStatRow(
            label = "Pressure",
            value = "12.6",
            unit = "bar",
            status = IndustrialTokens.Accent.Signal,
            trend = Trend.Down,
        )
    }
}

@Preview
@Composable
fun IndustrialHierarchicalRowPreview() = PreviewHost {
    Column {
        IndustrialHierarchicalRow(
            label = "Line A",
            value = "OK",
            level = 0,
            status = IndustrialTokens.Accent.Success,
        )
        IndustrialHierarchicalRow(label = "Pump 1", value = "87", level = 1, unit = "%")
        IndustrialHierarchicalRow(label = "Pump 2", value = "72", level = 1, unit = "%")
        IndustrialHierarchicalRow(
            label = "Valve",
            value = "ALERT",
            level = 2,
            status = IndustrialTokens.Accent.Signal,
        )
    }
}

@Preview
@Composable
fun IndustrialWidgetPreview() = PreviewHost {
    Row(horizontalArrangement = Arrangement.spacedBy(IndustrialTokens.Spacing.sm)) {
        IndustrialWidget(
            category = "RPM",
            hero = "3220",
            unit = "rpm",
            trend = "+4.2%",
        )
        IndustrialWidget(
            category = "Temp",
            hero = "87",
            unit = "°C",
            trend = "+1.1%",
        )
        IndustrialWidget(
            category = "Fuel",
            hero = "42",
            unit = "%",
            trend = "-3.8%",
        )
    }
}

@Preview
@Composable
fun IndustrialBarChartPreview() = PreviewHost {
    IndustrialBarChart(
        data = listOf(
            BarDatum(label = "Mon", value = 42f),
            BarDatum(label = "Tue", value = 78f),
            BarDatum(label = "Wed", value = 55f),
            BarDatum(label = "Thu", value = 91f),
            BarDatum(label = "Fri", value = 63f),
            BarDatum(label = "Sat", value = 30f),
            BarDatum(label = "Sun", value = 48f),
            BarDatum(label = "Avg", value = 58f),
        ),
        modifier = Modifier.fillMaxWidth(),
    )
}

@Preview
@Composable
fun IndustrialDotGridPreview() = PreviewHost {
    IndustrialDotGrid(
        rows = 10,
        cols = 10,
        values = { r, c -> ((r * 7 + c * 11) % 10) / 9f },
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp),
    )
}

@Preview
@Composable
fun IndustrialTablePreview() = PreviewHost {
    IndustrialTable(
        columns = listOf(
            TableColumn(header = "ID", mono = true, weight = 1f),
            TableColumn(header = "Unit", weight = 1.2f),
            TableColumn(header = "Status", weight = 1f),
            TableColumn(
                header = "Output",
                alignment = Alignment.End,
                weight = 1f,
                mono = true,
            ),
        ),
        rows = listOf(
            listOf("A-01", "Pump", "OK", "128"),
            listOf("A-02", "Valve", "OK", "094"),
            listOf("A-03", "Sensor", "WARN", "054"),
            listOf("A-04", "Motor", "OK", "212"),
            listOf("A-05", "Heater", "FAIL", "000"),
        ),
        activeRowIndex = 2,
    )
}
