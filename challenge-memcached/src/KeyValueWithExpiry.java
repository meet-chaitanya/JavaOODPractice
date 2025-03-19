import java.util.Date;

public class KeyValueWithExpiry {
    private String key;
    private String value;
    private int expiry;
    private long updatedAt;

    public KeyValueWithExpiry(String key, String value, int expiry) {
        this.key = key;
        this.value = value;
        this.expiry = expiry;
        updatedAt = System.currentTimeMillis();
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public int getExpiry() {
        return expiry;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setExpiry(int expiry) {
        this.expiry = expiry;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }
}
