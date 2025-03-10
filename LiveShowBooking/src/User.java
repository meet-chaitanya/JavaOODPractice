import java.util.ArrayList;
import java.util.List;

public class User {
    private String userName;
    private List<Booking> bookings;

    User(String userName) {
        this.userName = userName;
        this.bookings = new ArrayList<>();
    }

    public String getUserName() {
        return userName;
    }


    public List<Booking> getBookings() {
        return bookings;
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    public void removeBooking(Booking booking) {
        bookings.remove(booking);
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                '}';
    }
}
