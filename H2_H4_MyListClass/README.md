# MyList · Original classroom exercise

[← Study desk](../README.md)

This preserves the original integer-only linked list and its Swing tester.
It is useful classroom material, but **does not fully implement H2–H4** of the supplied 1CI sheet.

For complete Object-based versions, use [H2](../X4_H2_LinkedList/), [H3](../X5_H3_LinkedListContinued/) and [H4](../X6_H4_LinkedListImproved/).

Important differences: this class uses `delete(index)`, `replace(index, data)` and `insert(index, data)`;
the sheet uses `remove(index)`, `set(value, index)` and `insert(value, index)`.
Its size method traverses the chain, `sizeConstant` reads the cached count, and append still traverses because there is no last pointer.

Run `MainFrame` in NetBeans. Existing regression checks cover bounds and basic mutations, not all sheet requirements.
