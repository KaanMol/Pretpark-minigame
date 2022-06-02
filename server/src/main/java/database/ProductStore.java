package database;

import domain.Product;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProductStore {

    private final List<Product> products;

    public ProductStore() {
        this.products = new ArrayList<>();

        create("Fries", "404.jpeg", "Description",10);
        create("Coke", "404.jpeg", "Description",5);
        create("Coffee", "404.jpeg", "Description",10);
        create("Tea", "404.jpeg", "Description",10);
        create("Ice cream", "404.jpeg", "Description",20);
        create("Sandwich", "404.jpeg", "Description",15);
        create("Cake", "404.jpeg", "Description",30);
        create("Cupcake", "404.jpeg", "Description", 15);
    }

    public Product create(String name, String image, String description, int price) {
        Product product = new Product(products.size() + 1 + "", name, image, description, price);
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
