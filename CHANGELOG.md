# Changelog

All notable changes to this project are documented in this file.

Format follows [Keep a Changelog 1.1.0](https://keepachangelog.com/en/1.1.0/).
Versioning follows [Semantic Versioning 2.0.0](https://semver.org/spec/v2.0.0.html):
breaking public API changes require a major bump, new tokens or components require a minor bump,
fixes to colors, sizes, or documentation require a patch.

The library version and skill version always move together — treat each git tag as a single
coordinated release.

---

## [Unreleased]

### Added
- Component composables: initial set of production-ready UI components using existing tokens
- Foundation primitives:
  - `Motion` — `animateColorAsState` / `animateFloatAsState` wrappers enforcing `IndustrialTokens.Motion.Easing` and approved durations
  - `Adaptive` — window-size helpers for compact / medium / expanded breakpoints
  - `A11y` — semantic role utilities and minimum touch-target enforcement (44dp)
  - `Icons` — curated outline icon set, no filled or multi-color variants
  - `ExtendedTheme` — `CompositionLocal` for tokens outside MaterialTheme (Accent, Motion, Radius, Spacing)
- Demo gallery module (`demo/`) with one entry per component, dark and light previews
- Governance docs: `CHANGELOG.md`, `CONTRIBUTING.md`, `CODE_OF_CONDUCT.md`, `SECURITY.md`
- GitHub repository templates: PR template, bug/feature issue forms, `CODEOWNERS`
- Architecture Decision Records: `docs/adr/`
- RFC template for proposing new components: `docs/RFC_TEMPLATE.md`
- CI workflows: `ci.yml` (build/test/lint + iOS sim framework), `publish.yml` (tag-driven), `docs.yml` (Dokka → Pages)
- `@IndustrialExperimental` opt-in annotation (see ADR 0001) for APIs under iteration
- Commons unit tests: token stability, motion contracts, segmented-progress smoke paths, button click handling
- Gradle task `:library:exportTokens` — emits `build/tokens/tokens.json` (Style Dictionary format) for Figma round-tripping

### Changed (breaking)
- Publish coordinate migrated from JitPack to Maven Central. New coordinate: `io.github.po4yka:industrial-design-cmp:<version>`. Legacy `com.github.po4yka.industrial-design-cmp:library:<tag>` still resolves for tags ≤ 0.1.0 — consumers pinning to the next release must update their dependency declaration.
- Publishing Gradle plugin swapped from `maven-publish` to `com.vanniktech.maven.publish` (0.36.0). See [ADR 0003](docs/adr/0003-maven-central-publishing.md) and [`docs/publishing.md`](docs/publishing.md).

---

## [0.1.0] — 2026-04-10

### Added
- Scaffolded `library/` KMP module targeting Android, `iosX64`, `iosArm64`, `iosSimulatorArm64`
- `IndustrialTheme(darkTheme, content)` — root `MaterialTheme` wrapper
- `IndustrialDarkColorScheme` and `IndustrialLightColorScheme` — raw `ColorScheme` objects for advanced composition
- `IndustrialTypography()` — `@Composable` returning the industrial `Typography`; M3 slots filled with Doto, Space Grotesk, and Space Mono
- Design tokens via `IndustrialTokens`:
  - `Spacing` — nine-step `Dp` scale (`xs2` 2dp through `xl4` 96dp)
  - `Accent` — `Signal` (#D71921), `Success`, `Warning`, `Interactive` status colors outside M3
  - `Motion` — `MicroDurationMillis` (200ms), `TransitionDurationMillis` (300ms), `Easing` (subtle ease-out cubic-bezier)
  - `Radius` — `Technical` (4dp), `Compact` (8dp), `Card` (16dp), `Pill` (999dp)
- Bundled fonts (SIL OFL 1.1): Doto, Space Grotesk, Space Mono from `google/fonts`
- Claude Code skill at `skill/` covering design philosophy, craft rules, token reference, 15 component patterns, and Material 3 slot mapping
- JitPack publish coordinate: `com.github.po4yka.industrial-design-cmp:library:0.1.0`
- `jitpack.yml` specifying JDK 17 build environment

[Unreleased]: https://github.com/po4yka/industrial-design-cmp/compare/0.1.0...HEAD
[0.1.0]: https://github.com/po4yka/industrial-design-cmp/releases/tag/0.1.0
