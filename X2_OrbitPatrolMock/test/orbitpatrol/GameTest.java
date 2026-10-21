package orbitpatrol;

import java.nio.file.Files;
import java.nio.file.Path;

public class GameTest {
    private static int checks;

    private static void check(boolean result, String message) {
        checks++;
        if(!result) throw new AssertionError(message);
    }

    public static void main(String[] args) throws Exception {
        Target moving = new Target(100, 100, 2, -1, 10, 10);
        moving.move(760, 440);
        check(moving.getX() == 102 && moving.getY() == 99, "Separate x/y velocities");
        Target edge = new Target(749, 282, 2, 2, 10, 10);
        edge.move(760, 440);
        check(edge.getX() == 750 && edge.getVx() == -2, "Right bounce");
        check(edge.getY() == 283 && edge.getVy() == -2, "Bottom bounce");
        Bullet bullet = new Bullet(100, 438, 0, 2, 5);
        bullet.move(760, 440);
        check(!bullet.isInactive(), "Partly visible bullet remains");
        for(int i = 0; i < 3; i++) bullet.move(760, 440);
        check(bullet.isInactive(), "Fully outside bullet removed");
        check(!new Bullet(0, 0, 0, 0, 5).collidesWith(new Bullet(65536, 0, 0, 0, 5)), "Large distance must not overflow");

        MyList list = new MyList();
        Target first = new Target(90, 50, 1, 2, 10, 10);
        Target last = new Target(20, 50, 2, 1, 20, 20);
        Bullet dead1 = new Bullet(30, 40, 0, -8, 5);
        Bullet dead2 = new Bullet(40, 40, 0, -8, 5);
        dead1.setInactive(true);
        dead2.setInactive(true);
        list.addToTail(first);
        list.addToTail(dead1);
        list.addToTail(dead2);
        list.addToTail(last);
        Node head = list.getRoot();
        check(list.removeInactive() == 2 && list.sizeConstant() == 2, "Consecutive inactive removal");
        check(list.getRoot() == head && head.getNext().getData() == last, "Survivor order and identity");
        list.sortByX();
        check(head.getData() == last && head.getNext().getData() == first, "Sort exchanges whole objects");
        first.setInactive(true);
        last.setInactive(true);
        check(list.removeInactive() == 2 && list.getRoot() == null && list.sizeConstant() == 0, "Remove all");
        list.sortByX();

        Game game = new Game();
        game.getObjects().addToTail(new Target(100, 100, 0, 0, 10, 20));
        game.getObjects().addToTail(new Bullet(100, 108, 0, -8, 5));
        game.move();
        check(game.getScore() == 20 && game.getObjects().sizeConstant() == 0, "Score and remove both hit objects");
        game.getObjects().addToTail(first);
        game.getObjects().addToTail(new Bullet(300, 300, 0, -8, 5));
        first.setInactive(false);
        game.aimAt(200);
        Path file = Files.createTempFile("orbit-regression-", ".txt");
        try {
            game.saveToFile(file.toString());
            Game loaded = new Game();
            loaded.loadFromFile(file.toString());
            check(loaded.getScore() == 20 && loaded.getPlayer().getX() == 200, "State round trip");
            check(loaded.getObjects().sizeConstant() == 2, "Object count round trip");
            check(loaded.getObjects().getRoot().getData().toFileRecord().equals(first.toFileRecord()), "Target round trip");
            check(loaded.getObjects().getRoot().getNext().getData() instanceof Bullet, "Bullet round trip");
            loaded.sortThings();
            check(loaded.getObjects().getRoot().getData() instanceof Bullet, "Radius sort");
            Files.writeString(file, "STATE;5;200\nINVALID\n");
            try {
                loaded.loadFromFile(file.toString());
                throw new AssertionError("Accepted broken save");
            } catch(java.io.IOException expected) {
                check(loaded.getScore() == 20 && loaded.getObjects().sizeConstant() == 2, "Failed load preserves state");
            }
        } finally {
            Files.deleteIfExists(file);
        }
        System.out.println("OrbitPatrol: " + checks + " regression checks passed");
    }
}
