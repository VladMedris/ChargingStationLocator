package ro.ubbcluj.econ.chargingstationlocator.locator.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubbcluj.econ.chargingstationlocator.locator.entity.*;

import java.awt.geom.Point2D;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ChargingStationDataTransformer {

    public GetStationResponseBE transformDetailedData(final List<ChargingStationData> dataList){
        final List<DetailedStationBE> transformedData = Optional.ofNullable(dataList)
                .orElse(Collections.emptyList())
                .stream()
                .map(this::createDetailedStation)
                .collect(Collectors.toList());

        return GetStationResponseBE.builder()
                .data(transformedData)
                .build();
    }

    public GetStationResponseBE transformSimpleData(final List<ChargingStationData> dataList){
        final List<SimpleStationBE> transformedData = Optional.ofNullable(dataList)
                .orElse(Collections.emptyList())
                .stream()
                .map(this::createSimpleStation)
                .collect(Collectors.toList());

        return GetStationResponseBE.builder()
                .simpleData(transformedData)
                .build();
    }

    private List<PlugsBE> transformPlugs(final List<PlugData> dataList){
        return Optional.ofNullable(dataList)
                .orElse(Collections.emptyList())
                .stream()
                .map(this::createPlugs)
                .collect(Collectors.toList());
    }

    private PlugsBE createPlugs(final PlugData plugData){
        return PlugsBE.builder()
                .plugType(plugData.getPlugType())
                .phase(plugData.getPhase())
                .highPowerCharging(plugData.isHighPower())
                .voltage(plugData.getElectricInfoData().getVoltage())
                .current(plugData.getElectricInfoData().getCurrent())
                .power(plugData.getElectricInfoData().getPower())
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
                .operator("Charging Station Locator")
                .access(String.valueOf(data.getAccessData().getAccess()))
                .service(String.valueOf(data.getAccessData().getService()))
                .build();
    }

    private AvailabilityBE createAvailability(final ChargingStationData data){
        return AvailabilityBE.builder()
                .status(String.valueOf(data.getAvailabilityData().getStatus()))
                .reservable(data.getAvailabilityData().isReservable())
                .build();
    }

    private ContactBE createContact(final ChargingStationData data){
        return ContactBE.builder()
                .contact(String.valueOf(data.getContact()))
                .phone("+40 746056655")
                .build();
    }

    private DetailedStationBE createDetailedStation(final ChargingStationData data){
        return DetailedStationBE.builder()
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
                .plugs(transformPlugs(new ArrayList<>(data.getPlugData())))
                .build();
    }

    private SimpleStationBE createSimpleStation(final ChargingStationData data){
        return SimpleStationBE.builder()
                .dataSource("Google")
                .dataSourceId(1)
                .stationId(String.valueOf(data.getStationId()))
                .name(String.valueOf(data.getName()))
                .location(createLocation(data))
                .address(createAddress(data))
                .contact(createContact(data))
                .build();
    }
}
