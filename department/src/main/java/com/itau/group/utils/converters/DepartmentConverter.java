package com.itau.group.utils.converters;

import com.itau.group.models.dto.inbound.DepartmentDtoIn;
import com.itau.group.models.dto.outbound.DepartmentDtoOut;
import com.itau.group.models.entities.DepartmentEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DepartmentConverter {

    @Autowired
    private ModelMapper modelMapper;

    public DepartmentEntity convertToEntity(DepartmentDtoIn departmentDtoIn) {
        return modelMapper.map(departmentDtoIn, DepartmentEntity.class);
    }

    public DepartmentDtoOut convertToDto(DepartmentEntity departmentEntity) {
        return modelMapper.map(departmentEntity, DepartmentDtoOut.class);
    }
}
