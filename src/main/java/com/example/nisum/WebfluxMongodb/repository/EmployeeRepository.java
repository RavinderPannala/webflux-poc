package com.example.nisum.WebfluxMongodb.repository;

import com.example.nisum.WebfluxMongodb.entity.Employee;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface EmployeeRepository  extends ReactiveMongoRepository<Employee,String> {

    @Query("{department: ?0}")
    Flux<Employee> findAllByDepartment(String dev);
}
