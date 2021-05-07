package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.service.ReportingStructureService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportingStructureServiceImplTest {

    private String employeeUrl;
    private String reportingStructureUrl;

    private String johnLennonEmployeeId;

    @Autowired
    private ReportingStructureService reportingStructureService;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        employeeUrl = "http://localhost:" + port + "/employee";
        reportingStructureUrl = "http://localhost:" + port + "/reportingStructure/{id}";

        johnLennonEmployeeId = "16a596ae-edd3-4847-99fe-c4518e82c86f";
    }

    @Test
    public void testRead() {
        Employee testEmployee = new Employee();
        testEmployee.setFirstName("John");
        testEmployee.setLastName("Doe");
        testEmployee.setDepartment("Engineering");
        testEmployee.setPosition("Developer");

        // Create dummy employee
        Employee createdEmployee =
            restTemplate.postForEntity(
                    employeeUrl,
                    testEmployee,
                    Employee.class).getBody();


        // Read check (dummy employee)
        ReportingStructure dummyReportingStructure =
            restTemplate.getForEntity(
                    reportingStructureUrl,
                    ReportingStructure.class,
                    createdEmployee.getEmployeeId()).getBody();
        assertEquals(dummyReportingStructure.getNumberOfReports(), 0);

        // Read check (known employee)
        // NOTE: in the starter code given, the number of reports to this
        // employee is 2 even though it is listed as 4 in the README
        ReportingStructure johnLennonReportingStructure =
            restTemplate.getForEntity(
                    reportingStructureUrl,
                    ReportingStructure.class,
                    johnLennonEmployeeId).getBody();
        assertEquals(johnLennonReportingStructure.getNumberOfReports(), 2);
    }
}
