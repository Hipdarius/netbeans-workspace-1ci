package scipr.museum.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Crate extends PackItem {
    private final int tare;
    private final int limit;
    private final List<PackItem> children = new ArrayList<>();

    public Crate(String id, int tare, int limit) {
        super(id);
        this.tare = tare;
        this.limit = limit;
    }

    public int getTare() {
        return tare;
    }

    public int getLimit() {
        return limit;
    }

    public List<PackItem> getChildren() {
        return Collections.unmodifiableList(children);
    }

    public void addChild(PackItem item) {
        children.add(item);
    }

    public boolean removeChild(PackItem item) {
        return children.remove(item);
    }

    public int payloadWeight() {
        // TODO 2
        return 0;
    }

    @Override
    public int totalWeight() {
        // TODO 3
        return 0;
    }
}
