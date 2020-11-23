package com.itau.group.models.dto.outbound;

import com.itau.group.models.dto.inbound.DepartmentDtoIn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDtoOut extends DepartmentDtoIn {

    @Min(value = 1, message = "id should be greater then 0")
    private Integer id;
}
