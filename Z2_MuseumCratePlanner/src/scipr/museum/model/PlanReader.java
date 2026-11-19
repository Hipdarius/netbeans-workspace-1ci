package scipr.museum.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Set;

public class PlanReader {
    public PlanReader() {
    }

    private String nextNonBlank(BufferedReader reader) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (!line.isEmpty()) {
                return line;
            }
        }
        return null;
    }

    private PackItem parseItem(String line) throws IOException {
        // TODO 9
        throw new IOException("Item parsing is unfinished.");
    }

    private void readChildren(BufferedReader reader, Crate crate, Set<String> ids) throws IOException {
        // TODO 10
        throw new IOException("Crate reading is unfinished.");
    }

    public PackingPlan read(Reader source) throws IOException {
        // TODO 11
        throw new IOException("Plan import is unfinished. Use the demo plan for now.");
    }
}
