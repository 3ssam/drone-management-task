package com.drone.management.service;

import com.drone.management.projection.MedicationProjection;
import com.drone.management.requests.MedicationRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MedicationService {
    public MedicationProjection createMedication(String jsonObject, MultipartFile image) throws Exception;

    public MedicationProjection updateMedication(MedicationRequest request, Long id) throws Exception;

    public List<MedicationProjection> getAllMedications();

    public MedicationProjection getMedication(Long id) throws Exception;

    public MedicationProjection deleteMedication(Long id) throws Exception;

    public String getImageOfMedication(Long id) throws Exception;

}
