package com.drone.management.controllers;


import com.drone.management.response.ApiResponse;
import com.drone.management.service.EventAuditService;
import io.swagger.annotations.Api;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Api(tags = "Audit API", description = "this API for Audit batteries of drones")
@RequestMapping("audit")
public class AuditController {

    private static Logger logger = LogManager.getLogger(AuditController.class);
    @Autowired
    private EventAuditService eventAuditService;

    @GetMapping()
    public ApiResponse getAllAudits() {
        logger.info("getAllAudits received request");
        return ApiResponse.ok(eventAuditService.getAllAudits());
    }

}
