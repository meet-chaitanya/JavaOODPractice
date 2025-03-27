import java.util.List;

public class SoldOut implements State{
    private VendingMachine vendingMachine;

    public SoldOut(VendingMachine vendingMachine){
        this.vendingMachine = vendingMachine;
    }
    @Override
    public void restockItems(List<ProductItem> items) {
        for (ProductItem item: items) {
            vendingMachine.addProduct(item);
        }
        vendingMachine.setState(vendingMachine.getIdleState());
    }

    @Override
    public void insertCoin(Coin coin, int quantity) {
        System.out.println("Currently there are no products in the machine!");
    }

    @Override
    public void insertNote(Note note, int quantity) {
        System.out.println("Currently there are no products in the machine!");
    }

    @Override
    public void cancelTransaction() {
        System.out.println("No Transaction made to cancel! Also there are no products:)");
    }

    @Override
    public void selectProduct(String name, int quantity) {
        System.out.println("No products!");
    }

    @Override
    public void cancelSelection() {
        System.out.println("No products, So it would not have selected to cancel");
    }

    @Override
    public void calculateChange() {
        System.out.println("No products, So it would not have accepted Notes!");
    }

    @Override
    public void dispense() {
        System.out.println("Nothing to dispense.");
    }
}
