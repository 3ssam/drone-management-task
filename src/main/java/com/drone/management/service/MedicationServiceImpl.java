package com.drone.management.service;

import com.drone.management.exceptions.FileStorageException;
import com.drone.management.models.Drone;
import com.drone.management.models.Medication;
import com.drone.management.projection.MedicationProjection;
import com.drone.management.repositories.MedicationRepository;
import com.drone.management.requests.MedicationRequest;
import com.drone.management.util.ImageUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Service
public class MedicationServiceImpl implements MedicationService {
    private static Logger logger = LogManager.getLogger(MedicationServiceImpl.class);

    @Autowired
    private MedicationRepository medicationRepository;
    @Autowired
    private ValidationService validationService;
    @Autowired
    private DroneService droneService;

    @Override
    @Transactional
    public MedicationProjection createMedication(String jsonObject, MultipartFile image) throws Exception {
        MedicationRequest request = validationService.validateRequest(jsonObject);
        Drone drone = droneService.getDroneOfMedication(request.getDroneId());
        Medication medication = new Medication();
        medication.setCode(request.getCode());
        medication.setName(request.getName());
        medication.setWeight(request.getWeight());
        if (validationService.isAvailableToLoad(drone, medication)) {
            medication.setDrone(drone);
        } else {
            throw new Exception("this weight is bigger than remain weight of drone");
        }
        medication = saveImage(medication, image);
        medicationRepository.save(medication);
        droneService.loadMedication(drone, medication);
        return medicationRepository.getMedicationById(medication.getId());
    }

    @Override
    @Transactional
    public MedicationProjection updateMedication(MedicationRequest request, Long id) throws Exception {
        Medication medication = medicationRepository.findById(id).get();
        if (medication == null) {
            throw new Exception("Medication not found and this Id not exist");
        }
        medication.setCode(request.getCode());
        medication.setName(request.getName());
        medication.setWeight(request.getWeight());
        if (request.getDroneId() != medication.getDrone().getId()) {
            Drone drone = droneService.getDroneOfMedication(request.getDroneId());
            if (validationService.isAvailableToLoad(drone, medication)) {
                droneService.unloadMedication(medication.getDrone(), medication);
                medication.setDrone(drone);
                medicationRepository.save(medication);
                droneService.loadMedication(drone, medication);
            } else {
                throw new Exception("this weight is bigger than remain weight of drone");
            }
        }
        medicationRepository.save(medication);
        return medicationRepository.getMedicationById(medication.getId());
    }

    @Override
    public List<MedicationProjection> getAllMedications() {
        return medicationRepository.findAllBy();
    }

    @Override
    public MedicationProjection getMedication(Long id) throws Exception {
        MedicationProjection medicationProjection = medicationRepository.getMedicationById(id);
        if (medicationProjection == null) {
            throw new Exception("Medication not found and this Id not exist");
        }
        return medicationProjection;
    }

    @Override
    @Transactional
    public MedicationProjection deleteMedication(Long id) throws Exception {
        MedicationProjection medicationProjection = medicationRepository.getMedicationById(id);
        if (medicationProjection == null) {
            throw new Exception("Medication not found and this Id not exist");
        }
        medicationRepository.deleteById(medicationProjection.getId());
        return medicationProjection;
    }

    @Override
    public String getImageOfMedication(Long id) throws Exception {
        Medication medication = medicationRepository.findById(id).get();
        if (medication == null) {
            throw new Exception("Medication not found and this Id not exist");
        }
        return medication.getImageSource();
    }

    private Medication saveImage(Medication medication, MultipartFile image) {
        if (!image.getOriginalFilename().isEmpty()) {
            String fileName = StringUtils.cleanPath(image.getOriginalFilename());
            try {
                if (fileName.contains("..")) {
                    throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
                }
                medication.setImageName(fileName);
                medication.setImagetype(image.getContentType());
                medication.setImageByte(ImageUtil.compressBytes(image.getBytes()));
                medication.setImageSource(ImageUtil.convertToImage(medication.getImageByte()));
            } catch (IOException ex) {
                throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
            }
        }
        return medication;
    }
}
