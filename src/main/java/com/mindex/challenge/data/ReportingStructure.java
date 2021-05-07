package com.mindex.challenge.data;

import java.util.list;

public class ReportingStructure {
    private Employee employee;
    private int numberOfReports;

    public ReportingStructure() {
    }

    public Employee getEmployee() {
        return employee;
    }

    public Employee setEmployee() {
        this.employee = employee;
        this.numberOfReports = calculateNumberOfReports(employee);
    }

    public int getNumberOfReports() {
        return numberOfReports;
    }

    /*
     * Calculate the total number of reports to the given employee.
     *
     * @param employee  the root employee
     * @return          the total number of employees directly or indirectly
     *                  reporting to the root employee
     */
    private int calculateNumberOfReports(Employee employee) {
        int num = 0;
        for (Employee directReport : employee.getDirectReports()) {
            num += 1;
            num += calculateNumberOfReports(directReport);
        }

        return num;
    }
}
