import java.util.List;

public class ChangeCalculatedAndDispense implements State{
    private VendingMachine vendingMachine;

    public ChangeCalculatedAndDispense(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void restockItems(List<ProductItem> items) {
        System.out.println("Machine currently processing another request, please restock later...");
    }

    @Override
    public void insertCoin(Coin coin, int quantity) {
        System.out.println("cant insert coins, dispensing product....");
    }

    @Override
    public void insertNote(Note note, int quantity) {
        System.out.println("cant insert notes, dispensing product....");
    }

    @Override
    public void cancelTransaction() {
        System.out.println("can't cancel the transaction, already dispensing the product");
    }

    @Override
    public void selectProduct(String name, int quantity) {
        System.out.println("product is already selected...");
    }

    @Override
    public void cancelSelection() {
        System.out.println("cant unselect the product, already dispensing the product");
    }

    @Override
    public void calculateChange() {
        System.out.println("already calculated change, dispensing the product");
    }

    @Override
    public void dispense() {
        Inventory inventory = vendingMachine.getInventory();
        String selectedProductName = vendingMachine.getSelectedProductName();
        int quantity = vendingMachine.getSelectedProductQuantity();
        System.out.println("dispensing the product : " + selectedProductName + " and quantity: " + quantity);
        inventory.removeProduct(selectedProductName, quantity);

        if (vendingMachine.checkIfProductsExists()) {
            vendingMachine.setState(vendingMachine.getIdleState());
        } else {
            vendingMachine.setState(vendingMachine.getSoldOutState());
        }
    }
}
