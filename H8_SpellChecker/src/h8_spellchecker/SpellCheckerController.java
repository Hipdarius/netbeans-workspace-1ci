package h8_spellchecker;

import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ExecutionException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;

/** Connects the form designer's components to the spell-checking model. */
public class SpellCheckerController {
    private final JTextArea input;
    private final JTextArea output;
    private final JButton check;
    private final JLabel status;
    private SpellChecker checker;

    public SpellCheckerController(JTextArea input, JTextArea output, JButton check, JLabel status) {
        this.input = input;
        this.output = output;
        this.check = check;
        this.status = status;
        loadWords();
    }

    private void loadWords() {
        check.setEnabled(false);
        new SwingWorker<SpellChecker, Void>() {
            @Override
            protected SpellChecker doInBackground() throws Exception {
                return new SpellChecker(Paths.get("DE_Wortliste_UTF8.txt"));
            }
            @Override
            protected void done() {
                try {
                    checker = get();
                    status.setText("Loaded " + checker.getArraySize() + " words. Enter text and click Check.");
                    check.setEnabled(true);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    status.setText("Loading interrupted.");
                } catch (ExecutionException ex) {
                    status.setText("Could not load DE_Wortliste_UTF8.txt from the project folder.");
                    output.setText(ex.getCause().toString());
                }
            }
        }.execute();
    }

    public void checkText() {
        String text = input.getText();
        check.setEnabled(false);
        status.setText("Checking... ArrayList searches may take a while.");
        new SwingWorker<String, Void>() {
            @Override
            protected String doInBackground() {
                return compareDictionaries(text);
            }
            @Override
            protected void done() {
                try {
                    output.setText(get());
                    output.setCaretPosition(0);
                    status.setText("Finished. Edit the input and check again.");
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    status.setText("Checking interrupted.");
                } catch (ExecutionException ex) {
                    status.setText("Checking failed.");
                    output.setText(ex.getCause().toString());
                } finally {
                    check.setEnabled(true);
                }
            }
        }.execute();
    }

    private String compareDictionaries(String text) {
        long start = System.nanoTime();
        List<SpellChecker.Misspelling> listResult = checker.analyseText(text, false);
        double listTime = (System.nanoTime() - start) / 1_000_000.0;

        start = System.nanoTime();
        List<SpellChecker.Misspelling> treeResult = checker.analyseText(text, true);
        double treeTime = (System.nanoTime() - start) / 1_000_000.0;

        String times = String.format("ArrayList: %.3f ms%nTreeSet: %.3f ms%n", listTime, treeTime);
        if (listResult.size() != treeResult.size()) {
            times += "Warning: result counts differ.\n";
        }
        return times + formatMisspellings(treeResult);
    }

    private String formatMisspellings(List<SpellChecker.Misspelling> mistakes) {
        StringBuilder result = new StringBuilder();
        result.append("Unknown occurrences: ").append(mistakes.size()).append("\n");
        result.append("Positions are 1-based, inclusive (Java text offsets).\n\n");
        for (SpellChecker.Misspelling mistake : mistakes) {
            result.append(mistake).append("\n");
        }
        return result.toString();
    }
}
