# Publishing

The library publishes to **Maven Central** via the Sonatype **Central Portal** using the [`com.vanniktech.maven.publish`](https://github.com/vanniktech/gradle-maven-publish-plugin) plugin. JitPack continues to work in parallel — no extra configuration needed for JitPack consumers.

## Coordinates

```
groupId    = io.github.po4yka
artifactId = industrial-design-cmp
version    = 0.1.0          # bumped per release
```

## Local verification

No credentials required — installs to your local Maven cache:

```
./gradlew :library:publishToMavenLocal
```

Artifacts land under `~/.m2/repository/io/github/po4yka/industrial-design-cmp/0.1.0/`. Consumers can depend on the local copy via `mavenLocal()` in their `repositories { … }` block.

## Credentials setup

### 1. Sonatype Central Portal account

Register a user at https://central.sonatype.com and verify the `io.github.po4yka` namespace (automatic for GitHub-backed IDs — the portal will walk you through it).

Generate a **user token** under "View Account" → "Generate User Token". That token, not your portal password, is what the plugin uses.

### 2. GPG signing key

Central requires artifacts to be signed. Generate once:

```
gpg --full-generate-key           # RSA 4096, no expiry or ≤2 years
gpg --list-secret-keys --keyid-format=long
gpg --armor --export-secret-key <KEY_ID> > signing.key.asc
```

Store `signing.key.asc` somewhere safe (offline, encrypted) — it is your signing identity. Publish the public half:

```
gpg --keyserver keys.openpgp.org --send-keys <KEY_ID>
```

### 3. GitHub Actions secrets

In repo → Settings → Secrets and variables → Actions, add:

| Secret | Value |
| --- | --- |
| `MAVEN_CENTRAL_USERNAME` | user token username from the portal |
| `MAVEN_CENTRAL_PASSWORD` | user token password from the portal |
| `SIGNING_IN_MEMORY_KEY_ID` | short key ID, last 8 chars of `<KEY_ID>` |
| `SIGNING_IN_MEMORY_KEY_PASSWORD` | the passphrase set during `gpg --full-generate-key` |
| `SIGNING_IN_MEMORY_KEY` | full ASCII-armored private key (contents of `signing.key.asc`) |

The plugin reads these via `ORG_GRADLE_PROJECT_*` env vars; the publish workflow maps each secret into the correct variable name.

### 4. Local credentials (optional)

For publishing from a workstation, add to `~/.gradle/gradle.properties` (not the repo):

```
mavenCentralUsername=…
mavenCentralPassword=…
signingInMemoryKey=…
signingInMemoryKeyId=…
signingInMemoryKeyPassword=…
```

Never commit this file.

## Release flow

1. Bump `version` in `library/build.gradle.kts`.
2. Update `CHANGELOG.md`: rename `[Unreleased]` to `[0.x.y] — YYYY-MM-DD` and add a fresh `[Unreleased]` section.
3. Commit and tag: `git tag v0.x.y && git push origin main --tags`.
4. The `Publish` workflow runs on the tag and pushes to Central **staging**.
5. Verify staging at https://central.sonatype.com → open the deployment → "Publish".
6. Or: trigger `workflow_dispatch` with `release: true` to publish+release in one shot.

After release, Central usually indexes within 30–60 minutes; search.maven.org can lag up to a few hours.

## JitPack compatibility

JitPack builds the same `publishToMavenLocal` publication that the plugin creates, so the legacy coordinate `com.github.po4yka:industrial-design-cmp:<tag>` will continue to resolve. We keep it as a fallback for consumers who haven't migrated to the Central coordinate.

## Rollback

Central deployments can be **dropped** from the staging page before release. Once released, a version is immutable — the remedy is to publish a patch version with the fix.
