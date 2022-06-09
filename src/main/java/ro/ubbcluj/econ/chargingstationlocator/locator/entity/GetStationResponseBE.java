package ro.ubbcluj.econ.chargingstationlocator.locator.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import javax.annotation.Nullable;
import java.util.List;

@Data
@Builder
public class GetStationResponseBE {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("detailed stations")
    private List<DetailedStationBE> data;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("simple stations")
    private List<SimpleStationBE> simpleData;
}
