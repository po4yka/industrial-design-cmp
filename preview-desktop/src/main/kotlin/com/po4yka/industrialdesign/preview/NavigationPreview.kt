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
import com.po4yka.industrialdesign.components.IndustrialBackButton
import com.po4yka.industrialdesign.components.IndustrialBottomBar
import com.po4yka.industrialdesign.components.IndustrialPeriodNav
import com.po4yka.industrialdesign.components.IndustrialTopNav
import com.po4yka.industrialdesign.components.NavItem
import com.po4yka.industrialdesign.components.Separator
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun IndustrialBottomBarPreview() = PreviewHost {
    IndustrialBottomBar(
        items = listOf(
            NavItem(label = "Home"),
            NavItem(label = "Data"),
            NavItem(label = "Alerts"),
            NavItem(label = "More"),
        ),
        selectedIndex = 0,
        onSelect = {},
    )
}

@Preview
@Composable
fun IndustrialTopNavPipePreview() = PreviewHost {
    IndustrialTopNav(
        items = listOf(
            NavItem(label = "Overview"),
            NavItem(label = "Telemetry"),
            NavItem(label = "Logs"),
        ),
        selectedIndex = 1,
        onSelect = {},
        separator = Separator.Pipe,
    )
}

@Preview
@Composable
fun IndustrialTopNavBracketPreview() = PreviewHost {
    IndustrialTopNav(
        items = listOf(
            NavItem(label = "Overview"),
            NavItem(label = "Telemetry"),
            NavItem(label = "Logs"),
        ),
        selectedIndex = 0,
        onSelect = {},
        separator = Separator.Bracket,
    )
}

@Preview
@Composable
fun IndustrialBackButtonPreview() = PreviewHost {
    IndustrialBackButton(onClick = {})
}

@Preview
@Composable
fun IndustrialPeriodNavPreview() = PreviewHost {
    IndustrialPeriodNav(
        label = "Mar 2026",
        onPrev = {},
        onNext = {},
    )
}

@Preview
@Composable
fun IndustrialPeriodNavBoundaryPreview() = PreviewHost {
    IndustrialPeriodNav(
        label = "Jan 2026",
        onPrev = {},
        onNext = {},
        prevEnabled = false,
    )
}

@Preview
@Composable
fun IndustrialNavigationOverviewPreview() = PreviewHost {
    Column {
        IndustrialBottomBar(
            items = listOf(
                NavItem(label = "Home"),
                NavItem(label = "Data"),
                NavItem(label = "Alerts"),
                NavItem(label = "More"),
            ),
            selectedIndex = 2,
            onSelect = {},
        )
        Spacer(Modifier.height(IndustrialTokens.Spacing.md))
        IndustrialTopNav(
            items = listOf(
                NavItem(label = "Overview"),
                NavItem(label = "Telemetry"),
                NavItem(label = "Logs"),
            ),
            selectedIndex = 1,
            onSelect = {},
            separator = Separator.Pipe,
        )
        Spacer(Modifier.height(IndustrialTokens.Spacing.md))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(IndustrialTokens.Spacing.md),
        ) {
            IndustrialBackButton(onClick = {})
            IndustrialPeriodNav(label = "Mar 2026", onPrev = {}, onNext = {})
        }
    }
}
