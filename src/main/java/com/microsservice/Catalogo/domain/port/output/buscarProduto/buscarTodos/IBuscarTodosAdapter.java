package com.microsservice.Catalogo.domain.port.output.buscarProduto.buscarTodos;

import com.microsservice.Catalogo.dto.CatalogoDTO;
import reactor.core.publisher.Mono;

import java.util.List;

public interface IBuscarTodosAdapter {
    Mono<List<CatalogoDTO>> buscarTodos ();
}
