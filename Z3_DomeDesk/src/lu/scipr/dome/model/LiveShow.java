package lu.scipr.dome.model;

public class LiveShow extends Show {
    private int setupMinutes;

    public LiveShow(String title, String group, int requested, int minutes, int setupMinutes) {
        super(title, group, requested, minutes);
        this.setupMinutes = setupMinutes;
    }

    @Override
    public String getKind() {
        return "L";
    }

    @Override
    public int getSetupMinutes() {
        return setupMinutes;
    }

    @Override
    public int occupiedMinutes() {
        // TODO B2
        return 0;
    }
}
