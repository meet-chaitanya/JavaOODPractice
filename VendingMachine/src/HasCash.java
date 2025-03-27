import java.util.List;

public class HasCash implements State{
    private VendingMachine vendingMachine;

    public HasCash(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void restockItems(List<ProductItem> items) {
        System.out.println("Machine currently processing another request, please restock later...");
    }

    @Override
    public void insertCoin(Coin coin, int quantity) {
        vendingMachine.getCashManager().insertCoin(coin, quantity);
    }

    @Override
    public void insertNote(Note note, int quantity) {
        vendingMachine.getCashManager().insertNote(note, quantity);
    }

    @Override
    public void cancelTransaction() {
        vendingMachine.getCashManager().refundTotalAmount();
        vendingMachine.setState(vendingMachine.getIdleState());
    }

    @Override
    public void selectProduct(String name, int quantity) {
        if (vendingMachine.getInventory().hasProduct(name, quantity)) {
            vendingMachine.setSelectedProductName(name);
            vendingMachine.setSelectedProductQuantity(quantity);
            vendingMachine.setState(vendingMachine.getProductSelectedState());
        } else {
            System.out.println("Selected Product " + name +  " does not have " + quantity + " items");
            ProductItem item = vendingMachine.getInventory().getProduct(name);
            if (item != null) {
                System.out.println("It only has " + item.getQuantity() + " items");
            }
            System.out.println("Please reselect!!");
        }
    }

    @Override
    public void cancelSelection() {
        System.out.println("You have not selected the product...");
    }

    @Override
    public void calculateChange() {
        System.out.println("You have not selected the product... to dispense the item and change");
    }

    @Override
    public void dispense() {
        System.out.println("You have not selected the product... to dispense the item");
    }
}
