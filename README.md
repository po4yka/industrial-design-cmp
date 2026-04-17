# industrial-design-cmp

An industrial-monochrome design system for Compose Multiplatform. Swiss typography, OLED blacks, instrument-panel aesthetic. Drop it into any Compose Multiplatform app to get a ready-made `MaterialTheme`, three coordinated fonts, a complete token scale, and a Claude Code skill that teaches the craft rules.

[![Release](https://jitpack.io/v/po4yka/industrial-design-cmp.svg)](https://jitpack.io/#po4yka/industrial-design-cmp)

## Why this exists

Compose Multiplatform gives you the runtime and `MaterialTheme` scaffolding; you still have to wire colors, typography, fonts, spacing, and motion from scratch. This repo is the single source of truth for a cohesive monochrome design system — with coordinated typography (Doto + Space Grotesk + Space Mono), a dark/light palette mapped to Material 3 slots, and supplementary tokens (spacing, motion, radius, status colors) the framework leaves undefined.

The repo ships two artifacts in lockstep:

| Artifact | Path | Purpose |
|----------|------|---------|
| **Kotlin library** | `library/` | KMP module published to Maven Central (`io.github.po4yka:industrial-design-cmp`). Android + iOS (x64, Arm64, SimulatorArm64). Bundles fonts. |
| **Claude Code skill** | `skill/` | Design philosophy, craft rules, anti-patterns, 15 component patterns with Compose snippets, Material 3 slot mapping. Installs via git subtree. |

Every git tag simultaneously releases a library version (via JitPack) and a skill version. Consumers can upgrade both with a single version bump.

## Design philosophy at a glance

- **Subtract, don't add.** Every element earns its pixel.
- **Structure is ornament.** Expose the grid, the data, the hierarchy.
- **Monochrome is the canvas.** Color is an event — status-red, status-green. Never decorative.
- **Type does the heavy lifting.** Scale, weight, spacing create hierarchy — not color, icons, or borders.
- **Dark and light are equal.** Dark is OLED black. Light is off-white technical manual. Neither is derived.
- **Industrial warmth.** Braun and Teenage Engineering lineage — technical, precise, but never cold.

For the full rule set, open [`skill/SKILL.md`](skill/SKILL.md) or install the Claude Code skill.

## Install

### Library (consumer Gradle setup)

**Step 1 — repositories in `settings.gradle.kts`:**

```kotlin
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        // Optional fallback for unreleased snapshot tags:
        // maven("https://jitpack.io")
    }
}
```

**Step 2 — depend on the library in `commonMain`:**

```kotlin
// composeApp/build.gradle.kts
kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation("io.github.po4yka:industrial-design-cmp:0.1.0")
        }
    }
}
```

> Legacy JitPack coordinate `com.github.po4yka.industrial-design-cmp:library:<tag>` continues to resolve for tags ≤ 0.1.0; new work publishes to Maven Central (see [`docs/publishing.md`](docs/publishing.md)).

**Step 3 — wrap your UI at the root:**

```kotlin
import com.po4yka.industrialdesign.IndustrialTheme
import com.po4yka.industrialdesign.IndustrialTokens

@Composable
fun App() {
    IndustrialTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(IndustrialTokens.Spacing.md),
            verticalArrangement = Arrangement.spacedBy(IndustrialTokens.Spacing.xl),
        ) {
            Text(
                text = "SESSION STATUS".uppercase(),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Text(
                text = "36",
                style = MaterialTheme.typography.displayLarge,
                color = MaterialTheme.colorScheme.primary,
            )
            Text(
                text = "active connections",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
            )
        }
    }
}
```

That's the three-layer rule in action: tertiary label, primary hero number in Doto, secondary context underneath.

### Skill (Claude Code)

**Recommended — git subtree from the `skill-only` branch** (flattened so the skill files sit directly under `.claude/skills/industrial-design/`):

```bash
git subtree add --prefix=.claude/skills/industrial-design \
  https://github.com/po4yka/industrial-design-cmp.git skill-only --squash

# Pull updates later:
git subtree pull --prefix=.claude/skills/industrial-design \
  https://github.com/po4yka/industrial-design-cmp.git skill-only --squash
```

**Alternative — clone and copy** (no version pinning):

```bash
git clone --depth 1 https://github.com/po4yka/industrial-design-cmp.git /tmp/id-cmp
cp -r /tmp/id-cmp/skill ~/.claude/skills/industrial-design
```

Once installed, invoke from Claude Code with `/industrial-design` or the keywords `industrial design`, `monochrome UI`, `instrument style`.

## Public API

| Symbol | Purpose |
|--------|---------|
| `IndustrialTheme(darkTheme, content)` | Root theme wrapper — sets `MaterialTheme` with industrial `ColorScheme` + `Typography`. Picks dark/light via `isSystemInDarkTheme()` by default. |
| `IndustrialDarkColorScheme` / `IndustrialLightColorScheme` | Raw `ColorScheme`s for advanced composition (e.g. custom `MaterialTheme` wrappers). |
| `IndustrialTypography()` | `@Composable` returning the industrial `Typography`. M3 slots filled with Doto / Space Grotesk / Space Mono — see `skill/references/tokens.md` for the size table. |
| `IndustrialTokens.Spacing.*` | `Dp` scale: `xs2`(2), `xs`(4), `sm`(8), `md`(16), `lg`(24), `xl`(32), `xl2`(48), `xl3`(64), `xl4`(96). |
| `IndustrialTokens.Accent.Signal` / `.Success` / `.Warning` / `.Interactive` | Status / accent colors that M3 doesn't cover. Signal red (`#D71921`) is also bound to `error` and `tertiary`. |
| `IndustrialTokens.Motion.MicroDurationMillis` / `.TransitionDurationMillis` / `.Easing` | Durations (200ms / 300ms) and a subtle ease-out `cubic-bezier(0.25, 0.1, 0.25, 1)`. No spring/bounce. |
| `IndustrialTokens.Radius.Technical` / `.Compact` / `.Card` / `.Pill` | Shape radii: 4dp, 8dp, 16dp, 999dp. |

Bundled fonts are accessible at `com.po4yka.industrialdesign.resources.Res.font.*` if you need to build custom `FontFamily` variants.

## Material 3 slot mapping

The industrial palette binds to M3 slots as follows (applies in both dark and light modes — the values swap, the roles don't):

| Token | M3 slot | Dark value | Light value |
|-------|---------|------------|-------------|
| Text display | `primary`, `onPrimary` inverse | `#FFFFFF` | `#000000` |
| Text body | `onSurface`, `secondary` | `#E8E8E8` | `#1A1A1A` |
| Text label | `onSurfaceVariant` | `#999999` | `#666666` |
| Background | `background` | `#000000` | `#F5F5F5` |
| Surface | `surface` | `#111111` | `#FFFFFF` |
| Surface raised | `surfaceVariant` | `#1A1A1A` | `#F0F0F0` |
| Border | `outlineVariant` | `#222222` | `#E8E8E8` |
| Border visible | `outline` | `#333333` | `#CCCCCC` |
| Signal red | `error`, `tertiary` | `#D71921` | `#D71921` |

Disabled text has no direct M3 slot — apply `onSurface.copy(alpha = 0.4f)` at the call site.

## Versions

| Component | Version |
|-----------|---------|
| Kotlin | 2.3.20 |
| Compose Multiplatform | 1.10.3 |
| AGP | 9.0.1 |
| JDK target | 17 |
| Android `minSdk` / `compileSdk` | 27 / 36 |
| iOS targets | `iosX64`, `iosArm64`, `iosSimulatorArm64` |

The library uses `com.android.kotlin.multiplatform.library` (AGP 9+) as its Android plugin, so consumers on older AGP versions may need to upgrade.

## Versioning policy

Semantic versioning. Breaking changes to the public API (renamed symbols, changed `Typography` structure, removed tokens) require a major bump. Adding new tokens or components is a minor bump. Fixing colors, sizes, or documentation is a patch. The library version and skill version always move together — treat each git tag as a single coordinated release.

## Skill contents

The Claude Code skill at `skill/` covers:

- **`SKILL.md`** — design philosophy (6 principles), 9 craft rules (hierarchy, font discipline, spacing, containers, color, consistency, balance, the industrial vibe, data density), anti-patterns, workflow.
- **`references/tokens.md`** — full type scale, dark/light color tables, spacing, motion, iconography rules, dot-matrix motif.
- **`references/components.md`** — 15 components with specs + Compose snippets: cards, buttons, inputs, list rows, tables, navigation, tags, segmented controls, toggles, **segmented progress bars** (the signature), charts, widgets, overlays, state patterns.
- **`references/compose-mapping.md`** — library dependency, Material 3 slot mapping, `IndustrialTokens` usage, dark/light switching, advanced customization.

When invoked in Claude Code, the skill pulls exactly the right reference file for the task instead of loading everything. See [`skill/SKILL.md`](skill/SKILL.md) for the workflow section.

## Local development

To build the library and verify the publication layout without JitPack:

```bash
./gradlew :library:assemble            # compile all targets
./gradlew :library:publishToMavenLocal # publish to ~/.m2 for local testing
```

Consumers can then test against the local Maven coordinate `io.github.po4yka:industrial-design-cmp:0.1.0` by adding `mavenLocal()` before the public repositories in their `settings.gradle.kts`.

## Publishing (maintainers)

```bash
# 1. Bump version in library/build.gradle.kts
# 2. Commit the bump
git add library/build.gradle.kts && git commit -m "Bump library version to 0.X.Y"

# 3. Tag and push
git tag 0.X.Y
git push --follow-tags origin main

# 4. Update the skill-only branch (git subtree split from main)
git subtree split --prefix=skill -b skill-only
git push -f origin skill-only

# 5. Visit https://jitpack.io/#po4yka/industrial-design-cmp/0.X.Y to trigger the first build
# 6. (Optional) Verify: ./gradlew :library:publishToMavenLocal
```

JitPack caches failed builds hard — if the first build at a tag fails, delete the tag and push a new one rather than retrying the same tag.

## Licensing

- Kotlin / Compose source code — **MIT License** (see `LICENSE`).
- Bundled fonts (Space Grotesk, Space Mono, Doto) — **SIL Open Font License 1.1**. See `OFL.txt` at the repo root and `library/src/commonMain/composeResources/font/OFL.txt`. All three fonts come from the [`google/fonts`](https://github.com/google/fonts) repository.

## Related

- Template that consumes this library: [`po4yka/kmp-app`](https://github.com/po4yka/kmp-app)
- The Claude Code skills platform: [Anthropic docs](https://docs.anthropic.com/en/docs/claude-code/skills)
- Compose Multiplatform resources API: [`compose-multiplatform/tutorials/Resources`](https://github.com/JetBrains/compose-multiplatform/blob/master/tutorials/Resources/README.md)
