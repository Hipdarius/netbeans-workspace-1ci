# H1–H5 · Coverage and verification

[← Project index](../README.md) · [Study guide](STUDY_GUIDE.md)

Reviewed on **18 November 2026** against `EXERCICES_1CI_2025_12_03.pdf`, pages 2–5, including its UML diagrams and screenshots.

## What was present before this review?

- The actively used repository is `netbeans-workspace-1ci`. The older `netbeans-workspace` is a separate 2CI collection.
- H1 Telephone book was missing from the 1CI repository.
- `MyList` was a partial, integer-only classroom implementation. It did not match the Object-based H2 UML, lacked several H3 methods and did not maintain the H4 last-node reference.
- `H5_WordStatsClass` contained both required counting versions and manual descending-frequency sorting. Its key-release listener missed edits without keyboard events. Lowercasing depended on the computer's default locale.
- `X3_WordStatsAI` was already a separate alternative. Its word rules and sorting implementation differ from the classroom version.

## Coverage now

| Exercise | Requirements covered | Where |
| :--- | :--- | :--- |
| H1 | Name, phone with spaces, email; sorted entries; add/edit; navigation; confirmation before deletion; automatic JSON load and close-time save | `H1_TelephoneBook` |
| H2 | Object-valued Node/MyList; both remove overloads; add, size, print, clear, indexOf, get, private getNode, toArray, insert and set | `X4_H2_LinkedList` |
| H3 | count, sort, verifySort, reverse, isEmpty, toJson, toXML, plus H2 methods | `X5_H3_LinkedListContinued` |
| H4 | Maintained last and size; O(1) append and size; discussion of further improvements | `X6_H4_LinkedListImproved`, study guide |
| H5 | Live statistics; HashMap and list versions; StatsEntry fields; descending frequency; complexity/performance discussion | `H5_WordStatsClass`, study guide |

H1 and the X4–X6 references are **new worked references**, not recovered classroom work or teacher corrections. They use small model classes, loops and straightforward methods. Gson handles JSON. H5 retains the classroom counting/sorting algorithms; only event coverage and locale-independent lowercasing were changed.

## Checks performed

- Built all five study projects using NetBeans 25's Ant and JDK 23.
- Compiled the new H1–H4 models with `javac --release 11` and ran their tests.
- H1 tests: alphabetical insertion/reordering, preserving phone formatting, valid JSON, persistence round-trip, deletion, empty directory, malformed/missing-field input and retaining data on failed load.
- H2–H4: 5,000 deterministic randomized mutations per version, compared with ArrayList after each operation; boundaries, null values, returned removed values and array-copy independence.
- H3/H4: counts, natural ordering, reversal, append after reversal, tail removal, reusing an emptied list, JSON parsed by Gson, XML parsed by the JDK parser, and rejection of XML-invalid characters.
- H5: 300 randomized texts plus fixed cases, whitespace and punctuation rules, descending order, recalculation after deletion, empty input and Turkish default-locale regression.
- H5 event regression: document insertion/replacement/removal without key events, both counting versions, and read-only output.
- Launched runnable H1 and H5 JARs. Visually checked H1 load/navigation/delete confirmation and cancel; H5 live counts, version switching and clearing.
- Launched H3/H4 console JARs to verify JSON dependency packaging, not just compilation.
- Opened H1–H4 in NetBeans and verified H1–H5 in its project tree.
- Compiled every source project in this 1CI repository and ran the existing tests for MyList, H6, Orbit Patrol and the alternative H5.

Run the checks again with `scripts/verify-study.ps1`; use `-AllProjects` for the wider compilation/regression pass. Ant builds and visual checks above are separate from that script.

## Limits and conventions to understand

- Passing these checks gives useful evidence, not a proof for every possible input or a guarantee of the teacher's marking preferences.
- H1 sorts names case-insensitively using Java string order. Its window is a NetBeans GUI Builder `.form`; buttons and the close event have named handlers. Press Save entry to commit field edits before navigating or closing.
- H3/H4 natural sorting expects mutually comparable values. XML exports text values and a null marker; it does not reconstruct arbitrary Java object types.
- H5 intentionally preserves classroom whitespace tokenization and quadratic manual sorting. Large pasted documents can pause its interface. Equal-frequency order is unspecified.
- The classroom `MyList`, renamed `H2_H4_MyListClass`, stays available as classroom material, with its differences clearly labeled. It is not the canonical complete H2–H4 correction.
- H6, H7 and X projects are beyond the supplied H1–H5 review. Compilation does not certify them against their own full assignments. X1 still contains intentional TODOs.
- `JSON_test` has no source or portable project metadata; only ignored private settings remain locally.
