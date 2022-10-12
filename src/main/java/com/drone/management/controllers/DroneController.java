package com.drone.management.controllers;


import com.drone.management.requests.DroneRequest;
import com.drone.management.response.ApiResponse;
import com.drone.management.service.DroneService;
import io.swagger.annotations.Api;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@Api(tags = "Drone API", description = "this API for Drone CRUD operations")
@RequestMapping("drone")
public class DroneController {

    private static Logger logger = LogManager.getLogger(DroneController.class);
    @Autowired
    private DroneService droneService;

    @PostMapping
    public ApiResponse createDrone(@Validated @RequestBody DroneRequest request) throws Exception {
        logger.info("createDrone received request");
        return ApiResponse.created(droneService.createDrone(request));
    }

    @PutMapping("{droneId}")
    public ApiResponse updateDrone(@PathVariable Long droneId, @Validated @RequestBody DroneRequest request) throws Exception {
        logger.info("updateDrone received request");
        return ApiResponse.updated(droneService.updateDrone(request, droneId));
    }

    @DeleteMapping("{droneId}")
    public ApiResponse deleteDrone(@PathVariable Long droneId) throws Exception {
        logger.info("deleteDrone received request");
        return ApiResponse.deleted(droneService.deleteDrone(droneId));
    }

    @GetMapping({"{droneId}"})
    public ApiResponse getDrone(@PathVariable Long droneId) throws Exception {
        logger.info("getDrone received request");
        return ApiResponse.ok(droneService.getDrone(droneId));
    }

    @GetMapping()
    public ApiResponse getAllDrones() {
        logger.info("getAllDrones received request");
        return ApiResponse.ok(droneService.getAllDrones());
    }

    @GetMapping("getAvailableDrones")
    public ApiResponse getAvailableDrones() {
        logger.info("getAvailableDrones received request");
        return ApiResponse.ok(droneService.getAvailableDrones());
    }

    @GetMapping({"getBatteryLevel/{droneId}"})
    public ApiResponse getBatteryLevel(@PathVariable Long droneId) throws Exception {
        logger.info("getBatteryLevel received request");
        return ApiResponse.ok(droneService.getBatteryLevel(droneId));
    }

}
