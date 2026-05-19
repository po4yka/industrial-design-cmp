package com.po4yka.industrialdesign.preview

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.po4yka.industrialdesign.IndustrialTokens
import com.po4yka.industrialdesign.components.IndustrialMonoTextField
import com.po4yka.industrialdesign.components.IndustrialTextField
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun IndustrialTextFieldEmptyPreview() = PreviewHost {
    IndustrialTextField(
        value = "",
        onValueChange = {},
        label = "Operator",
        placeholder = "Enter name",
    )
}

@Preview
@Composable
fun IndustrialTextFieldFilledPreview() = PreviewHost {
    IndustrialTextField(
        value = "Yuri Schimke",
        onValueChange = {},
        label = "Operator",
    )
}

@Preview
@Composable
fun IndustrialTextFieldErrorPreview() = PreviewHost {
    IndustrialTextField(
        value = "bad@",
        onValueChange = {},
        label = "Email",
        placeholder = "ops@site.io",
        isError = true,
        errorMessage = "Invalid email address",
    )
}

@Preview
@Composable
fun IndustrialTextFieldDisabledPreview() = PreviewHost {
    IndustrialTextField(
        value = "Read only",
        onValueChange = {},
        label = "Operator",
        enabled = false,
    )
}

@Preview
@Composable
fun IndustrialMonoTextFieldPreview() = PreviewHost {
    IndustrialMonoTextField(
        value = "SERIAL-09812",
        onValueChange = {},
        label = "Serial",
        placeholder = "SERIAL-XXXXX",
    )
}

@Preview
@Composable
fun IndustrialInputsOverviewPreview() = PreviewHost {
    Column {
        IndustrialTextField(
            value = "",
            onValueChange = {},
            label = "Operator",
            placeholder = "Enter name",
        )
        Spacer(Modifier.height(IndustrialTokens.Spacing.md))
        IndustrialMonoTextField(
            value = "SERIAL-09812",
            onValueChange = {},
            label = "Serial",
        )
        Spacer(Modifier.height(IndustrialTokens.Spacing.md))
        IndustrialTextField(
            value = "bad@",
            onValueChange = {},
            label = "Email",
            isError = true,
            errorMessage = "Invalid email address",
        )
    }
}
