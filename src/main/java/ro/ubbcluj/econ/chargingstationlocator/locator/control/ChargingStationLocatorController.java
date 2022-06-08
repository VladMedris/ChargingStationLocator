package ro.ubbcluj.econ.chargingstationlocator.locator.control;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.ubbcluj.econ.chargingstationlocator.locator.entity.GetStationByIdRequest;
import ro.ubbcluj.econ.chargingstationlocator.locator.entity.GetStationResponseBE;
import ro.ubbcluj.econ.chargingstationlocator.locator.entity.GetStationsByGeolocationRequest;
import ro.ubbcluj.econ.chargingstationlocator.locator.service.LocatorService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor(onConstructor_ = {@Autowired})

public class ChargingStationLocatorController {

    private final LocatorService locatorService;

    @GetMapping("/v1/stationById")
    @Valid
    public GetStationResponseBE getStationById(@Valid final GetStationByIdRequest getStationByIdRequest){
        return locatorService.getStationByIdResponse(getStationByIdRequest);
    }

    @GetMapping("/v1/stationsByGeolocation")
    @Valid
    public GetStationResponseBE getStationByGeolocationRequest(@Valid final GetStationsByGeolocationRequest getStationsByGeolocationRequest){
        return locatorService.getStationByGeolocation(getStationsByGeolocationRequest);
    }
}
