# 1CI — SciPr Projects

This repository collects every project written for the **SciPr** (Science de la
programmation) course of class **1CI** at the **Lycée des Arts et Métiers**,
covered during the **2026/27** school year.

Every project is a standalone NetBeans Java application. Most projects are
built around Swing, while some exercises are console programs.

## Projects

`H*` folders and `MyList` are class projects. `X*` folders are personal
practice projects, mock tests, or explicitly labelled AI-generated projects.

| Folder | Description |
| --- | --- |
| `H5_WordStatsClass` | H5 correction: working reference code through Version 1 for now, with efficient HashMap counting and results ordered by highest count. Version 2 is pending. |
| `X3_WordStatsAI` | AI-made H5 implementation, previously named `H5_WordStats`; includes HashMap and ArrayList versions. |
| `H6_Vectors` | Vector drawing app: `Drawing` holds `Shape` objects (`Line`, `Rectangle`, `Oval`). |
| `MyList` | Hand-written singly linked list of integers (add, insert, delete, sort, search) with a Swing tester. |
| `X1_PrintDeskMock` | Unfinished mock-test exercises (TODOs preserved): print-job queue, urgent jobs, sorting, and file save. |
| `X2_OrbitPatrolMock` | Mock test: shooter game with a linked list of objects, sorting, and save/load. |

## Building

Each folder is a NetBeans Ant project. Open it in NetBeans and run the main
class, or build from the project root with `ant jar` and run the produced
JAR from `dist/`.

## License

Everything in this repository is free to use. You may copy, modify, and
redistribute any of the code without restriction.
