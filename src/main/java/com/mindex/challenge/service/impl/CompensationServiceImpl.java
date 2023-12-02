package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * The service implementation for the compensation
 *
 * @author Brett Lubberts
 */
@Service
public class CompensationServiceImpl implements CompensationService {

    private static final Logger LOG = LoggerFactory.getLogger(CompensationServiceImpl.class);

    @Autowired
    private CompensationRepository compensationRepository;

    /**
     * Creates a compensation
     * @param compensation the compensation
     * @return the compensation
     */
    @Override
    public Compensation create(Compensation compensation) {
        LOG.debug("Creating compensation for employee [{}]", compensation.getEmployee().getEmployeeId());

        compensationRepository.insert(compensation);

        return compensation;
    }

    /**
     * Reads a compensation
     * @param employeeId the employee id
     * @return the compensation
     */
    @Override
    public Compensation read(String employeeId) {
        LOG.debug("Reading compensation for employee with id [{}]", employeeId);

        Compensation compensation = compensationRepository.findByEmployee_EmployeeId(employeeId);

        if (compensation == null) {
            throw new RuntimeException("Invalid employeeId: " + employeeId);
        }

        return compensation;
    }


}
