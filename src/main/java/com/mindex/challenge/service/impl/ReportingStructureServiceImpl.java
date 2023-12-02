package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.mindex.challenge.service.ReportingStructureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The service implementation for the reporting structure
 *
 * @author Brett Lubberts
 */
@Service
public class ReportingStructureServiceImpl implements ReportingStructureService {

    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * Reads the reporting structure for an employee
     * @param id the employee id
     * @return the reporting structure
     */
    @Override
    public ReportingStructure read(String id) {
        LOG.debug("Creating reporting structure with id [{}]", id);


        Employee employee = employeeRepository.findByEmployeeId(id);
        ReportingStructure reportingStructure = new ReportingStructure(employeeRepository);
        reportingStructure.setEmployee(employee);
        reportingStructure.setNumberOfReports();



        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        return reportingStructure;
    }
}
