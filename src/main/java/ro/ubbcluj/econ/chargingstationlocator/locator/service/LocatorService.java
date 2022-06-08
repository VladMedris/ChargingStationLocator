package ro.ubbcluj.econ.chargingstationlocator.locator.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubbcluj.econ.chargingstationlocator.locator.entity.GetStationsByGeolocationRequest;
import ro.ubbcluj.econ.chargingstationlocator.locator.repository.QueryDatabase;
import ro.ubbcluj.econ.chargingstationlocator.locator.entity.ChargingStationData;
import ro.ubbcluj.econ.chargingstationlocator.locator.entity.GetStationByIdRequest;
import ro.ubbcluj.econ.chargingstationlocator.locator.entity.GetStationResponseBE;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor_ = { @Autowired})
public class LocatorService {

    private final QueryDatabase queryDatabase;
    private final ChargingStationDataTransformer transformer;

    @Transactional
    public GetStationResponseBE getStationByIdResponse(GetStationByIdRequest getStationByIdRequest) {
        StringBuilder sb = new StringBuilder();
        getStationByIdRequest.getIds().forEach(id -> {
            sb.append(id);
            sb.append(",");
        });
        sb.deleteCharAt(sb.length() - 1);
        final List<ChargingStationData> stations = queryDatabase.findStationById(sb.toString());
        return transformer.transform(stations);
    }

    @Transactional
    public GetStationResponseBE getStationByGeolocation(GetStationsByGeolocationRequest getStationsByGeolocationRequest){
        float latitude = getStationsByGeolocationRequest.getLatitude();
        float longitude = getStationsByGeolocationRequest.getLongitude();
        int distance = getStationsByGeolocationRequest.getDistance();

        final List<ChargingStationData> stations = queryDatabase.findStationByGeolocation(latitude, longitude, distance);
        return transformer.transform(stations);

    }

}
