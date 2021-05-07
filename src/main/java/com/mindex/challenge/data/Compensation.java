package com.mindex.challenge.data;

import java.util.Date;

/**
 * Employee compensation record
 *
 * I assume the system should allow for effective salary dates to be
 * back-dated, so {@link #setEffectiveDate} allows the date to be set directly.
 * Another option would be to have {@link #setSalary} do this automatically.
 *
 * @author Daman Morris
 */
public class Compensation {
    private Employee employee;
    private int salary;
    private Date effectiveDate;

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

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
}
