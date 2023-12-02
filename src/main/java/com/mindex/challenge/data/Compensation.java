package com.mindex.challenge.data;

/**
 * The Compensation class that contains data about employee compensation
 *
 * @author Brett Lubberts
 */
public class Compensation {
    private Employee employee;
    private int salary;
    private String effectiveDate;

    public Compensation() {
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
}
