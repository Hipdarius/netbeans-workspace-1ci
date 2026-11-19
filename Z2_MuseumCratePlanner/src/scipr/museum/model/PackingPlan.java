package scipr.museum.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PackingPlan {
    private final Crate root;

    public PackingPlan(Crate root) {
        this.root = Objects.requireNonNull(root);
    }

    public Crate getRoot() {
        return root;
    }

    public PackItem find(String id) {
        // TODO 4
        return null;
    }

    public Crate findParent(String id) {
        // TODO 5
        return null;
    }

    public List<Crate> overloadedCrates() {
        // TODO 6
        return new ArrayList<>();
    }

    public Crate tightestCrate(String id) {
        // TODO 7
        return null;
    }

    public boolean move(String itemId, String destinationId) {
        // TODO 8
        return false;
    }
}
