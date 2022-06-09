package ro.ubbcluj.econ.chargingstationlocator.locator.entity;

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

    @JsonProperty("distance")
    private float distance;

    @JsonProperty("plugs")
    private List<PlugsBE> plugs;
}
