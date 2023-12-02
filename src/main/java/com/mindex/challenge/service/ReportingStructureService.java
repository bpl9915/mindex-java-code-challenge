package com.mindex.challenge.service;

import com.mindex.challenge.data.ReportingStructure;

/**
 * The service for the reporting structure, implemented by ReportingStructureServiceImpl.java
 *
 * @author Brett Lubberts
 */
public interface ReportingStructureService {

    /**
     * Reads the reporting structure for an employee
     * @param employeeId the employee id
     * @return the reporting structure
     */
    ReportingStructure read(String employeeId);
}
