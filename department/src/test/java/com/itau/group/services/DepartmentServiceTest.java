package com.itau.group.services;

import com.itau.group.exceptions.ConflictException;
import com.itau.group.exceptions.NotFoundException;
import com.itau.group.mocks.DepartmentMocks;
import com.itau.group.models.entities.DepartmentEntity;
import com.itau.group.repositories.DepartmentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.itau.group.mocks.DepartmentMocks.getDepartmentEntityMock;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class DepartmentServiceTest {


    @Autowired
    DepartmentService departmentService;

    @MockBean
    DepartmentRepository departmentRepository;


    @Test
    void WHEN_NO_DEPARTMENTS_FOUND_GET_DEPARTMENTS_RETURNS_EMPTY_LIST() {
        when(departmentRepository.findAllByOrderByCodeAsc()).thenReturn(Collections.emptyList());
        List<DepartmentEntity> response = departmentService.getDepartments();
        assertThat(response).isEmpty();
    }

    @Test
    void WHEN_DEPARTMENTS_FOUND_GET_DEPARTMENTS_RETURNS_DEPARTMENT_LIST() {
        when(departmentRepository.findAllByOrderByCodeAsc()).thenReturn(DepartmentMocks.getListDepartmentsEntityMock());
        List<DepartmentEntity> response = departmentService.getDepartments();
        assertThat(response.size()).isEqualTo(DepartmentMocks.getListDepartmentsEntityMock().size());
    }

    @Test
    void WHEN_NO_DEPARTMENT_FOUND_GET_DEPARTMENTS_BY_ID_THROWS_NOTFOUND_EXCEPTION() {
        when(departmentRepository.findById(any())).thenReturn(Optional.empty());
        NotFoundException exception = assertThrows(NotFoundException.class, () ->
                departmentService.getDepartmentsById(getDepartmentEntityMock().getCode()));
        String expectedMessage = "Department not found";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void WHEN_DEPARTMENT_FOUND_GET_DEPARTMENTS_BY_ID_RETURNS_DEPARTMENT_ENTITY() {
        when(departmentRepository.findById(any())).thenReturn(Optional.of(getDepartmentEntityMock()));
        DepartmentEntity response = departmentService.getDepartmentsById(getDepartmentEntityMock().getId());
        assertThat(response).isEqualTo(getDepartmentEntityMock());
    }

    @Test
    void WHEN_DEPARTMENT_ALREADY_EXISTS_REGISTER_DEPARTMENTS_THROWS_CONFLICT_EXCEPTION() {
        when(departmentRepository.findByCode(any())).thenReturn(Optional.of(getDepartmentEntityMock()));
        ConflictException exception = assertThrows(
                ConflictException.class, () -> departmentService.registerDepartment(getDepartmentEntityMock()));
        String expectedMessage = "This department already exists!";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void WHEN_DEPARTMENT_DOESNT_EXISTS_REGISTER_DEPARTMENTS_RETURNS_SAVED_DEPARTMENT_ENTITY() {
        when(departmentRepository.save(any())).thenReturn(getDepartmentEntityMock());
        DepartmentEntity response = departmentService.registerDepartment(getDepartmentEntityMock());
        assertThat(response).isEqualTo(getDepartmentEntityMock());
    }

    @Test
    void WHEN_DEPARTMENT_DOESNT_EXISTS_UPDATE_DEPARTMENT_CREATES_WITH_THE_NEXT_AVAILABLE_ID() {
        when(departmentRepository.save(any())).thenReturn(getDepartmentEntityMock());
        DepartmentEntity response = departmentService.updateDepartment(getDepartmentEntityMock(), 2);
        assertThat(response).isEqualTo(getDepartmentEntityMock());
    }

    @Test
    void VERIFY_IF_ID_USED_TO_DELETE_DEPARTMENT_IS_CORRECT() {
        departmentService.deleteDepartmentById(getDepartmentEntityMock().getId());
        verify(departmentRepository).deleteById(getDepartmentEntityMock().getId());
    }
}
