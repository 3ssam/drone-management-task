package com.drone.management.projection;

import com.drone.management.models.Medication;
import com.drone.management.models.enums.DroneModel;
import com.drone.management.models.enums.DroneState;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;


@JsonPropertyOrder({"id", "serialNumber", "model", "weightLimit", "remainWeight", "batteryCapacity", "state", "medications"})
@ApiModel
public interface DroneProjection {

    @ApiModelProperty(position = 1)
    Long getId();

    @ApiModelProperty(position = 2)
    String getSerialNumber();

    @ApiModelProperty(position = 3)
    DroneModel getModel();

    @ApiModelProperty(position = 4)
    BigDecimal getWeightLimit();

    @ApiModelProperty(position = 5)
    BigDecimal getRemainWeight();

    @ApiModelProperty(position = 6)
    BigDecimal getBatteryCapacity();

    @ApiModelProperty(position = 7)
    DroneState getState();

    @ApiModelProperty(position = 8)
    List<Medication> getMedications();
}
