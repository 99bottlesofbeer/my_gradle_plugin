import java.util.HashMap;
import java.util.Map;

public class CodeMap {
    Map<String, String> map;
    private static CodeMap instance;

    private CodeMap()
    {
        map = new HashMap<>();
    }
    public static synchronized CodeMap getInstance()
    {
        if(instance == null) instance = new CodeMap();
        return instance;
    }
    public boolean containsKey(String string)
    {
        return map.containsKey(string);
    }
    public void put(String k, String v)
    {
        map.put(k,v);
    }
    public String get(String k)
    {
        return map.get(k);
    }
}
