import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.Strictness;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;

/** The model manages entries; the frame manages selection and user interaction. */
public class PhoneBook {
    private ArrayList<Person> entries = new ArrayList<>();
    private final Gson gson = new GsonBuilder().setPrettyPrinting()
            .setStrictness(Strictness.STRICT).create();

    public int size() { return entries.size(); }
    public Person get(int index) { return entries.get(index); }

    public int add(Person person) {
        if (person == null) throw new IllegalArgumentException("Missing person");
        entries.add(person);
        Collections.sort(entries);
        return entries.indexOf(person);
    }

    public int replace(int index, Person person) {
        if (person == null) throw new IllegalArgumentException("Missing person");
        entries.set(index, person);
        Collections.sort(entries);
        return entries.indexOf(person);
    }

    public void remove(int index) { entries.remove(index); }

    public void load(Path file) throws IOException {
        if (!Files.exists(file)) return; // A new directory starts empty.
        ArrayList<Person> loaded = new ArrayList<>();
        try (Reader reader = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
            Person[] people = gson.fromJson(reader, Person[].class);
            if (people == null) throw new IllegalArgumentException("Expected a JSON array");
            for (Person person : people) {
                if (person == null) throw new IllegalArgumentException("Null entry");
                // Gson reads fields directly; validate them through the constructor.
                loaded.add(new Person(person.getFullName(), person.getPhone(), person.getEmail()));
            }
        } catch (JsonParseException | IllegalArgumentException ex) {
            throw new IOException("Invalid phonebook.json: " + ex.getMessage(), ex);
        }
        Collections.sort(loaded);
        entries = loaded; // Replace only after the complete file has been validated.
    }

    public void save(Path file) throws IOException {
        // Write completely before replacing the previous file.
        Path temporary = Files.createTempFile(file.toAbsolutePath().getParent(), "phonebook-", ".tmp");
        try {
            try (Writer writer = Files.newBufferedWriter(temporary, StandardCharsets.UTF_8)) {
                gson.toJson(entries, writer);
            }
            Files.move(temporary, file, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
        } finally {
            Files.deleteIfExists(temporary);
        }
    }
}
