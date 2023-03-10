package com.springBoot.EmployeeLeaveManagement.interfaces;

import com.springBoot.EmployeeLeaveManagement.entities.ApprovalTable;
import com.springBoot.EmployeeLeaveManagement.enums.ApprovalStatusEnum;
import com.springBoot.EmployeeLeaveManagement.exceptions.ExceedingAnnualLeaveThresholdException;
import com.springBoot.EmployeeLeaveManagement.exceptions.AnnualLeaveAlreadyApprovedException;
import com.springBoot.EmployeeLeaveManagement.exceptions.StartDateIsGreaterThanEndDateException;

public interface IApprovalTopicType {

    void updateStatus(ApprovalTable entity, ApprovalStatusEnum status) throws StartDateIsGreaterThanEndDateException, ExceedingAnnualLeaveThresholdException, AnnualLeaveAlreadyApprovedException;

}
