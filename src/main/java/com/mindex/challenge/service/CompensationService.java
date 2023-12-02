package com.mindex.challenge.service;

import com.mindex.challenge.data.Compensation;

/**
 * The service for the compensation, implemented by CompensationServiceImpl.java
 *
 * @author Brett Lubberts
 */
public interface CompensationService {

    /**
     * Creates a compensation
     * @param compensation the compensation
     * @return the compensation
     */
    Compensation create(Compensation compensation);

    /**
     * Reads a compensation
     * @param employeeId the employee id
     * @return the compensation
     */
    Compensation read(String employeeId);
}
