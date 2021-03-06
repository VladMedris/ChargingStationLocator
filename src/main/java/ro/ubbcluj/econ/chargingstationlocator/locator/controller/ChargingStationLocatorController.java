package ro.ubbcluj.econ.chargingstationlocator.locator.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ro.ubbcluj.econ.chargingstationlocator.locator.entity.*;
import ro.ubbcluj.econ.chargingstationlocator.locator.service.LocatorService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor(onConstructor_ = {@Autowired})

public class ChargingStationLocatorController {

    private final LocatorService locatorService;

    @GetMapping("charging-station-locator/clients/v1/stationById")
    @Valid
    public GetStationResponseBE getStationById(final GetStationByIdRequest getStationByIdRequest,
                                               @RequestHeader(value = "business-case") String businessCase){
        return locatorService.getStationByIdResponse(getStationByIdRequest, businessCase, getStationByIdRequest.getLanguage());
    }

    @GetMapping("charging-station-locator/clients/v1/stationsByGeolocation")
    @Valid
    public GetStationResponseBE getStationByGeolocationRequest(final GetStationsByGeolocationRequest getStationsByGeolocationRequest,
                                                               @RequestHeader(value = "business-case") String businessCase){
        return locatorService.getStationByGeolocation(getStationsByGeolocationRequest, businessCase,
                getStationsByGeolocationRequest.getLatitude(), getStationsByGeolocationRequest.getLongitude(), getStationsByGeolocationRequest.getLanguage());
    }

    @DeleteMapping("charging-station-locator/admin/v1/station")
    @Valid
    void deleteStationById(final DeleteStationByIdRequest deleteStationByIdRequest){
       locatorService.deleteStationById(deleteStationByIdRequest);
    }

    @PutMapping(value = "charging-station-locator/admin/v1/updateStationById")
    @Valid
    public GetStationResponseBE updateStationByIdRequest(final UpdateStationByIdRequest updateStationByIdRequest, @RequestBody final DetailedStationBE chargingStationData){
        return locatorService.updateStationByIdRequest(updateStationByIdRequest,chargingStationData);
    }

}
