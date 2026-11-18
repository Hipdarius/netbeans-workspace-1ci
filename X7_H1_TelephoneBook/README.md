# H1 · Telephone book

[← Study desk](../README.md) · [Explanations and practice](../docs/STUDY_GUIDE.md) · [Verification](../docs/REVIEW.md)

**AI-supported reference.** Based on the supplied 1CI December 2025 exercise sheet.

Sorted people, navigation, confirmation before deletion, automatic JSON load/save.

## Read and run

Read **Person → PhoneBook → MainFrame** in `src/`. Open `MainFrame.java` and choose **Design** to see the NetBeans GUI Builder form; double-click a button to inspect its event handler. Open this folder as a NetBeans project and run `MainFrame` with F6.
Java 11 or later is sufficient for this project; JDK 23 matches the overall workspace.

```text
ant jar
java -jar dist/X7_H1_TelephoneBook.jar
```

The root `scripts/verify-study.ps1` runs the regression tests in `test/`.

## JSON dependency

Gson 2.13.2 is included in `lib/`, together with its Apache 2.0 license.
Keep `dist/lib/` next to the runnable JAR when copying a built application.
Sources: [Gson release](https://github.com/google/gson/releases/tag/gson-parent-2.13.2) · [User guide](https://google.github.io/gson/UserGuide.html).

Data is saved to `phonebook.json` in the working directory; NetBeans normally uses this project folder. Press **Save entry** before navigating/closing to commit field edits. The JSON file is ignored by Git. On a load error, repair the file and restart; it is not overwritten on close.
