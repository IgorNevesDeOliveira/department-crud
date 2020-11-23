package com.itau.group.models.dto.inbound;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDtoIn {

    @Min(value = 1, message = "code should be greater then 0")
    @NotNull
    private Integer code;

    @NotBlank(message = "name cannot be empty")
    private String name;

    @NotBlank(message = "local cannot be empty")
    private String local;

    @NotBlank(message = "city cannot be empty")
    private String city;

    @NotBlank(message = "state cannot be empty")
    private String state;

    @NotBlank(message = "board cannot be empty")
    private String board;
}
