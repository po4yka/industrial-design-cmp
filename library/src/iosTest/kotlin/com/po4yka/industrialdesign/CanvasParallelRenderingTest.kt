package com.po4yka.industrialdesign

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.v2.runComposeUiTest
import androidx.compose.ui.unit.dp
import com.po4yka.industrialdesign.components.BarDatum
import com.po4yka.industrialdesign.components.IndustrialBarChart
import com.po4yka.industrialdesign.components.IndustrialDial
import com.po4yka.industrialdesign.components.IndustrialDotGrid
import com.po4yka.industrialdesign.components.IndustrialRadialGauge
import kotlin.test.Test
import kotlin.test.assertTrue

/**
 * Smoke-tests for canvas-heavy components under CMP 1.11's parallel rendering.
 * Drives recomposition across 20 frames to surface obvious race conditions in
 * draw scopes — canvas allocation bugs, shared mutable state, etc.
 */
@OptIn(ExperimentalTestApi::class)
class CanvasParallelRenderingTest {

    @Test
    fun canvasComponentsRecomposeUnderParallelRendering() = runComposeUiTest {
        var frame = 0

        setContent {
            val value = (frame % 10) / 10f   // cycles 0.0 .. 0.9
            IndustrialTheme {
                Column {
                    repeat(4) {
                        IndustrialRadialGauge(
                            value = value,
                            modifier = Modifier.size(80.dp),
                        )
                        IndustrialDial(
                            value = value,
                            modifier = Modifier.size(80.dp),
                        )
                        IndustrialDotGrid(
                            rows = 3,
                            cols = 3,
                            values = { _, _ -> value },
                            modifier = Modifier.size(80.dp),
                        )
                        IndustrialBarChart(
                            data = listOf(
                                BarDatum(label = "A", value = value),
                                BarDatum(label = "B", value = 1f - value),
                                BarDatum(label = "C", value = value * 0.5f),
                            ),
                            modifier = Modifier.size(80.dp),
                        )
                    }
                }
            }
        }

        var exceptionThrown = false
        try {
            for (i in 0..20) {
                frame = i
                mainClock.advanceTimeBy(16L)
            }
        } catch (e: Throwable) {
            exceptionThrown = true
        }

        assertTrue(!exceptionThrown, "Canvas components should not throw during recomposition")
    }
}
