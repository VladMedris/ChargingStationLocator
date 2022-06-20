package ro.ubbcluj.econ.chargingstationlocator.locator.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubbcluj.econ.chargingstationlocator.locator.entity.*;

@Service
@RequiredArgsConstructor(onConstructor_ = { @Autowired})
public class ChargingReverseTransformer {

    public ChargingStationData createStation(final ChargingStationData data, final DetailedStationBE detailedStationBE){
        return ChargingStationData.builder()
                .id(data.getId())
                .stationId(String.valueOf(detailedStationBE.getStationId()))
                .name(String.valueOf(detailedStationBE.getName()))
                .latitude(detailedStationBE.getLocation().getLat())
                .longitude(detailedStationBE.getLocation().getLon())
                .street(detailedStationBE.getAddress().getStreet())
                .city(detailedStationBE.getAddress().getCity())
                .country(detailedStationBE.getAddress().getCountry())
                .number(detailedStationBE.getAddress().getNumber())
                .countryCode(detailedStationBE.getAddress().getCountryCode())
                .hours(data.getHours())
                .contact(data.getContact())
                .plugData(data.getPlugData())
                .accessData(data.getAccessData())
                .availabilityData(data.getAvailabilityData())
                .build();
    }
}
