package com.microsservice.Catalogo.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseApi<T> {
   private String message;
   private T data;
   private boolean success;
}
