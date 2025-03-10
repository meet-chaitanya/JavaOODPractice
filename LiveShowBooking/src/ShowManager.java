import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ShowManager {
    private static ShowManager instance;
    private Map<String, LiveShow> shows = new HashMap<>();
    private RankingStrategy rankingStrategy;
    private ShowManager() {}

    public static ShowManager getInstance() {
        if (instance == null) {
            instance = new ShowManager();
        }
        return instance;
    }

    public void setRankingStrategy(RankingStrategy rankingStrategy) {
        this.rankingStrategy = rankingStrategy;
    }

    public Map<String, LiveShow> getShows() {
        return shows;
    }

    public LiveShow getShow(String showName) {
        return shows.get(showName);
    }

    public String registerShow(String showName, String genre) {
        if (!shows.containsKey(showName)) {
            LiveShow show = new LiveShow(showName, genre);
            shows.put(showName, show);
            return showName + " show is registered !!";
        }
        return showName + " show already exists !!";
    }

    public String onboardShowSlots(String showName, List<String[]> slotsInfo) {
        if (!shows.containsKey(showName)) {
            return showName + " show not found";
        }
        LiveShow show = getShow(showName);
        for(String[] slotInfo: slotsInfo) {
            String slotStartTime = slotInfo[0];
            Integer capacity = Integer.parseInt(slotInfo[1]);
            Slot slot = new Slot(slotStartTime, capacity);
            show.addSlot(slot);
        }
        return "Done!";
    }

    public List<LiveShow> getAvailableShowsByGenre(String genre) {
        return shows.values().stream()
                .filter(show -> show.getGenre().equalsIgnoreCase(genre))
                .sorted(Comparator.comparing(show -> show.getSlots().stream()
                        .min(Comparator.comparing(Slot::getStartTime))
                        .map(Slot::getStartTime)
                        .orElse("")))
                .collect(Collectors.toList());
    }

    public List<LiveShow> getShowsByRank(){
        return rankingStrategy.rankShows(shows);
    }
}
