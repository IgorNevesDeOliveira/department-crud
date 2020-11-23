package com.itau.group.exceptions;

import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
public class ConflictException extends RuntimeException {

  public ConflictException(String message) {
    super(message);
  }

}