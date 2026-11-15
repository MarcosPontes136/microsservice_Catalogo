package com.microsservice.Catalogo.domain.port.output.removerProduto;

import jakarta.validation.constraints.Null;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.UUID;

public interface IRemoverProdutoAdapter {
    Mono<Map<String, Null>> removerProduto (UUID id);
}