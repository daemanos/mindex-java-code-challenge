package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import java.util.List;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportingStructureServiceImpl implements ReportingStructureService {

    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public ReportingStructure read(String id) {
        LOG.debug("Creating employee with id [{}]", id);

        Employee employee = employeeRepository.findByEmployeeId(id);

        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        LOG.debug("Creating reporting structure for employee");

        ReportingStructure reportingStructure = new ReportingStructure();
        reportingStructure.setEmployee(employee);

        Set<String> processed = new TreeSet<String>();
        Queue<String> toProcess = new LinkedList<String>();

        // add direct reports
        List<Employee> reports = employee.getDirectReports();
        if (reports != null) {
            for (Employee report : reports) {
                toProcess.add(report.getEmployeeId());
            }
        }

        // BFS for additional reports
        String nextId;
        Employee nextEmployee;
        List<Employee> nextReports;
        while ((nextId = toProcess.poll()) != null) {
            nextEmployee = employeeRepository.findByEmployeeId(nextId);
            if (nextEmployee == null) continue;
            LOG.debug("Processing: " + nextEmployee.getEmployeeId());

            processed.add(nextEmployee.getEmployeeId());

            nextReports = nextEmployee.getDirectReports();
            if (nextReports == null) continue;

            for (Employee report : nextReports) {
                if (!processed.contains(report.getEmployeeId())) {
                    toProcess.add(report.getEmployeeId());
                }
            }
        }

        reportingStructure.setNumberOfReports(processed.size());

        return reportingStructure;
    }
}
