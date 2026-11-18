# H1–H5 · Learn the reasoning

[← Project index](../README.md) · [Verification record](REVIEW.md)

These references follow the supplied December 2025 exercise sheet. They are not teacher-issued corrections. Aim to understand the methods well enough to rebuild them on paper or in a fresh project.

## 1 · Telephone book: separate data from the window

Read [Person](../H1_TelephoneBook/src/Person.java), then [PhoneBook](../H1_TelephoneBook/src/PhoneBook.java), then [MainFrame](../H1_TelephoneBook/src/MainFrame.java).

`Person` holds one name, telephone number and email. A telephone number is a `String`: an integer cannot preserve leading zeroes, spaces or `+`.

`PhoneBook` owns an `ArrayList<Person>`. Adding or editing sorts the list by name, then returns the person's new index. The window needs that index because sorting can move the selected entry.

The frame owns `selected`. `-1` means an unsaved new record. Previous/Next change the index; `showEntry()` updates the fields and enables valid navigation buttons. **Save entry** copies the fields into a Person. Field edits are not committed until this button is pressed.

On startup, `load()` reads a JSON array. On close, `save()` writes saved entries to `phonebook.json` in the working directory. Gson handles quotes, accents and JSON syntax; it is an included library, not a built-in JDK class. Loading validates the whole file before replacing the current list. A damaged input file is reported and is not silently overwritten on close.

**Trace it:** add Zoe, then Ada. Ada becomes index 0, Zoe index 1. Rename Ada to Zzz; the edited entry becomes index 1. Delete it: the remaining selection becomes index 0.

**Explain:** Why does the model not contain text fields? Why keep phone numbers as text? Why is JSON an array of objects rather than `toString()` output? What happens if a file does not exist?

## 2 · Linked list: references, not array positions

Read [Node](../H2_LinkedList/src/Node.java) and [MyList](../H2_LinkedList/src/MyList.java).

```text
root ──► [ A | next ] ──► [ B | next ] ──► [ C | null ]
```

`root` references the first Node. A Node contains an `Object` value and a reference to its successor. To reach index 2, start at root and follow next twice. There is no direct indexed access as in an array.

| Operation | What changes |
| :--- | :--- |
| `add(value)` | Find the last node and attach a new node. Empty list: set root. |
| `insert(value, index)` | Set the new node's next to the old successor, then connect its predecessor. At index 0, replace root. |
| `remove(index)` | Connect the predecessor to the removed node's successor. At index 0, advance root. Return the removed value. |
| `remove(value)` | Find the first equal value; remove that position; return whether it existed. |
| `set(value, index)` | Replace the data in a node; links do not change. |
| `clear()` | Set root to null. |
| `toArray()` | Traverse and fill a new array. The array is independent; element objects are shared. |

**Bounds:** get/set/remove accept `0 .. size - 1`. Insert also accepts `size`, meaning append. Negative indices fail. `getNode` is private, matching the UML: callers must not break the chain.

**Two traps:**

- `Objects.equals(a, b)` compares values and handles null. `a == b` compares object references.
- With both overloads, `remove(2)` removes **index 2**. `remove(Integer.valueOf(2))` removes the first **value 2**.

The sheet uses `insert(value, index)` and `set(value, index)`. The original integer-only `MyList` uses different names and argument order: do not mix them up.

## 3 · Extend the list without losing the chain

Read [H3 MyList](../H3_LinkedListContinued/src/MyList.java) after H2.

`count(value)` traverses the list and increments a counter for equal values. `isEmpty()` checks root against null. `verifySort()` compares neighboring values and stops on the first descending pair; empty and single-element lists are sorted.

**Selection sort:** for each position, find the smallest value in the remaining suffix and swap the values. Links stay unchanged. There are roughly n(n−1)/2 comparisons: O(n²) time and O(1) extra space.

Because `Object` has no universal ordering, this reference sorts mutually comparable values, such as all Integers or all Strings. Null sorts first. Mixing an Integer and a String is not a supported sort input. `Comparable` supplies ordering; the cast is necessary because the UML stores Object.

**Reverse:** maintain `previous`, `current` and `next`.

```java
Node next = current.getNext(); // Save the remaining chain first.
current.setNext(previous);    // Reverse this link.
previous = current;
current = next;
```

At the end, root becomes previous. Trace this with three nodes, then with zero and one. Reversing twice must recover the original order.

`toJson()` serializes an array of values, not the internal nodes. `toXML()` exports `<list><item>...</item></list>`; null uses `<item null="true"/>`. Text escapes `&`, `<`, `>`, quotes and apostrophes. XML export represents objects by their text and does not reconstruct their Java types. The sheet specifies no exact export schema; this is the documented choice. XML 1.0-invalid characters are rejected.

