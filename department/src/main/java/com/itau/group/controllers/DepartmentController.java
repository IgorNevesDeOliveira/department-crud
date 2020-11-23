package com.itau.group.controllers;

import com.itau.group.models.ResponseWrapper;
import com.itau.group.models.dto.inbound.DepartmentDtoIn;
import com.itau.group.models.dto.outbound.DepartmentDtoOut;
import com.itau.group.models.entities.DepartmentEntity;
import com.itau.group.services.DepartmentService;
import com.itau.group.utils.converters.DepartmentConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.itau.group.utils.ResponseStatus.SUCCESS;

@RestController
@RequestMapping("/v1/departments")
public class DepartmentController {

    private final DepartmentService departmentService;
    private final DepartmentConverter departmentConverter;

    @Autowired
    public DepartmentController(DepartmentService departmentService, DepartmentConverter departmentConverter) {
        this.departmentService = departmentService;
        this.departmentConverter = departmentConverter;
    }

    @GetMapping
    public ResponseWrapper getDepartments() {
        List<DepartmentDtoOut> response = departmentService.getDepartments()
                .stream()
                .map(departmentConverter::convertToDto)
                .collect(Collectors.toList());

        return new ResponseWrapper<>("Departments fetched successfully", SUCCESS, response);
    }

    @GetMapping("/{id}")
    public ResponseWrapper getDepartmentById(@PathVariable Integer id) {
        DepartmentDtoOut response = departmentConverter.convertToDto(
                departmentService.getDepartmentsById(id)
        );
        return new ResponseWrapper<>("Department fetched successfully", SUCCESS, response);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseWrapper registerDepartment(@RequestBody @Validated DepartmentDtoIn departmentDtoIn) {
        DepartmentEntity departmentEntity = departmentConverter.convertToEntity(departmentDtoIn);
        DepartmentDtoOut response = departmentConverter.convertToDto(
                departmentService.registerDepartment(departmentEntity)
        );
        return new ResponseWrapper<>("Department created successfully", SUCCESS, response);
    }

    @PutMapping("/{id}")
    public ResponseWrapper updateDepartment(@PathVariable Integer id,
                                            @RequestBody @Validated DepartmentDtoIn departmentDtoIn) {
        DepartmentEntity departmentEntity = departmentConverter.convertToEntity(departmentDtoIn);
        DepartmentDtoOut response = departmentConverter.convertToDto(
                departmentService.updateDepartment(departmentEntity, id)
        );
        return new ResponseWrapper<>("Department updated successfully", SUCCESS, response);
    }

    @DeleteMapping("/{id}")
    public ResponseWrapper deleteDepartmentById(@PathVariable Integer id) {
        departmentService.deleteDepartmentById(id);
        return new ResponseWrapper<>("Department deleted successfully", SUCCESS, null);
    }
}
