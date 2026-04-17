package com.po4yka.industrialdesign.foundation

/**
 * Opt-in marker for industrial-design-cmp APIs that may change without a major-version bump.
 * Annotate experimental composables and tokens with `@IndustrialExperimental`; callers
 * opt in via `@OptIn(IndustrialExperimental::class)`. See ADR 0001.
 */
@RequiresOptIn(
    message = "Industrial design experimental API — may change without notice.",
    level = RequiresOptIn.Level.WARNING,
)
@Retention(AnnotationRetention.BINARY)
@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY,
    AnnotationTarget.TYPEALIAS,
)
annotation class IndustrialExperimental
