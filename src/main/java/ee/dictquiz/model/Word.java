package ee.dictquiz.model;

import java.util.HashMap;
import java.util.Map;

public class Word {

    private Map<String, String> translations = new HashMap<>();

    public Word(String translationString) {

        String [] translations = translationString.split(",");

        for (String translation: translations) {

            String [] langMapping = translation.split(":");

            this.translations.put(langMapping[0], langMapping[1]);
        }
    }

    public String getTranslation(String code) {
        return this.translations.get(code);
    }

    public String getTargetTranslation(String code) {

        Map<String, String> targetTranslations = new HashMap<>();

        for (String language: translations.keySet()) {
            if (!code.equals(language)) {
                targetTranslations.put(language, this.translations.get(language));
            }
        }
        return targetTranslations.values().toArray()[0].toString();
    }

    public boolean translatesTo(String translation, String languageMapping) {
        if (languageMapping.startsWith("ee")) {
            for (String language: this.translations.keySet()) {
                if (this.translations.get(language).equals(translation.trim())) {
                    return true;
                }
            }
            return false;
        } else {
            return translation.trim().equals(this.translations.get("ee"));
        }
    }

    public String showTranslations() {

        StringBuilder translations = new StringBuilder();

        translations.append("(");
        for (String language: this.translations.keySet()) {
            translations.append(language);
            translations.append(",");
        }
        translations.deleteCharAt(translations.length() - 1);
        translations.append(")");

        return translations.toString();
    }
}