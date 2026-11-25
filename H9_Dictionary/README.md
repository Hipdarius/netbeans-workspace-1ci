# H.9 Dictionary

Open this existing project in NetBeans and choose Run Project. Type a word or phrase, select the structure and direction, then click Translate or press Enter. The supplied UTF-8 file is included as a project resource.

`DictionaryModel` offers HashMap, TreeMap and TreeSet implementations. It accepts whole words or phrases, ignores case and surrounding spaces, and returns all distinct translations. The GUI uses the components in the NetBeans form; its event handlers and component settings are outside the generated code. A model is loaded on first use of each structure and cached for later searches.

A map cannot store several separate values under one key: adding a second value would overwrite the first. Here each key maps to a set of translations. A TreeSet also discards elements that compare equal, so its comparison includes both the source word and translation; comparing only the source word would lose translations. Identical pairs are stored once.

Both indexes use extra memory. If only German-to-French lookup needed to be fast, one could retain the German index alone and scan all pairs for French-to-German lookup, saving an index at the cost of a linear reverse search. HashMap has average constant-time key lookup; TreeMap takes logarithmic time; TreeSet orders word/translation pairs and starts searching at the requested word, giving logarithmic positioning plus the number of matches.
