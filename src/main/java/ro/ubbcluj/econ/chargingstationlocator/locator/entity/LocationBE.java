package ro.ubbcluj.econ.chargingstationlocator.locator.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LocationBE {

    @JsonProperty("lat")
    private float lat;

    @JsonProperty("lon")
    private float lon;
}
