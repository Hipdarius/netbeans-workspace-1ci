# H.8 German spell checker

Open this existing folder as a project in NetBeans and run it with **Run Project** (F6). The project uses JDK 23, as configured in its NetBeans properties. The supplied `DE_Wortliste_UTF8.txt` must stay in the project folder, which is the default working directory when running from NetBeans.

Wait until the status says the word list has loaded. Type or paste a German text into the upper text area and click **Check and compare**. The lower area lists each unknown occurrence, with its position in the original text, and the elapsed analysis times for both structures. Correct the upper text and run the check again.

`SpellChecker` loads the words into both an `ArrayList` and a `TreeSet` and uses one shared `analyseText` method. The comparison includes tokenization and lookup, but not file loading. Times vary between runs and are illustrative rather than a rigorous benchmark. The list has over 1.6 million entries, so the linear `ArrayList` search can take a long time on long texts; the work runs in the background to keep the window responsive.

Words are sequences of Unicode letters and combining marks. Lookup ignores case, and punctuation (including hyphens) separates words. Result positions are 1-based and inclusive, measured in Java UTF-16 text offsets. A word absent from the supplied list is reported as unknown even if it is valid German. `MainFrame.form` contains the components made in the NetBeans GUI designer; `SpellCheckerController` connects them to the model.
