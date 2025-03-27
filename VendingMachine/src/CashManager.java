import java.util.HashMap;
import java.util.Map;

public class CashManager {
    private Map<Coin, Integer> availableCoins;
    private Map<Note, Integer> availableNotes;
    private Map<Coin, Integer> insertedCoins;
    private Map<Note, Integer> insertedNotes;
    private Map<Coin, Integer> returnCoins;
    private Map<Note, Integer> returnNotes;

    public CashManager() {
        insertedCoins = new HashMap<>();
        insertedNotes = new HashMap<>();
        returnCoins = new HashMap<>();
        returnNotes = new HashMap<>();
    }

    public Map<Coin, Integer> getAvailableCoins() {
        return availableCoins;
    }

    public void setAvailableCoins(Map<Coin, Integer> availableCoins) {
        this.availableCoins = availableCoins;
    }

    public Map<Note, Integer> getAvailableNotes() {
        return availableNotes;
    }

    public void setAvailableNotes(Map<Note, Integer> availableNotes) {
        this.availableNotes = availableNotes;
    }

    public Map<Coin, Integer> getInsertedCoins() {
        return insertedCoins;
    }

    public void setInsertedCoins(Map<Coin, Integer> insertedCoins) {
        this.insertedCoins = insertedCoins;
    }

    public Map<Note, Integer> getInsertedNotes() {
        return insertedNotes;
    }

    public void setInsertedNotes(Map<Note, Integer> insertedNotes) {
        this.insertedNotes = insertedNotes;
    }

    public Map<Coin, Integer> getReturnCoins() {
        return returnCoins;
    }

    public void setReturnCoins(Map<Coin, Integer> returnCoins) {
        this.returnCoins = returnCoins;
    }

    public Map<Note, Integer> getReturnNotes() {
        return returnNotes;
    }

    public void setReturnNotes(Map<Note, Integer> returnNotes) {
        this.returnNotes = returnNotes;
    }

    public void display(Map<Note, Integer> notes, Map<Coin, Integer> coins) {
        for (Map.Entry<Note, Integer> noteEntry: notes.entrySet()) {
            System.out.println("Note: " + noteEntry.getKey() + " | no of notes: " + noteEntry.getValue());
        }

        for (Map.Entry<Coin, Integer> coinEntry: coins.entrySet()) {
            System.out.println("Coin: " + coinEntry.getKey() + " | no of coins: " + coinEntry.getValue());
        }
    }

    public void insertCoin(Coin coin, int quantity) {
        insertedCoins.put(coin, insertedCoins.getOrDefault(coin, 0) + quantity);
    }

    public void insertNote(Note note, int quantity) {
        insertedNotes.put(note, insertedNotes.getOrDefault(note, 0) + quantity);
    }

    public void refundTotalAmount() {
        for (Map.Entry<Note, Integer> noteEntry: insertedNotes.entrySet()) {
            System.out.println("Returning note: " + noteEntry.getKey() + " quantity: " + noteEntry.getValue());
        }
        for (Map.Entry<Coin, Integer> coinEntry: insertedCoins.entrySet()) {
            System.out.println("Returning coin: " + coinEntry.getKey() + " quantity: " + coinEntry.getValue());
        }
        insertedNotes = new HashMap<>();
        insertedCoins = new HashMap<>();
    }

    public int getTotalAmountInMachine() {
        int totalAmountInMachine = 0;
        for (Map.Entry<Coin, Integer> coinEntry: availableCoins.entrySet()) {
            totalAmountInMachine += (coinEntry.getKey().getValue() * coinEntry.getValue());
        }
        for (Map.Entry<Note, Integer> noteEntry: availableNotes.entrySet()) {
            totalAmountInMachine += (noteEntry.getKey().getValue() * noteEntry.getValue());
        }
        return totalAmountInMachine;
    }

    public int getTotalInsertedAmount() {
        int totalInsertedAmount = 0;
        for (Map.Entry<Coin, Integer> coinEntry: insertedCoins.entrySet()) {
            totalInsertedAmount += (coinEntry.getKey().getValue() * coinEntry.getValue());
        }
        for (Map.Entry<Note, Integer> noteEntry: insertedNotes.entrySet()) {
            totalInsertedAmount += (noteEntry.getKey().getValue() * noteEntry.getValue());
        }
        return totalInsertedAmount;
    }

