package com.po4yka.industrialdesign.foundation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf

/**
 * Extension surface for consumer-defined tokens without forking the library.
 *
 * Example:
 * ```
 * data class BrandTokens(
 *     val brandBlue: Color,
 *     val graphGap: Dp,
 * ) : IndustrialExtendedTokens
 *
 * IndustrialTheme {
 *     ProvideIndustrialExtended(BrandTokens(brandBlue = ..., graphGap = 12.dp)) {
 *         val brand = industrialExtended<BrandTokens>()
 *         // use brand.brandBlue, brand.graphGap
 *     }
 * }
 * ```
 */

/** Marker interface for consumer token bundles surfaced via composition. */
interface IndustrialExtendedTokens

/** Composition local carrying the active extended tokens, if any. */
val LocalIndustrialExtendedTokens = staticCompositionLocalOf<IndustrialExtendedTokens?> { null }

/**
 * Reads the current [IndustrialExtendedTokens] and casts to the requested
 * subtype. Throws with a helpful message when no tokens have been provided.
 */
@Composable
inline fun <reified T : IndustrialExtendedTokens> industrialExtended(): T {
    val tokens = LocalIndustrialExtendedTokens.current
        ?: error(
            "No IndustrialExtendedTokens provided. Wrap your content in " +
                "ProvideIndustrialExtended(tokens) { ... } near the IndustrialTheme call.",
        )
    return tokens as? T
        ?: error(
            "IndustrialExtendedTokens in composition is " +
                "${tokens::class.simpleName}, not ${T::class.simpleName}.",
        )
}

/** Installs [tokens] into the composition tree for [content] and descendants. */
@Composable
fun ProvideIndustrialExtended(
    tokens: IndustrialExtendedTokens,
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(LocalIndustrialExtendedTokens provides tokens) {
        content()
    }
}
