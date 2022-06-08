package ro.ubbcluj.econ.chargingstationlocator.locator.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlugsBE {

    @JsonProperty("plugType")
    private String plugType;

    @JsonProperty("phase")
    private String phase;

    @JsonProperty("highPowerCharging")
    private boolean highPowerCharging;

    @JsonProperty("power")
    private int power;

    @JsonProperty("current")
    private double current;

    @JsonProperty("voltage")
    private double voltage;
}
