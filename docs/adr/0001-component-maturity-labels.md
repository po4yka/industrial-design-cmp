# ADR 0001: Component maturity labels

## Status

Accepted — 2026-04-17

## Context

Consumers need a reliable signal for which parts of the public API are stable enough to depend on, which may change, and which are being removed. Semver alone is not enough: a component may be useful enough to publish but still iterating on ergonomics, and we need a way to communicate "use this, but expect turbulence."

## Decision

Three maturity tiers, expressed as Kotlin annotations on public composables, data classes, and enums:

1. **Stable** — default, unannotated. Covered by semver. Breaking changes require a major version.
2. **Experimental** — annotated `@IndustrialExperimental`. Opt-in via `@OptIn(IndustrialExperimental::class)` at the call site. API may change in any release. No breaking-change discipline.
3. **Deprecated** — standard Kotlin `@Deprecated(message = "…", replaceWith = ReplaceWith("…"), level = DeprecationLevel.WARNING)`. Ships for one minor cycle before removal at the next minor (pre-1.0) or major (post-1.0).

Annotation definition lives in `library/src/commonMain/kotlin/com/po4yka/industrialdesign/foundation/Maturity.kt`:

```kotlin
@RequiresOptIn(
    message = "Industrial design experimental API — may change without notice",
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
```

## Consequences

**Easier:**
- Consumers can pin on stable API with confidence.
- Library authors can iterate on experimental APIs without committing to their shape.
- Deprecation windows are enforced by the compiler, not by trust.

**Harder:**
- Each new public API must explicitly pick a tier; this is a small bit of friction at review time.
- Promoting experimental → stable requires CHANGELOG bookkeeping.

## See also

- ADR 0002 — No animations by default.
- CONTRIBUTING.md — checklist references the maturity tag.
