package com.po4yka.industrialdesign

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

/**
 * Industrial-monochrome design system theme wrapper. Drop this in at the root
 * of any Compose Multiplatform UI and children will inherit the monochrome
 * palette, typography (Doto / Space Grotesk / Space Mono), and Material 3
 * component styling.
 *
 * Values outside Material 3 (spacing scale, signal/status colors, motion,
 * shape radii) live on the `IndustrialTokens` object.
 */
@Composable
fun IndustrialTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme = if (darkTheme) IndustrialDarkColorScheme else IndustrialLightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = IndustrialTypography(),
        content = content,
    )
}
