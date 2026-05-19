package com.po4yka.industrialdesign.preview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.po4yka.industrialdesign.IndustrialTokens
import com.po4yka.industrialdesign.foundation.ArrowDirection
import com.po4yka.industrialdesign.foundation.IndustrialArrowIcon
import com.po4yka.industrialdesign.foundation.IndustrialCheckIcon
import com.po4yka.industrialdesign.foundation.IndustrialCloseIcon
import com.po4yka.industrialdesign.foundation.IndustrialDotIcon
import com.po4yka.industrialdesign.foundation.IndustrialIconSpec
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun IndustrialArrowIconsPreview() = PreviewHost {
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
    }
}

@Preview
@Composable
fun IndustrialCloseIconPreview() = PreviewHost {
    IndustrialCloseIcon(modifier = Modifier.size(IndustrialIconSpec.SizeMd))
}

@Preview
@Composable
fun IndustrialCheckIconPreview() = PreviewHost {
    IndustrialCheckIcon(modifier = Modifier.size(IndustrialIconSpec.SizeMd))
}

@Preview
@Composable
fun IndustrialDotIconPreview() = PreviewHost {
    IndustrialDotIcon(size = IndustrialIconSpec.SizeMd)
}

@Preview
@Composable
fun IndustrialIconsOverviewPreview() = PreviewHost {
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
