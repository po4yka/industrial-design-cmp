package com.po4yka.industrialdesign.components

import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha

/**
 * Industrial-monochrome switch. Pill track, circle thumb, no thumb icon.
 * Disabled state applies 0.4 alpha to the whole control.
 */
@Composable
fun IndustrialSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    val colorScheme = MaterialTheme.colorScheme
    Switch(
        checked = checked,
        onCheckedChange = onCheckedChange,
        modifier = if (enabled) modifier else modifier.alpha(0.4f),
        enabled = enabled,
        thumbContent = null,
        colors = SwitchDefaults.colors(
            checkedTrackColor = colorScheme.primary,
            checkedThumbColor = colorScheme.onPrimary,
            uncheckedTrackColor = colorScheme.outline,
            uncheckedThumbColor = colorScheme.onSurfaceVariant,
            uncheckedBorderColor = colorScheme.outline,
            checkedBorderColor = colorScheme.primary,
        ),
    )
}

/**
 * Industrial-monochrome checkbox. Border is `outline` when unchecked,
 * `primary` fill with `onPrimary` checkmark when checked.
 */
@Composable
fun IndustrialCheckbox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    val colorScheme = MaterialTheme.colorScheme
    Checkbox(
        checked = checked,
        onCheckedChange = onCheckedChange,
        modifier = if (enabled) modifier else modifier.alpha(0.4f),
        enabled = enabled,
        colors = CheckboxDefaults.colors(
            checkedColor = colorScheme.primary,
            uncheckedColor = colorScheme.outline,
            checkmarkColor = colorScheme.onPrimary,
        ),
    )
}
