package com.po4yka.industrialdesign.demo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.po4yka.industrialdesign.IndustrialTheme
import com.po4yka.industrialdesign.IndustrialTokens
import com.po4yka.industrialdesign.components.BarDatum
import com.po4yka.industrialdesign.components.ControlShape
import com.po4yka.industrialdesign.components.IndustrialBackButton
import com.po4yka.industrialdesign.components.IndustrialBarChart
import com.po4yka.industrialdesign.components.IndustrialBottomBar
import com.po4yka.industrialdesign.components.IndustrialBottomSheet
import com.po4yka.industrialdesign.components.IndustrialCard
import com.po4yka.industrialdesign.components.IndustrialCheckbox
import com.po4yka.industrialdesign.components.IndustrialDestructiveButton
import com.po4yka.industrialdesign.components.IndustrialDial
import com.po4yka.industrialdesign.components.IndustrialDialog
import com.po4yka.industrialdesign.components.IndustrialDotGrid
import com.po4yka.industrialdesign.components.IndustrialDropdownMenu
import com.po4yka.industrialdesign.components.IndustrialGhostButton
import com.po4yka.industrialdesign.components.IndustrialHierarchicalRow
import com.po4yka.industrialdesign.components.IndustrialInlineStatus
import com.po4yka.industrialdesign.components.IndustrialLinearProgress
import com.po4yka.industrialdesign.components.IndustrialMonoTextField
import com.po4yka.industrialdesign.components.IndustrialPeriodNav
import com.po4yka.industrialdesign.components.IndustrialPrimaryButton
import com.po4yka.industrialdesign.components.IndustrialProgressReadout
import com.po4yka.industrialdesign.components.IndustrialRadialGauge
import com.po4yka.industrialdesign.components.IndustrialSecondaryButton
import com.po4yka.industrialdesign.components.IndustrialSegmentedControl
import com.po4yka.industrialdesign.components.IndustrialSegmentedProgressBar
import com.po4yka.industrialdesign.components.IndustrialStatRow
import com.po4yka.industrialdesign.components.IndustrialSwitch
import com.po4yka.industrialdesign.components.IndustrialTable
import com.po4yka.industrialdesign.components.IndustrialTag
import com.po4yka.industrialdesign.components.IndustrialTextField
import com.po4yka.industrialdesign.components.IndustrialTopNav
import com.po4yka.industrialdesign.components.IndustrialWidget
import com.po4yka.industrialdesign.components.NavItem
import com.po4yka.industrialdesign.components.Separator
import com.po4yka.industrialdesign.components.StatusKind
import com.po4yka.industrialdesign.components.TableColumn
import com.po4yka.industrialdesign.components.TagShape
import com.po4yka.industrialdesign.components.Trend
import com.po4yka.industrialdesign.foundation.ArrowDirection
import com.po4yka.industrialdesign.foundation.IndustrialArrowIcon
import com.po4yka.industrialdesign.foundation.IndustrialCheckIcon
import com.po4yka.industrialdesign.foundation.IndustrialCloseIcon
import com.po4yka.industrialdesign.foundation.IndustrialDotIcon
import com.po4yka.industrialdesign.foundation.IndustrialIconSpec

@Composable
fun DemoApp() {
    var darkTheme by remember { mutableStateOf(true) }

    IndustrialTheme(darkTheme = darkTheme) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
            ) {
                val themeOptions = listOf("DARK", "LIGHT")
                val themeIndex = if (darkTheme) 0 else 1
                IndustrialSegmentedControl(
                    options = themeOptions,
                    selectedIndex = themeIndex,
                    onSelect = { darkTheme = it == 0 },
                )

                Spacer(Modifier.height(IndustrialTokens.Spacing.lg))

                ButtonsSection()
                Spacer(Modifier.height(IndustrialTokens.Spacing.lg))

                CardsSection()
                Spacer(Modifier.height(IndustrialTokens.Spacing.lg))

                InputsSection()
                Spacer(Modifier.height(IndustrialTokens.Spacing.lg))

                StatRowsSection()
                Spacer(Modifier.height(IndustrialTokens.Spacing.lg))

                NavigationSection()
                Spacer(Modifier.height(IndustrialTokens.Spacing.lg))

                TagsSection()
                Spacer(Modifier.height(IndustrialTokens.Spacing.lg))

                TogglesSection()
                Spacer(Modifier.height(IndustrialTokens.Spacing.lg))

                SegmentedControlSection()
                Spacer(Modifier.height(IndustrialTokens.Spacing.lg))

                TableSection()
                Spacer(Modifier.height(IndustrialTokens.Spacing.lg))

                SegmentedProgressSection()
                Spacer(Modifier.height(IndustrialTokens.Spacing.lg))

                LinearProgressSection()
                Spacer(Modifier.height(IndustrialTokens.Spacing.lg))

                BarChartSection()
                Spacer(Modifier.height(IndustrialTokens.Spacing.lg))

                RadialGaugeSection()
                Spacer(Modifier.height(IndustrialTokens.Spacing.lg))

                DotGridSection()
                Spacer(Modifier.height(IndustrialTokens.Spacing.lg))

                WidgetsSection()
                Spacer(Modifier.height(IndustrialTokens.Spacing.lg))

                OverlaysSection()
                Spacer(Modifier.height(IndustrialTokens.Spacing.lg))

                InlineStatusSection()
                Spacer(Modifier.height(IndustrialTokens.Spacing.lg))

                IconsSection()
                Spacer(Modifier.height(IndustrialTokens.Spacing.lg))
            }
        }
    }
}

