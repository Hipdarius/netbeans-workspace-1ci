import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import java.io.StringReader;
import java.util.Arrays;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class ExtendedListTest {
    private static int checks;
    private static void check(boolean condition) {
        checks++;
        if (!condition) throw new AssertionError("Extended check " + checks);
    }

    public static void main(String[] args) throws Exception {
        MyList list = new MyList();
        check(list.isEmpty() && list.verifySort());
        list.reverse(); list.sort();
        list.add(3); list.add(1); list.add(3); list.add(null); list.add(2);
        check(list.count(3) == 2 && list.count(null) == 1 && list.count(9) == 0);
        check(!list.verifySort());
        list.sort();
        check(list.verifySort());
        check(Arrays.equals(list.toArray(), new Object[]{null, 1, 2, 3, 3}));
        list.reverse();
        check(Arrays.equals(list.toArray(), new Object[]{3, 3, 2, 1, null}));
        list.add(8); // Must attach to the NEW tail after reversal.
        check(list.size() == 6 && list.get(5).equals(8));
        while (!list.isEmpty()) list.remove(list.size() - 1);
        list.add("one"); list.reverse(); list.add("two");
        check(Arrays.equals(list.toArray(), new Object[]{"one", "two"}));
        list.clear(); list.insert("tail", 0); list.insert("head", 0); list.add("end");
        check(Arrays.equals(list.toArray(), new Object[]{"head", "tail", "end"}));
        list.clear();
        String special = "A < B & \"quotes\" '\n\tété";
        list.add(special); list.add(null); list.add(42); list.add(true);
        JsonArray json = JsonParser.parseString(list.toJson()).getAsJsonArray();
        check(json.size() == 4 && json.get(0).getAsString().equals(special));
        check(json.get(1).isJsonNull() && json.get(2).getAsInt() == 42 && json.get(3).getAsBoolean());
        Document xml = DocumentBuilderFactory.newInstance().newDocumentBuilder()
                .parse(new InputSource(new StringReader(list.toXML())));
        check(xml.getElementsByTagName("item").getLength() == 4);
        check(xml.getElementsByTagName("item").item(0).getTextContent().equals(special));
        list.clear(); list.add("\u0000");
        try { list.toXML(); throw new AssertionError("Invalid XML character accepted"); }
        catch (IllegalArgumentException expected) { checks++; }
        System.out.println("ExtendedListTest: " + checks + " checks passed");
    }
}
