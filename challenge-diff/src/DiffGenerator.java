import java.util.List;

public class DiffGenerator {
    private final DiffStrategy diffStrategy;

    public DiffGenerator(DiffStrategy diffStrategy) {
        this.diffStrategy = diffStrategy;
    }

    public List<String> generateDiff(List<String> original, List<String> modified) {
        return diffStrategy.generateDiff(original, modified);
    }
}
