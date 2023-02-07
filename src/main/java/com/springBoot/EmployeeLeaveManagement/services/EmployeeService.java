package com.springBoot.EmployeeLeaveManagement.services;

import com.springBoot.EmployeeLeaveManagement.dtos.EmployeeDto;
import com.springBoot.EmployeeLeaveManagement.entities.Employee;
import com.springBoot.EmployeeLeaveManagement.util.Utils;
import com.springBoot.EmployeeLeaveManagement.repositories.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service @Slf4j
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class EmployeeService {

    private final EmployeeRepository repository;
    private String time = LocalDateTime.now().toString();

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public void printCurrentTime() {
        log.info("Current time is: " + time);
    }

    public List<EmployeeDto> toDtoList(List<Employee> employeeList) {
        return employeeList.stream().map(e -> Utils.MODEL_MAPPER.map(e, EmployeeDto.class)).collect(Collectors.toList());
    }

    public Employee toEntity(EmployeeDto dto) {
        return Utils.MODEL_MAPPER.map(dto, Employee.class);
    }

    public void createNewEmployee(EmployeeDto employeeDto) {
        employeeDto.setStarted(LocalDateTime.now());

        log.info("Current Thread Id: " + Thread.currentThread().getId());

        repository.save(toEntity(employeeDto));
    }


    public List<Employee> getEmployeeList() {
        return repository.findAll();
    }

}
