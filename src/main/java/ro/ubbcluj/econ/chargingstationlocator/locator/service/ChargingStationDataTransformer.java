package ro.ubbcluj.econ.chargingstationlocator.locator.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubbcluj.econ.chargingstationlocator.locator.entity.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor_ = { @Autowired})
public class ChargingStationDataTransformer {

    public GetStationResponseBE transform(final List<ChargingStationData> dataList){
        final List<StationBE> transformedData = Optional.ofNullable(dataList)
                .orElse(Collections.emptyList())
                .stream()
                .map(this::createStation)
                .collect(Collectors.toList());

        return GetStationResponseBE.builder()
                .data(transformedData)
                .build();
    }

    private AddressBE createAddress(final ChargingStationData data){
        return AddressBE.builder()
                .countryCode(String.valueOf(data.getCountryCode()))
                .country(String.valueOf(data.getCountry()))
                .city(String.valueOf(data.getCity()))
                .street(String.valueOf(data.getStreet()))
                .number(String.valueOf(data.getNumber()))
                .build();
    }

    private LocationBE createLocation(final ChargingStationData data){
        return LocationBE.builder()
                .lat(data.getLatitude())
                .lon(data.getLongitude())
                .build();
    }

    private OperatingScheduleBE createOperatingSchedule(final ChargingStationData data){
        return OperatingScheduleBE.builder()
                .hours(String.valueOf(data.getHours()))
                .currentStatus("Open")
                .build();
    }

    private DetailedInformationBE createDetailedInformation(final ChargingStationData data){
        return DetailedInformationBE.builder()
                .operator("operator placeholder")
                .access(String.valueOf(data.getAccess()))
                .service(String.valueOf(data.getService()))
                .chargingPoints(plugsPlaceholder(data))
                .build();
    }

    //replace
    private List<PlugsBE> plugsPlaceholder(final ChargingStationData data){
        List<PlugsBE> testList = new ArrayList<>();
        testList.add(createPlugs(data));
        testList.add(createPlugs(data));
        return testList;
    }

    private PlugsBE createPlugs(ChargingStationData data){
        return PlugsBE.builder()
                .highPowerCharging(data.isHighPower())
                .current(data.getCurrent())
                .power(data.getPower())
                .voltage(data.getVoltage())
                .plugType(String.valueOf(data.getPlugType()))
                .phase(String.valueOf(data.getPhase()))
                .build();
    }

    private AvailabilityBE createAvailability(final ChargingStationData data){
        return AvailabilityBE.builder()
                .status(String.valueOf(data.getStatus()))
                .reservable(data.isReservable())
                .build();
    }

    private ContactBE createContact(final ChargingStationData data){
        return ContactBE.builder()
                .contact(String.valueOf(data.getContact()))
                .phone("phone placeholder")
                .build();
    }

    private StationBE createStation(final ChargingStationData data){
        return StationBE.builder()
                .dataSource("Google")
                .dataSourceId(1)
                .stationId(String.valueOf(data.getStationId()))
                .name(String.valueOf(data.getName()))
                .location(createLocation(data))
                .address(createAddress(data))
                .operatingSchedule(createOperatingSchedule(data))
                .detailedInformation(createDetailedInformation(data))
                .availability(createAvailability(data))
                .contact(createContact(data))
                .build();
    }
}
