package exam;
import java.awt.*;
import javax.swing.*;

/** JPanel Form: supports NetBeans Design view and custom painting. */
public class DrawPanel extends JPanel {
    private Workshop workshop;

    public DrawPanel() {
        initComponents();
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(720, 230));
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {//GEN-BEGIN:initComponents
        setLayout(new java.awt.BorderLayout());
    }//GEN-END:initComponents
    // </editor-fold>

    public void setWorkshop(Workshop workshop) { this.workshop = workshop; }
    public void updateDrawingWidth() {
        int n = workshop == null ? 0 : workshop.getQueue().sizeConstant();
        setPreferredSize(new Dimension(Math.max(720, 35 + n * 125), 230));
        revalidate();
    }
    @Override protected void paintComponent(Graphics g) {
        // TODO C5
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
