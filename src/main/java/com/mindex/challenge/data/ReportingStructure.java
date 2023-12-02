package com.mindex.challenge.data;

import com.mindex.challenge.dao.EmployeeRepository;
import org.springframework.data.annotation.Transient;

import java.util.LinkedList;
import java.util.Queue;

/**
 * The ReportingStructure class is a data class that contains an Employee object and
 * the number of reports that the employee has.
 *
 * @author Brett Lubberts
 */
public class ReportingStructure {
    @Transient
    private Employee employee;
    @Transient
    private int numberOfReports;
    EmployeeRepository employeeRepository;

    public ReportingStructure(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public ReportingStructure() {
    }

    @Transient
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Transient
    public int getNumberOfReports() {
        return this.numberOfReports;
    }

    public void setNumberOfReports() {
        this.numberOfReports = calculateNumberOfReports(this.employee);
    }

    /**
     * Calculates the number of reports that an employee has.
     *
     * @param employee the employee to calculate the number of reports for
     * @return the number of reports that the employee has
     */
    private int calculateNumberOfReports(Employee employee) {
        int numberOfReports = 0;

        if (employee == null) {
            return 0;
        }

        if (employee.getDirectReports() == null) {
            return 0;
        }

        // implement with BFS
        Queue<Employee> queue = new LinkedList<>();
        for (Employee directReport : employee.getDirectReports()) {
            // use the employeeRepository to get the employee object from the database
            // so that the employee object has the correct directReports
            Employee temp = employeeRepository.findByEmployeeId(directReport.getEmployeeId());
            queue.add(temp);
        }

        while (!queue.isEmpty()) {
            Employee currentEmployee = queue.remove();
            numberOfReports++;
            if (currentEmployee.getDirectReports() != null) {
                for (Employee directReport : currentEmployee.getDirectReports()) {
                    Employee temp = employeeRepository.findByEmployeeId(directReport.getEmployeeId());
                    queue.add(temp);
                }
            }
        }

        return numberOfReports;
    }
}
