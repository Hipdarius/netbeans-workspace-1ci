package scipr.museum.view;

import java.awt.Component;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;
import scipr.museum.model.Crate;
import scipr.museum.model.Exhibit;
import scipr.museum.model.PackItem;
import scipr.museum.model.PackingPlan;
import scipr.museum.model.PlanReader;

public class MainFrame extends JFrame {
    private PackingPlan plan;

    public MainFrame() {
        initComponents();
        configureTree();
        showPlan(demoPlan(), "Demo packing plan");
    }

    private void configureTree() {
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        tree.getAccessibleContext().setAccessibleName("Packing hierarchy");
        tree.setCellRenderer(new DefaultTreeCellRenderer() {
            @Override
            public Component getTreeCellRendererComponent(JTree treeView, Object value,
                    boolean selected, boolean expanded, boolean leaf, int row, boolean focused) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
                Object item = node.getUserObject();
                String text = node.toString();
                boolean exhibit = leaf;
                if (item instanceof Crate) {
                    Crate crate = (Crate) item;
                    text = crate.getId() + "  [crate | tare " + crate.getTare()
                            + " kg | limit " + crate.getLimit() + " kg]";
                    exhibit = false;
                } else if (item instanceof Exhibit) {
                    Exhibit piece = (Exhibit) item;
                    text = piece.getId() + "  [exhibit | " + piece.getWeight() + " kg]";
                    exhibit = true;
                }
                return super.getTreeCellRendererComponent(
                        treeView, text, selected, expanded, exhibit, row, focused);
            }
        });
    }

    private void showPlan(PackingPlan newPlan, String sourceName) {
        plan = newPlan;
        sourceLabel.setText(sourceName);

        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(plan.getRoot());
        Queue<DefaultMutableTreeNode> pending = new ArrayDeque<>();
        pending.add(rootNode);
        while (!pending.isEmpty()) {
            DefaultMutableTreeNode node = pending.remove();
            Object item = node.getUserObject();
            if (item instanceof Crate) {
                Crate crate = (Crate) item;
                for (PackItem child : crate.getChildren()) {
                    DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(child);
                    node.add(childNode);
                    pending.add(childNode);
                }
            }
        }

        tree.setModel(new DefaultTreeModel(rootNode));
        for (int row = 0; row < tree.getRowCount(); row++) {
            tree.expandRow(row);
        }
        tree.clearSelection();
        refreshDetails();
    }

    private PackItem getSelectedItem() {
        DefaultMutableTreeNode selected = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
        if (selected == null) {
            return null;
        }
        return (PackItem) selected.getUserObject();
    }

    private void refreshDetails() {
        // TODO 12
    }

    private void openPlan() {
        JFileChooser chooser = new JFileChooser(Path.of("samples").toFile());
        chooser.setDialogTitle("Open packing plan");
        chooser.setFileFilter(new FileNameExtensionFilter("Text plans (*.txt)", "txt"));
        if (chooser.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) {
            return;
        }
        Path path = chooser.getSelectedFile().toPath();
        PackingPlan loaded;
        try (Reader source = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            loaded = new PlanReader().read(source);
        } catch (IOException exception) {
            JOptionPane.showMessageDialog(this, exception.getMessage(),
                    "Could not open plan", JOptionPane.ERROR_MESSAGE);
            return;
        }
        showPlan(loaded, path.getFileName().toString());
    }

    private void moveSelectedItem() {
        PackItem selected = getSelectedItem();
        if (selected == null) {
            return;
        }
        if (plan.move(selected.getId(), destinationField.getText().trim())) {
            showPlan(plan, sourceLabel.getText());
        } else {
            JOptionPane.showMessageDialog(this, "The item could not be moved.",
                    "Move item", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void showOverloadedCrates() {
        List<Crate> crates = plan.overloadedCrates();
        if (crates.isEmpty()) {
            JOptionPane.showMessageDialog(this, "The audit returned no overloaded crates.",
                    "Payload audit", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        JTextArea report = new JTextArea(Math.min(crates.size(), 10), 16);
        report.setEditable(false);
        report.getAccessibleContext().setAccessibleName("Overloaded crate IDs");
        for (Crate crate : crates) {
            report.append(crate.getId() + "\n");
        }
        report.setCaretPosition(0);
        JOptionPane.showMessageDialog(this, new JScrollPane(report),
                "Overloaded crates", JOptionPane.INFORMATION_MESSAGE);
    }

    private static PackingPlan demoPlan() {
        Crate road = new Crate("ROAD", 4, 45);
        Crate east = new Crate("EAST", 2, 12);
        Crate inner = new Crate("INNER", 1, 7);
        Crate west = new Crate("WEST", 3, 17);

        road.addChild(east);
        road.addChild(west);
        road.addChild(new Exhibit("SIGN", 8));
        east.addChild(new Exhibit("MASK", 7));
        east.addChild(inner);
        inner.addChild(new Exhibit("SEAL", 7));
        west.addChild(new Exhibit("VASE", 11));
        return new PackingPlan(road);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        contentPanel = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        importButton = new javax.swing.JButton();
        demoButton = new javax.swing.JButton();
        sourceLabel = new javax.swing.JLabel();
        treeScrollPane = new javax.swing.JScrollPane();
        tree = new javax.swing.JTree();
        detailsLabel = new javax.swing.JLabel();
        destinationLabel = new javax.swing.JLabel();
        destinationField = new javax.swing.JTextField();
        moveButton = new javax.swing.JButton();
        overloadButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Museum Crate Planner");
        setMinimumSize(new java.awt.Dimension(720, 460));

        contentPanel.setBackground(new java.awt.Color(247, 245, 239));

        titleLabel.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        titleLabel.setForeground(new java.awt.Color(28, 55, 47));
        titleLabel.setText("Museum Crate Planner");

        importButton.setText("Open plan...");
        importButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importButtonActionPerformed(evt);
            }
        });

        demoButton.setText("Reset to demo");
        demoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                demoButtonActionPerformed(evt);
            }
        });

        sourceLabel.setForeground(new java.awt.Color(28, 55, 47));
        sourceLabel.setText("Demo packing plan");

        tree.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        tree.setRowHeight(26);
        tree.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                treeValueChanged(evt);
            }
        });
        treeScrollPane.setViewportView(tree);

        detailsLabel.setForeground(new java.awt.Color(28, 55, 47));
        detailsLabel.setText("No item selected.");

        destinationLabel.setLabelFor(destinationField);
        destinationLabel.setText("Destination crate ID");

        destinationField.setColumns(12);

        moveButton.setText("Move");
        moveButton.setEnabled(false);
        moveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveButtonActionPerformed(evt);
            }
        });

        overloadButton.setText("Check payload limits");
        overloadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                overloadButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout contentPanelLayout = new javax.swing.GroupLayout(contentPanel);
        contentPanel.setLayout(contentPanelLayout);
        contentPanelLayout.setHorizontalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(contentPanelLayout.createSequentialGroup()
                        .addComponent(titleLabel)
                        .addGap(12, 306, Short.MAX_VALUE)
                        .addComponent(importButton)
                        .addGap(8, 8, 8)
                        .addComponent(demoButton))
                    .addComponent(sourceLabel)
                    .addComponent(treeScrollPane, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(detailsLabel)
                    .addGroup(contentPanelLayout.createSequentialGroup()
                        .addComponent(destinationLabel)
                        .addGap(8, 8, 8)
                        .addComponent(destinationField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(moveButton)
                        .addGap(8, 8, 8)
                        .addComponent(overloadButton)))
                .addGap(20, 20, 20))
        );
        contentPanelLayout.setVerticalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(titleLabel)
                    .addComponent(importButton)
                    .addComponent(demoButton))
                .addGap(5, 5, 5)
                .addComponent(sourceLabel)
                .addGap(16, 16, 16)
                .addComponent(treeScrollPane, 0, 328, Short.MAX_VALUE)
                .addGap(16, 16, 16)
                .addComponent(detailsLabel)
                .addGap(9, 9, 9)
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(destinationLabel)
                    .addComponent(destinationField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(moveButton)
                    .addComponent(overloadButton))
                .addGap(16, 16, 16))
        );

        getContentPane().add(contentPanel, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void importButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importButtonActionPerformed
        openPlan();
    }//GEN-LAST:event_importButtonActionPerformed

    private void demoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_demoButtonActionPerformed
        showPlan(demoPlan(), "Demo packing plan");
    }//GEN-LAST:event_demoButtonActionPerformed

    private void treeValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_treeValueChanged
        refreshDetails();
    }//GEN-LAST:event_treeValueChanged

    private void moveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveButtonActionPerformed
        moveSelectedItem();
    }//GEN-LAST:event_moveButtonActionPerformed

    private void overloadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_overloadButtonActionPerformed
        showOverloadedCrates();
    }//GEN-LAST:event_overloadButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel contentPanel;
    private javax.swing.JButton demoButton;
    private javax.swing.JTextField destinationField;
    private javax.swing.JLabel destinationLabel;
    private javax.swing.JLabel detailsLabel;
    private javax.swing.JButton importButton;
    private javax.swing.JButton moveButton;
    private javax.swing.JButton overloadButton;
    private javax.swing.JLabel sourceLabel;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JTree tree;
    private javax.swing.JScrollPane treeScrollPane;
    // End of variables declaration//GEN-END:variables
}
