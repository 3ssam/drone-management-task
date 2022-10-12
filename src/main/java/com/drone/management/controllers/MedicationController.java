package com.drone.management.controllers;


import com.drone.management.requests.MedicationRequest;
import com.drone.management.response.ApiResponse;
import com.drone.management.service.MedicationService;
import io.swagger.annotations.Api;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@Api(tags = "Medication API", description = "this API for Medication CRUD operations")
@RequestMapping("medication")
public class MedicationController {

    private static Logger logger = LogManager.getLogger(MedicationController.class);

    @Autowired
    private MedicationService medicationService;

    @PostMapping
    public ApiResponse createMedication(@Validated @RequestParam("jsonBody") String jsonBody, @RequestParam("image") MultipartFile image) throws Exception {
        logger.info("createMedication received request");
        return ApiResponse.created(medicationService.createMedication(jsonBody, image));
    }

    @PutMapping("{medicationId}")
    public ApiResponse updateMedication(@PathVariable Long medicationId, @Validated @RequestBody MedicationRequest request) throws Exception {
        logger.info("updateMedication received request");
        return ApiResponse.updated(medicationService.updateMedication(request, medicationId));
    }

    @DeleteMapping("{medicationId}")
    public ApiResponse deleteMedication(@PathVariable Long medicationId) throws Exception {
        logger.info("deleteMedication received request");
        return ApiResponse.deleted(medicationService.deleteMedication(medicationId));
    }

    @GetMapping({"{medicationId}"})
    public ApiResponse getMedication(@PathVariable Long medicationId) throws Exception {
        logger.info("getMedication received request");
        return ApiResponse.ok(medicationService.getMedication(medicationId));
    }

    @GetMapping()
    public ApiResponse getAllMedications() {
        logger.info("getAllMedications received request");
        return ApiResponse.ok(medicationService.getAllMedications());
    }

    @GetMapping("image/{medicationId}")
    public ApiResponse getImageOfMedication(@PathVariable Long medicationId) throws Exception {
        logger.info("getImageOfMedication received request");
        return ApiResponse.ok(medicationService.getImageOfMedication(medicationId));
    }

}
