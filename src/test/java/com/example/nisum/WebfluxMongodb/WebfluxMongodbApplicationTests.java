package com.example.nisum.WebfluxMongodb;

import com.example.nisum.WebfluxMongodb.entity.Employee;
import com.example.nisum.WebfluxMongodb.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.Assert;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
class WebfluxMongodbApplicationTests {

    @Autowired
    EmployeeRepository employeeRepository;


    @Test
    public void save() {
        Mono<Employee> save = employeeRepository.save(new Employee("1", "Ravi", "29", "EV", "HYD"));
        StepVerifier.create(save).assertNext(e -> Assert.notNull(e, e.getId())).expectComplete().verify();

    }

    @Test
    public void getAllBy() {
        Employee save = employeeRepository.save(new Employee("2", "Raju", "30", "DEV", "HYD")).block();
        Flux<Employee> findAll = employeeRepository.findAllByDepartment("DEV");
        StepVerifier.create(findAll).assertNext(emp -> {
            Assert.notNull(emp.getName());
            Assert.hasLength(emp.getName(), String.valueOf(4));
        }).expectNextCount(2).verifyComplete();
    }

}
