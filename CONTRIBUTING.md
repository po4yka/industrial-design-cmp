# Contributing

## Running locally

Build the library and verify the publication layout:

```bash
./gradlew :library:assemble            # compile all targets
./gradlew :library:publishToMavenLocal # publish to ~/.m2 for local testing
```

Run the demo gallery on Android (requires a connected device or emulator):

```bash
./gradlew :demo:installDebug
```

Run static analysis:

```bash
./gradlew ktlintCheck
./gradlew detekt
```

Auto-fix ktlint violations:

```bash
./gradlew ktlintFormat
```

## Branching

- Branch from `main` for all work: `git checkout -b feature/your-topic`
- Open a pull request against `main`
- PRs must pass CI (ktlint, detekt, assemble, screenshot tests) before merge
- The `skill-only` branch is machine-managed via `git subtree split` — do not commit to it directly

## Commit message style

Short imperative subject line, no period. 50 characters or fewer is the target.

```
Add StatusBadge component with Experimental label
Fix Radius.Technical token value (was 2dp, should be 4dp)
Bump library version to 0.2.0
```

- No co-author trailers, no tool attribution
- Body is optional; use it to explain *why* when the subject is not self-evident
- Reference issues with `Fixes #N` or `Closes #N` at the end of the body when applicable

## Component contribution checklist

Before opening a PR that adds or modifies a component:

- [ ] Matches the design philosophy — no shadows, no spring/bounce, flat surfaces
- [ ] Uses existing tokens — does not introduce magic numbers
- [ ] Uses Material 3 slot APIs where possible
- [ ] Has KDoc explaining which tokens are consumed and why
- [ ] Has an entry in the demo gallery (`demo/` module) with dark and light previews
- [ ] Carries a maturity label: `@IndustrialExperimental`, stable (no annotation), or `@Deprecated`
- [ ] WCAG: 3:1 non-text contrast verified; interactive parts have a 44dp minimum touch target
- [ ] Screenshot tests added for dark and light modes
- [ ] `CHANGELOG.md` `[Unreleased]` section updated

## Maturity labels

| Label | Meaning |
|-------|---------|
| `@IndustrialExperimental` | API may change without notice; consumers must opt in |
| (none) | Stable — covered by semantic versioning guarantees |
| `@Deprecated` | Scheduled for removal; use `replaceWith` parameter |

See `docs/adr/0001-component-maturity-labels.md` for the full decision record.

## Deprecation policy

A symbol must carry `@Deprecated(message = "...", replaceWith = ReplaceWith("..."))` for at least
one minor version before it is removed. Removal happens at the next major version bump. If no
replacement exists, the deprecation message must say so explicitly.

## Code style

ktlint and detekt are enforced on every PR. Configuration lives in the repo root. To check locally
before pushing:

```bash
./gradlew ktlintCheck detekt
```

Fix formatting automatically with:

```bash
./gradlew ktlintFormat
```

Detekt violations must be resolved — suppression with `@Suppress` is acceptable when the rule
genuinely does not apply, but requires a comment explaining why.
