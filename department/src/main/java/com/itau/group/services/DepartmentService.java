package com.itau.group.services;

import com.itau.group.exceptions.ConflictException;
import com.itau.group.exceptions.NotFoundException;
import com.itau.group.models.entities.DepartmentEntity;
import com.itau.group.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<DepartmentEntity> getDepartments() {
        return departmentRepository.findAllByOrderByCodeAsc();
    }

    public DepartmentEntity getDepartmentsById(Integer code) {
        Optional<DepartmentEntity> departmentOptional = departmentRepository.findById(code);

        if (departmentOptional.isEmpty()) {
            throw new NotFoundException("Department not found");
        }

        return departmentOptional.get();
    }

    public DepartmentEntity registerDepartment(DepartmentEntity departmentEntity) {
        Optional<DepartmentEntity> departmentOptional = departmentRepository.findByCode(departmentEntity.getCode());

        if (departmentOptional.isPresent()) {
            throw new ConflictException("This department already exists!");
        }

        return departmentRepository.save(departmentEntity);
    }

    public DepartmentEntity updateDepartment(DepartmentEntity departmentEntity, Integer id) {
        departmentEntity.setId(id);
        return departmentRepository.save(departmentEntity);
    }

    public void deleteDepartmentById(Integer id) {
        departmentRepository.deleteById(id);
    }


}