package scipr.museum.model;

public abstract class PackItem {
    private final String id;

    protected PackItem(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public abstract int totalWeight();
}
