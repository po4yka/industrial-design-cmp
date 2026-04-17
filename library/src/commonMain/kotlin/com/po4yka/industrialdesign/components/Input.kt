package com.po4yka.industrialdesign.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

private const val DisabledAlpha = 0.4f

@Composable
private fun IndustrialTextFieldInternal(
    value: String,
    onValueChange: (String) -> Unit,
    textStyle: TextStyle,
    modifier: Modifier,
    label: String?,
    placeholder: String?,
    enabled: Boolean,
    isError: Boolean,
    errorMessage: String?,
    singleLine: Boolean,
    keyboardOptions: KeyboardOptions,
    visualTransformation: VisualTransformation,
    leadingIcon: @Composable (() -> Unit)?,
    trailingIcon: @Composable (() -> Unit)?,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    val dividerColor = when {
        isError -> MaterialTheme.colorScheme.error
        !enabled -> MaterialTheme.colorScheme.outlineVariant.copy(alpha = DisabledAlpha)
        isFocused -> MaterialTheme.colorScheme.onSurface
        else -> MaterialTheme.colorScheme.outlineVariant
    }
    val contentColor = if (enabled) {
        MaterialTheme.colorScheme.onSurface
    } else {
        MaterialTheme.colorScheme.onSurface.copy(alpha = DisabledAlpha)
    }
    val placeholderColor = MaterialTheme.colorScheme.onSurfaceVariant.let {
        if (enabled) it else it.copy(alpha = DisabledAlpha)
    }

    Column(modifier = modifier.fillMaxWidth()) {
        if (label != null) {
            Text(
                text = label.uppercase(),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant.let {
                    if (enabled) it else it.copy(alpha = DisabledAlpha)
                },
                modifier = Modifier.padding(bottom = 8.dp),
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 44.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            if (leadingIcon != null) {
                leadingIcon()
            }
            Box(modifier = Modifier.weight(1f)) {
                BasicTextField(
                    value = value,
                    onValueChange = onValueChange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp),
                    enabled = enabled,
                    textStyle = textStyle.copy(color = contentColor),
                    singleLine = singleLine,
                    keyboardOptions = keyboardOptions,
                    visualTransformation = visualTransformation,
                    interactionSource = interactionSource,
                    cursorBrush = SolidColor(
                        if (isError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurface,
                    ),
                )
                if (value.isEmpty() && placeholder != null) {
                    Text(
                        text = placeholder,
                        style = textStyle,
                        color = placeholderColor,
                        modifier = Modifier.padding(vertical = 10.dp),
                    )
                }
            }
            if (trailingIcon != null) {
                trailingIcon()
            }
        }

        HorizontalDivider(thickness = 1.dp, color = dividerColor)

        if (isError && errorMessage != null) {
            Text(
                text = errorMessage,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 4.dp),
            )
        }
    }
}

/**
 * Underline-style text field. Label above in `labelMedium` ALL CAPS `onSurfaceVariant`; bottom
 * `HorizontalDivider` switches to `onSurface` on focus and `error` when `isError`. Error message
 * below in `error` + `bodySmall`.
 */
@Composable
fun IndustrialTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    placeholder: String? = null,
    enabled: Boolean = true,
    isError: Boolean = false,
    errorMessage: String? = null,
    singleLine: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    IndustrialTextFieldInternal(
        value = value,
        onValueChange = onValueChange,
        textStyle = LocalTextStyle.current.merge(MaterialTheme.typography.bodyLarge),
        modifier = modifier,
        label = label,
        placeholder = placeholder,
        enabled = enabled,
        isError = isError,
        errorMessage = errorMessage,
        singleLine = singleLine,
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
    )
}

/**
 * Monospace variant of `IndustrialTextField`. Uses Space Mono via `labelLarge.fontFamily` at
 * `bodyMedium` sizing — suited for numeric entry, codes, identifiers.
 */
@Composable
fun IndustrialMonoTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    placeholder: String? = null,
    enabled: Boolean = true,
    isError: Boolean = false,
    errorMessage: String? = null,
    singleLine: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    val monoStyle = MaterialTheme.typography.bodyMedium.copy(
        fontFamily = MaterialTheme.typography.labelLarge.fontFamily,
    )
    IndustrialTextFieldInternal(
        value = value,
        onValueChange = onValueChange,
        textStyle = monoStyle,
        modifier = modifier,
        label = label,
        placeholder = placeholder,
        enabled = enabled,
        isError = isError,
        errorMessage = errorMessage,
        singleLine = singleLine,
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
    )
}