@Composable
private fun SectionHeader(title: String) {
    Text(
        text = title.uppercase(),
        style = MaterialTheme.typography.headlineSmall,
        color = MaterialTheme.colorScheme.onSurface,
        modifier = Modifier.padding(bottom = 24.dp),
    )
}

@Composable
private fun ButtonsSection() {
    Column {
        SectionHeader("Buttons")
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
        Spacer(Modifier.height(IndustrialTokens.Spacing.sm))
        Row(horizontalArrangement = Arrangement.spacedBy(IndustrialTokens.Spacing.sm)) {
            IndustrialGhostButton(onClick = {}, text = "Ghost", enabled = false)
            IndustrialDestructiveButton(onClick = {}, text = "Destructive", enabled = false)
        }
    }
}

@Composable
private fun CardsSection() {
    Column {
        SectionHeader("Cards")
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
        Spacer(Modifier.height(IndustrialTokens.Spacing.sm))
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
        Spacer(Modifier.height(IndustrialTokens.Spacing.sm))
        IndustrialCard {
            Text(
                text = "CARD WITH CONTENT",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Spacer(Modifier.height(IndustrialTokens.Spacing.sm))
            IndustrialStatRow(label = "Output", value = "128", unit = "kW")
            IndustrialStatRow(label = "Runtime", value = "04:12", unit = "h")
        }
    }
}

@Composable
private fun InputsSection() {
    var text by remember { mutableStateOf("") }
    var mono by remember { mutableStateOf("SERIAL-09812") }
    var invalid by remember { mutableStateOf("bad@") }

    Column {
        SectionHeader("Inputs")
        IndustrialTextField(
            value = text,
            onValueChange = { text = it },
            label = "Operator",
            placeholder = "Enter name",
        )
        Spacer(Modifier.height(IndustrialTokens.Spacing.md))
        IndustrialMonoTextField(
            value = mono,
            onValueChange = { mono = it },
            label = "Serial",
            placeholder = "SERIAL-XXXXX",
        )
        Spacer(Modifier.height(IndustrialTokens.Spacing.md))
        IndustrialTextField(
            value = invalid,
            onValueChange = { invalid = it },
            label = "Email",
            placeholder = "ops@site.io",
            isError = true,
            errorMessage = "Invalid email address",
        )
    }
}

@Composable
private fun StatRowsSection() {
    Column {
        SectionHeader("Stat Rows")
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
        Spacer(Modifier.height(IndustrialTokens.Spacing.sm))
        IndustrialHierarchicalRow(
            label = "Line A",
            value = "OK",
            level = 0,
            status = IndustrialTokens.Accent.Success,
        )
        IndustrialHierarchicalRow(
            label = "Pump 1",
            value = "87",
            level = 1,
            unit = "%",
        )
        IndustrialHierarchicalRow(
            label = "Pump 2",
            value = "72",
            level = 1,
            unit = "%",
        )
        IndustrialHierarchicalRow(
            label = "Valve",
            value = "ALERT",
            level = 2,
            status = IndustrialTokens.Accent.Signal,
        )
    }
}

@Composable
private fun NavigationSection() {
    var bottomIndex by remember { mutableStateOf(0) }
    var topIndex by remember { mutableStateOf(1) }

    Column {
        SectionHeader("Navigation")
        IndustrialBottomBar(
            items = listOf(
                NavItem(label = "Home"),
                NavItem(label = "Data"),
                NavItem(label = "Alerts"),
                NavItem(label = "More"),
            ),
            selectedIndex = bottomIndex,
            onSelect = { bottomIndex = it },
        )
        Spacer(Modifier.height(IndustrialTokens.Spacing.md))
        IndustrialTopNav(
            items = listOf(
                NavItem(label = "Overview"),
                NavItem(label = "Telemetry"),
                NavItem(label = "Logs"),
            ),
            selectedIndex = topIndex,
            onSelect = { topIndex = it },
            separator = Separator.Pipe,
        )
        Spacer(Modifier.height(IndustrialTokens.Spacing.md))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(IndustrialTokens.Spacing.md),
        ) {
            IndustrialBackButton(onClick = {})
            IndustrialPeriodNav(
                label = "Mar 2026",
                onPrev = {},
                onNext = {},
            )
        }
    }
}

