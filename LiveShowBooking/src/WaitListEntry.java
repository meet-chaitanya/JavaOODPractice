public class WaitListEntry {
    private User user;
    private Integer persons;

    WaitListEntry(User user, Integer persons) {
        this.user = user;
        this.persons = persons;
    }

    public User getUser() {
        return user;
    }

    public Integer getPersons() {
        return persons;
    }

    @Override
    public String toString() {
        return "WaitListEntry{" +
                "user=" + user +
                ", persons=" + persons +
                '}';
    }
}