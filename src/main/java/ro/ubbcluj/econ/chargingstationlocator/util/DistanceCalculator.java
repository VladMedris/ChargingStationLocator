package ro.ubbcluj.econ.chargingstationlocator.util;

import org.springframework.stereotype.Component;
import ro.ubbcluj.econ.chargingstationlocator.locator.entity.DetailedStationBE;
import ro.ubbcluj.econ.chargingstationlocator.locator.entity.GetStationResponseBE;
import ro.ubbcluj.econ.chargingstationlocator.locator.entity.SimpleStationBE;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DistanceCalculator {

    public GetStationResponseBE calculateDistanceForSimpleStations(GetStationResponseBE response, double x1, double y1){
        List<SimpleStationBE> processedStations = response.getSimpleData().stream().map(station -> {
            station.setDistance(distance(x1, y1, station.getLocation().getLat(), station.getLocation().getLon()));
            return station;
        }).collect(Collectors.toList());
        response.setSimpleData(processedStations);
        return response;
    }

    public GetStationResponseBE calculateDistanceForDetailedStations(GetStationResponseBE response, double x1, double y1){
        List<DetailedStationBE> processedStations = response.getData().stream().map(station -> {
            station.setDistance(distance(x1, y1, station.getLocation().getLat(), station.getLocation().getLon()));
            return station;
        }).collect(Collectors.toList());
        response.setData(processedStations);
        return response;
    }

    private static double distance(double lat1, double lon1, double lat2, double lon2){

        lon1 = Math.toRadians(lon1);
        lon2 = Math.toRadians(lon2);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        // Haversine formula
        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.pow(Math.sin(dlon / 2),2);

        double c = 2 * Math.asin(Math.sqrt(a));
        double r = 6371;

        return(c * r);
    }
}
