package com.drone.management.service;

import com.drone.management.models.Drone;
import com.drone.management.models.Medication;
import com.drone.management.projection.DroneProjection;
import com.drone.management.requests.DroneRequest;

import java.math.BigDecimal;
import java.util.List;

public interface DroneService {
    public DroneProjection createDrone(DroneRequest request) throws Exception;

    public DroneProjection updateDrone(DroneRequest request, Long id) throws Exception;

    public List<DroneProjection> getAllDrones();

    public DroneProjection getDrone(Long id) throws Exception;

    public BigDecimal getBatteryLevel(Long id) throws Exception;

    public DroneProjection deleteDrone(Long id) throws Exception;

    public Drone getDroneOfMedication(Long id) throws Exception;

    public Drone loadMedication(Drone drone, Medication medication) throws Exception;

    public Drone unloadMedication(Drone drone, Medication medication) throws Exception;

    public List<DroneProjection> getAvailableDrones();

}
