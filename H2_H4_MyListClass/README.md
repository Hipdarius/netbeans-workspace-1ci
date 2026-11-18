# H2–H4 · MyList classroom project

[← Study desk](../README.md) · [H2–H7 revision notes](../docs/STUDY_GUIDE.md)

This is your original NetBeans classroom project, extended in the same style to cover H2–H4 of the sheet. `Node` stores an `Object` and a reference to the next node. `MyList` keeps `root`, `last` and `size`.

Open the project in NetBeans and run `MainFrame`. The existing GUI is a simple **integer tester**. The underlying `MyList` class also supports other Objects, as requested by the UML. The window uses `toIntegerArray()` because its JList contains integers; do not call that helper on a list of strings.

## Methods to revise

- H2: `add`, `size`, `remove(int)`, `remove(Object)`, `indexOf`, `print`, `clear`, `get`, private `getNode`, `toArray`, `insert(Object, int)` and `set(Object, int)`.
- H3: `count`, `sort`, `verifySort`, `reverse`, `isEmpty`, `toJson`, and `toXML`.
- H4: `last` and `size` are updated when adding, inserting, removing, clearing and reversing. Appending and reading size are O(1).

The sheet uses **value first**: `list.insert(10, 1)` inserts value 10 at index 1; `list.set(10, 1)` replaces index 1 with 10. The old classroom index-first insertion is available under the different name `insertAtIndex(index, value)`; `replace(index, value)` and `delete(index)` remain for the GUI. With both `remove` overloads, `remove(2)` removes index 2, whereas `remove(Integer.valueOf(2))` removes the first value 2.

Selection sort uses the original nested-loop approach. Sort only mutually comparable values, such as all integers or all strings; null sorts first. `toArray()` copies the list structure, not the objects themselves. JSON uses the included Gson library, as in H6. XML stores each object's text in an `<item>` and represents null separately; it is an export, not a typed Java-object round trip.

The older `getRoot()` method remains for class compatibility. Avoid changing node links directly: doing so bypasses MyList's updates to `last` and `size`.

## Checks

From the repository root run `powershell -NoProfile -ExecutionPolicy Bypass -File scripts/verify-study.ps1 -AllProjects`. The list checks cover boundary indices, different Object values, JSON/XML parsing, head/tail changes, reverse followed by append, and the original integer operations. Build with NetBeans or `ant jar`. Keep `dist/lib` beside the JAR.
