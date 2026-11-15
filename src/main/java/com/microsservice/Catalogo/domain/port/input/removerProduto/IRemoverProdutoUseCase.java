package com.microsservice.Catalogo.domain.port.input.removerProduto;

import jakarta.validation.constraints.Null;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.UUID;

public interface IRemoverProdutoUseCase {
    Mono<Map<String, Null>> removerProduto (UUID id);
}