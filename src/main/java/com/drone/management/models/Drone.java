package com.drone.management.models;

import com.drone.management.models.enums.DroneModel;
import com.drone.management.models.enums.DroneState;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@Entity
public class Drone extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 3042126055658047285L;

    @Column(nullable = false)
    private String serialNumber;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private DroneModel model;
    @Column(nullable = false)
    private BigDecimal weightLimit;
    @Column(precision = 5, scale = 2, nullable = false)
    private BigDecimal batteryCapacity;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private DroneState state;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "drone")
    private Set<Medication> medications;
    @JsonIgnore
    @Column(nullable = false)
    private BigDecimal remainWeight;
}
