# H5 Words Stats â€” correction

This is the existing classroom-style H5 with Version 1 (HashMap) and Version 2 (ArrayList).
It is a reviewed worked solution, not an official teacher correction.
The other H5 project, now called `X3_WordStatsAI`, was AI-made.

The left text area accepts text. Editing it updates the word counts on the right,
ordered from highest count to lowest. Counting ignores case and splits on whitespace.
`StatsEntry` stores one word and its count; `WordsStats` manages the HashMap and
`WordsStatsList` manages the ArrayList. Choose the version at the top of the window.

HashMap counting provides efficient average lookup: counting N words takes O(N)
on average under the usual constant-cost word-operation assumption. The current
manual sorting takes O(UÂ²) for U distinct words; it is not an optimized sorting algorithm.
The list version searches up to U entries for each word, so counting takes O(N Ã— U)
in the worst case. Both versions use the same O(UÂ²) sorting approach.

Open this folder in NetBeans and run `MainFrame`.

## Review notes

[Full study guide](../docs/STUDY_GUIDE.md) · [Coverage and tests](../docs/REVIEW.md)

The document listener updates after typing, mouse-driven paste/cut and deletion.
Lowercasing uses `Locale.ROOT` so counts do not depend on Windows language settings.
Punctuation stays attached to whitespace-separated tokens. Ties have no prescribed order.

Both counting implementations and non-keyboard document changes have executable tests in `test/`.
Run the repository's `scripts/verify-study.ps1` to verify them.
