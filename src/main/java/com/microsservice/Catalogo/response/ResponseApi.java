package com.microsservice.Catalogo.response;

public record ResponseApi<T>(
            String message,
            T data,
            boolean success
) { }


