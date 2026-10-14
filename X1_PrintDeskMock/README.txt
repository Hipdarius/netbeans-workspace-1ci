PRINTDESK MOCK TEST - NETBEANS GUI FORM STARTER

1. Extract the ZIP completely (do not open files inside the ZIP).
2. NetBeans: File > Open Project > select PrintDeskMock.
3. Use JDK 11 or newer; Java SE and GUI Builder support must be enabled.
4. Build Project once so the custom exam.DrawPanel component is compiled.
5. Source Packages > exam > MainFrame: open it, then click Design.
   MainFrame.java + MainFrame.form are a JFrame Form, with all controls supplied.
6. Open DrawPanel and click Design: it is a JPanel Form.
   The panel is already embedded inside MainFrame's drawingScrollPane.
7. Drag Swing components from the Palette into the form; use Properties and
   Events to change them. BorderLayout allows one component in each region;
   use nested panels for extra controls. Input rows use FlowLayout.
8. Complete TODO C1-C7 in Source view, outside generated guarded sections.
   NetBeans owns initComponents and component declarations. Leave those intact.
   Double-click a button in Design to open its supplied action handler, which
   calls the relevant onAdd/onDelete/onFind/onSave method.
9. Run Project: exam.MainFrame is the main class.

Complete C1 before pressing Example. The initial window is intentionally empty.
Workshop, GUI layout, and listener registration are supplied and earn no marks.
The exercise and marks in PrintDesk_Mock_Test.pdf remain the same.
Implement C5 in DrawPanel.paintComponent: remember super.paintComponent(g).
No automatic GUI timer is required; Tick 1 min is the supplied simulation control.
Submit your project and handwritten UML, plus task/time/error log.
Do not edit Workshop to work around unfinished queue methods.

If Design is missing, check that BOTH .java and .form files are present beside
each other in src/exam, and that you opened the full project after extracting.
If NetBeans requests a Java platform, select your installed JDK 11+.
