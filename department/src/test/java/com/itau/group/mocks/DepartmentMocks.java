package com.itau.group.mocks;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itau.group.models.entities.DepartmentEntity;

import java.util.List;

public class DepartmentMocks {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static DepartmentEntity getDepartmentEntityMock() {
        return new DepartmentEntity(1,
                1,
                "HUMAN RESOURCES",
                "LOCAL",
                "SP",
                "SP",
                "EIS"
        );
    }

    public static List<DepartmentEntity> getListDepartmentsEntityMock() {
        return List.of(getDepartmentEntityMock());
    }

    public static String getValidDepartmentUpdateRequest() throws JsonProcessingException {
        return mapper.writeValueAsString(getDepartmentEntityMock());
    }

    public static String getJsonWithInvalidCode() throws JsonProcessingException {
        DepartmentEntity invalidCode = getDepartmentEntityMock();
        invalidCode.setCode(null);
        return mapper.writeValueAsString(invalidCode);
    }

    public static String getJsonWithInvalidName() throws JsonProcessingException {
        DepartmentEntity invalidName = getDepartmentEntityMock();
        invalidName.setName(null);
        return mapper.writeValueAsString(invalidName);
    }


    public static String getJsonWithInvalidLocal() throws JsonProcessingException {
        DepartmentEntity invalidLocal = getDepartmentEntityMock();
        invalidLocal.setLocal(null);
        return mapper.writeValueAsString(invalidLocal);
    }

    public static String getJsonWithInvalidCity() throws JsonProcessingException {
        DepartmentEntity invalidCity = getDepartmentEntityMock();
        invalidCity.setLocal(null);
        return mapper.writeValueAsString(invalidCity);
    }

    public static String getJsonWithInvalidState() throws JsonProcessingException {
        DepartmentEntity invalidState = getDepartmentEntityMock();
        invalidState.setLocal(null);
        return mapper.writeValueAsString(invalidState);
    }

    public static String getJsonWithInvalidBoard() throws JsonProcessingException {
        DepartmentEntity invalidBoard = getDepartmentEntityMock();
        invalidBoard.setLocal(null);
        return mapper.writeValueAsString(invalidBoard);
    }

}
