package com.po4yka.industrialdesign.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.po4yka.industrialdesign.IndustrialTokens

/**
 * Navigation item descriptor for [IndustrialBottomBar] and [IndustrialTopNav].
 */
data class NavItem(
    val label: String,
    val icon: (@Composable () -> Unit)? = null,
)

/**
 * Separator style for [IndustrialTopNav].
 */
enum class Separator { Pipe, Bracket, None }

/**
 * Mobile bottom navigation bar. Equal-weight items with ALL CAPS labels.
 * Active item is rendered in primary color with a 2dp indicator dot below.
 */
@Composable
fun IndustrialBottomBar(
    items: List<NavItem>,
    selectedIndex: Int,
    onSelect: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val activeColor = MaterialTheme.colorScheme.primary
    val inactiveColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        items.forEachIndexed { index, item ->
            val isActive = index == selectedIndex
            val color = if (isActive) activeColor else inactiveColor
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .clickable { onSelect(index) }
                    .padding(vertical = IndustrialTokens.Spacing.sm),
                contentAlignment = Alignment.Center,
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(IndustrialTokens.Spacing.xs),
                ) {
                    if (item.icon != null) {
                        item.icon.invoke()
                    }
                    Text(
                        text = item.label.uppercase(),
                        style = MaterialTheme.typography.labelSmall,
                        color = color,
                    )
                    Box(
                        modifier = Modifier
                            .size(width = 8.dp, height = 2.dp)
                            .background(if (isActive) activeColor else Color.Transparent),
                    )
                }
            }
        }
    }
}

/**
 * Desktop / wide layout horizontal text navigation with configurable separator.
 * Labels are rendered Space Mono ALL CAPS.
 */
@Composable
fun IndustrialTopNav(
    items: List<NavItem>,
    selectedIndex: Int,
    onSelect: (Int) -> Unit,
    modifier: Modifier = Modifier,
    separator: Separator = Separator.Pipe,
) {
    val activeColor = MaterialTheme.colorScheme.primary
    val inactiveColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
    val separatorColor = MaterialTheme.colorScheme.outline
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(IndustrialTokens.Spacing.md),
    ) {
        items.forEachIndexed { index, item ->
            if (index > 0 && separator == Separator.Pipe) {
                Text(
                    text = "|",
                    style = MaterialTheme.typography.labelLarge,
                    color = separatorColor,
                )
            }
            val isActive = index == selectedIndex
            val color = if (isActive) activeColor else inactiveColor
            val decorated = if (isActive && separator == Separator.Bracket) {
                "[ ${item.label.uppercase()} ]"
            } else {
                item.label.uppercase()
            }
            Text(
                text = decorated,
                style = MaterialTheme.typography.labelLarge,
                color = color,
                modifier = Modifier.clickable { onSelect(index) },
            )
        }
    }
}

/**
 * Circular 44dp back button with a 1dp outline and centered thin chevron.
 */
@Composable
fun IndustrialBackButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .size(44.dp)
            .clip(CircleShape)
            .border(1.dp, MaterialTheme.colorScheme.outline, CircleShape)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = "<",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface,
        )
    }
}

/**
 * Linear period navigator rendered as `< LABEL >` with 44dp touch targets on
 * each chevron. Disabled chevrons fade to 0.4 alpha and stop accepting input.
 */
@Composable
fun IndustrialPeriodNav(
    label: String,
    onPrev: () -> Unit,
    onNext: () -> Unit,
    modifier: Modifier = Modifier,
    prevEnabled: Boolean = true,
    nextEnabled: Boolean = true,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(IndustrialTokens.Spacing.md),
    ) {
        PeriodChevron(symbol = "<", enabled = prevEnabled, onClick = onPrev)
        Text(
            text = label.uppercase(),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSurface,
        )
        PeriodChevron(symbol = ">", enabled = nextEnabled, onClick = onNext)
    }
}

@Composable
private fun PeriodChevron(
    symbol: String,
    enabled: Boolean,
    onClick: () -> Unit,
) {
    val baseModifier = Modifier.size(44.dp)
    val interactiveModifier = if (enabled) {
        baseModifier.clickable(onClick = onClick)
    } else {
        baseModifier.alpha(0.4f)
    }
    Box(
        modifier = interactiveModifier,
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = symbol,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}