    public boolean calculateChange(int selectedProductsPrice) {
        int totalInsertedAmount = getTotalInsertedAmount();

        if (totalInsertedAmount < selectedProductsPrice) {
            System.out.println("inserted amount is " + (selectedProductsPrice - totalInsertedAmount) + " lesser, exiting...");
            return false;
        }

        int totalChange = totalInsertedAmount - selectedProductsPrice;
        if (totalChange == 0) return true;
        Note[] notes = Note.getNotesDescendingOrder();
        Coin[] coins = Coin.getCoinsDescendingOrder();

        for (Note note: notes) {
            int quantity = availableNotes.getOrDefault(note, 0);
            int noteValue = note.getValue();
            int totalNoteValue = noteValue * quantity;

            if (totalChange >= totalNoteValue && quantity > 0) {
                totalChange -= totalNoteValue;
                returnNotes.put(note, quantity);
            } else if (totalChange > 0){
                int requiredNotes = totalChange / noteValue;
                if (requiredNotes > 0) {
                    totalChange -= requiredNotes * noteValue;
                    returnNotes.put(note, requiredNotes);
                }
            }

            if (totalChange <= 0) break;
//            ---------------------------------

            quantity = insertedNotes.getOrDefault(note, 0);
            totalNoteValue = noteValue * quantity;
            if (totalChange >= totalNoteValue && quantity > 0) {
                totalChange -= totalNoteValue;
                returnNotes.put(note, returnNotes.getOrDefault(note, 0) + quantity);
            } else {
                int requiredNotes = totalChange / noteValue;
                if (requiredNotes > 0) {
                    totalChange -= requiredNotes * noteValue;
                    returnNotes.put(note, requiredNotes);
                }
            }

            if (totalChange <= 0) break;
        }

        for (Coin coin: coins) {
            int quantity = availableCoins.getOrDefault(coin, 0);
            int coinValue = coin.getValue();
            int totalCoinValue = coinValue * quantity;

            if (totalChange >= totalCoinValue && quantity > 0) {
                totalChange -= totalCoinValue;
                returnCoins.put(coin, quantity);
            } else if (totalChange > 0){
                int requiredCoins = totalChange / coinValue;
                if (requiredCoins > 0) {
                    totalChange -= requiredCoins * coinValue;
                    returnCoins.put(coin, requiredCoins);
                }
            }

            if (totalChange <= 0) break;
//            ---------------------------------

            quantity = insertedCoins.getOrDefault(coin, 0);
            totalCoinValue = coinValue* quantity;
            if (totalChange >= totalCoinValue && quantity > 0) {
                totalChange -= totalCoinValue;
                returnCoins.put(coin, returnCoins.getOrDefault(coin, 0) + quantity);
            } else {
                int requiredCoins = totalChange / coinValue;
                if (requiredCoins > 0) {
                    totalChange -= requiredCoins * coinValue;
                    returnCoins.put(coin, requiredCoins);
                }
            }

            if (totalChange <= 0) break;
        }

        return totalChange == 0;
    }

    public void dispenseChange() {
        for (Map.Entry<Note, Integer> noteEntry: availableNotes.entrySet()) {
            Note note = noteEntry.getKey();
            int insertedNoteCount = insertedNotes.getOrDefault(note, 0);
            int returnedNoteCount = returnNotes.getOrDefault(note, 0);
            availableNotes.put(note, availableNotes.get(note) + insertedNoteCount - returnedNoteCount);
        }
        for (Map.Entry<Coin, Integer> coinEntry: availableCoins.entrySet()) {
            Coin coin = coinEntry.getKey();
            int insertedCoinCount = insertedCoins.getOrDefault(coin, 0);
            int returnedCoinCount = returnCoins.getOrDefault(coin, 0);
            availableCoins.put(coin, availableCoins.get(coin) + insertedCoinCount - returnedCoinCount);
        }

        System.out.println("Returning Change to user....");
        display(returnNotes, returnCoins);

    }
}
