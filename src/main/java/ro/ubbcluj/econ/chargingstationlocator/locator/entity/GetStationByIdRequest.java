package ro.ubbcluj.econ.chargingstationlocator.locator.entity;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class GetStationByIdRequest {

    @Parameter(name = "ids", in = ParameterIn.QUERY, required = true)
    @NotNull
    @Size(min = 1, max = 1000)
    private List<String> ids;
}
