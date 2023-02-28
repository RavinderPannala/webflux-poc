package com.example.nisum.WebfluxMongodb.controller;

import com.example.nisum.WebfluxMongodb.entity.Employee;
import com.example.nisum.WebfluxMongodb.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping("/save")
    public Mono<ResponseEntity<Employee>> save(@RequestBody Employee employee) {
        return employeeRepository.save(employee).map(e -> new ResponseEntity<>(e, HttpStatus.CREATED));
    }

    @GetMapping("/getAll")
    public Flux<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @PutMapping("/update/{id}")
    public Mono<ResponseEntity<Employee>> update(@PathVariable String id, @RequestBody Employee employee) {
        return employeeRepository.findById(id).flatMap(existingEmp -> {
            existingEmp.setAddress(employee.getAddress());
            existingEmp.setAge(employee.getAge());
            return employeeRepository.save(existingEmp);
        }).map(e -> ResponseEntity.ok(e)).defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Employee>> getEmployeeById(@PathVariable String id) {
        return employeeRepository.findById(id).map(e -> ResponseEntity.ok(e)).defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        employeeRepository.findById(id).
                flatMap(e -> employeeRepository.delete(e)
                        .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK))))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/getStream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Employee> streamOfEmployees() {
        return employeeRepository.findAll().flatMap(e -> {
            Flux<Long> interval = Flux.interval(Duration.ofMillis(3));
            Flux<Employee> employeeFlux = Flux.fromStream(
                    Stream.generate(() -> {
                        return e;
                    }));
            return Flux.zip(interval, employeeFlux).map(Tuple2::getT2);
        });
    }

    @GetMapping("/getByDept/{department}")
    public Flux<Employee> findAllByDept(@PathVariable String department) {
        return employeeRepository.findAllByDepartment(department);
    }

}
