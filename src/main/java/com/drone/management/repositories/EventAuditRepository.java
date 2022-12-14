package com.drone.management.repositories;

import com.drone.management.models.EventAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EventAuditRepository extends JpaRepository<EventAudit, Long> {

}
