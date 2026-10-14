package exam;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/** Open the Design tab to edit components. Complete TODOs outside guarded code. */
public class MainFrame extends JFrame {
    private Workshop workshop = new Workshop();

    public MainFrame() {
        initComponents();
        drawPanel.setWorkshop(workshop);
        drawPanel.updateDrawingWidth();
        setSize(1000, 650);
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {//GEN-BEGIN:initComponents
        topPanel = new javax.swing.JPanel();
        inputsPanel = new javax.swing.JPanel();
        nameLabel = new javax.swing.JLabel();
        nameField = new javax.swing.JTextField();
        durationLabel = new javax.swing.JLabel();
        durationField = new javax.swing.JTextField();
        urgentBox = new javax.swing.JCheckBox();
        addButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        exampleButton = new javax.swing.JButton();
        resetButton = new javax.swing.JButton();
        actionsPanel = new javax.swing.JPanel();
        startButton = new javax.swing.JButton();
        tickButton = new javax.swing.JButton();
        sortButton = new javax.swing.JButton();
        prioritiseButton = new javax.swing.JButton();
        findLabel = new javax.swing.JLabel();
        findField = new javax.swing.JTextField();
        findButton = new javax.swing.JButton();
        filesPanel = new javax.swing.JPanel();
        fileLabel = new javax.swing.JLabel();
        fileField = new javax.swing.JTextField();
        saveButton = new javax.swing.JButton();
        centrePanel = new javax.swing.JPanel();
        listScrollPane = new javax.swing.JScrollPane();
        listBox = new javax.swing.JList();
        drawingScrollPane = new javax.swing.JScrollPane();
        drawPanel = new exam.DrawPanel();
        statusLabel = new javax.swing.JLabel();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PrintDesk - Mock Test");
        topPanel.setLayout(new java.awt.GridLayout(3, 1));
        nameLabel.setText("Name");
        inputsPanel.add(nameLabel);
        nameField.setColumns(7);
        inputsPanel.add(nameField);
        durationLabel.setText("Minutes");
        inputsPanel.add(durationLabel);
        durationField.setColumns(4);
        inputsPanel.add(durationField);
        urgentBox.setText("Urgent");
        inputsPanel.add(urgentBox);
        addButton.setText("Add");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });
        inputsPanel.add(addButton);
        deleteButton.setText("Delete selected");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });
        inputsPanel.add(deleteButton);
        exampleButton.setText("Example");
        exampleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exampleButtonActionPerformed(evt);
            }
        });
        inputsPanel.add(exampleButton);
        resetButton.setText("Reset");
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });
        inputsPanel.add(resetButton);
        topPanel.add(inputsPanel);
        startButton.setText("Start next");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });
        actionsPanel.add(startButton);
        tickButton.setText("Tick 1 min");
        tickButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tickButtonActionPerformed(evt);
            }
        });
        actionsPanel.add(tickButton);
        sortButton.setText("Sort duration");
        sortButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sortButtonActionPerformed(evt);
            }
        });
        actionsPanel.add(sortButton);
        prioritiseButton.setText("Prioritise");
        prioritiseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prioritiseButtonActionPerformed(evt);
            }
        });
        actionsPanel.add(prioritiseButton);
        findLabel.setText("Find");
        actionsPanel.add(findLabel);
        findField.setColumns(7);
        actionsPanel.add(findField);
        findButton.setText("Find");
        findButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findButtonActionPerformed(evt);
            }
        });
        actionsPanel.add(findButton);
        topPanel.add(actionsPanel);
        fileLabel.setText("File");
        filesPanel.add(fileLabel);
        fileField.setText("queue.csv");
        fileField.setColumns(10);
        filesPanel.add(fileField);
        saveButton.setText("Save waiting");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });
        filesPanel.add(saveButton);
        topPanel.add(filesPanel);
        getContentPane().add(topPanel, java.awt.BorderLayout.NORTH);
        centrePanel.setLayout(new java.awt.BorderLayout());
        listBox.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listScrollPane.setViewportView(listBox);
        centrePanel.add(listScrollPane, java.awt.BorderLayout.CENTER);
        drawingScrollPane.setViewportView(drawPanel);
        centrePanel.add(drawingScrollPane, java.awt.BorderLayout.SOUTH);
        getContentPane().add(centrePanel, java.awt.BorderLayout.CENTER);
        statusLabel.setText("Waiting: 0 | Average: 0.00 min | Completed: 0");
        getContentPane().add(statusLabel, java.awt.BorderLayout.SOUTH);
        pack();
    }//GEN-END:initComponents
    // </editor-fold>

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        onAdd();
    }//GEN-LAST:event_addButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        onDelete();
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void exampleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exampleButtonActionPerformed
        workshop.loadExample(); refreshView();
    }//GEN-LAST:event_exampleButtonActionPerformed

    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        workshop.reset(); refreshView();
    }//GEN-LAST:event_resetButtonActionPerformed

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        workshop.startNext(); refreshView();
    }//GEN-LAST:event_startButtonActionPerformed

    private void tickButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tickButtonActionPerformed
        workshop.tick(); refreshView();
    }//GEN-LAST:event_tickButtonActionPerformed

    private void sortButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sortButtonActionPerformed
        workshop.getQueue().sortByDuration(); refreshView();
    }//GEN-LAST:event_sortButtonActionPerformed

    private void prioritiseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prioritiseButtonActionPerformed
        workshop.getQueue().prioritise(); refreshView();
    }//GEN-LAST:event_prioritiseButtonActionPerformed

    private void findButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findButtonActionPerformed
        onFind();
    }//GEN-LAST:event_findButtonActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        onSave();
    }//GEN-LAST:event_saveButtonActionPerformed
    private void showError(String message) { JOptionPane.showMessageDialog(this,message); }
    private void onAdd() {
        // TODO C6
    }
    private void onDelete() {
        // TODO C6
    }
    private void onFind() {
        // TODO C6
    }
    private void refreshView() {
        // TODO C6
    }
    private void onSave() {
        // TODO C7
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel topPanel;
    private javax.swing.JPanel inputsPanel;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField nameField;
    private javax.swing.JLabel durationLabel;
    private javax.swing.JTextField durationField;
    private javax.swing.JCheckBox urgentBox;
    private javax.swing.JButton addButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton exampleButton;
    private javax.swing.JButton resetButton;
    private javax.swing.JPanel actionsPanel;
    private javax.swing.JButton startButton;
    private javax.swing.JButton tickButton;
    private javax.swing.JButton sortButton;
    private javax.swing.JButton prioritiseButton;
    private javax.swing.JLabel findLabel;
    private javax.swing.JTextField findField;
    private javax.swing.JButton findButton;
    private javax.swing.JPanel filesPanel;
    private javax.swing.JLabel fileLabel;
    private javax.swing.JTextField fileField;
    private javax.swing.JButton saveButton;
    private javax.swing.JPanel centrePanel;
    private javax.swing.JScrollPane listScrollPane;
    private javax.swing.JList listBox;
    private javax.swing.JScrollPane drawingScrollPane;
    private exam.DrawPanel drawPanel;
    private javax.swing.JLabel statusLabel;
    // End of variables declaration//GEN-END:variables
}
