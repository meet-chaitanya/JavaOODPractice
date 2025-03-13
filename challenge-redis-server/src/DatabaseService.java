import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DatabaseService {
    private static final DatabaseService instance = new DatabaseService();
    private Map<String, KeyValueWithExpiry> dataStore;

    private DatabaseService() {
        dataStore = new ConcurrentHashMap<>();
    }

    public static DatabaseService getInstance() {
        return instance;
    }

    public void set(String key, String value) {
        dataStore.put(key, new KeyValueWithExpiry(value, -1));
    }

    public String get(String key) {
        KeyValueWithExpiry kv = dataStore.get(key);
        return kv != null ? kv.getValue() : null;
    }

    // Other database operations (e.g., DEL, EXISTS, etc.)
}
