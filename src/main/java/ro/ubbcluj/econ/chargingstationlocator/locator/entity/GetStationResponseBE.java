package ro.ubbcluj.econ.chargingstationlocator.locator.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetStationResponseBE {

    @JsonProperty("stations")
    private List<StationBE> data;
}
