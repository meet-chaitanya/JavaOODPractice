import java.util.List;

public class Idle implements State{
    private VendingMachine vendingMachine;

    public Idle(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void restockItems(List<ProductItem> items) {
        for (ProductItem item: items) {
            if (vendingMachine.checkIfProductExists(item.getName())) {
                vendingMachine.updateProduct(item.getName(), item.getQuantity(), item.getPrice());
            } else {
                vendingMachine.addProduct(item);
            }
        }
    }

    @Override
    public void insertCoin(Coin coin, int quantity) {
        vendingMachine.getCashManager().insertCoin(coin, quantity);
        vendingMachine.setState(vendingMachine.getHasCashState());
    }

    @Override
    public void insertNote(Note note, int quantity) {
        vendingMachine.getCashManager().insertNote(note, quantity);
        vendingMachine.setState(vendingMachine.getHasCashState());
    }

    @Override
    public void cancelTransaction() {
        System.out.println("you have not inserted any cash!");
    }

    @Override
    public void selectProduct(String name, int quantity) {
        System.out.println("please insert money first");
    }

    @Override
    public void cancelSelection() {
        System.out.println("please insert money first");
    }

    @Override
    public void calculateChange() {
        System.out.println("You have not inserted any money");
    }

    @Override
    public void dispense() {
        System.out.println("can't dispense without money");
    }
}
