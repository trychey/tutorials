package com.baeldung.reactive.webflux;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.BDDMockito.given;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@WebFluxTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private WebTestClient testClient;

    @MockBean
    private EmployeeRepository employeeRepository;

    @Test
    @WithMockUser
    public void givenEmployeeId_whenGetEmployeeById_thenCorrectEmployee() {

        Employee employee = new Employee("1", "Employee 1 Name");
        given(employeeRepository.findEmployeeById("1")).willReturn(Mono.just(employee));
        testClient.get()
            .uri("/employees/1")
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody(Employee.class)
            .isEqualTo(employee);
    }

    @Test
    @WithMockUser
    public void whenGetAllEmployees_thenCorrectEmployees() {

        List<Employee> employeeList = new ArrayList<>();

        Employee employee1 = new Employee("1", "Employee 1 Name");
        Employee employee2 = new Employee("2", "Employee 2 Name");
        Employee employee3 = new Employee("3", "Employee 3 Name");

        employeeList.add(employee1);
        employeeList.add(employee2);
        employeeList.add(employee3);

        Flux<Employee> employeeFlux = Flux.fromIterable(employeeList);

        given(employeeRepository.findAllEmployees()).willReturn(employeeFlux);
        testClient.get()
            .uri("/employees")
            .exchange()
            .expectStatus()
            .isOk()
            .expectBodyList(Employee.class)
            .hasSize(3)
            .isEqualTo(employeeList);
    }

    @Test
    @WithMockUser
    public void givenEmployeeId_whenGetEmployeeAccessKey_thenCorrectAccessKey() {

        String employeeAccessKey = "Employee Access Key";
        given(employeeRepository.findEmployeeAccessKey("1")).willReturn(Mono.just(employeeAccessKey));
        testClient.get()
            .uri("/employees/access-key/1")
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody(String.class)
            .isEqualTo(employeeAccessKey);
    }
}
