import java.util.HashMap;
import java.util.Map;

public class Driver {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        ProductItem itemA = new ProductItem("Icecream", 10, 50);
        inventory.addProduct(itemA);
        ProductItem itemB = new ProductItem("DairyMilk", 10, 40);
        inventory.addProduct(itemB);
        ProductItem itemC = new ProductItem("Chocolate", 20, 10);
        inventory.addProduct(itemC);

        Map<Coin, Integer> coinMap = new HashMap<>();
        coinMap.put(Coin.FIVE, 10);
        coinMap.put(Coin.TWO, 20);
        coinMap.put(Coin.ONE, 30);

        Map<Note, Integer> noteMap = new HashMap<>();
        noteMap.put(Note.THOUSAND, 2);
        noteMap.put(Note.FIVE_HUNDRED, 5);
        noteMap.put(Note.TWO_HUNDRED, 8);
        noteMap.put(Note.HUNDRED, 10);
        noteMap.put(Note.FIFTY, 15);
        noteMap.put(Note.TWENTY, 20);
        noteMap.put(Note.TEN, 30);

        VendingMachine vendingMachine = new VendingMachine(inventory, coinMap, noteMap);

        vendingMachine.displayAvailableCoinsAndNotes();

        vendingMachine.insertCoin(Coin.FIVE, 9);
        vendingMachine.insertNote(Note.HUNDRED, 1);
        vendingMachine.selectProduct("Icecream", 2);
        vendingMachine.calculateChange();
        vendingMachine.dispense();

        vendingMachine.displayAvailableProducts();
        vendingMachine.displayAvailableCoinsAndNotes();
    }
}
