package com.drone.management.projection;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;


@JsonPropertyOrder({"id", "name", "code", "weight"})
@ApiModel
public interface MedicationProjection {

    @ApiModelProperty(position = 1)
    Long getId();

    @ApiModelProperty(position = 2)
    String getName();

    @ApiModelProperty(position = 3)
    String getCode();

    @ApiModelProperty(position = 4)
    BigDecimal getWeight();
}
