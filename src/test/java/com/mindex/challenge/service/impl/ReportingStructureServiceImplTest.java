package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;


import java.util.ArrayList;
import java.util.List;

/**
 * Test the ReportingStructureService
 *
 * @author Brett Lubberts
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportingStructureServiceImplTest {

    private String reportingStructureUrl;
    private String employeeUrl;

    @Autowired
    private EmployeeRepository employeeRepository;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        employeeUrl = "http://localhost:" + port + "/employee";
        reportingStructureUrl = "http://localhost:" + port + "/reportingStructure/{id}";
    }

    @Test
    public void testRead() {
        Employee indirectReporter = new Employee();
        indirectReporter.setFirstName("James");
        indirectReporter.setLastName("Doe");
        indirectReporter.setDepartment("Engineering");
        indirectReporter.setPosition("Developer");
        Employee createdIndirectReport = restTemplate.postForEntity(employeeUrl, indirectReporter, Employee.class).getBody();

        Employee directReporter = new Employee();
        directReporter.setFirstName("Jane");
        directReporter.setLastName("Doe");
        directReporter.setDepartment("Engineering");
        directReporter.setPosition("Developer");
        //set the indirect reporter as the direct reporter's direct report
        indirectReporter = new Employee();
        indirectReporter.setEmployeeId(createdIndirectReport.getEmployeeId());
        List<Employee> indirectReports = new ArrayList<>();
        indirectReports.add(indirectReporter);
        directReporter.setDirectReports(indirectReports);

        Employee createdDirectReport = restTemplate.postForEntity(employeeUrl, directReporter, Employee.class).getBody();

        Employee testEmployee = new Employee();
        testEmployee.setFirstName("John");
        testEmployee.setLastName("Doe");
        testEmployee.setDepartment("Engineering");
        testEmployee.setPosition("Developer");

        //set the direct reporter as the test employee's direct report
        directReporter = new Employee();
        directReporter.setEmployeeId(createdDirectReport.getEmployeeId());
        List<Employee> directReports = new ArrayList<>();
        directReports.add(directReporter);
        testEmployee.setDirectReports(directReports);

        Employee createdEmployee = restTemplate.postForEntity(employeeUrl, testEmployee, Employee.class).getBody();

        ReportingStructure testReportingStructure = new ReportingStructure(employeeRepository);
        testReportingStructure.setEmployee(createdEmployee);
        testReportingStructure.setNumberOfReports();

        //Read check
        ReportingStructure readReportingStructure = restTemplate.getForEntity(reportingStructureUrl, ReportingStructure.class, createdEmployee.getEmployeeId()).getBody();
        assertReportingStructureEquivalence(testReportingStructure, readReportingStructure);

    }

    private static void assertReportingStructureEquivalence(ReportingStructure expected, ReportingStructure actual) {
        Employee expectedEmployee = expected.getEmployee();
        Employee actualEmployee = actual.getEmployee();
        assertEquals(expectedEmployee.getFirstName(), actualEmployee.getFirstName());
        assertEquals(expectedEmployee.getLastName(), actualEmployee.getLastName());
        assertEquals(expectedEmployee.getDepartment(), actualEmployee.getDepartment());
        assertEquals(expectedEmployee.getPosition(), actualEmployee.getPosition());

        assertEquals(expected.getNumberOfReports(), actual.getNumberOfReports());
    }
}
