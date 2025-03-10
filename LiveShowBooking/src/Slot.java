import java.util.ArrayList;
import java.util.List;

public class Slot {
    private String startTime;
    private Integer capacity;
    private Integer bookedCount;
    private List<WaitListEntry> waitList;


    Slot(String startTime, Integer capacity) {
        this.startTime = startTime;
        this.capacity = capacity;
        bookedCount = 0;
        waitList = new ArrayList<>();
    }

    public String getStartTime() {
        return startTime;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public boolean isAvailable(Integer persons) {
        return getAvailableCapacity() >= persons;
    }

    public Integer getAvailableCapacity() {
        return getCapacity() - bookedCount;
    }

    public void bookSlot(Integer persons) {
        bookedCount += persons;
    }

    public void cancelSlot(Integer persons) {
        bookedCount -= persons;
    }

    public void addToWaitList(User user, int count) {
        waitList.add(new WaitListEntry(user, count));
    }

    public void removeFromWaitList(User user) {
        waitList.removeIf(entry -> entry.getUser().equals(user));
    }

    public List<WaitListEntry> getWaitList() {
        return waitList;
    }

    @Override
    public String toString() {
        return "Slot{" +
                "startTime='" + startTime + '\'' +
                ", capacity=" + capacity +
                ", bookedCount=" + bookedCount +
                ", waitList=" + waitList +
                '}';
    }
}
