import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MockDatabase {
    static Map<String, String> data;
    
    static public void setUp() {
        data = new HashMap<>();
        data.put("apples", "red");
        data.put("bananas", "yellow");
        data.put("grapes", "green");
    }

    static public String getValue(String key) { return data.get(key); }

    static public Set<String> getKeys() { return data.keySet(); }

    static public void setPair(String key, String value) { data.put(key, value); }
}