@Composable
private fun TagsSection() {
    var active by remember { mutableStateOf(true) }
    Column {
        SectionHeader("Tags")
        Row(horizontalArrangement = Arrangement.spacedBy(IndustrialTokens.Spacing.sm)) {
            IndustrialTag(text = "Pill", shape = TagShape.Pill)
            IndustrialTag(text = "Technical", shape = TagShape.Technical)
            IndustrialTag(text = "Active", active = true)
            IndustrialTag(text = "Inactive", active = false)
            IndustrialTag(
                text = if (active) "On" else "Off",
                active = active,
                onClick = { active = !active },
            )
        }
    }
}

@Composable
private fun TogglesSection() {
    var switch1 by remember { mutableStateOf(true) }
    var switch2 by remember { mutableStateOf(false) }
    var check1 by remember { mutableStateOf(true) }
    var check2 by remember { mutableStateOf(false) }

    Column {
        SectionHeader("Toggles")
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(IndustrialTokens.Spacing.md),
        ) {
            IndustrialSwitch(checked = switch1, onCheckedChange = { switch1 = it })
            IndustrialSwitch(checked = switch2, onCheckedChange = { switch2 = it })
            IndustrialSwitch(checked = true, onCheckedChange = {}, enabled = false)
        }
        Spacer(Modifier.height(IndustrialTokens.Spacing.md))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(IndustrialTokens.Spacing.md),
        ) {
            IndustrialCheckbox(checked = check1, onCheckedChange = { check1 = it })
            IndustrialCheckbox(checked = check2, onCheckedChange = { check2 = it })
            IndustrialCheckbox(checked = true, onCheckedChange = {}, enabled = false)
        }
    }
}

@Composable
private fun SegmentedControlSection() {
    var twoIndex by remember { mutableStateOf(0) }
    var threeIndex by remember { mutableStateOf(1) }
    var fourIndex by remember { mutableStateOf(2) }

    Column {
        SectionHeader("Segmented Control")
        IndustrialSegmentedControl(
            options = listOf("On", "Off"),
            selectedIndex = twoIndex,
            onSelect = { twoIndex = it },
        )
        Spacer(Modifier.height(IndustrialTokens.Spacing.md))
        IndustrialSegmentedControl(
            options = listOf("Day", "Week", "Month"),
            selectedIndex = threeIndex,
            onSelect = { threeIndex = it },
        )
        Spacer(Modifier.height(IndustrialTokens.Spacing.md))
        IndustrialSegmentedControl(
            options = listOf("Q1", "Q2", "Q3", "Q4"),
            selectedIndex = fourIndex,
            onSelect = { fourIndex = it },
            shape = ControlShape.Compact,
        )
    }
}

