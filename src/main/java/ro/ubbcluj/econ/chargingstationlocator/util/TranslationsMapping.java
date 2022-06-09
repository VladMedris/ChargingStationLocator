package ro.ubbcluj.econ.chargingstationlocator.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import java.util.Map;

@Data
@ConfigurationProperties(prefix = "translation.chargingstationlocator")
public class TranslationsMapping {

    private Map<String, String> translations;

    public String getTranslation(String requestedTranslationKey){
        return translations.get(requestedTranslationKey);
    }
}
