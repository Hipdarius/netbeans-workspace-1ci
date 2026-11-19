# DomeDesk · Devoir en classe 2

**1CI Science de la programmation · 60 points · 180 minutes**

## Sit the two papers in order

1. Print **Mock_Exam_2_Modelling.pdf**. Complete the UML on paper. Hand it in or put it aside within 35 minutes (15 points).
2. Open **Mock_Exam_2_Implementation.pdf** and the starter project. Complete the thirteen numbered questions in the time remaining (45 points). The implementation paper supplies the definitive UML.
3. Save and close your attempt before opening **Mock_Exam_2_Answer_Key.pdf** to mark it.

## Open and run the starter

1. Extract **Mock_Exam_2_NetBeans_Starter.zip** to a writable folder.
2. In Apache NetBeans, choose **File > Open Project**. Select the extracted **DomeDesk** folder containing `build.xml`, `nbproject`, `src` and `data`.
3. Right-click **DomeDesk** in Projects and choose **Set as Main Project** if another project is active. Choose **Clean and Build**, then press **F6** to run.
4. The window titled **DomeDesk - Planetarium programme** opens with an empty running order. The marked methods have placeholder bodies. For example, Load and Save initially report that the relevant exercise is unfinished.
5. Open **Source Packages > lu.scipr.dome.ui > MainFrame.java**. Use **Design** to see the JFrame and its components, or **Source** for the two B13 methods. The companion `.form` file provides the editable NetBeans layout.

The project uses the Java SE tooling in NetBeans and targets Java 11. Use a configured JDK that supports this target. Swing and the file-handling classes come with the JDK. All project data is local.

## Find your work

Search the project for `// TODO B`. A question can cover two related methods.

| Source file | Questions | Methods |
| --- | --- | --- |
| `Show.java` | B1, B3 | `compareTo`, `getRank`, `toString` |
| `LiveShow.java` | B2 | `occupiedMinutes` |
| `Schedule.java` | B4–B7 | `addInOrder`, `getNode`, `swap`, `sortByRequest` |
| `Schedule.java` | B8–B10 | `plan`, `toArray`, `countByGroup` |
| `Schedule.java` | B11–B12 | `saveToFile`, `loadFromFile` |
| `MainFrame.java` | B13 | `updateView`, `updateButtons` |

The model classes are in `lu.scipr.dome.model`. The supplied `Node.java`, Swing component layout and event handlers are complete.

## Sample files

- **data/morning.dome** contains four bookings in file order. Part B, pages 4 and 5, shows the expected timetable and the exact file contents.
- **data/broken.dome** starts with a valid booking and has an invalid second booking. Use it to check what happens to an existing timetable after a failed load.

## Before handing in

- [ ] Build the project and launch the JFrame.
- [ ] Try an empty list, an insertion at each end and two requests with the same ordering key.
- [ ] Load the sample programme, plan it and compare each row with the printed example.
- [ ] Select and clear a row. Check the Plan and Details buttons in each state.
- [ ] Save to a fresh filename. Check the field order, separators and line endings. Reload the saved file.
- [ ] Try the malformed sample with a timetable already on screen.
- [ ] Hand in the modelling sheet and the complete edited **DomeDesk** project folder.

