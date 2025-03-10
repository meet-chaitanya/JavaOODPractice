import java.util.List;
import java.util.Map;

public interface RankingStrategy {
    List<LiveShow> rankShows(Map<String, LiveShow> shows);
}
