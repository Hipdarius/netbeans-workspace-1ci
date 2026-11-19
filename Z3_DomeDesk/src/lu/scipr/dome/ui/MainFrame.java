package lu.scipr.dome.ui;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import lu.scipr.dome.model.Schedule;
import lu.scipr.dome.model.Show;

public class MainFrame extends javax.swing.JFrame {
    private final Schedule schedule = new Schedule();

    public MainFrame() {
        initComponents();
        updateView();
    }

    private void updateView() {
        // TODO B13
    }

    private void updateButtons() {
        // TODO B13
    }

    private JFileChooser fileChooser() {
        JFileChooser chooser = new JFileChooser(new File("data"));
        chooser.setFileFilter(new FileNameExtensionFilter("Dome programme (*.dome)", "dome"));
        return chooser;
    }

    private void showError(IOException error) {
        JOptionPane.showMessageDialog(this, error.getMessage(), "DomeDesk", JOptionPane.ERROR_MESSAGE);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        requestHeading = new javax.swing.JLabel();
        titleLabel = new javax.swing.JLabel();
        groupLabel = new javax.swing.JLabel();
        kindLabel = new javax.swing.JLabel();
        requestedLabel = new javax.swing.JLabel();
        minutesLabel = new javax.swing.JLabel();
        setupLabel = new javax.swing.JLabel();
        listHeading = new javax.swing.JLabel();
        titleField = new javax.swing.JTextField();
        groupField = new javax.swing.JTextField();
        requestedField = new javax.swing.JTextField();
        minutesField = new javax.swing.JTextField();
        setupField = new javax.swing.JTextField();
        kindBox = new javax.swing.JComboBox<>();
        listScroll = new javax.swing.JScrollPane();
        showList = new javax.swing.JList<>();
        addButton = new javax.swing.JButton();
        sortButton = new javax.swing.JButton();
        planButton = new javax.swing.JButton();
        detailsButton = new javax.swing.JButton();
        countsButton = new javax.swing.JButton();
        loadButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("DomeDesk - Planetarium programme");

        requestHeading.setText("Show request");

        titleLabel.setText("Title");

        groupLabel.setText("Group");

        kindLabel.setText("Type");

        requestedLabel.setText("Requested minute");

        minutesLabel.setText("Show minutes");

        setupLabel.setText("Setup minutes");

        listHeading.setText("Running order");

        requestedField.setText("0");

        minutesField.setText("1");

        setupField.setText("1");
        setupField.setEnabled(false);

        kindBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "R", "L" }));
        kindBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kindBoxActionPerformed(evt);
            }
        });

        showList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        showList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                showListValueChanged(evt);
            }
        });

        listScroll.setViewportView(showList);

        addButton.setText("Add show");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        sortButton.setText("Sort");
        sortButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sortButtonActionPerformed(evt);
            }
        });

        planButton.setText("Plan");
        planButton.setEnabled(false);
        planButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                planButtonActionPerformed(evt);
            }
        });

        detailsButton.setText("Details");
        detailsButton.setEnabled(false);
        detailsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                detailsButtonActionPerformed(evt);
            }
        });

        countsButton.setText("Group counts");
        countsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                countsButtonActionPerformed(evt);
            }
        });

        loadButton.setText("Load...");
        loadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadButtonActionPerformed(evt);
            }
        });

        saveButton.setText("Save...");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(requestHeading, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7)
                        .addComponent(titleField, 60, 155, Short.MAX_VALUE)
                        .addGap(7)
                        .addComponent(groupLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7)
                        .addComponent(groupField, 60, 120, Short.MAX_VALUE)
                        .addGap(7)
                        .addComponent(kindLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7)
                        .addComponent(kindBox, 55, 65, 100))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(requestedLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7)
                        .addComponent(requestedField, 42, 55, Short.MAX_VALUE)
                        .addGap(7)
                        .addComponent(minutesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7)
                        .addComponent(minutesField, 42, 55, Short.MAX_VALUE)
                        .addGap(7)
                        .addComponent(setupLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7)
                        .addComponent(setupField, 42, 55, Short.MAX_VALUE)
                        .addGap(7)
                        .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(listHeading, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(listScroll, 440, 670, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(sortButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(7)
                            .addComponent(planButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(7)
                            .addComponent(detailsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(7)
                            .addComponent(countsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(loadButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(7)
                            .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(12))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(requestHeading, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(8)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(titleField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(groupLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(groupField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(kindLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(kindBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(7)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(requestedLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(requestedField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(minutesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(minutesField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(setupLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(setupField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(16)
                    .addComponent(listHeading, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(5)
                    .addComponent(listScroll, 130, 217, Short.MAX_VALUE)
                    .addGap(8)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sortButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(planButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(detailsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(countsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(loadButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void kindBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kindBoxActionPerformed
        setupField.setEnabled("L".equals(kindBox.getSelectedItem()));
    }//GEN-LAST:event_kindBoxActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        String kind = (String) kindBox.getSelectedItem();
        String setup = "L".equals(kind) ? setupField.getText() : "0";
        String[] fields = {kind, titleField.getText(), groupField.getText(),
            requestedField.getText(), minutesField.getText(), setup};
        try {
            Show show = Schedule.fromFields(fields);
            schedule.sortByRequest();
            schedule.addInOrder(show);
            updateView();
        } catch (IOException ex) {
            showError(ex);
        }
    }//GEN-LAST:event_addButtonActionPerformed

    private void sortButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sortButtonActionPerformed
        schedule.sortByRequest();
        updateView();
    }//GEN-LAST:event_sortButtonActionPerformed

    private void planButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_planButtonActionPerformed
        schedule.plan();
        updateView();
    }//GEN-LAST:event_planButtonActionPerformed

    private void detailsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_detailsButtonActionPerformed
        Show selected = showList.getSelectedValue();
        if (selected != null) {
            String detail = selected.toString() + "\nType: " + selected.getKind()
                    + "\nRequested minute: " + selected.getRequested()
                    + "\nShow minutes: " + selected.getMinutes()
                    + "\nSetup minutes: " + selected.getSetupMinutes();
            JOptionPane.showMessageDialog(this, detail, "Show details", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_detailsButtonActionPerformed

    private void countsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_countsButtonActionPerformed
        HashMap<String, Integer> counts = schedule.countByGroup();
        StringBuilder text = new StringBuilder();
        for (String group : counts.keySet()) {
            text.append(group).append("  ").append(counts.get(group)).append('\n');
        }
        String message = text.length() == 0 ? "No groups." : text.toString();
        JOptionPane.showMessageDialog(this, message, "Group counts", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_countsButtonActionPerformed

    private void loadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadButtonActionPerformed
        JFileChooser chooser = fileChooser();
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                schedule.loadFromFile(chooser.getSelectedFile().getPath());
                updateView();
            } catch (IOException ex) {
                showError(ex);
            }
        }
    }//GEN-LAST:event_loadButtonActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        JFileChooser chooser = fileChooser();
        if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File target = chooser.getSelectedFile();
            if (target.exists() && JOptionPane.showConfirmDialog(this, "Replace this file?",
                    "Save programme", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) {
                return;
            }
            try {
                schedule.saveToFile(target.getPath());
            } catch (IOException ex) {
                showError(ex);
            }
        }
    }//GEN-LAST:event_saveButtonActionPerformed

    private void showListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_showListValueChanged
        if (!evt.getValueIsAdjusting()) {
            updateButtons();
        }
    }//GEN-LAST:event_showListValueChanged

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JButton countsButton;
    private javax.swing.JButton detailsButton;
    private javax.swing.JButton loadButton;
    private javax.swing.JButton planButton;
    private javax.swing.JButton saveButton;
    private javax.swing.JButton sortButton;
    private javax.swing.JComboBox<String> kindBox;
    private javax.swing.JLabel groupLabel;
    private javax.swing.JLabel kindLabel;
    private javax.swing.JLabel listHeading;
    private javax.swing.JLabel minutesLabel;
    private javax.swing.JLabel requestHeading;
    private javax.swing.JLabel requestedLabel;
    private javax.swing.JLabel setupLabel;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JList<lu.scipr.dome.model.Show> showList;
    private javax.swing.JScrollPane listScroll;
    private javax.swing.JTextField groupField;
    private javax.swing.JTextField minutesField;
    private javax.swing.JTextField requestedField;
    private javax.swing.JTextField setupField;
    private javax.swing.JTextField titleField;
    // End of variables declaration//GEN-END:variables
}
