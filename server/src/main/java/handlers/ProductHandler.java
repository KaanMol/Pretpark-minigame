package handlers;

import database.Language;
import domain.Product;
import http.HttpResponse;
import http.JsonHttpResponse;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class ProductHandler extends Handler{
    @Override
    protected HttpResponse get() throws IOException {
        String productId = getParameter("id");
        String languageRaw = getParameter("language");

        if(languageRaw == null) {
            languageRaw = "dutch";
        }

        Language language = switch (languageRaw.trim().toLowerCase(Locale.ROOT)) {
            case "en", "english" -> Language.ENGLISH;
            default -> Language.DUTCH;
        };

        return productId == null ? getAll(language) : getById(productId, language);
    }

    private HttpResponse getById(String id, Language language) {
        Product product = getStore().products().find(id);

        if (product == null) {
            return conflict("Product not found");
        }

        return ok(translate(product, language));
    }

    private HttpResponse getAll(Language language) {
        List<Product> products = getStore().products().all();
        return ok(products.stream().map(p -> translate(p, language)).toArray(Product[]::new));
    }

    @Override
    protected HttpResponse post() throws IOException {
        return error("Not implemented");
    }

    @Override
    protected HttpResponse put() throws IOException {
        return error("Not implemented");
    }

    @Override
    protected HttpResponse delete() throws IOException {
        return error("Not implemented");
    }

    private Product translate(Product product, Language language) {
        String name = getStore().localisations().get(product.productId() + "_title", language);
        String description = getStore().localisations().get(product.productId() + "_description", language);
        return new Product(product.productId(), name, product.image(), description, product.price());
    }
}
