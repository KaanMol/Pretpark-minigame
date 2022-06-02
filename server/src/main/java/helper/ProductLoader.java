package helper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import database.Language;
import database.LocalisationStore;
import database.ProductStore;
import database.Store;
import io.FileManager;
import logging.Logger;

import java.util.List;

public class ProductLoader {
    public static void loadProducts(String json, ProductStore products, LocalisationStore localisations) {
        try {
            ProductEntry[] productEntries = new ObjectMapper().readValue(json, ProductEntry[].class);

            Logger.debug("Loading " + productEntries.length + " products");

            for (ProductEntry productEntry : productEntries) {
                try {
                    products.create(productEntry.id(), productEntry.id() + "_title", productEntry.id() + "_description", productEntry.image(), productEntry.price());
                    localisations.set(productEntry.id() + "_title", Language.DUTCH, productEntry.name().dutch());
                    localisations.set(productEntry.id() + "_title", Language.ENGLISH, productEntry.name().english());
                    localisations.set(productEntry.id() + "_description", Language.DUTCH, productEntry.description().dutch());
                    localisations.set(productEntry.id() + "_description", Language.ENGLISH, productEntry.description().english());

                    Logger.debug("Loaded product " + productEntry.id());
                } catch (Exception ex) {
                    Logger.warn(ex, "Failed to load product " + productEntry.id());
                }
            }
        } catch (Exception ex) {
            Logger.error(ex, "Failed to load products");
        }
    }
}

record ProductEntry(String id, Translatable name, Translatable description, String image, int price) {

}

record Translatable(String dutch, String english) {

}
