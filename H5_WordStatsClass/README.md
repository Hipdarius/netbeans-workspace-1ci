# H5 Words Stats

Open MainFrame in NetBeans and use the version selector to switch between the HashMap and ArrayList counts. Both versions count whitespace-separated words and sort them by decreasing frequency with nested loops.

In Design, the input text area uses the class **keyReleased** event and the version selector uses **actionPerformed**. Typing, deleting, keyboard paste and changing versions refresh the results. A programmatic edit or a paste that sends no key event does not automatically refresh.

Lowercasing follows the computer's default locale. Equal counts can appear in a different order in the two versions. Read the [revision notes](../docs/STUDY_GUIDE.md) for the counting and sorting costs.
