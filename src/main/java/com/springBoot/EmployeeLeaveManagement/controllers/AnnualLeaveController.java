package com.springBoot.EmployeeLeaveManagement.controllers;

import com.springBoot.EmployeeLeaveManagement.services.AnnualLeaveService;
import com.springBoot.EmployeeLeaveManagement.dtos.AnnualLeaveDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/annual-leave")
@Tag(name = "Annual Leave", description = "the Annual Leave API")
public class AnnualLeaveController {

    private AnnualLeaveService leaveService;

    @Autowired
    public AnnualLeaveController(AnnualLeaveService leaveService) {
        this.leaveService = leaveService;
    }

    @GetMapping
    private ResponseEntity<List<AnnualLeaveDto>> getAnnualLeaveList() {
        return new ResponseEntity<>(
                leaveService.toDtoList(leaveService.getAnnualLeaveList()),
                HttpStatus.OK);
    }

}
