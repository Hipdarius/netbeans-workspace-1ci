package orbitpatrol;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private MyList objects = new MyList();
    private Player player = new Player(380, 415);
    private int width = 760, height = 440, score = 0;
    private final Random random = new Random(2026);

    // SUPPLIED drawing coordinator and setters.
    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
        player.setPosition(player.getX(), width, height);
    }
    public void draw(Graphics g) {
        g.setColor(new Color(220, 226, 231));
        g.drawLine(0, height * 2 / 3, width, height * 2 / 3);
        objects.draw(g);
        player.draw(g);
        g.setColor(new Color(90, 98, 108));
        g.drawString("Move the mouse to aim. Click or press Shoot to fire.", 14, height - 8);
    }
    public void aimAt(int x) { player.setPosition(x, width, height); }
    public int getScore() { return score; }
    public Player getPlayer() { return player; }
    public MyList getObjects() { return objects; }
    public void sortByX() { objects.sortByX(); }
    public void reset() { objects.clear(); score = 0; aimAt(width / 2); }

    // SUPPLIED scenario setup. Objects are not automatically spawned by the timer.
    public void example() {
        reset();
        objects.addToTail(new Target(100, 95, 2, 1, 24, 10));
        objects.addToTail(new Target(260, 180, -2, 1, 18, 20));
        objects.addToTail(new Target(400, 80, 1, -1, 24, 10));
        objects.addToTail(new Target(555, 170, -1, 2, 18, 20));
        objects.addToTail(new Target(665, 95, -2, 1, 24, 10));
        objects.addToTail(new Bullet(380, 350, 0, -8, 5));
    }
    public void addRandomTarget() {
        boolean special = random.nextBoolean();
        int radius = special ? 18 : 24;
        int points = special ? 20 : 10;
        objects.addToTail(new Target(40 + random.nextInt(Math.max(1, width - 80)),
                40 + random.nextInt(Math.max(1, height * 2 / 3 - 80)),
                random.nextBoolean() ? 2 : -2, random.nextBoolean() ? 1 : -1, radius, points));
    }

    public void shoot() {
        // TODO C5_SHOOT
    }
    public void move() {
        // TODO C5_TICK
    }

    /** SUPPLIED: flags hits first; structural deletion happens afterwards. */
    private void resolveHits() {
        Node tmp = objects.getRoot();
        while (tmp != null) {
            Orb orb = tmp.getData();
            if (orb instanceof Bullet && !orb.isInactive()) {
                Target target = objects.findTargetHitBy((Bullet) orb);
                if (target != null) {
                    target.setInactive(true);
                    orb.setInactive(true);
                    score += target.getPoints();
                }
            }
            tmp = tmp.getNext();
        }
    }

    public void saveToFile(String fileName) throws IOException {
        // TODO C8_SAVE
    }
    public void loadFromFile(String fileName) throws IOException {
        // TODO C8_LOAD
    }
}
