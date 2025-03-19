import java.util.HashMap;
import java.util.Map;

public class StoreService {
    private static final StoreService instance = new StoreService();
    private Map<String, KeyValueWithExpiry> cache;

    private StoreService() {
        cache = new HashMap<>();
    }

    public static StoreService getInstance() {
        return instance;
    }

    public void add(String key, String value, int expiry) {
        KeyValueWithExpiry data = cache.getOrDefault(key, new KeyValueWithExpiry(key, value, 0));
        data.setValue(value);
        data.setExpiry(expiry);
        data.setUpdatedAt(System.currentTimeMillis());
        cache.put(key, data);
    }

    public String get(String key) {
        KeyValueWithExpiry data = cache.get(key);
        if (data == null || isDataExpired(data)) {
            return null;
        }
        return data.getValue();
    }

    public boolean append(String key, String value, int expiry) {
        KeyValueWithExpiry data = cache.get(key);
        if (data == null) return false;
        data.setExpiry(expiry);
        data.setValue(data.getValue() + value);
        cache.put(key, data);
        return true;
    }

    public boolean prepend(String key, String value, int expiry) {
        KeyValueWithExpiry data = cache.get(key);
        if (data == null) return false;
        data.setExpiry(expiry);
        data.setValue(value + data.getValue());
        cache.put(key, data);
        return true;
    }

    private boolean isDataExpired(KeyValueWithExpiry data) {
        if (data.getExpiry() == 0) {
            return false;
        }
        if (data.getExpiry() < 0) {
            return true;
        }
        return System.currentTimeMillis() < data.getUpdatedAt() + data.getExpiry();
    }


}
