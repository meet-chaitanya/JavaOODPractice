import java.util.ArrayList;
import java.util.List;

public class LiveShowBookingDemo {
    public static void main(String[] args) {
        ShowManager showManager = ShowManager.getInstance();

        System.out.println(showManager.registerShow("TMKOC", "Comedy"));

        List<String[]> slotsInfo = new ArrayList<>();
        slotsInfo.add(new String[]{"09:00", "2"});
        slotsInfo.add(new String[]{"10:00", "3"});
        slotsInfo.add(new String[]{"11:00", "4"});
        System.out.println(showManager.onboardShowSlots("TMKOC", slotsInfo));

        showManager.setRankingStrategy(new RankByStartTimeStrategy());
        List<LiveShow> liveShows = showManager.getAvailableShowsByGenre("Comedy");
        for (LiveShow show: liveShows) {
            System.out.println(show);
        }

        BookingManager bookingManager = BookingManager.getInstance();
        User userA = new User("UserA");
        bookingManager.bookTicket(userA, "TMKOC", "09:00", 2);
        liveShows = showManager.getAvailableShowsByGenre("Comedy");
        for (LiveShow show: liveShows) {
            System.out.println(show);
        }
        String bookingId = userA.getBookings().stream()
                        .filter(booking -> booking.getLiveShow().getShowName().equalsIgnoreCase("TMKOC") && booking.getSlot().getStartTime().equalsIgnoreCase("09:00"))
                                .map(Booking::getBookingId)
                                        .findFirst().orElse("");
        System.out.println("BookingId: " + bookingId);
        User userB = new User("UserB");
        bookingManager.bookTicket(userB, "TMKOC", "09:00", 2);

        liveShows = showManager.getAvailableShowsByGenre("Comedy");
        for (LiveShow show: liveShows) {
            System.out.println(show);
        }

        bookingManager.cancelBooking(userA, bookingId);
        liveShows = showManager.getAvailableShowsByGenre("Comedy");
        for (LiveShow show: liveShows) {
            System.out.println(show);
        }
    }
}
