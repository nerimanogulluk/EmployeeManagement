package com.springBoot.EmployeeLeaveManagement.repositories;

import com.springBoot.EmployeeLeaveManagement.entities.AnnualLeave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnualLeaveRepository extends JpaRepository<AnnualLeave, Long> {
}
