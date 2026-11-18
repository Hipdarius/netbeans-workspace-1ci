# Classroom code review · 18 November 2026

[Project index](../README.md) · [Revision notes](STUDY_GUIDE.md)

Compared today's project source changes with the previous 1CI commit and with the earlier saved classroom versions. The comparison covered model fields, data structures, method signatures, loop logic, Swing event handlers, and the generated NetBeans forms.

| Project | Review result |
| :--- | :--- |
| [MyList](../H2_H4_MyListClass/) | Restored integer nodes, integer arrays, index-first insertion and the original selection sort. The integer-based additions use direct node traversal for counting, reversal and order checking. The H4 tail and size are maintained on every list mutation. |
| [H5 Words Stats](../H5_WordStatsClass/) | Restored the earlier counting classes and matching NetBeans form, with keyReleased on the input and actionPerformed on the selector. Output remains read-only. |
| [H6 Vectors](../H6_Vectors/STUDY.md) | No source changes today. The existing source uses mouse events, shape subclasses and an ArrayList. Its earlier Gson serialization and shared stroke/fill color differ from the PDF. Oval selection uses a 360-segment approximation. |
| [H7 Performance Tests](../H7_PerformanceTests/) | Kept the original array and three collections. The completed searches use ordinary loops and built-in collection methods. The array stream was removed. |
| [X1 PrintDesk](../X1_PrintDeskMock/) and [X2 Orbit Patrol](../X2_OrbitPatrolMock/) | No source changes today. These remain practice projects. |
| [X3 Word Stats](../X3_WordStatsAI/) and X4–X6 | AI-supported alternatives. X4–X6 cover the worksheet's Object-based linked list. |
| [X7 Telephone Book](../X7_H1_TelephoneBook/) | The entire H1 implementation was created as an AI-supported reference. The repository contains no earlier classroom H1 source. |

The original classroom MyList used ints. The worksheet uses Object and specifies a different insertion argument order. Use the classroom project to revise its integer implementation. Use X4–X6 when checking the worksheet's exact signatures. The MyList completion and H7 search additions follow the classroom patterns but were authored during these edits.

Run `scripts/verify-study.ps1` for the revision projects and add `-AllProjects` to include the supplementary projects. The checks exercise list mutations and Swing actions, H5 counting and sorting, H6 drawings, H7 searches, and the applicable X-project regressions.
