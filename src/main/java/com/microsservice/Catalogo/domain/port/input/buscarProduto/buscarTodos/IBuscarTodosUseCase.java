package com.microsservice.Catalogo.domain.port.input.buscarProduto.buscarTodos;

import com.microsservice.Catalogo.dto.CatalogoDTO;
import reactor.core.publisher.Mono;

import java.util.List;

public interface IBuscarTodosUseCase {
    Mono<List<CatalogoDTO>> buscarTodos ();
}