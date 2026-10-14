package orbitpatrol;

import java.io.IOException;

/** SUPPLIED parser. String.split arrays are allowed for parsing, not list algorithms. */
public final class SnapshotCodec {
    private SnapshotCodec() { }
    public static int[] parseState(String line) throws IOException {
        try {
            String[] f = line.split(";", -1);
            if (f.length != 3 || !f[0].equals("STATE")) throw new IllegalArgumentException();
            int score = Integer.parseInt(f[1]);
            int playerX = Integer.parseInt(f[2]);
            if (score < 0) throw new IllegalArgumentException();
            return new int[] { score, playerX };
        } catch (RuntimeException ex) { throw new IOException("Invalid STATE record", ex); }
    }
    public static Orb parseOrb(String line) throws IOException {
        try {
            String[] f = line.split(";", -1);
            boolean target = f[0].equals("TARGET");
            if ((target && f.length != 7) || (!target && (!f[0].equals("BULLET") || f.length != 6))) {
                throw new IllegalArgumentException();
            }
            int x = Integer.parseInt(f[1]), y = Integer.parseInt(f[2]);
            int vx = Integer.parseInt(f[3]), vy = Integer.parseInt(f[4]);
            int radius = Integer.parseInt(f[5]);
            if (radius <= 0 || radius > 40) throw new IllegalArgumentException();
            if (target) {
                int points = Integer.parseInt(f[6]);
                if (points != 10 && points != 20) throw new IllegalArgumentException();
                return new Target(x, y, vx, vy, radius, points);
            }
            return new Bullet(x, y, vx, vy, radius);
        } catch (RuntimeException ex) { throw new IOException("Invalid object record: " + line, ex); }
    }
}
