package com.drone.management.repositories;

import com.drone.management.models.Drone;
import com.drone.management.projection.DroneProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;


@Repository
public interface DroneRepository extends JpaRepository<Drone, Long> {

    DroneProjection getDroneById(Long id);

    List<DroneProjection> findAllBy();

    List<DroneProjection> findAllByRemainWeightGreaterThan(BigDecimal value);


}
