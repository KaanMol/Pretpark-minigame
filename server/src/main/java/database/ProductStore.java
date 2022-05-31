package database;

import domain.Product;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProductStore {

    private final List<Product> products;

    public ProductStore() {
        this.products = new ArrayList<>();

        create("Fries", 10);
        create("Coke", 5);
        create("Coffee", 10);
        create("Tea", 10);
        create("Ice cream", 20);
        create("Sandwich", 15);
        create("Cake", 30);
        create("Cupcake", 15);
    }

    public Product create(String name, int price) {
        Product product = new Product(products.size() + 1 + "", name, price);
        products.add(product);
        return product;
    }

    public Product find(String productId) {
        for (Product product : products) {
            if (product.productId().equals(productId)) {
                return product;
            }
        }

        return null;
    }

    public List<Product> all() {
        return products;
    }

    public void clear() {
        products.clear();
    }
}
