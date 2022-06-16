package ro.ubbcluj.econ.chargingstationlocator.locator.service;

import io.swagger.models.auth.In;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ro.ubbcluj.econ.chargingstationlocator.locator.entity.*;
import ro.ubbcluj.econ.chargingstationlocator.locator.repository.QueryDatabase;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor_ = { @Autowired})
public class LocatorService {

    private final QueryDatabase queryDatabase;
    private final ChargingStationDataTransformer transformer;
    private final ChargingReverseTransformer reverseTransformer;
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


    public void deleteStationById (DeleteStationByIdRequest deleteStationByIdRequest){
        queryDatabase.deleteStationById(deleteStationByIdRequest.getId());
    }

    public GetStationResponseBE updateStationByIdRequest(UpdateStationByIdRequest updateStationByIdRequest, DetailedStationBE stationBE){
        Optional<ChargingStationData> stationToUpdateOpt = queryDatabase.findById(updateStationByIdRequest.getId());
        if(stationToUpdateOpt.isPresent()) {
            ChargingStationData transformedStation = reverseTransformer.createStation(stationToUpdateOpt.get(), stationBE);
            return transformer.transformSimpleData(Collections.singletonList(queryDatabase.save(transformedStation)));
        }
        return null;
    }
}
