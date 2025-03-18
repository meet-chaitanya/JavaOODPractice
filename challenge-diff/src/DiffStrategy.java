import java.util.List;

public interface DiffStrategy {
    List<String> generateDiff(List<String> original, List<String> modified);
}
