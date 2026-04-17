# industrial-design-cmp

Industrial-monochrome design system for Compose Multiplatform. Swiss typography, OLED blacks, instrument-panel aesthetic. Ships a Kotlin library + a Claude Code skill that can be installed into any agent-enabled repo.

## What's inside

| Artifact | Path | Contents |
|----------|------|----------|
| **Kotlin library** | `library/` | `IndustrialTheme`, `IndustrialDarkColorScheme`, `IndustrialLightColorScheme`, `IndustrialTypography`, `IndustrialTokens` (spacing, motion, accent, radius). Bundles Space Grotesk, Space Mono, and Doto as Compose resources. |
| **Claude Code skill** | `skill/` | Design philosophy, craft rules, anti-patterns, 15 component patterns with Compose snippets, Material 3 slot mapping |

Both artifacts are versioned together. Every git tag is a simultaneous library release (via JitPack) and skill version.

## Kotlin library — consumer setup

Add JitPack to your repositories:

```kotlin
// settings.gradle.kts
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
}
```

Depend on the library in `commonMain`:

```kotlin
// composeApp/build.gradle.kts
commonMain.dependencies {
    implementation("com.github.po4yka.industrial-design-cmp:library:0.1.0")
}
```

Wrap your UI:

```kotlin
import com.po4yka.industrialdesign.IndustrialTheme
import com.po4yka.industrialdesign.IndustrialTokens

@Composable
fun App() {
    IndustrialTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(IndustrialTokens.Spacing.md),
        ) {
            Text(
                text = "36",
                style = MaterialTheme.typography.displayLarge,
                color = MaterialTheme.colorScheme.primary,
            )
        }
    }
}
```

## Claude Code skill — consumer setup

### Option A: git subtree (recommended)

```bash
# First install in the consumer repo:
git subtree add --prefix=.claude/skills/industrial-design \
  https://github.com/po4yka/industrial-design-cmp.git main --squash

# Pull updates later:
git subtree pull --prefix=.claude/skills/industrial-design \
  https://github.com/po4yka/industrial-design-cmp.git main --squash
```

Because the skill files live at the root of the `skill/` dir, you may want to publish the skill on a branch that has `skill/*` promoted to the root — see the `Advanced` section below if you want that topology.

### Option B: plain git clone + copy

```bash
git clone --depth 1 https://github.com/po4yka/industrial-design-cmp.git /tmp/id-cmp
cp -r /tmp/id-cmp/skill ~/.claude/skills/industrial-design
```

No version pinning — you'll need to re-run to update.

## Versions

| Component | Version |
|-----------|---------|
| Kotlin | 2.3.20 |
| Compose Multiplatform | 1.10.3 |
| AGP | 9.0.1 |
| JDK target | 17 |
| Android minSdk / compileSdk | 27 / 36 |
| iOS targets | x64, Arm64, SimulatorArm64 |

## Public API

| Symbol | Purpose |
|--------|---------|
| `IndustrialTheme(darkTheme, content)` | Theme wrapper — sets `MaterialTheme` with industrial `ColorScheme` + `Typography` |
| `IndustrialDarkColorScheme` / `IndustrialLightColorScheme` | Direct `ColorScheme`s for advanced composition |
| `IndustrialTypography()` | `@Composable` returning the industrial `Typography` (M3 slots filled with Doto / Space Grotesk / Space Mono) |
| `IndustrialTokens.Spacing.*` | `Dp` spacing scale (`xs2`..`xl4`) |
| `IndustrialTokens.Accent.*` | Signal / success / warning / interactive colors |
| `IndustrialTokens.Motion.*` | Duration constants + ease-out `Easing` |
| `IndustrialTokens.Radius.*` | Shape radii (`Technical`, `Compact`, `Card`, `Pill`) |

## Licensing

- Kotlin/Compose source — **MIT** (see `LICENSE`)
- Bundled fonts (Space Grotesk, Space Mono, Doto) — **SIL Open Font License 1.1** (see `OFL.txt` and `library/src/commonMain/composeResources/font/OFL.txt`)

## Publishing (maintainers)

```bash
# 1. Bump version in library/build.gradle.kts
# 2. Commit, tag, push
git tag 0.2.0
git push --tags
# 3. Visit jitpack.io/#po4yka/industrial-design-cmp to trigger the first build
# 4. Verify with: ./gradlew :library:publishToMavenLocal
```

JitPack caches failed builds hard — if the first build fails, bump to a new tag rather than retry the same one.
