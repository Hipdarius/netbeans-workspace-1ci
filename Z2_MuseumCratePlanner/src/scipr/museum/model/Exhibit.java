package scipr.museum.model;

public class Exhibit extends PackItem {
    private final int weight;

    public Exhibit(String id, int weight) {
        super(id);
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public int totalWeight() {
        // TODO 1
        return 0;
    }
}
