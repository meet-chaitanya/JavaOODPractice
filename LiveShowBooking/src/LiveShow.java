import java.util.ArrayList;
import java.util.List;

public class LiveShow {
    private String showName;
    private String genre;
    private List<Slot> slots;

    LiveShow(String name, String genre) {
        this.showName = name;
        this.genre = genre;
        slots = new ArrayList<>();
    }

    public List<Slot> getSlots() {
        return slots;
    }

    public String getGenre() {
        return genre;
    }

    public String getShowName() {
        return showName;
    }

    public void addSlot(Slot slot) {
        slots.add(slot);
    }

    @Override
    public String toString() {
        return "LiveShow{" +
                "showName='" + showName + '\'' +
                ", genre='" + genre + '\'' +
                ", slots=" + slots +
                '}';
    }
}
