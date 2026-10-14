package h5_wordstats;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

/**
 * Tests sans bibliotheque externe : executer la methode main.
 */
public class WordsStatsTest {

    private static int checks = 0;
    private static final WordsStats wordsStats = new WordsStats();

    public static void main(String[] args) {
        checkBoth("", new String[0]);
        checkBoth(" \t\r\n...!? 123 -- ' \u0301", new String[0]);
        checkBoth(null, new String[0]);
        checkBoth("chat chien chat oiseau chien chat",
                new String[]{"chat --> 3", "chien --> 2", "oiseau --> 1"});
        checkBoth("zebra beta alpha beta zebra alpha",
                new String[]{"alpha --> 2", "beta --> 2", "zebra --> 2"});
        checkBoth("ÉCOLE école E\u0301cole Été été A\u0300 à",
                new String[]{"école --> 3", "à --> 2", "été --> 2"});
        checkBoth("L'été, l’été! arc-en-ciel\narc\t42 你好 你好",
                new String[]{"arc --> 2", "l --> 2", "été --> 2",
                    "你好 --> 2", "ciel --> 1", "en --> 1"});
        checkBoth("abc123def abc_def", new String[]{"abc --> 2", "def --> 2"});

        Locale oldLocale = Locale.getDefault();
        try {
            Locale.setDefault(Locale.forLanguageTag("tr-TR"));
            checkBoth("I i", new String[]{"i --> 2"});
        } finally {
            Locale.setDefault(oldLocale);
        }

        checkFreshCalculations();
        checkRandomTexts();
        System.out.println("WordsStatsTest: " + checks + " checks passed.");
    }

    private static void checkBoth(String text, String[] expected) {
        checkEntries(wordsStats.calculateWithHashMap(text), expected);
        checkEntries(wordsStats.calculateWithList(text), expected);
    }

    private static void checkEntries(ArrayList<StatsEntry> actual, String[] expected) {
        check(actual.size() == expected.length, "Incorrect number of distinct words");
        for (int i = 0; i < expected.length; i++) {
            check(actual.get(i).toString().equals(expected[i]),
                    "Expected " + expected[i] + ", got " + actual.get(i));
        }
    }

    private static void checkFreshCalculations() {
        ArrayList<StatsEntry> previousMap = wordsStats.calculateWithHashMap("un un deux");
        ArrayList<StatsEntry> previousList = wordsStats.calculateWithList("un un deux");

        // Simule la suppression d'un mot, puis de tout le texte dans la fenetre.
        checkBoth("un deux", new String[]{"deux --> 1", "un --> 1"});
        checkBoth("", new String[0]);
        checkEntries(previousMap, new String[]{"un --> 2", "deux --> 1"});
        checkEntries(previousList, new String[]{"un --> 2", "deux --> 1"});

        previousMap.get(0).count = 100;
        previousList.clear();
        checkBoth("un un deux", new String[]{"un --> 2", "deux --> 1"});
    }

    private static void checkRandomTexts() {
        Random random = new Random(20260930);
        String[] vocabulary = {"chat", "Chien", "ÉCOLE", "été", "à", "你好", "lune", "soleil"};
        String[] separators = {" ", "\n", ", ", "-", "'", "\t", " 123 "};
        for (int example = 0; example < 300; example++) {
            int wordCount = random.nextInt(250);
            StringBuilder text = new StringBuilder();
            for (int i = 0; i < wordCount; i++) {
                text.append(vocabulary[random.nextInt(vocabulary.length)]);
                text.append(separators[random.nextInt(separators.length)]);
            }

            ArrayList<StatsEntry> mapResult = wordsStats.calculateWithHashMap(text.toString());
            ArrayList<StatsEntry> listResult = wordsStats.calculateWithList(text.toString());
            check(mapResult.size() == listResult.size(), "The versions disagree on result size");
            int total = 0;
            for (int i = 0; i < mapResult.size(); i++) {
                StatsEntry entry = mapResult.get(i);
                check(entry.toString().equals(listResult.get(i).toString()),
                        "The versions disagree on a word count or ordering");
                total += entry.count;
                if (i > 0) {
                    StatsEntry previous = mapResult.get(i - 1);
                    check(previous.count > entry.count
                            || (previous.count == entry.count
                            && previous.word.compareTo(entry.word) < 0),
                            "Results are not sorted or contain duplicate words");
                }
            }
            check(total == wordCount, "The total does not match the number of generated words");
        }
    }

    private static void check(boolean condition, String message) {
        checks++;
        if (!condition) {
            throw new AssertionError(message);
        }
    }
}
