import java.util.Iterator;

public class WaitlistManager {
    private static WaitlistManager instance;
    private WaitlistManager() {}

    public static WaitlistManager getInstance() {
        if (instance == null) {
            instance = new WaitlistManager();
        }
        return instance;
    }

    public void addToWaitList(User user, Slot slot, int persons) {
        slot.addToWaitList(user, persons);
    }

    public void processWaitList(Slot slot) {
        Iterator<WaitListEntry> iterator = slot.getWaitList().iterator();
        while (iterator.hasNext()) {
            WaitListEntry entry = iterator.next();
            if (slot.isAvailable(entry.getPersons())) {
                slot.bookSlot(entry.getPersons());
                iterator.remove();
            } else {
                break;
            }
        }
    }
}
