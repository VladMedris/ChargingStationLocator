package ro.ubbcluj.econ.chargingstationlocator.locator.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ro.ubbcluj.econ.chargingstationlocator.locator.entity.GetStationsByGeolocationRequest;
import ro.ubbcluj.econ.chargingstationlocator.locator.repository.QueryDatabase;
import ro.ubbcluj.econ.chargingstationlocator.locator.entity.ChargingStationData;
import ro.ubbcluj.econ.chargingstationlocator.locator.entity.GetStationByIdRequest;
import ro.ubbcluj.econ.chargingstationlocator.locator.entity.GetStationResponseBE;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor_ = { @Autowired})
public class LocatorService {

    private final QueryDatabase queryDatabase;
    private final ChargingStationDataTransformer transformer;
    private final TranslationService translationService;

    @Transactional
    public GetStationResponseBE getStationByIdResponse(GetStationByIdRequest getStationByIdRequest, String businessCase) {
        StringBuilder sb = new StringBuilder();
        getStationByIdRequest.getIds().forEach(id -> {
            sb.append(id);
            sb.append(",");
        });
        sb.deleteCharAt(sb.length() - 1);

        final List<ChargingStationData> stations;
        switch (businessCase.toLowerCase()){
            case "simple":
                stations = queryDatabase.findStationByIdSimpleFormat(sb.toString()).stream().distinct().collect(Collectors.toList());
                return transformer.transformSimpleData(stations);
            case "detailed":
                stations = queryDatabase.findStationByIdDetailedFormat(sb.toString()).stream().distinct().collect(Collectors.toList());
                return transformer.transformDetailedData(stations);
            default:
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Unexpected business-case header value: " + businessCase.toLowerCase());
        }
    }

    @Transactional
    public GetStationResponseBE getStationByGeolocation(GetStationsByGeolocationRequest getStationsByGeolocationRequest, String businessCase){
        float latitude = getStationsByGeolocationRequest.getLatitude();
        float longitude = getStationsByGeolocationRequest.getLongitude();
        int distance = getStationsByGeolocationRequest.getDistance();

        final List<ChargingStationData> stations;
        switch (businessCase.toLowerCase()){
            case "simple":
                stations = queryDatabase.findStationByGeolocationSimpleFormat(latitude, longitude, distance).stream().distinct().collect(Collectors.toList());
                return transformer.transformSimpleData(stations);
            case "detailed":
                stations = queryDatabase.findStationByGeolocationDetailedFormat(latitude, longitude, distance).stream().distinct().collect(Collectors.toList());
                return transformer.transformDetailedData(stations);
            default:
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Unexpected business-case header value: " + businessCase.toLowerCase());
        }
    }
}
