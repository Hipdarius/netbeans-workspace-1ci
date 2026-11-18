# 1CI · Programming study desk

**Five exercises. A clear progression. Code you can run, trace and explain.**

Science de la programmation · 2026/27 · Java & NetBeans

[Start studying](docs/STUDY_GUIDE.md) · [Coverage & checks](docs/REVIEW.md) · [Run the checks](scripts/verify-study.ps1)

---

## Your route through H1–H5

Aligned with **EXERCICES_1CI_2025_12_03**, pages 2–5.
These are worked study solutions, **not official teacher corrections**.
H1–H4 are newly written reference projects; H5 keeps the existing classroom approach.

| Step | Open this NetBeans project | What to understand | Provenance |
| :--- | :--- | :--- | :--- |
| **H1** | [Telephone book](H1_TelephoneBook/) | Objects, alphabetical ordering, Swing events, JSON persistence | Worked reference |
| **H2** | [Linked list](H2_LinkedList/) | Nodes, traversal, insertion, removal, index bounds | Worked reference matching the UML |
| **H3** | [Linked list continued](H3_LinkedListContinued/) | Counting, selection sort, reversal, JSON/XML | H2 extended |
| **H4** | [Linked list improved](H4_LinkedListImproved/) | Maintain `last` and `size`; reason about complexity | H3 extended |
| **H5** | [Words Stats — class version](H5_WordStatsClass/) | HashMap versus list; counting versus sorting costs | Classroom code with targeted fixes |

**Recommended routine:** read the model → trace an example on paper → run it → rewrite one method without looking → run the tests.

## Open and run

1. Use a JDK; **JDK 23** matches the existing classroom projects and the checked environment.
2. In NetBeans, choose **File → Open Project** and select a project folder from the table.
3. Choose **Run Project (F6)**. H1 and H5 open windows; H2–H4 print examples in Output.
4. Read the project's README and the [study guide](docs/STUDY_GUIDE.md) alongside its source.

Each project is independent. The required Gson JSON JAR is included in the new projects' `lib/` directories with its license; no machine-specific library setup is needed.
H1–H4 use Java 11-compatible source. Runnable JARs using Gson need their accompanying `dist/lib/` folder.

From a project directory, with Ant on your PATH:

```text
ant jar
java -jar dist/PROJECT_NAME.jar
```

### Verify the study set

From the repository root in PowerShell:

```powershell
powershell -NoProfile -ExecutionPolicy Bypass -File scripts/verify-study.ps1
```

Add `-AllProjects` to compile the other projects and run their available regression tests too.
The script exits with an error when compilation or a test fails. It does not open GUI windows.

## Classroom originals and extra practice

| Project | Purpose and current scope |
| :--- | :--- |
| [MyList](MyList/) | Original integer-only classroom list and Swing tester. Useful for comparing styles; **not the complete H2–H4 solution**. |
| [H6_Vectors](H6_Vectors/) | Vector drawing; [its own study notes](H6_Vectors/STUDY.md). Beyond this H1–H5 review. |
| [H7_PerformanceTests](H7_PerformanceTests/) | Collection timing experiments. Beyond this H1–H5 review. |
| [X1_PrintDeskMock](X1_PrintDeskMock/) | Unfinished mock test: TODOs intentionally remain for practice. |
| [X2_OrbitPatrolMock](X2_OrbitPatrolMock/) | Shooter mock test with linked-list and persistence regression checks. |
| [X3_WordStatsAI](X3_WordStatsAI/) | Alternative AI-made H5, with different token rules and library sorting. Study the class version first. |

The older **2CI** repository contains `H1_CryptoBox` and `H3_AsteroidDrawer`.
Those names refer to a different exercise series and do not satisfy this sheet's H1 and H3.
`JSON_test` contains only local NetBeans private settings, not a source project.

## Repository conventions

- `src/`: readable code; `.form` files stay beside GUI-builder sources.
- `test/`: executable regression checks without a test-framework dependency.
- `nbproject/` and `build.xml`: portable NetBeans/Ant setup.
- `docs/`: revision notes, coverage and verification evidence.
- `build/`, `dist/`, private IDE settings and personal `phonebook.json` data are not committed.

The existing repository permits reuse of its project code. Bundled third-party libraries retain their own licenses; see `lib/LICENSE-Gson.txt` in each new JSON-using project.
