package com.mindex.challenge.data;

import java.util.Date;

public class Compensation {
    private Employee employee;
    private String employeeId;
    private int salary;
    private Date effectiveDate;

    public Compensation() {
    }

    public Employee getEmployee() {
        return employee;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
        this.employeeId = employee.getEmployeeId();
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
}
