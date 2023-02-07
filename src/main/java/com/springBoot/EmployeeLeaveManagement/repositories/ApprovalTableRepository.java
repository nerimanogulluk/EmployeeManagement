package com.springBoot.EmployeeLeaveManagement.repositories;

import com.springBoot.EmployeeLeaveManagement.entities.ApprovalTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApprovalTableRepository extends JpaRepository<ApprovalTable, Long> {
}
