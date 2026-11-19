package boardclub;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

public class BoardPanel extends JPanel {
    private Board board = new Board(6);

    public BoardPanel() {
        setBackground(new Color(247, 249, 250));
        setPreferredSize(new Dimension(300, 300));
    }

    public void setBoard(Board board) {
        this.board = board;
        repaint();
    }

    private int cellSize() {
        return Math.min(getWidth(), getHeight()) / board.size();
    }

    private int left() {
        return (getWidth() - cellSize() * board.size()) / 2;
    }

    private int top() {
        return (getHeight() - cellSize() * board.size()) / 2;
    }

    public int rowAt(int y) {
        int offset = y - top();
        if (cellSize() == 0 || offset < 0 || offset >= cellSize() * board.size()) {
            return -1;
        }
        return offset / cellSize();
    }

    public int colAt(int x) {
        int offset = x - left();
        if (cellSize() == 0 || offset < 0 || offset >= cellSize() * board.size()) {
            return -1;
        }
        return offset / cellSize();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int cell = cellSize();
        if (cell == 0) {
            return;
        }
        int x = left();
        int y = top();
        int side = cell * board.size();
        g.setColor(new Color(175, 191, 200));
        for (int i = 0; i <= board.size(); i++) {
            g.drawLine(x + i * cell, y, x + i * cell, y + side);
            g.drawLine(x, y + i * cell, x + side, y + i * cell);
        }
        // TODO E2b: paint the markers after the grid.
    }
}
