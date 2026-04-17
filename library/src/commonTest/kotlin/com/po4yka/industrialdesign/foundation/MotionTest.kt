package com.po4yka.industrialdesign.foundation

import kotlin.test.Test
import kotlin.test.assertTrue

/** Motion contract: no spring helpers exposed; all durations bounded. */
class MotionTest {
    @Test
    fun duration_order_is_monotonic() {
        assertTrue(IndustrialMotion.DurationMicro < IndustrialMotion.DurationFast)
        assertTrue(IndustrialMotion.DurationFast < IndustrialMotion.DurationStandard)
        assertTrue(IndustrialMotion.DurationStandard < IndustrialMotion.DurationSlow)
    }

    @Test
    fun durations_are_bounded() {
        assertTrue(IndustrialMotion.DurationMicro in 60..150)
        assertTrue(IndustrialMotion.DurationSlow in 300..600)
    }

    @Test
    fun tween_helper_returns_spec() {
        val spec = industrialTween<Float>()
        assertTrue(spec.durationMillis > 0)
    }
}
