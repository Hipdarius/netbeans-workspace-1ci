import java.lang.reflect.Field;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

/** Exercises document changes WITHOUT keyboard events. The frame is never shown. */
public class LiveUpdateTest {
    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            MainFrame frame = new MainFrame();
            try {
                JTextArea input = (JTextArea) field(frame, "textArea");
                JTextArea output = (JTextArea) field(frame, "resultArea");
                JComboBox<?> version = (JComboBox<?>) field(frame, "versionBox");
                for (int i = 0; i < 2; i++) {
                    version.setSelectedIndex(i);
                    input.setText("cat dog cat");
                    check(output.getText().equals("cat ---> 2\ndog ---> 1\n"));
                    input.replaceRange("", 4, input.getText().length());
                    check(output.getText().equals("cat ---> 1\n"));
                    input.setText("");
                    check(output.getText().isEmpty());
                }
                check(!output.isEditable());
                System.out.println("LiveUpdateTest: 7 checks passed");
            } finally { frame.dispose(); }
        });
    }

    private static Object field(Object target, String name) {
        try {
            Field field = target.getClass().getDeclaredField(name);
            field.setAccessible(true);
            return field.get(target);
        } catch (ReflectiveOperationException ex) { throw new AssertionError(ex); }
    }

    private static void check(boolean result) {
        if (!result) throw new AssertionError("Live statistics did not update correctly");
    }
}
