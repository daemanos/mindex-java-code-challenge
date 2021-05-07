package com.mindex.challenge.data;

import java.util.List;

public class ReportingStructure {
    private Employee employee;
    private int numberOfReports;

    public ReportingStructure() {
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
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

        List<Employee> directReports = employee.getDirectReports();
        if (directReports != null) {
            for (Employee directReport : directReports) {
                num += 1;
                num += calculateNumberOfReports(directReport);
            }
        }

        return num;
    }
}
