package com.po4yka.industrialdesign.preview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.po4yka.industrialdesign.IndustrialTokens
import com.po4yka.industrialdesign.components.ControlShape
import com.po4yka.industrialdesign.components.IndustrialCheckbox
import com.po4yka.industrialdesign.components.IndustrialSegmentedControl
import com.po4yka.industrialdesign.components.IndustrialSwitch
import com.po4yka.industrialdesign.components.IndustrialTag
import com.po4yka.industrialdesign.components.TagShape
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun IndustrialSegmentedControlTwoPreview() = PreviewHost {
    IndustrialSegmentedControl(
        options = listOf("On", "Off"),
        selectedIndex = 0,
        onSelect = {},
    )
}

@Preview
@Composable
fun IndustrialSegmentedControlThreePreview() = PreviewHost {
    IndustrialSegmentedControl(
        options = listOf("Day", "Week", "Month"),
        selectedIndex = 1,
        onSelect = {},
    )
}

@Preview
@Composable
fun IndustrialSegmentedControlCompactPreview() = PreviewHost {
    IndustrialSegmentedControl(
        options = listOf("Q1", "Q2", "Q3", "Q4"),
        selectedIndex = 2,
        onSelect = {},
        shape = ControlShape.Compact,
    )
}

@Preview
@Composable
fun IndustrialSwitchOnPreview() = PreviewHost {
    IndustrialSwitch(checked = true, onCheckedChange = {})
}

@Preview
@Composable
fun IndustrialSwitchOffPreview() = PreviewHost {
    IndustrialSwitch(checked = false, onCheckedChange = {})
}

@Preview
@Composable
fun IndustrialSwitchDisabledPreview() = PreviewHost {
    IndustrialSwitch(checked = true, onCheckedChange = {}, enabled = false)
}

@Preview
@Composable
fun IndustrialCheckboxOnPreview() = PreviewHost {
    IndustrialCheckbox(checked = true, onCheckedChange = {})
}

@Preview
@Composable
fun IndustrialCheckboxOffPreview() = PreviewHost {
    IndustrialCheckbox(checked = false, onCheckedChange = {})
}

@Preview
@Composable
fun IndustrialCheckboxDisabledPreview() = PreviewHost {
    IndustrialCheckbox(checked = true, onCheckedChange = {}, enabled = false)
}

@Preview
@Composable
fun IndustrialTagPillPreview() = PreviewHost {
    IndustrialTag(text = "Pill", shape = TagShape.Pill)
}

@Preview
@Composable
fun IndustrialTagTechnicalPreview() = PreviewHost {
    IndustrialTag(text = "Technical", shape = TagShape.Technical)
}

@Preview
@Composable
fun IndustrialTagActivePreview() = PreviewHost {
    IndustrialTag(text = "Active", active = true)
}

@Preview
@Composable
fun IndustrialTagsOverviewPreview() = PreviewHost {
    Column {
        Row(horizontalArrangement = Arrangement.spacedBy(IndustrialTokens.Spacing.sm)) {
            IndustrialTag(text = "Pill", shape = TagShape.Pill)
            IndustrialTag(text = "Technical", shape = TagShape.Technical)
            IndustrialTag(text = "Active", active = true)
            IndustrialTag(text = "Inactive", active = false)
        }
    }
}

@Preview
@Composable
fun IndustrialTogglesOverviewPreview() = PreviewHost {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(IndustrialTokens.Spacing.md),
        ) {
            IndustrialSwitch(checked = true, onCheckedChange = {})
            IndustrialSwitch(checked = false, onCheckedChange = {})
            IndustrialSwitch(checked = true, onCheckedChange = {}, enabled = false)
        }
        Spacer(Modifier.height(IndustrialTokens.Spacing.md))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(IndustrialTokens.Spacing.md),
        ) {
            IndustrialCheckbox(checked = true, onCheckedChange = {})
            IndustrialCheckbox(checked = false, onCheckedChange = {})
            IndustrialCheckbox(checked = true, onCheckedChange = {}, enabled = false)
        }
    }
}
