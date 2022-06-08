package ro.ubbcluj.econ.chargingstationlocator.locator.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressBE {

    @JsonProperty("countryCode")
    private String countryCode;

    @JsonProperty("country")
    private String country;

    @JsonProperty("city")
    private String city;

    @JsonProperty("street")
    private String street;

    @JsonProperty("number")
    private String number;
}
