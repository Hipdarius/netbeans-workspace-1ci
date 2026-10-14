package orbitpatrol;

import java.awt.Graphics;

/** An editable JPanel form; painting logic belongs outside generated initComponents. */
public class DrawPanel extends javax.swing.JPanel {
    private Game game;
    public DrawPanel() { initComponents(); }
    public void setGame(Game game) { this.game = game; }

    @Override
    protected void paintComponent(Graphics g) {
        // TODO C7_PAINT
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        setBackground(new java.awt.Color(247, 249, 251));
        setPreferredSize(new java.awt.Dimension(760, 440));
        setLayout(new java.awt.BorderLayout());
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
