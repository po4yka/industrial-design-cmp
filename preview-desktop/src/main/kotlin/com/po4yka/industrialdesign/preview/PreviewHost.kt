package com.po4yka.industrialdesign.preview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.po4yka.industrialdesign.IndustrialTheme

/**
 * Wraps preview content in [IndustrialTheme] and an `MaterialTheme.colorScheme.background`
 * Box so PNG renders match the visual baseline shown in the demo app.
 *
 * Use [darkTheme] to render the dark variant (default — the design system's primary mode).
 */
@Composable
internal fun PreviewHost(
    darkTheme: Boolean = true,
    padding: PaddingValues = PaddingValues(16.dp),
    content: @Composable () -> Unit,
) {
    IndustrialTheme(darkTheme = darkTheme) {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(padding),
        ) {
            content()
        }
    }
}
