# Z2 · Museum Crate Planner

**SCIPR 1CI mock exam 2** · 180 minutes · 60 points

| Material | Details |
| --- | --- |
| [Part I · Modelling](docs/Z2_MuseumCratePlanner_Modelling.pdf) | 11 points. Allow up to 35 minutes. Draw your UML on paper. |
| [Part II · Implementation](docs/Z2_MuseumCratePlanner_Implementation.pdf) | 49 points. Includes the definitive UML and 12 graded Java tasks. |
| [Download the NetBeans starter](docs/Z2_MuseumCratePlanner_Starter.zip?raw=1) | Includes the NetBeans project, Swing form and two sample manifests. |

This mock practises recursive tree operations, nested text-file parsing and Swing integration in a museum packing planner.

## Open and run

1. Use **JDK 17 or newer** for the project. Open this folder from a repository checkout, or extract the starter ZIP and open the inner `Z2_MuseumCratePlanner` folder using **File → Open Project**.
2. Before timing, press **F6** and confirm that **Museum Crate Planner** opens with the expanded demo tree. Close the window. Keep Part II and the source tabs closed until you finish Part I.
3. In Part II, use **Source Packages → scipr.museum.view → MainFrame → Design** to inspect the supplied form. Complete the numbered TODOs in **Source**. The project uses the standard Java libraries.

## TODO locations

| TODOs | Source files |
| --- | --- |
| 1–3 | `Exhibit.java` and `Crate.java` |
| 4–8 | `PackingPlan.java` |
| 9–11 | `PlanReader.java` |
| 12 | `MainFrame.java` |

## Sample files

- `samples/packing-plan.txt` describes the built-in demo plan.
- `samples/invalid-duplicate.txt` contains a repeated ID for testing import error handling.
