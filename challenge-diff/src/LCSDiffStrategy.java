import java.util.ArrayList;
import java.util.List;

public class LCSDiffStrategy implements DiffStrategy{
    @Override
    public List<String> generateDiff(List<String> original, List<String> modified) {
        int m = original.size();
        int n = modified.size();

        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1;j <= n; j++) {
                if (original.get(i - 1).equals(modified.get(j - 1))) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        int i = m, j = n;
        List<String> diff = new ArrayList<>();
        while (i > 0 && j > 0) {
            if (original.get(i - 1).equals(modified.get(j - 1))) {
                i--;
                j--;
            } else if (dp[i - 1][j] >= dp[i][j - 1]) {
                diff.add("< " + original.get(i - 1));
                i--;
            } else {
                diff.add("> " + modified.get(j - 1));
                j--;
            }
        }
        while (i > 0) {
            diff.add("< " + original.get(i - 1));
            i--;
        }

        while (j > 0) {
            diff.add("> " + modified.get(j - 1));
            j--;
        }
        return diff;
    }
}
