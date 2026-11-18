# H5 Words Stats — correction

This is the H5 correction and current reference code through **Version 1 (HashMap)** for now.
The other H5 project, now called `X3_WordStatsAI`, was AI-made.

The left text area accepts text. Releasing a key updates the word counts on the right,
ordered from highest count to lowest. Counting ignores case and splits on whitespace.
`StatsEntry` stores one word and its count; `WordsStats` manages the HashMap.

HashMap counting provides efficient average lookup: counting N words takes O(N)
on average under the usual constant-cost word-operation assumption. The current
manual sorting takes O(U²) for U distinct words; it is not an optimized sorting algorithm.
Updates currently use a key listener, so edits made only through mouse menus do not
trigger a refresh until the next key release.

**Version 2 (a list of StatsEntry objects) and the final comparison are pending.**

Open this folder in NetBeans and run `MainFrame`.
