# H4 · Linked list improved

[← Study desk](../README.md) · [Explanations and practice](../docs/STUDY_GUIDE.md) · [Verification](../docs/REVIEW.md)

**Worked reference, not an official teacher correction.** Based on the supplied 1CI December 2025 exercise sheet.

H3 plus cached last and size. Compare append and size with the H3 version.

## Read and run

Read **Node → MyList → Main** in `src/`. Open this folder as a NetBeans project and run `Main` with F6.
Java 11 or later is sufficient for this project; JDK 23 matches the overall workspace.

```text
ant jar
java -jar dist/H4_LinkedListImproved.jar
```

The root `scripts/verify-study.ps1` runs the regression tests in `test/`.

## JSON dependency

Gson 2.13.2 is included in `lib/`, together with its Apache 2.0 license.
Keep `dist/lib/` next to the runnable JAR when copying a built application.
Sources: [Gson release](https://github.com/google/gson/releases/tag/gson-parent-2.13.2) · [User guide](https://google.github.io/gson/UserGuide.html).
