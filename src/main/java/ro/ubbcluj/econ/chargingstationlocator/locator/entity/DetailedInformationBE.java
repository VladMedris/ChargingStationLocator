package ro.ubbcluj.econ.chargingstationlocator.locator.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DetailedInformationBE {

    @JsonProperty("operator")
    private String operator;

    @JsonProperty("access")
    private String access;

    @JsonProperty("service")
    private String service;
}
