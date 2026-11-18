import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.nio.file.Path;
import javax.swing.*;

/** Hand-written Swing: the event handlers are deliberately small and readable. */
public class MainFrame extends JFrame {
    private final PhoneBook book = new PhoneBook();
    private final Path file = Path.of("phonebook.json");
    private final JTextField name = new JTextField(28);
    private final JTextField phone = new JTextField(28);
    private final JTextField email = new JTextField(28);
    private final JLabel position = new JLabel();
    private final JButton previous = new JButton("Previous");
    private final JButton next = new JButton("Next");
    private final JButton delete = new JButton("Delete");
    private int selected = -1; // -1 means a new, not yet saved entry.
    private boolean loaded = true;

    public MainFrame() {
        super("H1 - Telephone book");
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        JPanel navigation = new JPanel();
        JButton add = new JButton("New");
        navigation.add(previous);
        navigation.add(position);
        navigation.add(next);
        navigation.add(add);
        navigation.add(delete);
        JPanel fields = new JPanel(new GridLayout(3, 2, 10, 10));
        fields.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
        fields.add(new JLabel("Full name")); fields.add(name);
        fields.add(new JLabel("Telephone")); fields.add(phone);
        fields.add(new JLabel("Email")); fields.add(email);
        JButton save = new JButton("Save entry");
        add(navigation, BorderLayout.NORTH);
        add(fields, BorderLayout.CENTER);
        add(save, BorderLayout.SOUTH);
        previous.addActionListener(e -> { selected--; showEntry(); });
        next.addActionListener(e -> { selected++; showEntry(); });
        add.addActionListener(e -> { selected = -1; showEntry(); name.requestFocusInWindow(); });
        save.addActionListener(e -> saveEntry());
        delete.addActionListener(e -> deleteEntry());
        addWindowListener(new WindowAdapter() {
            @Override public void windowClosing(WindowEvent event) { closeBook(); }
        });
        try {
            book.load(file);
        } catch (IOException ex) {
            loaded = false;
            JOptionPane.showMessageDialog(this, ex.getMessage()
                    + "\nThe file will not be overwritten. Repair it and restart.");
        }
        save.setEnabled(loaded);
        if (book.size() > 0) selected = 0;
        showEntry();
        pack();
        setLocationRelativeTo(null);
    }

    private void showEntry() {
        Person person = selected >= 0 ? book.get(selected) : null;
        name.setText(person == null ? "" : person.getFullName());
        phone.setText(person == null ? "" : person.getPhone());
        email.setText(person == null ? "" : person.getEmail());
        position.setText(person == null ? "New entry" : "Record " + (selected + 1) + " of " + book.size());
        previous.setEnabled(selected > 0);
        next.setEnabled(selected < book.size() - 1);
        delete.setEnabled(selected >= 0);
    }

    private void saveEntry() {
        try {
            Person person = new Person(name.getText(), phone.getText(), email.getText());
            selected = selected < 0 ? book.add(person) : book.replace(selected, person);
            showEntry();
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    private void deleteEntry() {
        if (JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete this entry permanently?",
                "Delete entry", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            book.remove(selected);
            selected = Math.min(selected, book.size() - 1);
            showEntry();
        }
    }

    private void closeBook() {
        try {
            if (loaded) book.save(file);
            dispose();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Could not save: " + ex.getMessage()
                    + "\nThe window stays open so you can retry.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}
