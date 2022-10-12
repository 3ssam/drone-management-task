package com.drone.management.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Setter
@Getter
@Entity
public class Medication extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 3042161255658047285L;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String code;
    @Column(nullable = false)
    private BigDecimal weight;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "drone_id", referencedColumnName = "id")
    private Drone drone;
    @JsonIgnore
    @Column(name = "imageName", length = 3000)
    private String imageName;
    @JsonIgnore
    @Column(name = "imageType", length = 3000)
    private String imagetype;
    @JsonIgnore
    @Lob
    @Column(name = "imageByte", length = 3000)
    private byte[] imageByte;
    @JsonIgnore
    @Lob
    private String imageSource;


}
