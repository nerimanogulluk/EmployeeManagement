package com.springBoot.EmployeeLeaveManagement.services;

import com.springBoot.EmployeeLeaveManagement.entities.AnnualLeave;
import com.springBoot.EmployeeLeaveManagement.repositories.AnnualLeaveRepository;
import com.springBoot.EmployeeLeaveManagement.dtos.AnnualLeaveDto;
import com.springBoot.EmployeeLeaveManagement.util.Utils;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AnnualLeaveService {

    private final AnnualLeaveRepository repository;

    public AnnualLeaveService(AnnualLeaveRepository repository) {
        this.repository = repository;
    }

    public List<AnnualLeaveDto> toDtoList(List<AnnualLeave> annualLeaveList) {
        return annualLeaveList.stream().map(e -> Utils.MODEL_MAPPER.map(e, AnnualLeaveDto.class)).collect(Collectors.toList());
    }

    public List<AnnualLeave> getAnnualLeaveList() {
        return repository.findAll();
    }

}
