import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class RankByStartTimeStrategy implements RankingStrategy{
    @Override
    public List<LiveShow> rankShows(Map<String, LiveShow> shows) {
        return shows.values().stream()
                .sorted(Comparator.comparing(show -> show.getSlots().stream()
                        .min(Comparator.comparing(Slot::getStartTime))
                        .map(Slot::getStartTime)
                        .orElse("")))
                .collect(Collectors.toList());
    }
}
