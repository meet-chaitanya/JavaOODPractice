import java.util.ArrayList;
import java.util.List;

public class LineBasedDiffStrategy implements DiffStrategy{
    @Override
    public List<String> generateDiff(List<String> original, List<String> modified) {
        List<String> diff = new ArrayList<>();
        for (int i = 0; i < original.size(); i++) {
            if (!original.get(i).equals(modified.get(i))) {
                diff.add("< " + original.get(i));
                diff.add("> " + modified.get(i));
            }
        }
        return diff;
    }
}