@Composable
private fun TableSection() {
    Column {
        SectionHeader("Table")
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
}

@Composable
private fun SegmentedProgressSection() {
    Column {
        SectionHeader("Segmented Progress")
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
        IndustrialProgressReadout(label = "Overflow", value = "120", unit = "%")
        Spacer(Modifier.height(IndustrialTokens.Spacing.xs))
        IndustrialSegmentedProgressBar(
            progress = 1.2f,
            modifier = Modifier.fillMaxWidth(),
            height = 12.dp,
            fillColor = IndustrialTokens.Accent.Signal,
        )
    }
}

@Composable
private fun LinearProgressSection() {
    Column {
        SectionHeader("Linear Progress")
        IndustrialLinearProgress(
            progress = 0.42f,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Composable
private fun BarChartSection() {
    Column {
        SectionHeader("Bar Chart")
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
}

@Composable
private fun RadialGaugeSection() {
    Column {
        SectionHeader("Radial Gauge")
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(IndustrialTokens.Spacing.md),
        ) {
            IndustrialRadialGauge(
                value = 0.65f,
                modifier = Modifier.weight(1f),
                label = "Load",
            )
            IndustrialDial(
                value = 0.65f,
                modifier = Modifier.weight(1f),
                label = "Flow",
            )
        }
    }
}

@Composable
private fun DotGridSection() {
    Column {
        SectionHeader("Dot Grid")
        IndustrialDotGrid(
            rows = 10,
            cols = 10,
            values = { r, c -> ((r * 7 + c * 11) % 10) / 9f },
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp),
        )
    }
}

@Composable
private fun WidgetsSection() {
    Column {
        SectionHeader("Widgets")
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(IndustrialTokens.Spacing.sm),
        ) {
            IndustrialWidget(
                category = "RPM",
                hero = "3220",
                unit = "rpm",
                trend = "+4.2%",
                modifier = Modifier.weight(1f),
            )
            IndustrialWidget(
                category = "Temp",
                hero = "87",
                unit = "°C",
                trend = "+1.1%",
                modifier = Modifier.weight(1f),
            )
            IndustrialWidget(
                category = "Fuel",
                hero = "42",
                unit = "%",
                trend = "-3.8%",
                modifier = Modifier.weight(1f),
            )
        }
    }
}

@Composable
private fun OverlaysSection() {
    var showDialog by remember { mutableStateOf(false) }
    var showSheet by remember { mutableStateOf(false) }
    var showMenu by remember { mutableStateOf(false) }

    Column {
        SectionHeader("Overlays")
        Row(horizontalArrangement = Arrangement.spacedBy(IndustrialTokens.Spacing.sm)) {
            IndustrialSecondaryButton(onClick = { showDialog = true }, text = "Dialog")
            IndustrialSecondaryButton(onClick = { showSheet = true }, text = "Sheet")
            Box {
                IndustrialSecondaryButton(onClick = { showMenu = true }, text = "Menu")
                IndustrialDropdownMenu(
                    expanded = showMenu,
                    onDismiss = { showMenu = false },
                ) {
                    DropdownMenuItem(
                        text = { Text("OPTION A") },
                        onClick = { showMenu = false },
                    )
                    DropdownMenuItem(
                        text = { Text("OPTION B") },
                        onClick = { showMenu = false },
                    )
                    DropdownMenuItem(
                        text = { Text("OPTION C") },
                        onClick = { showMenu = false },
                    )
                }
            }
        }

        if (showDialog) {
            IndustrialDialog(
                onDismiss = { showDialog = false },
                title = "Confirm",
            ) {
                Text(
                    text = "Shut down line A?",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Row(horizontalArrangement = Arrangement.spacedBy(IndustrialTokens.Spacing.sm)) {
                    IndustrialGhostButton(onClick = { showDialog = false }, text = "Cancel")
                    IndustrialDestructiveButton(onClick = { showDialog = false }, text = "Shutdown")
                }
            }
        }

        if (showSheet) {
            IndustrialBottomSheet(onDismiss = { showSheet = false }) {
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
                    IndustrialPrimaryButton(onClick = { showSheet = false }, text = "Dismiss")
                }
            }
        }
    }
}

@Composable
private fun InlineStatusSection() {
    Column {
        SectionHeader("Inline Status")
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

@Composable
private fun IconsSection() {
    Column {
        SectionHeader("Icons")
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(IndustrialTokens.Spacing.md),
        ) {
            IndustrialArrowIcon(
                direction = ArrowDirection.Up,
                modifier = Modifier.size(IndustrialIconSpec.SizeMd),
            )
            IndustrialArrowIcon(
                direction = ArrowDirection.Down,
                modifier = Modifier.size(IndustrialIconSpec.SizeMd),
            )
            IndustrialArrowIcon(
                direction = ArrowDirection.Left,
                modifier = Modifier.size(IndustrialIconSpec.SizeMd),
            )
            IndustrialArrowIcon(
                direction = ArrowDirection.Right,
                modifier = Modifier.size(IndustrialIconSpec.SizeMd),
            )
            IndustrialCloseIcon(modifier = Modifier.size(IndustrialIconSpec.SizeMd))
            IndustrialCheckIcon(modifier = Modifier.size(IndustrialIconSpec.SizeMd))
            IndustrialDotIcon(size = IndustrialIconSpec.SizeMd)
        }
    }
}
