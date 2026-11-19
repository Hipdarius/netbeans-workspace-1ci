# 1CI · Programming study desk

**Classroom exercises, study references and timed mock exams.**

Science de la programmation · 2026/27 · Java & NetBeans

[Start studying](docs/STUDY_GUIDE.md) · [Coverage & checks](docs/REVIEW.md) · [Run the checks](scripts/verify-study.ps1) · [Mock exams](#timed-mock-exams)

---

## Classroom projects

| Project | What to revise |
| :--- | :--- |
| [H2–H4 MyList](H2_H4_MyListClass/) | Integer nodes, traversal, insertion, removal, selection sort, reverse and a cached tail |
| [H5 Words Stats](H5_WordStatsClass/) | HashMap and ArrayList counting, nested-loop sort and NetBeans events |
| [H6 Vectors](H6_Vectors/STUDY.md) | Shapes, mouse events, drawing order and persistence |
| [H7 Performance Tests](H7_PerformanceTests/) | Arrays, collections and linear searches |

## Timed mock exams

The `Z` prefix identifies two-part SCIPR practice exams with NetBeans starters.
Each exam lasts 180 minutes and is worth 60 points. Allow up to 35 minutes for
the 11-point modelling paper. Open Part II after finishing Part I.

| Exam | Practice focus |
| :--- | :--- |
| [Z1 · Board Game Club Desk](Z1_BoardGameClubDesk/) | Linked lists, JSON, collections, board graphics, search and recursion |
| [Z2 · Museum Crate Planner](Z2_MuseumCratePlanner/) | Recursive tree operations, nested manifests and Swing integration |

Each exam folder links to both papers and a downloadable starter ZIP. You can
also open either project folder directly in NetBeans. Press **F6** before
starting the timer to check that the starter launches, then close the window
and leave the source tabs closed until you finish modelling.

## Open and run

1. Use a JDK; **JDK 23** matches the existing classroom projects and the checked environment.
2. In NetBeans, choose **File → Open Project** and select a project folder from the table.
3. Choose **Run Project (F6)**. H2–H6 open windows. H7 and the supplementary linked-list examples print in Output.
4. Read the project's README and the [study guide](docs/STUDY_GUIDE.md) alongside its source.

Each project is independent. Projects that use Gson bundle the JAR and its license in `lib/`.
The classroom MyList project targets Java 23. The X4–X7 references use Java 11-compatible source. Runnable JARs using Gson need their accompanying `dist/lib/` folder.

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

## Extra practice and AI-supported references

| Project | Purpose and current scope |
| :--- | :--- |
| [X4_H2_LinkedList](X4_H2_LinkedList/) | AI-supported H2 reference matching the Object-based UML. |
| [X5_H3_LinkedListContinued](X5_H3_LinkedListContinued/) | AI-supported H3 reference, extending H2. |
| [X6_H4_LinkedListImproved](X6_H4_LinkedListImproved/) | AI-supported H4 reference with a last pointer and cached size. |
| [X7_H1_TelephoneBook](X7_H1_TelephoneBook/) | AI-supported telephone book. The repository history does not contain a classroom H1. |
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
