package ro.ubbcluj.econ.chargingstationlocator.locator.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DetailedStationBE {

    @JsonProperty("dataSource")
    private String dataSource;

    @JsonProperty("dataSourceId")
    private int dataSourceId;

    @JsonProperty("stationId")
    private String stationId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("location")
    private LocationBE location;

    @JsonProperty("address")
    private AddressBE address;

    @JsonProperty("operatingSchedule")
    private OperatingScheduleBE operatingSchedule;

    @JsonProperty("detailedInformation")
    private DetailedInformationBE detailedInformation;

    @JsonProperty("availability")
    private AvailabilityBE availability;

    @JsonProperty("contact")
    private ContactBE contact;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("distance")
    private double distance;

    @JsonProperty("plugs")
    private List<PlugsBE> plugs;
}
