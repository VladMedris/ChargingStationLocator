package ro.ubbcluj.econ.chargingstationlocator.locator.entity;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class GetStationsByGeolocationRequest {

    @Parameter(name = "latitude", in = ParameterIn.QUERY, required = true)
    @NotNull
    @Range(min = -90, max = 90, message = "Latitude should be between -90 and 90")
    private float latitude;

    @Parameter(name = "longitude", in = ParameterIn.QUERY, required = true)
    @NotNull
    @Range(min = -180, max = 180, message = "Distance should be between -180 and 180")
    private float longitude;

    @Parameter(name = "distance", in = ParameterIn.QUERY, required = true)
    @NotNull
    @Range(min = 1, max = 1000, message = "Distance should be between 1 and 1000")
    private int distance;
}
