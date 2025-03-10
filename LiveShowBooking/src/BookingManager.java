import java.util.HashMap;
import java.util.Map;

public class BookingManager {
    ShowManager showManager = ShowManager.getInstance();
    WaitlistManager waitlistManager = WaitlistManager.getInstance();
    private Map<String, Booking> bookingMap = new HashMap<>();

    private static BookingManager instance;
    private BookingManager() {
    }

    public static BookingManager getInstance() {
        if (instance == null) {
            instance = new BookingManager();
        }
        return instance;
    }

    public void bookTicket(User user, String showName, String slotTime, int persons) {
        LiveShow show = showManager.getShow(showName);
        if (show == null) {
            System.out.println(showName + " not found !!");
            return;
        }
        Slot showSlot = show.getSlots().stream()
                .filter(slot -> slot.getStartTime().equalsIgnoreCase(slotTime))
                .findAny()
                .orElse(null);
        if (showSlot == null) {
            System.out.println(slotTime + " for " + showName + " is not available!");
            return;
        }
        if (showSlot.isAvailable(persons)) {
            showSlot.bookSlot(persons);
            Booking booking = new Booking(user, show, showSlot, persons);
            bookingMap.put(booking.getBookingId(), booking);
            user.addBooking(booking);
        } else {
            waitlistManager.addToWaitList(user, showSlot, persons);
        }
    }

    public void cancelBooking(User user, String bookingId) {
        if (!bookingMap.containsKey(bookingId)) {
            System.out.println(bookingId + " not found !!");
            return;
        }
        Booking booking = bookingMap.get(bookingId);
        user.removeBooking(booking);
        Slot slot = booking.getSlot();
        slot.cancelSlot(booking.getPersons());
        waitlistManager.processWaitList(slot);
    }
}
