package com.drone.management.service;

import com.drone.management.models.EventAudit;
import com.drone.management.repositories.EventAuditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventAuditServiceImpl implements EventAuditService {

    @Autowired
    private EventAuditRepository eventAuditRepository;

    @Override
    public List<EventAudit> getAllAudits() {
        return eventAuditRepository.findAll();
    }

}
