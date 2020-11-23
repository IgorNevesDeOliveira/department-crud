package com.itau.group.exceptions;

import com.itau.group.utils.ResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
class ExceptionFormat {
    private ResponseStatus status;
    private String message;
    private LocalDateTime timestamp;
}
