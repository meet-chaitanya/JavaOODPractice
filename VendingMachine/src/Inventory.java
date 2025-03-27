import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private Map<String, ProductItem> products;

    public Inventory() {
        products = new HashMap<>();
    }

    public Map<String, ProductItem> getProducts() {
        return products;
    }

    public void addProduct(ProductItem productItem) {
        if (contains(productItem.getName())) {
            System.out.println("Product: " + productItem.getName() + " already exists. use update product with quantity for updating product quantity");
            return;
        }
        products.put(productItem.getName(), productItem);
    }

    public boolean hasProduct(String name, int quantity) {
        if (!contains(name)) {
            System.out.println("Product: " + name + " does not exists!");
            return false;
        }
        ProductItem item = products.get(name);
        return item.getQuantity() >= quantity;
    }

    public void removeProduct(String name, int quantity) {
        products.get(name).remove(quantity);
        if (products.get(name).getQuantity() == 0) {
            products.remove(name);
        }
    }

    public void updateProduct(String name, int quantity, int price) {
        products.get(name).add(quantity);
        if (price > 0)
            products.get(name).setPrice(price);
    }

    public boolean contains(String name) {
        return products.containsKey(name);
    }

    public ProductItem getProduct(String name) {
        if (contains(name)) {
            return products.get(name);
        }
        return null;
    }

    public void display() {
        for (ProductItem item: products.values()) {
            System.out.println("-------------------------------------");
            System.out.println("Item Name: " + item.getName());
            System.out.println("Item Price: " + item.getPrice());
            System.out.println("No of items left: " + item.getQuantity());
        }
    }
}
