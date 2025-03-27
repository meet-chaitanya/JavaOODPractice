import java.util.Arrays;

public enum Note{
    TEN(10), TWENTY(20), FIFTY(50), HUNDRED(100), TWO_HUNDRED(200), FIVE_HUNDRED(500), THOUSAND(1000);

    private final int value;

    Note(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public Note findByValue(int value) {
        for (Note note: values()) {
            if (note.value == value) {
                return note;
            }
        }
        return null;
    }

    public static Note[] getNotesDescendingOrder() {
        Note[] notes = values();
        Arrays.sort(notes, (a, b) -> Integer.compare(b.getValue(), a.getValue()));
        return notes;
    }
}
