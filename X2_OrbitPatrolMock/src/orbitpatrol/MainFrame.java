package orbitpatrol;

import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class MainFrame extends javax.swing.JFrame {
    private final Game game = new Game();
    private Timer timer;

    public MainFrame() {
        initComponents();
        drawPanel.setGame(game);
        // TODO C7_TIMER
        timer = new Timer(20, null); // TODO: register the update callback.
        refresh();
        setLocationRelativeTo(null);
    }
    private void refresh() {
        statusLabel.setText("Score: " + game.getScore() + "     Objects: "
                + game.getObjects().sizeConstant() + "     "
                + (timer != null && timer.isRunning() ? "Running" : "Paused"));
        drawPanel.repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        toolbar = new javax.swing.JPanel();
        row1 = new javax.swing.JPanel();
        exampleButton = new javax.swing.JButton();
        addButton = new javax.swing.JButton();
        shootButton = new javax.swing.JButton();
        startButton = new javax.swing.JButton();
        pauseButton = new javax.swing.JButton();
        row2 = new javax.swing.JPanel();
        sortButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        loadButton = new javax.swing.JButton();
        resetButton = new javax.swing.JButton();
        drawPanel = new orbitpatrol.DrawPanel();
        statusLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Orbit Patrol - linked-list mock");
        setResizable(false);
        getContentPane().setLayout(new java.awt.BorderLayout());
        toolbar.setLayout(new java.awt.GridLayout(2, 1));
        row1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 8, 7));
        exampleButton.setText("Example");
        exampleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) { exampleButtonActionPerformed(evt); }
        });
        row1.add(exampleButton);
        addButton.setText("Add target");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) { addButtonActionPerformed(evt); }
        });
        row1.add(addButton);
        shootButton.setText("Shoot");
        shootButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) { shootButtonActionPerformed(evt); }
        });
        row1.add(shootButton);
        startButton.setText("Start");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) { startButtonActionPerformed(evt); }
        });
        row1.add(startButton);
        pauseButton.setText("Pause");
        pauseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) { pauseButtonActionPerformed(evt); }
        });
        row1.add(pauseButton);
        toolbar.add(row1);
        row2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 8, 7));
        sortButton.setText("Sort x");
        sortButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) { sortButtonActionPerformed(evt); }
        });
        row2.add(sortButton);
        saveButton.setText("Save");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) { saveButtonActionPerformed(evt); }
        });
        row2.add(saveButton);
        loadButton.setText("Load");
        loadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) { loadButtonActionPerformed(evt); }
        });
        row2.add(loadButton);
        resetButton.setText("Reset");
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) { resetButtonActionPerformed(evt); }
        });
        row2.add(resetButton);
        toolbar.add(row2);
        getContentPane().add(toolbar, java.awt.BorderLayout.NORTH);
        drawPanel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) { drawPanelMouseMoved(evt); }
        });
        drawPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) { drawPanelMousePressed(evt); }
        });
        getContentPane().add(drawPanel, java.awt.BorderLayout.CENTER);
        statusLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 12, 8, 12));
        statusLabel.setText("Score: 0     Objects: 0     Paused");
        getContentPane().add(statusLabel, java.awt.BorderLayout.SOUTH);
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exampleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exampleButtonActionPerformed
        timer.stop(); game.example(); refresh();
    }//GEN-LAST:event_exampleButtonActionPerformed
    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        game.addRandomTarget(); refresh();
    }//GEN-LAST:event_addButtonActionPerformed
    private void shootButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shootButtonActionPerformed
        // TODO C7_SHOOT_EVENT
    }//GEN-LAST:event_shootButtonActionPerformed
    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        timer.start(); refresh();
    }//GEN-LAST:event_startButtonActionPerformed
    private void pauseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pauseButtonActionPerformed
        timer.stop(); refresh();
    }//GEN-LAST:event_pauseButtonActionPerformed
    private void sortButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sortButtonActionPerformed
        timer.stop(); game.sortByX(); refresh();
    }//GEN-LAST:event_sortButtonActionPerformed
    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        timer.stop(); refresh();
        JFileChooser chooser = new JFileChooser();
        if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            try { game.saveToFile(chooser.getSelectedFile().getAbsolutePath()); }
            catch (IOException ex) { JOptionPane.showMessageDialog(this, ex.getMessage()); }
        }
    }//GEN-LAST:event_saveButtonActionPerformed
    private void loadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadButtonActionPerformed
        timer.stop(); refresh();
        JFileChooser chooser = new JFileChooser();
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try { game.loadFromFile(chooser.getSelectedFile().getAbsolutePath()); refresh(); }
            catch (IOException ex) { JOptionPane.showMessageDialog(this, ex.getMessage()); }
        }
    }//GEN-LAST:event_loadButtonActionPerformed
    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        timer.stop(); game.reset(); refresh();
    }//GEN-LAST:event_resetButtonActionPerformed
    private void drawPanelMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_drawPanelMouseMoved
        // TODO C7_AIM_EVENT
    }//GEN-LAST:event_drawPanelMouseMoved
    private void drawPanelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_drawPanelMousePressed
        shootButton.doClick();
    }//GEN-LAST:event_drawPanelMousePressed

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override public void run() { new MainFrame().setVisible(true); }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private orbitpatrol.DrawPanel drawPanel;
    private javax.swing.JButton exampleButton;
    private javax.swing.JButton loadButton;
    private javax.swing.JButton pauseButton;
    private javax.swing.JButton resetButton;
    private javax.swing.JPanel row1;
    private javax.swing.JPanel row2;
    private javax.swing.JButton saveButton;
    private javax.swing.JButton shootButton;
    private javax.swing.JButton sortButton;
    private javax.swing.JButton startButton;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JPanel toolbar;
    // End of variables declaration//GEN-END:variables
}
