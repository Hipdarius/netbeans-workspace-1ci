import com.google.gson.JsonParser;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class PhoneBookTest {
    private static int checks;
    private static void check(boolean condition) {
        checks++;
        if (!condition) throw new AssertionError("PhoneBook check " + checks);
    }

    public static void main(String[] args) throws Exception {
        Path directory = Files.createTempDirectory("phonebook-test");
        Path file = directory.resolve("phonebook.json");
        try {
            PhoneBook book = new PhoneBook();
            book.load(file); check(book.size() == 0);
            book.add(new Person("Zoe", "00 352 123", "z@example.test"));
            int selected = book.add(new Person("Ada \"A\"", "+352 691 666 444", "a@example.test"));
            check(selected == 0 && book.get(0).getFullName().equals("Ada \"A\""));
            book.save(file);
            check(JsonParser.parseString(Files.readString(file)).getAsJsonArray().size() == 2);
            PhoneBook loaded = new PhoneBook(); loaded.load(file);
            check(loaded.get(0).getPhone().equals("+352 691 666 444"));
            check(loaded.get(1).getPhone().equals("00 352 123"));
            check(loaded.get(0).getFullName().equals("Ada \"A\""));
            selected = loaded.replace(0, new Person("zzz", "12", "x@example.test"));
            check(selected == 1);
            loaded.remove(0); loaded.remove(0); loaded.save(file);
            book.load(file); check(book.size() == 0);
            book.add(new Person("Preserved", "1", "p@example.test"));
            for (String invalid : new String[]{"", "null", "{}", "[null]", "[{\"fullName\":\"Missing fields\"}]", "[broken", "[] trailing"}) {
                Files.writeString(file, invalid);
                try { book.load(file); throw new AssertionError("Accepted: " + invalid); }
                catch (IOException expected) { check(book.size() == 1); }
            }
            try { new Person("  ", "", ""); throw new AssertionError("Blank name"); }
            catch (IllegalArgumentException expected) { checks++; }
            System.out.println("PhoneBookTest: " + checks + " checks passed");
        } finally {
            Files.deleteIfExists(file);
            Files.delete(directory);
        }
    }
}
