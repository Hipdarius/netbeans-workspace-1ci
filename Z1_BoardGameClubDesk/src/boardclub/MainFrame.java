package boardclub;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class MainFrame extends JFrame {
    private static final String ROSTER_FILE = "players.json";
    private final PlayerList players = new PlayerList();
    private final NotesTools notes = new NotesTools();
    private final Board board = new Board(6);
    private Player editing;
    private Color fillColor = new Color(63, 140, 180);
    private int dragRow = -1;
    private int dragCol = -1;

    public MainFrame() {
        initComponents();
        boardPanel.setBoard(board);
        try {
            notes.loadWords("words-de.txt");
            notes.loadDictionary("dictionary-de-fr.txt");
        } catch (IOException ex) {
            statusLabel.setText("Could not read the language files.");
        }
        updateView();
        setLocationRelativeTo(null);
    }

    private void updateView() {
        // TODO E3
    }

    private void clearSelection() {
        rosterList.clearSelection();
        editing = null;
        checkedInBox.setSelected(false);
        checkedInBox.setEnabled(false);
        emailLabel.setText("Select a player to edit check-in.");
    }

    private void refreshNotes() {
        String text = notesArea.getText();
        StringBuilder result = new StringBuilder();
        int mode = modeBox.getSelectedIndex();
        if (mode <= 1) {
            for (StatsEntry entry : notes.countWords(text, mode == 0)) {
                result.append(entry).append('\n');
            }
        } else if (mode <= 3) {
            for (WordHit hit : notes.findUnknown(text, mode == 3)) {
                result.append(hit).append('\n');
            }
        } else {
            ArrayList<WordHit> words = notes.words(text);
            if (!words.isEmpty()) {
                for (String translation : notes.translations(words.get(0).word)) {
                    result.append(translation).append('\n');
                }
            }
        }
        notesResultArea.setText(result.toString());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        rosterLabel = new javax.swing.JLabel();
        rosterList = new javax.swing.JList<>();
        rosterScroll = new javax.swing.JScrollPane();
        emailLabel = new javax.swing.JLabel();
        checkedInBox = new javax.swing.JCheckBox();
        doneButton = new javax.swing.JButton();
        addButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        sortButton = new javax.swing.JButton();
        reverseButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        loadButton = new javax.swing.JButton();
        notesLabel = new javax.swing.JLabel();
        modeBox = new javax.swing.JComboBox<>();
        notesArea = new javax.swing.JTextArea();
        notesScroll = new javax.swing.JScrollPane();
        notesResultLabel = new javax.swing.JLabel();
        notesResultArea = new javax.swing.JTextArea();
        notesResultScroll = new javax.swing.JScrollPane();
        boardLabel = new javax.swing.JLabel();
        boardHelpLabel = new javax.swing.JLabel();
        markerBox = new javax.swing.JComboBox<>();
        fillButton = new javax.swing.JButton();
        boardPanel = new boardclub.BoardPanel();
        statusLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Board Game Club Desk");

        rosterLabel.setText("Player roster");

        rosterList.setSelectionMode(0);
        rosterList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            @Override
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                rosterListValueChanged(evt);
            }
        });

        rosterScroll.setViewportView(rosterList);

        emailLabel.setText("Select a player to edit check-in.");

        checkedInBox.setText("Checked in");
        checkedInBox.setEnabled(false);

        doneButton.setText("Done");
        doneButton.setEnabled(false);
        doneButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doneButtonActionPerformed(evt);
            }
        });

        addButton.setText("Add");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        deleteButton.setText("Delete");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        sortButton.setText("Sort A-Z");
        sortButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sortButtonActionPerformed(evt);
            }
        });

        reverseButton.setText("Reverse");
        reverseButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reverseButtonActionPerformed(evt);
            }
        });

        saveButton.setText("Save JSON");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        loadButton.setText("Load JSON");
        loadButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadButtonActionPerformed(evt);
            }
        });

        notesLabel.setText("German notes");

        modeBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"Counts (HashMap)", "Counts (ArrayList)", "Spelling (ArrayList)", "Spelling (TreeSet)", "Translate first word"}));
        modeBox.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modeBoxActionPerformed(evt);
            }
        });

        notesArea.setRows(5);
        notesArea.setColumns(18);
        notesArea.setLineWrap(true);
        notesArea.setWrapStyleWord(true);
        notesArea.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                notesAreaKeyReleased(evt);
            }
        });

        notesScroll.setViewportView(notesArea);

        notesResultLabel.setText("Results");

        notesResultArea.setRows(5);
        notesResultArea.setColumns(18);
        notesResultArea.setLineWrap(true);
        notesResultArea.setWrapStyleWord(true);
        notesResultArea.setEditable(false);

        notesResultScroll.setViewportView(notesResultArea);

        boardLabel.setText("Game board");

        boardHelpLabel.setText("Click to place. Drag to move.");

        markerBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"Circle", "Square"}));

        fillButton.setText("Fill colour");
        fillButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fillButtonActionPerformed(evt);
            }
        });

        boardPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                boardPanelMousePressed(evt);
            }
            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                boardPanelMouseReleased(evt);
            }
        });

        statusLabel.setText("Ready");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(statusLabel, -2, -2, -2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rosterLabel, -2, -2, -2)
                            .addComponent(rosterScroll, -2, 274, -2)
                            .addComponent(emailLabel, -2, -2, -2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(checkedInBox, -2, -2, -2)
                                .addGap(8)
                                .addComponent(doneButton, -2, -2, -2))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(addButton, -2, -2, -2)
                                .addGap(8)
                                .addComponent(deleteButton, -2, -2, -2)
                                .addGap(8)
                                .addComponent(sortButton, -2, -2, -2)
                                .addGap(8)
                                .addComponent(reverseButton, -2, -2, -2))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(saveButton, -2, -2, -2)
                                .addGap(8)
                                .addComponent(loadButton, -2, -2, -2)))
                        .addGap(18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(notesLabel, -2, -2, -2)
                            .addComponent(modeBox, 0, 235, 32767)
                            .addComponent(notesScroll, 0, 235, 32767)
                            .addComponent(notesResultLabel, -2, -2, -2)
                            .addComponent(notesResultScroll, 0, 235, 32767))
                        .addGap(18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(boardLabel, -2, -2, -2)
                            .addComponent(boardHelpLabel, -2, -2, -2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(markerBox, -2, -2, -2)
                                .addGap(8)
                                .addComponent(fillButton, -2, -2, -2))
                            .addComponent(boardPanel, 0, 322, 32767))))
                .addGap(14)));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(rosterLabel, -2, -2, -2)
                        .addGap(8)
                        .addComponent(rosterScroll, 0, 244, 32767)
                        .addGap(8)
                        .addComponent(emailLabel, -2, -2, -2)
                        .addGap(8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(checkedInBox, -2, -2, -2)
                            .addComponent(doneButton, -2, -2, -2))
                        .addGap(8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(addButton, -2, -2, -2)
                            .addComponent(deleteButton, -2, -2, -2)
                            .addComponent(sortButton, -2, -2, -2)
                            .addComponent(reverseButton, -2, -2, -2))
                        .addGap(8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(saveButton, -2, -2, -2)
                            .addComponent(loadButton, -2, -2, -2)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(notesLabel, -2, -2, -2)
                        .addGap(8)
                        .addComponent(modeBox, -2, -2, -2)
                        .addGap(8)
                        .addComponent(notesScroll, 0, 142, 32767)
                        .addGap(8)
                        .addComponent(notesResultLabel, -2, -2, -2)
                        .addGap(8)
                        .addComponent(notesResultScroll, 0, 142, 32767))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(boardLabel, -2, -2, -2)
                        .addGap(8)
                        .addComponent(boardHelpLabel, -2, -2, -2)
                        .addGap(8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(markerBox, -2, -2, -2)
                            .addComponent(fillButton, -2, -2, -2))
                        .addGap(8)
                        .addComponent(boardPanel, 0, 322, 32767)))
                .addGap(8)
                .addComponent(statusLabel, -2, -2, -2)
                .addGap(14)));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rosterListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_rosterListValueChanged
        if (!evt.getValueIsAdjusting()) {
            editing = rosterList.getSelectedValue();
            checkedInBox.setEnabled(editing != null);
            checkedInBox.setSelected(editing != null && editing.isCheckedIn());
            doneButton.setEnabled(editing != null);
            emailLabel.setText(editing == null ? "Select a player to edit check-in." : editing.getEmail());
        }
    }//GEN-LAST:event_rosterListValueChanged

    private void doneButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doneButtonActionPerformed
        if (editing != null) {
            editing.setCheckedIn(checkedInBox.isSelected());
            clearSelection();
            updateView();
        }
    }//GEN-LAST:event_doneButtonActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        String name = JOptionPane.showInputDialog(this, "Name");
        if (name == null || name.isBlank()) {
            return;
        }
        String phone = JOptionPane.showInputDialog(this, "Phone");
        if (phone == null) {
            return;
        }
        String email = JOptionPane.showInputDialog(this, "Email");
        if (email == null) {
            return;
        }
        players.sortByName();
        players.addInOrder(new Player(name.trim(), phone.trim(), email.trim()));
        clearSelection();
        updateView();
    }//GEN-LAST:event_addButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        int index = rosterList.getSelectedIndex();
        if (index >= 0 && JOptionPane.showConfirmDialog(this, "Delete the selected player?",
                "Delete player", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            players.remove(index);
            clearSelection();
            updateView();
        }
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void sortButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sortButtonActionPerformed
        players.sortByName();
        clearSelection();
        updateView();
    }//GEN-LAST:event_sortButtonActionPerformed

    private void reverseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reverseButtonActionPerformed
        players.reverse();
        clearSelection();
        updateView();
    }//GEN-LAST:event_reverseButtonActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        try {
            players.saveToFile(ROSTER_FILE);
            statusLabel.setText("Saved " + ROSTER_FILE + ".");
        } catch (IOException ex) {
            statusLabel.setText("Could not save " + ROSTER_FILE + ".");
        }
    }//GEN-LAST:event_saveButtonActionPerformed

    private void loadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadButtonActionPerformed
        try {
            players.loadFromFile(ROSTER_FILE);
            clearSelection();
            updateView();
            statusLabel.setText("Finished loading " + ROSTER_FILE + ".");
        } catch (IOException | RuntimeException ex) {
            statusLabel.setText("Could not load " + ROSTER_FILE + ".");
        }
    }//GEN-LAST:event_loadButtonActionPerformed

    private void modeBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modeBoxActionPerformed
        refreshNotes();
    }//GEN-LAST:event_modeBoxActionPerformed

    private void notesAreaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_notesAreaKeyReleased
        refreshNotes();
    }//GEN-LAST:event_notesAreaKeyReleased

    private void fillButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fillButtonActionPerformed
        Color chosen = JColorChooser.showDialog(this, "Marker fill", fillColor);
        if (chosen != null) {
            fillColor = chosen;
        }
    }//GEN-LAST:event_fillButtonActionPerformed

    private void boardPanelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_boardPanelMousePressed
        if (SwingUtilities.isLeftMouseButton(evt)) {
            int row = boardPanel.rowAt(evt.getY());
            int col = boardPanel.colAt(evt.getX());
            if (board.isInside(row, col)) {
                if (board.get(row, col) == null) {
                    Color outline = new Color(29, 57, 72);
                    Marker marker;
                    if (markerBox.getSelectedIndex() == 0) {
                        marker = new CircleMarker(fillColor, outline);
                    } else {
                        marker = new SquareMarker(fillColor, outline);
                    }
                    board.place(marker, row, col);
                    boardPanel.repaint();
                } else {
                    dragRow = row;
                    dragCol = col;
                }
            }
        }
    }//GEN-LAST:event_boardPanelMousePressed

    private void boardPanelMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_boardPanelMouseReleased
        if (SwingUtilities.isLeftMouseButton(evt) && dragRow >= 0) {
            int row = boardPanel.rowAt(evt.getY());
            int col = boardPanel.colAt(evt.getX());
            if (board.move(dragRow, dragCol, row, col)) {
                boardPanel.repaint();
            }
            dragRow = -1;
            dragCol = -1;
        }
    }//GEN-LAST:event_boardPanelMouseReleased

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel rosterLabel;
    private javax.swing.JList<Player> rosterList;
    private javax.swing.JScrollPane rosterScroll;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JCheckBox checkedInBox;
    private javax.swing.JButton doneButton;
    private javax.swing.JButton addButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton sortButton;
    private javax.swing.JButton reverseButton;
    private javax.swing.JButton saveButton;
    private javax.swing.JButton loadButton;
    private javax.swing.JLabel notesLabel;
    private javax.swing.JComboBox<String> modeBox;
    private javax.swing.JTextArea notesArea;
    private javax.swing.JScrollPane notesScroll;
    private javax.swing.JLabel notesResultLabel;
    private javax.swing.JTextArea notesResultArea;
    private javax.swing.JScrollPane notesResultScroll;
    private javax.swing.JLabel boardLabel;
    private javax.swing.JLabel boardHelpLabel;
    private javax.swing.JComboBox<String> markerBox;
    private javax.swing.JButton fillButton;
    private boardclub.BoardPanel boardPanel;
    private javax.swing.JLabel statusLabel;
    // End of variables declaration//GEN-END:variables
}
