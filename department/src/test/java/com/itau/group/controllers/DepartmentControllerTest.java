package com.itau.group.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itau.group.services.DepartmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static com.itau.group.mocks.DepartmentMocks.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class DepartmentControllerTest {


    @Autowired
    private MockMvc mvc;

    @MockBean
    DepartmentService departmentService;

    @Autowired
    ObjectMapper mapper;

    @Test
    void WHEN_THERE_IS_NO_DEPARTMENTS_RETURN_EMPTY_DATA() throws Exception {
        when(departmentService.getDepartments()).thenReturn(Collections.emptyList());
        mvc.perform(get("/v1/departments")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("Departments fetched successfully")))
                .andExpect(jsonPath("$.status", is("SUCCESS")))
                .andExpect(jsonPath("$.data", is(empty())));
    }

    @Test
    void WHEN_THERE_IS_DEPARTMENTS_RETURN_DEPARTMENTS_ON_DATA() throws Exception {
        when(departmentService.getDepartments()).thenReturn(getListDepartmentsEntityMock());
        mvc.perform(get("/v1/departments")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("Departments fetched successfully")))
                .andExpect(jsonPath("$.status", is("SUCCESS")))
                .andExpect(jsonPath("$.data[0].code", is(getListDepartmentsEntityMock().get(0).getCode())))
                .andExpect(jsonPath("$.data[0].name", is(getListDepartmentsEntityMock().get(0).getName())))
                .andExpect(jsonPath("$.data[0].local", is(getListDepartmentsEntityMock().get(0).getLocal())))
                .andExpect(jsonPath("$.data[0].city", is(getListDepartmentsEntityMock().get(0).getCity())))
                .andExpect(jsonPath("$.data[0].board", is(getListDepartmentsEntityMock().get(0).getBoard())))
                .andExpect(jsonPath("$.data[0].state", is(getListDepartmentsEntityMock().get(0).getState())));
    }

    @Test
    void WHEN_ID_IS_INVALID_GET_DEPARTMENT_BY_ID_THROWS_BAD_REQUEST() throws Exception {
        when(departmentService.getDepartments()).thenReturn(Collections.emptyList());
        mvc.perform(get("/v1/departments/invalid")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status", is("FAIL")));
    }

    @Test
    void WHEN_ALL_VALID_GET_DEPARTMENT_BY_ID_RETURNS_DEPARTMENT_ON_DATA() throws Exception {
        when(departmentService.getDepartmentsById(anyInt())).thenReturn(getDepartmentEntityMock());
        mvc.perform(get("/v1/departments/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("Department fetched successfully")))
                .andExpect(jsonPath("$.status", is("SUCCESS")))
                .andExpect(jsonPath("$.data.code", is(getDepartmentEntityMock().getCode())))
                .andExpect(jsonPath("$.data.name", is(getDepartmentEntityMock().getName())))
                .andExpect(jsonPath("$.data.local", is(getDepartmentEntityMock().getLocal())))
                .andExpect(jsonPath("$.data.city", is(getDepartmentEntityMock().getCity())))
                .andExpect(jsonPath("$.data.board", is(getDepartmentEntityMock().getBoard())))
                .andExpect(jsonPath("$.data.state", is(getDepartmentEntityMock().getState())));
    }

    @Test
    void WHEN_CODE_IS_INVALID_REGISTER_DEPARTMENTS_THROWS_BAD_REQUEST() throws Exception {
        mvc.perform(post("/v1/departments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getJsonWithInvalidCode()))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status", is("FAIL")));
    }

    @Test
    void WHEN_NAME_IS_INVALID_REGISTER_DEPARTMENTS_THROWS_BAD_REQUEST() throws Exception {
        mvc.perform(post("/v1/departments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getJsonWithInvalidName()))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status", is("FAIL")));
    }

    @Test
    void WHEN_LOCAL_IS_INVALID_REGISTER_DEPARTMENTS_THROWS_BAD_REQUEST() throws Exception {
        mvc.perform(post("/v1/departments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getJsonWithInvalidLocal()))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status", is("FAIL")));
    }

    @Test
    void WHEN_CITY_IS_INVALID_REGISTER_DEPARTMENTS_THROWS_BAD_REQUEST() throws Exception {
        mvc.perform(post("/v1/departments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getJsonWithInvalidCity()))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status", is("FAIL")));
    }

    @Test
    void WHEN_STATE_IS_INVALID_REGISTER_DEPARTMENTS_THROWS_BAD_REQUEST() throws Exception {
        mvc.perform(post("/v1/departments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getJsonWithInvalidState()))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status", is("FAIL")));
    }

    @Test
    void WHEN_BOARD_IS_INVALID_REGISTER_DEPARTMENTS_THROWS_BAD_REQUEST() throws Exception {
        mvc.perform(post("/v1/departments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getJsonWithInvalidBoard()))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status", is("FAIL")));
    }

    @Test
    void WHEN_ALL_VALID_REGISTER_DEPARTMENT_RETURN_SAVED_DEPARTMENT() throws Exception {
        when(departmentService.registerDepartment(any())).thenReturn(getDepartmentEntityMock());
        mvc.perform(post("/v1/departments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getValidDepartmentUpdateRequest()))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status", is("SUCCESS")));
    }

    @Test
    void WHEN_ALL_VALID_UPDATE_DEPARTMENT_RETURN_SAVED_DEPARTMENT() throws Exception {
        when(departmentService.updateDepartment(any(), anyInt())).thenReturn(getDepartmentEntityMock());
        mvc.perform(put("/v1/departments/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getValidDepartmentUpdateRequest()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is("SUCCESS")));
    }

    @Test
    void WHEN_ALL_VALID_DELETE_DEPARTMENT_RETURN_SUCCESS() throws Exception {
        when(departmentService.updateDepartment(any(), anyInt())).thenReturn(getDepartmentEntityMock());
        mvc.perform(delete("/v1/departments/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is("SUCCESS")));
    }
}
