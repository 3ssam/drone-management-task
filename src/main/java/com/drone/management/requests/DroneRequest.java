package com.drone.management.requests;

import com.drone.management.models.enums.DroneModel;
import com.drone.management.models.enums.DroneState;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Setter
@Getter
@ApiModel
public class DroneRequest {
    @Length(max = 100, message = "maximum length for serialNumber field is 100 character")
    @NotBlank(message = "serialNumber field must not to be empty")
    @NotNull(message = "serialNumber field must be had a value")
    private String serialNumber;
    @NotNull(message = "model field must be had a value")
    @Pattern(regexp = "LIGHT|MIDDLE|HEAVY|CRUISER", message = "model value should be from LIGHT,MIDDLE,HEAVY,CRUISER")
    private String model;
    @NotNull(message = "weightLimit field must be had a value")
    @DecimalMin(value = "0.0", inclusive = false, message = "weightLimit field must be greater than 0.0")
    private BigDecimal weightLimit;
    @NotNull(message = "batteryCapacity field must be had a value")
    @DecimalMin(value = "0.0", inclusive = false, message = "batteryCapacity field must be greater than 0.0")
    @Digits(integer = 3, fraction = 2)
    private BigDecimal batteryCapacity;
    @NotNull(message = "state field must be had a value")
    @Pattern(regexp = "IDLE|LOADING|LOADED|DELIVERING|DELIVERED|RETURNING", message = "state should be value from IDLE,LOADING,LOADED,DELIVERING,DELIVERED,RETURNING")
    private String state;

    public DroneModel getModel() {
        return DroneModel.valueOf(model);
    }

    public DroneState getState() {
        return DroneState.valueOf(state);
    }
}
