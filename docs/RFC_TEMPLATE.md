# RFC: <title>

- Status: Draft | Review | Accepted | Rejected | Superseded
- Author: @<github-handle>
- Discussion: <link to issue or PR>
- Target release: <version>

## Summary

One paragraph. What does this RFC propose?

## Motivation

Why does this need to exist? What is not possible today? Who is affected?

## Design

### API

```kotlin
// Public composable / data signatures you propose.
```

### Tokens consumed

- Spacing:
- Color:
- Radius:
- Motion:
- New tokens (justify each):

### Slots and variants

Which slots are caller-provided? What variants does the component expose (size, shape, emphasis)?

### States

Default / hover / focus / pressed / disabled / error / loading / empty — describe visual treatment for each.

### Accessibility

- Touch target: ≥ 44dp on interactive areas.
- Contrast: 3:1 non-text contrast verified.
- Semantics: role, contentDescription, stateDescription.
- Font scale: behavior at 200% text size.
- RTL: describe mirroring.

## Prior art

Link to components in Material 3, Carbon, Fluent, or other design systems that solve a similar problem. Explain how this proposal differs.

## Alternatives

What other shapes of API were considered? Why not those?

## Open questions

Unresolved items. List them explicitly so reviewers can speak to each.

## Impact on existing API

- Breaking changes:
- Deprecations:
- Migration path for consumers:

## Implementation checklist

- [ ] Component implementation in `library/src/commonMain/kotlin/…`
- [ ] Demo gallery section
- [ ] KDoc on all public symbols
- [ ] Screenshot tests (dark + light)
- [ ] UI test covering state matrix
- [ ] Maturity tag chosen (stable / `@IndustrialExperimental`)
- [ ] CHANGELOG `[Unreleased]` entry
- [ ] README update if surfaces a new primary concept
