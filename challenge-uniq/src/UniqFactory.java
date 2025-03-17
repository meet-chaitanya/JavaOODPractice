public class UniqFactory {
    public UniqStrategy createUniqStrategy(boolean unique, boolean repeated, boolean count) {
        UniqStrategy strategy;
        if (count && repeated) {
            strategy = new CountAndRepeatedStrategy();
        } else if (count) {
            strategy = new CountStrategy();
        } else if (repeated) {
            strategy = new RepeatedStrategy();
        } else if (unique) {
            strategy = new UniqueStrategy();
        } else {
            strategy = new UniqueStrategy();
        }
        return strategy;
    }
}
