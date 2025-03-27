public class ProductItem {
    private String name;
    private int quantity;
    private int price;

    public ProductItem(String name, int quantity, int price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void remove(int count) {
        quantity -= count;
    }

    public void add(int count) {
        quantity += count;
    }
}
