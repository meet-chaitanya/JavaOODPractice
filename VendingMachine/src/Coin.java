import java.util.Arrays;

public enum Coin {
    ONE(1), TWO(2), FIVE(5);

    private final int value;

    Coin (int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public Coin findByValue(int value) {
        for (Coin coin: values()) {
            if (coin.value == value) {
                return coin;
            }
        }
        return null;
    }

    public static Coin[] getCoinsDescendingOrder() {
        Coin[] coins = values();
        Arrays.sort(coins, (a, b) -> Integer.compare(b.getValue(), a.getValue()));
        return coins;
    }
}

