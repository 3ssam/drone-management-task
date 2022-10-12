package com.drone.management.service;

import com.drone.management.models.EventAudit;

import java.util.List;

public interface EventAuditService {
    public List<EventAudit> getAllAudits();
}
