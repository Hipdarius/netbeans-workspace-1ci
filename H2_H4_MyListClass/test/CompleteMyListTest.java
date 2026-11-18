import com.google.gson.JsonParser;
import java.io.StringReader;
import java.util.Arrays;
import javax.xml.parsers.DocumentBuilderFactory;
import org.xml.sax.InputSource;

public class CompleteMyListTest {
    private static void check(boolean result) {
        if (!result) throw new AssertionError("List check failed");
    }

    public static void main(String[] args) throws Exception {
        MyList list = new MyList();
        check(list.isEmpty() && list.size() == 0 && list.verifySort());
        list.reverse();
        list.add("c"); list.add("a"); list.add("c"); list.add(null);
        list.insert("b", 1);
        check(Arrays.equals(list.toArray(), new Object[]{"c", "b", "a", "c", null}));
        check(list.count("c") == 2 && list.count(null) == 1);
        check(list.indexOf("a") == 2 && list.indexOf("missing") == -1);
        list.set("d", 1);
        check(list.get(1).equals("d"));
        check(list.remove((Object) "c") && !list.remove((Object) "missing"));
        check(list.remove(0).equals("d"));
        list.sort();
        check(list.verifySort() && Arrays.equals(list.toArray(), new Object[]{null, "a", "c"}));
        list.reverse(); list.add("tail");
        check(Arrays.equals(list.toArray(), new Object[]{"c", "a", null, "tail"}));
        check(list.remove(3).equals("tail"));
        list.add("new tail");
        check(list.size() == 4 && list.get(3).equals("new tail"));
        Object[] array = list.toArray(); array[0] = "changed";
        check(list.get(0).equals("c"));
        list.clear(); list.add("<a & b>"); list.add(null); list.add(42);
        check(JsonParser.parseString(list.toJson()).getAsJsonArray().get(0).getAsString().equals("<a & b>"));
        org.w3c.dom.Document xml = DocumentBuilderFactory.newInstance().newDocumentBuilder()
                .parse(new InputSource(new StringReader(list.toXML())));
        check(xml.getElementsByTagName("item").item(0).getTextContent().equals("<a & b>"));
        check(xml.getElementsByTagName("item").getLength() == 3);
        list.clear(); list.add(1); list.remove(0); list.add(2);
        check(list.size() == 1 && list.get(0).equals(2));
        try { list.get(-1); throw new AssertionError("Negative index accepted"); }
        catch (IndexOutOfBoundsException expected) { }
        try { list.insert(3, 2); throw new AssertionError("Invalid insert accepted"); }
        catch (IndexOutOfBoundsException expected) { }
        System.out.println("CompleteMyListTest passed");
    }
}
