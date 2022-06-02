package database;

import domain.Product;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProductStore {

    private final List<Product> products;

    public ProductStore() {
        this.products = new ArrayList<>();
    }

    public Product create(String id, String nameId, String descriptionId, String image,  int price) {
        Product product = new Product(id, nameId, image, descriptionId, price);
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
}
