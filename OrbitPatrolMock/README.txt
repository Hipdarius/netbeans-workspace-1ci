ORBIT PATROL - 60-mark mock test / Java 11+ / NetBeans Java with Ant

START HERE
1. Unzip the archive completely; do not open files inside the ZIP.
2. NetBeans: File > Open Project > select OrbitPatrolMock (the folder with build.xml).
   Import Project from ZIP is also possible. Do not open only the src folder.
3. Run Project. Controls and an empty canvas are expected before completing the TODOs.
4. Read docs/Orbit_Patrol_Mock_Test.pdf. Do the 12-mark paper UML BEFORE opening source.
5. Complete TODO C1-C8 only. Preserve supplied code and method signatures.

GUI DESIGNER
MainFrame.java and DrawPanel.java both have matching .form files.
Open either and select Design. All controls, listeners and layout are supplied.
Keep your work outside initComponents and the guarded variables declaration.
The custom DrawPanel can be compiled with Build Project before opening MainFrame Design.
If NetBeans requests a platform, choose an installed JDK 11, 17, 21 or newer.
If Java with Ant projects are disabled, enable the Java SE/Ant support in your IDE.
Fallback: create Java with Ant > Java Application; copy src/orbitpatrol, including .form files,
into Source Packages, and set orbitpatrol.MainFrame as the main class.
Or run run.bat from a Windows terminal with a JDK on PATH.

COMPLETION ORDER
UML on paper -> C1 -> C2 -> C3 -> C7 -> C5 -> C4 -> C6 -> C8.
Complete C1, C2, C3 and C7 before expecting moving objects.
Use Example, Start, aim with the mouse, click to shoot, then Pause.
Save and Load automatically pause the timer; loading stays paused until Start.

RULES
Use your own Node chain; no java.util.LinkedList or ArrayList for object storage.
Arrays are allowed only for the supplied text parser. They must not replace list algorithms.
The single Player is separate from the targets/bullets list. Objects supplied to add are non-null.
20 ms is a nominal 50 timer callbacks/second, not a guaranteed frame rate.

FILES
docs/Orbit_Patrol_Mock_Test.pdf = instructions, marks, target screenshot and UML task.
docs/Completed_Game_Reference.png = a render of the completed Swing game components.
docs/UML_Exercise_Reference.png = a Swing scene for the separate paper modelling task.
example_save.txt = valid example of the required file format.

This is original practice based on your provided course, classroom Node/MyList and UML example.
It is not an official test or a claim to know tomorrow's questions.
