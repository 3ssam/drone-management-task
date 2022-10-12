package com.drone.management.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;
import java.math.BigDecimal;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class EventAudit extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 3042169755658047285L;

    @Column(precision = 5, scale = 2, nullable = false)
    private BigDecimal batteryCapacity;
    @Column(nullable = false)
    private Long droneId;
}
