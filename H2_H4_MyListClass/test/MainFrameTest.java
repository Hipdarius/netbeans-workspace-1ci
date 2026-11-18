import java.lang.reflect.Field;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class MainFrameTest {
    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            MainFrame frame = new MainFrame();
            try {
                JList<?> list = (JList<?>) field(frame, "listBox");
                JLabel size = (JLabel) field(frame, "sizeLabel");
                JTextField input = (JTextField) field(frame, "textBox");
                JTextField replace = (JTextField) field(frame, "replaceBox");
                check(list.getModel().getSize() == 5 && size.getText().equals("5"));
                input.setText("12");
                ((JButton) field(frame, "addButton")).doClick();
                check(list.getModel().getSize() == 6 && size.getText().equals("6"));
                ((JButton) field(frame, "sortButton")).doClick();
                check(list.getModel().getElementAt(0).equals(3));
                list.setSelectedIndex(0); replace.setText("-5"); replace.postActionEvent();
                check(list.getModel().getElementAt(0).equals(-5));
                ((JButton) field(frame, "clearButton")).doClick();
                check(list.getModel().getSize() == 5 && size.getText().equals("5"));
                ((JButton) field(frame, "clearAllButtons")).doClick();
                check(list.getModel().getSize() == 0 && size.getText().equals("0"));
                ((JButton) field(frame, "fillRandom")).doClick();
                check(list.getModel().getSize() == 10 && size.getText().equals("10"));
                System.out.println("MainFrameTest: 7 checks passed");
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
        if (!result) throw new AssertionError("List tester did not update correctly");
    }
}
