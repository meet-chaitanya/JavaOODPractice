public class SortFactory {

    public static SortingStrategy getSortingStrategy(String algorithm) {
        switch (algorithm.toLowerCase()) {
            case "merge" -> {
                return new MergeSort();
            }
            case "quick" -> {
                return new QuickSort();
            }
            case "random" -> {
                return new RandomSort();
            }
            default -> {
                throw new IllegalArgumentException("Unsupported algorithm " + algorithm);
            }
        }
    }
}
