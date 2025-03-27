import java.util.List;

public interface State {
    void restockItems(List<ProductItem> items);
    void insertCoin(Coin coin, int quantity);
    void insertNote(Note note, int quantity);
    void cancelTransaction();
    void selectProduct(String name, int quantity);
    void cancelSelection();
    void calculateChange();
    void dispense();
}
