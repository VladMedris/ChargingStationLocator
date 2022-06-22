package ro.ubbcluj.econ.chargingstationlocator.locator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import ro.ubbcluj.econ.chargingstationlocator.locator.entity.DetailedStationBE;
import ro.ubbcluj.econ.chargingstationlocator.locator.entity.GetStationResponseBE;
import ro.ubbcluj.econ.chargingstationlocator.locator.entity.SimpleStationBE;
import ro.ubbcluj.econ.chargingstationlocator.util.TranslationsMapping;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@EnableConfigurationProperties(value = TranslationsMapping.class)
public class TranslationService {

    private static final String COUNTRY_KEY = "country";
    private static final String CURRENT_STATUS_KEY = "currentStatus";
    private static final String SERVICE_YES_KEY = "serviceYes";
    private static final String SERVICE_NO_KEY = "serviceNo";
    private static final String ACCESS_PUBLIC_KEY = "accessPublic";
    private static final String ACCESS_RESTRICTED_KEY = "accessRestricted";
    private static final String AVAILABILITY_STATUS_FREE_KEY = "statusYes";
    private static final String AVAILABILITY_STATUS_OCCUPIED_KEY = "statusNo";


    @Autowired
    private TranslationsMapping translationsMapping;

    public GetStationResponseBE translateDetailed(GetStationResponseBE response, String language){
        List<DetailedStationBE> detailedStations = response.getData();

        detailedStations = detailedStations.stream().map(station -> {
            station.getAddress().setCountry(translationsMapping.getTranslation(COUNTRY_KEY, language));
            station.getOperatingSchedule().setCurrentStatus(translationsMapping.getTranslation(CURRENT_STATUS_KEY, language));

            if(Objects.equals(station.getDetailedInformation().getService(), "Self Service")){
                station.getDetailedInformation().setService(translationsMapping.getTranslation(SERVICE_NO_KEY, language));
            } else {
                station.getDetailedInformation().setService(translationsMapping.getTranslation(SERVICE_YES_KEY, language));
            }

            if(Objects.equals(station.getDetailedInformation().getAccess(), "Public")){
                station.getDetailedInformation().setAccess(translationsMapping.getTranslation(ACCESS_PUBLIC_KEY, language));
            } else {
                station.getDetailedInformation().setAccess(translationsMapping.getTranslation(ACCESS_RESTRICTED_KEY, language));
            }

            if(Objects.equals(station.getAvailability().getStatus(), "Free")){
                station.getAvailability().setStatus(translationsMapping.getTranslation(AVAILABILITY_STATUS_FREE_KEY, language));
            } else {
                station.getAvailability().setStatus(translationsMapping.getTranslation(AVAILABILITY_STATUS_OCCUPIED_KEY, language));
            }

            return station;
        }).collect(Collectors.toList());
        response.setData(detailedStations);
        return response;
    }

    public GetStationResponseBE translateSimple(GetStationResponseBE response, String language){
        List<SimpleStationBE> simpleStations = response.getSimpleData();

        simpleStations = simpleStations.stream().map(station -> {
            station.getAddress().setCountry(translationsMapping.getTranslation(COUNTRY_KEY, language));
            return station;
        }).collect(Collectors.toList());
        response.setSimpleData(simpleStations);
        return response;
    }
}
