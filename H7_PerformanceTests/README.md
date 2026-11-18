# H7 · Performance tests

Open this project in NetBeans and run `H7_PerformanceTests` with F6. It builds an array of one million integers in [0, 999], copies them into an ArrayList, HashSet and HashMap, and compares searches of their values. The HashMap uses the original array index as its key.

The values 997 and 998 are reserved for the first and middle array positions, so duplicate random numbers cannot move their first occurrence. The missing value is 1000. A HashSet removes duplicates, and neither its iteration order nor a HashMap's iteration order is array position order; beginning/middle describe the original array and ArrayList only.

The program compares handwritten linear scans with `ArrayList.contains`, `HashMap.containsValue` and a stream over the array. `HashSet.contains` uses hash lookup, rather than a linear search, and is labeled accordingly. `HashMap.containsKey` would search indices, not stored values. One timing is not a benchmark: JVM warm-up, memory allocation and machine activity affect results. Re-run the program and use the same data when comparing.

For array/list linear search the best case is O(1) and the worst is O(n). A manual scan of the set or map values is linear in the elements and may also depend on hash-table capacity. HashSet's `contains` is O(1) on average under normal hashing assumptions. The set has at most 1000 distinct elements; the list and map each have one million entries.
