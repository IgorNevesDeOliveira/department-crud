package com.itau.group.models;

import com.itau.group.utils.ResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseWrapper<T> {
    private String message;
    private ResponseStatus status;
    T data;
}
