package com.drone.management.schedulingtasks;

import com.drone.management.models.EventAudit;
import com.drone.management.projection.DroneProjection;
import com.drone.management.repositories.EventAuditRepository;
import com.drone.management.service.DroneService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
public class BatteryAudits {

    private static Logger logger = LogManager.getLogger(BatteryAudits.class);
    @Autowired
    private DroneService droneService;
    @Autowired
    private EventAuditRepository eventAuditRepository;

    @Scheduled(cron = "${audit.battery.scheduler}")
    @Transactional
    public void auditBatteryCapacity() {
        logger.info("audit Battery of Drones Started");
        List<DroneProjection> drones = droneService.getAllDrones();
        List<EventAudit> audits = new ArrayList<>();
        for (DroneProjection drone : drones) {
            EventAudit eventAudit = new EventAudit(drone.getBatteryCapacity(), drone.getId());
            audits.add(eventAudit);
        }
        eventAuditRepository.saveAll(audits);
    }


}