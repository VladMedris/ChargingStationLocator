package ro.ubbcluj.econ.chargingstationlocator.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import java.util.Map;

@Data
@ConfigurationProperties(prefix = "chargingstationlocator")
public class TranslationsMapping {

    private Map<String, Map<String, String>> translations;

    public String getTranslation(String requestedTranslationKey, String language){

        if(language == null || language.isEmpty()){
            return translations.get(requestedTranslationKey).get("en");
        } else {
            return translations.get(requestedTranslationKey).get(language);
        }
    }
}
