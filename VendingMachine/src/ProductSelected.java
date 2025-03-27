import java.util.List;

public class ProductSelected implements State{
    private VendingMachine vendingMachine;

    public ProductSelected(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void restockItems(List<ProductItem> items) {
        System.out.println("Machine currently processing another request, please restock later...");
    }

    @Override
    public void insertCoin(Coin coin, int quantity) {
        System.out.println("cant insert coins, do you want the selected product?");
    }

    @Override
    public void insertNote(Note note, int quantity) {
        System.out.println("cant insert coins, do you want the selected product?");
    }

    @Override
    public void cancelTransaction() {
        vendingMachine.getCashManager().refundTotalAmount();
        vendingMachine.setState(vendingMachine.getIdleState());
    }

    @Override
    public void selectProduct(String name, int quantity) {
        System.out.println("You have already selected a product!!");
    }

    @Override
    public void cancelSelection() {
        vendingMachine.setSelectedProductName(null);
        vendingMachine.setSelectedProductQuantity(0);
        vendingMachine.setState(vendingMachine.getHasCashState());
    }

    @Override
    public void calculateChange() {
        Inventory inventory = vendingMachine.getInventory();
        ProductItem item = inventory.getProduct(vendingMachine.getSelectedProductName());
        int price = item.getPrice() * vendingMachine.getSelectedProductQuantity();
        CashManager cashManager = vendingMachine.getCashManager();
        boolean amountSufficeToPurchase = cashManager.calculateChange(price);
        if (amountSufficeToPurchase) {
            System.out.println("dispensing change...");
            cashManager.dispenseChange();
            vendingMachine.setState(vendingMachine.getChangeCalculatedAndDispenseState());
        } else {
            System.out.println("Insufficient funds... refunding the amount");
            cashManager.refundTotalAmount();
            vendingMachine.setState(vendingMachine.getIdleState());
        }
    }

    @Override
    public void dispense() {
        System.out.println("machine is validating the amount w/ selected product price and quantity, It will be dispensed once validated");
    }
}
