package com.springBoot.EmployeeLeaveManagement.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.springBoot.EmployeeLeaveManagement.component.ApprovalAnnualLeave;
import com.springBoot.EmployeeLeaveManagement.dtos.ApprovalTableDto;
import com.springBoot.EmployeeLeaveManagement.entities.ApprovalTable;
import com.springBoot.EmployeeLeaveManagement.enums.ApprovalStatusEnum;
import com.springBoot.EmployeeLeaveManagement.exceptions.ExceedingAnnualLeaveThresholdException;
import com.springBoot.EmployeeLeaveManagement.interfaces.IApprovalTopicType;
import com.springBoot.EmployeeLeaveManagement.enums.ApprovalEnum;
import com.springBoot.EmployeeLeaveManagement.exceptions.AnnualLeaveAlreadyApprovedException;
import com.springBoot.EmployeeLeaveManagement.exceptions.StartDateIsGreaterThanEndDateException;
import com.springBoot.EmployeeLeaveManagement.repositories.ApprovalTableRepository;
import com.springBoot.EmployeeLeaveManagement.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ApprovalTableService {

    private final ApprovalTableRepository repository;

    @Autowired
    private ApprovalAnnualLeave approvalAnnualLeave;

    @Autowired
    public ApprovalTableService(ApprovalTableRepository repository) {
        this.repository = repository;
    }

    public ApprovalTable toEntity(ApprovalTableDto dto) {
        return Utils.MODEL_MAPPER.map(dto, ApprovalTable.class);
    }

    public ApprovalTableDto toDto(ApprovalTable entity) {
        try {
            ApprovalTableDto dto = new ApprovalTableDto(
                    ApprovalEnum.valueOf(entity.getTopic()),
                    Utils.OBJECT_MAPPER.readTree(entity.getDtoBinder()),
                    ApprovalStatusEnum.valueOf(entity.getStatus()),
                    entity.getNote());
            return dto;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<ApprovalTableDto> toDtoList(List<ApprovalTable> approvalList) {
        return approvalList.stream().map(e ->toDto(e)).collect(Collectors.toList());
    }

    public List<ApprovalTable> getList() {
        return repository.findAll();
    }

    public void create(ApprovalTableDto dto) {
        repository.save(toEntity(dto));
    }

    public void updateApprovalStatus(Long id, ApprovalStatusEnum status)
            throws StartDateIsGreaterThanEndDateException, ExceedingAnnualLeaveThresholdException, AnnualLeaveAlreadyApprovedException {

        Optional<ApprovalTable> entityOpt = repository.findById(id);

        if (!entityOpt.isPresent()) {
            return;
        }

        ApprovalTable entity = entityOpt.get();

        IApprovalTopicType topicType =
                getApprovalTopicInterface(
                        ApprovalEnum.valueOf(entity.getTopic()));

        topicType.updateStatus(entity, status);

    }

    public IApprovalTopicType getApprovalTopicInterface(ApprovalEnum topic) {
        switch (topic) {
            case ANNUAL_LEAVE:
                return approvalAnnualLeave;
            default:
                return null;
        }
    }


}
