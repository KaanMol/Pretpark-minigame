package database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class LocalisationStore {
    private final HashMap<String, List<Translation>> translations;

    public LocalisationStore() {
        translations = new HashMap<>();
    }

    public void set(String key, Language language, String value) {
        if(translations.containsKey(key)) {
            // Add to list of translations
            List<Translation> keyTranslations = new ArrayList<>(translations.get(key));
            keyTranslations.add(new Translation(language, value));
            translations.put(key, keyTranslations);
        } else {
            // Create new list of translations
            translations.put(key, List.of(new Translation[]{new Translation(language, value)}));
        }
    }

    public String get(String key, Language language) {
        String fallback = key.toUpperCase(Locale.ROOT) + "_" + language.toString().toUpperCase();

        List<Translation> translations = this.translations.get(key);

        if(translations == null || translations.isEmpty()) {
            return fallback;
        }

        for(Translation translation : translations) {
            if(translation.language() == language) {
                return translation.value();
            }
        }

        return fallback;
    }
}

record Translation(Language language, String value) {

}

