import java.util.List;
import java.util.Map;

public class VendingMachine {
    private int userTotalAmount;
    private int totalChange;
    private String selectedProductName;
    private int selectedProductQuantity;
    private Inventory inventory;
    private CashManager cashManager;

    private State soldOutState;
    private State idleState;
    private State hasCashState;
    private State productSelectedState;
    private State changeCalculatedAndDispenseState;

    private State currentState;

    public VendingMachine(Inventory inventory, Map<Coin, Integer> coinMap, Map<Note, Integer> noteMap) {
        this.userTotalAmount = 0;
        this.totalChange = 0;
        this.selectedProductName = null;
        this.selectedProductQuantity = 0;
        this.cashManager = new CashManager();
        loadMoneyIntoMachine(coinMap, noteMap);
        this.inventory = inventory;


        soldOutState = new SoldOut(this);
        idleState = new Idle(this);
        hasCashState = new HasCash(this);
        productSelectedState = new ProductSelected(this);
        changeCalculatedAndDispenseState = new ChangeCalculatedAndDispense(this);

        if (!checkIfProductsExists()) {
            currentState = soldOutState;
        } else  {
            currentState = idleState;
        }
    }

    public CashManager getCashManager() {
        return cashManager;
    }

    public State getSoldOutState() {
        return soldOutState;
    }

    public State getChangeCalculatedAndDispenseState() {
        return changeCalculatedAndDispenseState;
    }

    public State getProductSelectedState() {
        return productSelectedState;
    }

    public State getHasCashState() {
        return hasCashState;
    }

    public State getIdleState() {
        return idleState;
    }

    public void setState(State currentState) {
        this.currentState = currentState;
    }

    public State getCurrentState() {
        return currentState;
    }

    public void loadMoneyIntoMachine(Map<Coin, Integer> coinMap, Map<Note, Integer> noteMap) {
        cashManager.setAvailableCoins(coinMap);
        cashManager.setAvailableNotes(noteMap);
    }

    public int getUserTotalAmount() {
        return userTotalAmount;
    }

    public int getTotalAmountInMachine() {
        return cashManager.getTotalAmountInMachine();
    }

    public int getTotalChange() {
        return totalChange;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setSelectedProductName(String selectedProductName) {
        this.selectedProductName = selectedProductName;
    }

    public String getSelectedProductName() {
        return selectedProductName;
    }

    public void setSelectedProductQuantity(int selectedProductQuantity) {
        this.selectedProductQuantity = selectedProductQuantity;
    }

    public int getSelectedProductQuantity() {
        return selectedProductQuantity;
    }

    public void addProduct(ProductItem item) {
        inventory.addProduct(item);
    }

    public boolean checkIfProductExists(String name) {
        return inventory.contains(name);
    }

    public void updateProduct(String name, int quantity, int price) {
        if (inventory.contains(name)) {
            inventory.updateProduct(name, quantity, price);
        } else {
            ProductItem item = new ProductItem(name, quantity, price);
            addProduct(item);
        }
    }

    public void removeProduct(String name, int quantity) {
        if (!inventory.hasProduct(name, quantity)) {
            System.err.println("Product: " + name + " does not exist or it does not have that much quantity: " + quantity);
            return;
        }
        inventory.removeProduct(name, quantity);
    }

    public void displayAvailableProducts() {
        inventory.display();
    }

    public void displayAvailableCoinsAndNotes() {
        cashManager.display(cashManager.getAvailableNotes(), cashManager.getAvailableCoins());
    }

    public boolean checkIfProductsExists() {
        return !inventory.getProducts().isEmpty();
    }

    public void insertCoin(Coin coin, int count) {
        currentState.insertCoin(coin, count);
    }

    public void insertNote(Note note, int count) {
        currentState.insertNote(note, count);
    }

    public void restockItems(List<ProductItem> items) {
        currentState.restockItems(items);
    }

    public void cancelTransaction() {
        currentState.cancelTransaction();
    }

    public void selectProduct(String name, int quantity) {
        currentState.selectProduct(name, quantity);
    }

    public void cancelSelection() {
        currentState.cancelSelection();
    }

    public void calculateChange() {
        currentState.calculateChange();
    }

    public void dispense() {
        currentState.dispense();
    }
}
