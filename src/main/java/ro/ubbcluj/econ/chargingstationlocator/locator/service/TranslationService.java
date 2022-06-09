package ro.ubbcluj.econ.chargingstationlocator.locator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import ro.ubbcluj.econ.chargingstationlocator.util.TranslationsMapping;

@Component
@EnableConfigurationProperties(value = TranslationsMapping.class)
public class TranslationService {

    @Autowired
    private TranslationsMapping translationsMapping;

    private String getTranslatedString(String key){
        return translationsMapping.getTranslation(key);
    }
}
