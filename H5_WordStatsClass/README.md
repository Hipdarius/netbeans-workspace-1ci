# H5 Words Stats — correction

This is the H5 correction with Version 1 (HashMap) and Version 2 (ArrayList).
The other H5 project, now called `X3_WordStatsAI`, was AI-made.

The left text area accepts text. Editing it updates the word counts on the right,
ordered from highest count to lowest. Counting ignores case and splits on whitespace.
`StatsEntry` stores one word and its count; `WordsStats` manages the HashMap and
`WordsStatsList` manages the ArrayList. Choose the version at the top of the window.

HashMap counting provides efficient average lookup: counting N words takes O(N)
on average under the usual constant-cost word-operation assumption. The current
manual sorting takes O(U²) for U distinct words; it is not an optimized sorting algorithm.
The list version searches up to U entries for each word, so counting takes O(N × U)
in the worst case. Both versions use the same O(U²) sorting approach.

Open this folder in NetBeans and run `MainFrame`.
