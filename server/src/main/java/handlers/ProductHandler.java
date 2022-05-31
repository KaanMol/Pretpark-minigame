package handlers;

import domain.Product;
import http.HttpResponse;

import java.io.IOException;
import java.util.List;

public class ProductHandler extends Handler{
    @Override
    protected HttpResponse get() throws IOException {
        String productId = getParameter("id");

        return productId == null ? getAll() : getById(productId);
    }

    private HttpResponse getById(String id) {
        Product product = getStore().products().find(id);

        if (product == null) {
            return conflict("Product not found");
        }

        return ok(product);
    }

    private HttpResponse getAll() {
        List<Product> products = getStore().products().all();

        return ok(products);
    }

    @Override
    protected HttpResponse post() throws IOException {
        return null;
    }

    @Override
    protected HttpResponse put() throws IOException {
        return null;
    }

    @Override
    protected HttpResponse delete() throws IOException {
        return null;
    }
}
