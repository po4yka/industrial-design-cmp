# ADR 0003: Publish to Maven Central via the Central Portal

## Status

Accepted — 2026-04-17

## Context

The library has been published via JitPack since 0.1.0. JitPack is zero-config — build on tag, artifact resolvable — but has two drawbacks for library consumers:

1. **Discoverability.** Not indexed by `search.maven.org`, Gradle Plugin Portal, or IDE suggest-complete. Consumers need to know the coordinate upfront and add the JitPack repository to their resolution chain.
2. **Reliability.** JitPack builds on demand from a tag; a transient build failure on JitPack's infrastructure makes the artifact unresolvable until the retry succeeds. Central artifacts are static files on a CDN.

## Decision

Publish to **Maven Central** via the Sonatype **Central Portal** (the post-2024 replacement for OSSRH), using [`com.vanniktech.maven.publish`](https://github.com/vanniktech/gradle-maven-publish-plugin) as the Gradle integration.

- **Coordinates:** `io.github.po4yka:industrial-design-cmp:<version>`. The `io.github.<user>` namespace is auto-verifiable against the corresponding GitHub account — no domain ownership required.
- **Host enum:** `SonatypeHost.CENTRAL_PORTAL` (not the legacy `S01`).
- **Signing:** every publication is signed with a GPG key via `signAllPublications()`. Keys are in-memory, supplied through `ORG_GRADLE_PROJECT_signingInMemoryKey*` env vars — never committed.
- **Automation:** two CI paths in `.github/workflows/publish.yml`:
  - Tag push `v*` → `publishToMavenCentral` (lands in Central **staging**; manual release via portal).
  - `workflow_dispatch` with `release: true` → `publishAndReleaseToMavenCentral` (one-shot).
- **JitPack continues to work** because JitPack reads the same `maven-publish` publications the plugin produces. We keep it as a fallback for the transition period; no-one is forced to migrate.

## Consequences

**Easier:**
- Consumers depend on `io.github.po4yka:industrial-design-cmp:<version>` from `mavenCentral()` — no extra repository declaration.
- Indexing, IDE autocomplete, and vulnerability scanners all discover the library.
- Signed artifacts — required by some enterprise build pipelines.
- Release is deliberate: artifacts land in staging first; a release step makes them public.

**Harder:**
- Requires a Sonatype Central Portal account with the `io.github.po4yka` namespace verified.
- Requires a GPG signing key (one-time setup; backed up offline).
- Every publish needs five environment variables set — an extra config surface area in GitHub Actions secrets.
- A released version is immutable. Mistakes ship as a patch, never as a correction to the same coordinate.

## Migration notes

- The original JitPack coordinate `com.github.po4yka.industrial-design-cmp:library:<tag>` still resolves for tags published before this change.
- The groupId shift (`com.github.po4yka.industrial-design-cmp` → `io.github.po4yka`) and artifactId consolidation (`library` → `industrial-design-cmp`) are breaking. Callers must update their dependency declaration at the next version bump. CHANGELOG called out as a breaking-change entry.

## See also

- `docs/publishing.md` — operator runbook for credentials and release flow.
- `.github/workflows/publish.yml` — the CI wiring.
- `library/build.gradle.kts` — the `mavenPublishing { … }` block.
