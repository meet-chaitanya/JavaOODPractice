import java.util.List;

public class DiffOutputFormatter {

    public void printDiff(List<String> diff) {
        for (String line: diff) {
            System.out.println(line);
        }
    }
}
