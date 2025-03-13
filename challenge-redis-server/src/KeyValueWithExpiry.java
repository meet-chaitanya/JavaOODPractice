public class KeyValueWithExpiry {

    private String value;
    private long expiryTime;

    public KeyValueWithExpiry(String value, long expiryTime) {
        this.value = value;
        this.expiryTime = expiryTime;
    }

    public String getValue() {
        return value;
    }

    public long getExpiryTime() {
        return expiryTime;
    }
}
