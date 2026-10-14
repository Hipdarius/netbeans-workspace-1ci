# 1CI — SciPr Projects

This repository collects every project written for the **SciPr** (Science de la
programmation) course of class **1CI** at the **Lycée des Arts et Métiers**,
covered during the **2026/27** school year.

Every project is a standalone NetBeans Java application. Most projects are
built around Swing, while some exercises are console programs.

## Projects

| Folder | Description |
| --- | --- |
| `H5_WordStats` | Live word statistics with HashMap and ArrayList versions, plus a complexity comparison (packaged version). |
| `H5_WordsStats` | Earlier default-package draft of the word statistics exercise. |
| `H6_Vectors` | Vector drawing app: `Drawing` holds `Shape` objects (`Line`, `Rectangle`, `Oval`). |
| `F06_Checkers` | Checkers board with pieces (carried over from 2CI). |
| `MyList` | Hand-written singly linked list of integers (add, insert, delete, sort, search) with a Swing tester. |
| `mocktest1ci` | Mock test: print-desk job queue (contains a revised `PrintDeskMock`). |
| `OrbitPatrolMock` | Mock test: shooter game with a linked list of objects, sorting, and save/load. |
| `PrintDeskMock` | Mock test: print-job queue as a linked list with urgent jobs, priority sorting, and file save. |

## Building

Each folder is a NetBeans Ant project. Open it in NetBeans and run the main
class, or build from the project root with `ant jar` and run the produced
JAR from `dist/`.

## License

Everything in this repository is free to use. You may copy, modify, and
redistribute any of the code without restriction.