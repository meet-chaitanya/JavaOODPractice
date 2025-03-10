import java.util.Map;

public class Booking {
    private User user;
    private LiveShow liveShow;
    private Slot slot;
    private int persons;
    private String bookingId;

    Booking(User user, LiveShow liveShow, Slot slot, int persons) {
        this.user = user;
        this.liveShow = liveShow;
        this.slot = slot;
        this.persons = persons;
        this.bookingId = user.getUserName() + "_" + liveShow.getShowName() + "_" + slot.getStartTime();
    }

    public LiveShow getLiveShow() {
        return liveShow;
    }

    public Slot getSlot() {
        return slot;
    }

    public int getPersons() {
        return persons;
    }

    public String getBookingId() {
        return bookingId;
    }
}
