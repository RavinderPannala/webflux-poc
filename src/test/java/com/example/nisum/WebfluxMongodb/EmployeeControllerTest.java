package com.example.nisum.WebfluxMongodb;

import com.example.nisum.WebfluxMongodb.entity.Employee;
import com.example.nisum.WebfluxMongodb.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.Collections;

//@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    public void save() {

        Employee employee = new Employee("1", "Ravi", "28", "DEV", "12-32");

        webTestClient.post().uri("/api/employee/save")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(employee), Employee.class)
                .exchange().expectStatus().isCreated().expectBody()
                .jsonPath("$.id").isNotEmpty()
                .jsonPath("$.name").isEqualTo("Ravi");
    }

    @Test
    public void getAll() {
        webTestClient.get().uri("/api/employee/getAll")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(Employee.class);
    }

    @Test
    public void getById() {
        Employee e = employeeRepository.save(new Employee("2", "Aran", "24", "QA", "234 234")).block();
        webTestClient.get()
                .uri("/api/employee/{id}", Collections.singletonMap("id", e.getId()))
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(Employee.class);
    }
}
