package com.springBoot.EmployeeLeaveManagement.repositories;

import com.springBoot.EmployeeLeaveManagement.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {


}
