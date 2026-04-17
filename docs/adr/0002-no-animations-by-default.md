# ADR 0002: No spring easing; 200–300ms ease-out only

## Status

Accepted — 2026-04-17

## Context

The industrial aesthetic is instrument-panel precise: mechanical, deliberate, numeric. Spring and bounce easing read as playful and soft — the opposite signal. Long durations (>400ms) read as "decorative" rather than "responsive." Material 3 defaults include bouncy transitions that, while lovely in their own system, undermine the voice this library is trying to speak.

## Decision

The library exposes exactly these motion primitives:

| Token | Duration | Easing |
|-------|----------|--------|
| `IndustrialMotion.DurationMicro` | 120ms | — |
| `IndustrialMotion.DurationFast` | 200ms | `EaseOut` |
| `IndustrialMotion.DurationStandard` | 300ms | `EaseOut` |
| `IndustrialMotion.DurationSlow` | 450ms | `Decelerate` |
| `IndustrialMotion.EaseOut` | — | `cubic-bezier(0.25, 0.1, 0.25, 1)` |
| `IndustrialMotion.Decelerate` | — | `cubic-bezier(0.0, 0.0, 0.2, 1)` |
| `IndustrialMotion.Linear` | — | `LinearEasing` |

The helper `industrialTween(duration, easing)` wraps `tween<T>()` — there is no `industrialSpring`. Components that need animation consume these tokens; they do not reach for `spring()` directly.

Consumers who need motion suppressed (reduced-motion preference, testing) provide `LocalReducedMotion = true`; components skip or instantiate snap animations.

## Consequences

**Easier:**
- Transitions across the system feel unified without per-component tuning.
- Screenshot tests are stable — no mid-spring frames.
- Reduced-motion story is a single CompositionLocal read.

**Harder:**
- Components that naturally want overshoot (e.g., a toast entering) are not built. This is intentional — toasts are banned anyway (see skill/SKILL.md).
- Animations that rely on physics (drag-dismiss momentum on bottom sheets) inherit Material 3 defaults for now; we accept that small gap until it proves a real problem.

## See also

- `library/src/commonMain/kotlin/com/po4yka/industrialdesign/foundation/Motion.kt`
- `skill/SKILL.md` — craft rules §5 (motion).
