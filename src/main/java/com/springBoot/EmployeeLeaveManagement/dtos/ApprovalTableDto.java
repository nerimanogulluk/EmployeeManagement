package com.springBoot.EmployeeLeaveManagement.dtos;

import com.fasterxml.jackson.databind.JsonNode;
import com.springBoot.EmployeeLeaveManagement.enums.ApprovalStatusEnum;
import com.springBoot.EmployeeLeaveManagement.enums.ApprovalEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ApprovalTableDto implements Serializable {
    private ApprovalEnum topic;
    private JsonNode dtoBinder;
    private ApprovalStatusEnum status;
    private String note;
}
