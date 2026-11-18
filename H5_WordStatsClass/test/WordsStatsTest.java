import java.util.HashMap;
import java.util.Random;

/** Checks classroom whitespace rules, counts and descending frequency order. */
public class WordsStatsTest {
    private static int checks;
    private static final WordsStats map = new WordsStats();
    private static final WordsStatsList list = new WordsStatsList();

    public static void main(String[] args) {
        checkText(""); checkText(" \n\t");
        checkText("chat chien CHAT chat");
        checkText("hello hello, hello. 42 42"); // Punctuation stays in each token.
        checkText("été ÉTÉ");
        Random random = new Random(18);
        String[] words = {"one", "two", "THREE", "four", "four,", "42"};
        for (int run = 0; run < 300; run++) {
            StringBuilder text = new StringBuilder();
            for (int i = random.nextInt(100); i > 0; i--) {
                text.append(words[random.nextInt(words.length)]).append(i % 2 == 0 ? "\n" : "  ");
            }
            checkText(text.toString());
        }
        checkText("one one"); checkText("one"); checkText("");
        System.out.println("WordsStatsClassTest: " + checks + " checks passed");
    }

    private static void checkText(String text) {
        HashMap<String, Integer> expected = new HashMap<>();
        String normalized = text.toLowerCase().trim();
        if (!normalized.isEmpty()) {
            for (String word : normalized.split("\\s+")) expected.merge(word, 1, Integer::sum);
        }
        map.calculate(text); list.calculate(text);
        checkOutput(map.toString(), expected); checkOutput(list.toString(), expected);
    }

    private static void checkOutput(String output, HashMap<String, Integer> expected) {
        HashMap<String, Integer> actual = new HashMap<>();
        int previous = Integer.MAX_VALUE;
        for (String line : output.split("\n")) {
            if (line.isEmpty()) continue;
            int separator = line.lastIndexOf(" ---> ");
            String word = line.substring(0, separator);
            int count = Integer.parseInt(line.substring(separator + 6));
            if (count > previous || actual.put(word, count) != null) throw new AssertionError(output);
            previous = count;
        }
        if (!actual.equals(expected)) throw new AssertionError("Wrong output: " + output);
        checks++;
    }
}
