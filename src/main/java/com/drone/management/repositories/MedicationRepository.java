package com.drone.management.repositories;

import com.drone.management.models.Medication;
import com.drone.management.projection.MedicationProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MedicationRepository extends JpaRepository<Medication, Long> {

    MedicationProjection getMedicationById(Long id);

    List<MedicationProjection> findAllBy();

}
