package lu.scipr.dome.model;

public class Show implements Comparable<Show> {
    private String title;
    private String group;
    private int requested;
    private int minutes;
    private int rank = 0;
    private int start = -1;

    public Show(String title, String group, int requested, int minutes) {
        this.title = title;
        this.group = group;
        this.requested = requested;
        this.minutes = minutes;
    }

    public String getTitle() {
        return title;
    }

    public String getGroup() {
        return group;
    }

    public int getRequested() {
        return requested;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getStart() {
        return start;
    }

    public void setSlot(int rank, int start) {
        this.rank = rank;
        this.start = start;
    }

    public String getKind() {
        return "R";
    }

    public int getSetupMinutes() {
        return 0;
    }

    public int occupiedMinutes() {
        return minutes;
    }

    @Override
    public int compareTo(Show other) {
        // TODO B1
        return 0;
    }

    public int getRank() {
        // TODO B3
        return 0;
    }

    @Override
    public String toString() {
        // TODO B3
        return title;
    }
}