## 4 · Faster operations require stronger invariants

Compare [H4 MyList](../H4_LinkedListImproved/src/MyList.java) with H3.

H4 caches `last` and `size`. After **every** change:

```text
Empty:     root == null, last == null, size == 0
Nonempty:  last is the final node, last.next == null,
           size equals the number of reachable nodes
```

Appending connects last to the new node, updates last, and increments size. Every insertion/removal updates size. Removing the tail updates last. Reversing makes the old root the new last. Clear resets all three fields.

| Method | H2 / H3 | H4 | Reason |
| :--- | :--- | :--- | :--- |
| `size()` | O(n) | O(1) | Traversal versus cached number |
| `add(value)` | O(n) | O(1) | Find tail versus stored tail |
| Append using insert | O(n) | O(1) | H4 delegates to add |
| get, set, search | O(n) worst case | O(n) worst case | Still follows links |
| General insert/remove | O(n) worst case | O(n) worst case | Find predecessor |
| Remove first | O(1) | O(1) | Move root |
| Reverse | O(n), H3 | O(n) | Every link changes |
| Selection sort | O(n²), H3 | O(n²) | Tail cache does not help |

Other improvements: generics for compile-time type checks; an iterator to avoid repeated indexed get; merge sort for O(n log n) sorting; a doubly linked list for direct backward navigation/removal when a node is already known. Each adds code or memory. A last pointer alone does **not** make tail removal O(1) in a singly linked list.

## 5 · Word statistics: count first, sort second

Study the [classroom H5](../H5_WordStatsClass/) first. Its core uses loops, arrays, HashMap, ArrayList and a small StatsEntry class.

- `StatsEntry`: one word and its count, initially 1.
- `WordsStats`: key = word, value = StatsEntry. Increment an existing entry or create one.
- `WordsStatsList`: search linearly for the word; increment if found, otherwise append.
- Both calculate methods clear previous counts before recalculating. This matters after deletion.
- Both toString methods use nested-loop sorting, highest frequency first. Equal-frequency order is unspecified and may differ between versions.
- A document listener updates on insert, paste, cut and deletion, including changes without a key event. Switching versions also refreshes results.

**Classroom token rule:** lowercase and split on whitespace. `Hello`, `hello,` and `hello.` become three different tokens; `42` is a token too. The sheet does not prescribe punctuation handling. This keeps the class convention. `X3_WordStatsAI` extracts letters and handles punctuation differently; its word definition is different.

Let N be total tokens and U distinct tokens. Initially treat word comparisons/hash operations as constant cost:

| Work in the classroom implementation | HashMap | ArrayList |
| :--- | :--- | :--- |
| Counting | O(N) average | O(N × U) worst case |
| Manual nested-loop sorting | O(U²) | O(U²) |
| Counting + sorting | O(N + U²) average | O(N × U + U²) worst case |

If all words differ, list searches examine 0 + 1 + ... + (N−1) entries: quadratic counting. HashMap counting is faster on average, **but the classroom display still sorts quadratically**. Do not claim the whole program is O(N).

More precisely, tokenization/lowercasing also scans L characters, hashing/equality inspect word characters, and repeated String `+=` can copy accumulated output repeatedly. The table isolates counting/sorting, not all rendering/allocation costs. Both keep O(U) entries; the split array also holds O(N) tokens and their characters. HashMap adds table overhead. Every edit recalculates all text on the Swing event thread, so this suits small classroom texts rather than large-document benchmarking.

## Practice before looking at the answers

1. Draw the chain before/after insertion at index 0, 1 and size.
2. Write remove(index), considering empty and single-node lists.
3. Reverse `4 → 7 → 2` on paper, showing the three references.
4. Which H4 fields change after deleting the only node?
5. Predict counts for `cat dog CAT`, then delete the last word.
6. Why do fast HashMap lookups not make H5's manual sort fast?
7. Write JSON for two persons with phone numbers containing spaces.
8. Find a sequence exposing a stale last pointer: reverse, then append.

<details>
<summary>Short answer checks</summary>

1. At 0, change root; otherwise reconnect the predecessor. Insert accepts index size.
2. Reject invalid indices before mutation. Removing index 0 advances root. H4 also updates size and last.
3. Save successor before changing next; the final root points to 2, then 7 and 4.
4. root = null, last = null, size = 0.
5. cat = 2, dog = 1; after deletion both counts are 1.
6. Counting and sorting are separate stages. Sorting still compares O(U²) pairs.
7. An array of objects with fullName, phone and email string fields. Quote phone numbers.
8. Reverse `1 → 2`, append 3: the result must be `2 → 1 → 3`, without lost nodes or a cycle.

</details>
