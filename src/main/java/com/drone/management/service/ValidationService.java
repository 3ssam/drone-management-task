package com.drone.management.service;

import com.drone.management.models.Drone;
import com.drone.management.models.Medication;
import com.drone.management.requests.MedicationRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Set;

@Service
public class ValidationService {

    @Autowired
    private Validator validator;
    @Autowired
    private ObjectMapper objectMapper;

    public MedicationRequest validateRequest(String requestJson) throws JsonProcessingException {
        MedicationRequest request = objectMapper.readValue(requestJson, MedicationRequest.class);
        Set<ConstraintViolation<MedicationRequest>> constraintViolation = validator.validate(request);
        if (!constraintViolation.isEmpty()) {
            throw new ConstraintViolationException(constraintViolation);
        }
        return request;
    }

    public Boolean isBatteryLow(Drone drone) throws Exception {
        if (drone.getBatteryCapacity().doubleValue() < 25.0) {
            throw new Exception("can't loading this drone because its battery blow 25%");
        }
        return false;
    }

    public Boolean isAvailableToLoad(Drone drone, Medication medication) throws JsonProcessingException {
        return medication.getWeight().doubleValue() <= drone.getRemainWeight().doubleValue();
    }


}
