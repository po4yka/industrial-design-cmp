package com.po4yka.industrialdesign.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.po4yka.industrialdesign.IndustrialTokens

private val ButtonContentPadding = PaddingValues(horizontal = 24.dp, vertical = 12.dp)
private const val DisabledAlpha = 0.4f

@Composable
private fun industrialButtonLabel(
    text: String,
    color: Color,
    leadingIcon: @Composable () -> Unit,
    trailingIcon: @Composable () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        leadingIcon()
        Text(
            text = text.uppercase(),
            style = MaterialTheme.typography.labelLarge,
            color = color,
        )
        trailingIcon()
    }
}

/**
 * Primary call-to-action. Pill radius, `primary` background, `onPrimary` label.
 * Consumes Space Mono `labelLarge`, ALL CAPS; 24dp × 12dp padding; 44dp min height.
 */
@Composable
fun IndustrialPrimaryButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    leadingIcon: @Composable () -> Unit = {},
    trailingIcon: @Composable () -> Unit = {},
) {
    IndustrialPrimaryButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
    ) {
        industrialButtonLabel(
            text = text,
            color = MaterialTheme.colorScheme.onPrimary,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
        )
    }
}

/**
 * Primary call-to-action, content slot overload. Caller owns child layout inside the pill.
 */
@Composable
fun IndustrialPrimaryButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable RowScope.() -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = modifier.defaultMinSize(minHeight = 44.dp),
        enabled = enabled,
        shape = RoundedCornerShape(IndustrialTokens.Radius.Pill),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = DisabledAlpha),
            disabledContentColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = DisabledAlpha),
        ),
        contentPadding = ButtonContentPadding,
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
            focusedElevation = 0.dp,
            hoveredElevation = 0.dp,
            disabledElevation = 0.dp,
        ),
        content = content,
    )
}

/**
 * Secondary button. Transparent background, 1dp `outline` border, `onSurface` text. Pill radius.
 */
@Composable
fun IndustrialSecondaryButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    leadingIcon: @Composable () -> Unit = {},
    trailingIcon: @Composable () -> Unit = {},
) {
    IndustrialSecondaryButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
    ) {
        industrialButtonLabel(
            text = text,
            color = MaterialTheme.colorScheme.onSurface,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
        )
    }
}

/**
 * Secondary button, content slot overload.
 */
@Composable
fun IndustrialSecondaryButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable RowScope.() -> Unit,
) {
    val borderColor = if (enabled) {
        MaterialTheme.colorScheme.outline
    } else {
        MaterialTheme.colorScheme.outline.copy(alpha = DisabledAlpha)
    }
    OutlinedButton(
        onClick = onClick,
        modifier = modifier.defaultMinSize(minHeight = 44.dp),
        enabled = enabled,
        shape = RoundedCornerShape(IndustrialTokens.Radius.Pill),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onSurface,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = MaterialTheme.colorScheme.onSurface.copy(alpha = DisabledAlpha),
        ),
        border = BorderStroke(1.dp, borderColor),
        contentPadding = ButtonContentPadding,
        content = content,
    )
}

/**
 * Ghost button. Transparent background, no border, `onSurfaceVariant` text, `Technical` (4dp) radius.
 */
@Composable
fun IndustrialGhostButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    leadingIcon: @Composable () -> Unit = {},
    trailingIcon: @Composable () -> Unit = {},
) {
    IndustrialGhostButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
    ) {
        industrialButtonLabel(
            text = text,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
        )
    }
}

/**
 * Ghost button, content slot overload.
 */
@Composable
fun IndustrialGhostButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable RowScope.() -> Unit,
) {
    TextButton(
        onClick = onClick,
        modifier = modifier.defaultMinSize(minHeight = 44.dp),
        enabled = enabled,
        shape = RoundedCornerShape(IndustrialTokens.Radius.Technical),
        colors = ButtonDefaults.textButtonColors(
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = DisabledAlpha),
        ),
        contentPadding = ButtonContentPadding,
        content = content,
    )
}

/**
 * Destructive button. Transparent background, 1dp `error` border, `error` text. Pill radius.
 */
@Composable
fun IndustrialDestructiveButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    leadingIcon: @Composable () -> Unit = {},
    trailingIcon: @Composable () -> Unit = {},
) {
    IndustrialDestructiveButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
    ) {
        industrialButtonLabel(
            text = text,
            color = MaterialTheme.colorScheme.error,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
        )
    }
}

/**
 * Destructive button, content slot overload.
 */
@Composable
fun IndustrialDestructiveButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable RowScope.() -> Unit,
) {
    val borderColor = if (enabled) {
        MaterialTheme.colorScheme.error
    } else {
        MaterialTheme.colorScheme.error.copy(alpha = DisabledAlpha)
    }
    OutlinedButton(
        onClick = onClick,
        modifier = modifier.defaultMinSize(minHeight = 44.dp),
        enabled = enabled,
        shape = RoundedCornerShape(IndustrialTokens.Radius.Pill),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.error,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = MaterialTheme.colorScheme.error.copy(alpha = DisabledAlpha),
        ),
        border = BorderStroke(1.dp, borderColor),
        contentPadding = ButtonContentPadding,
        content = content,
    )
}
