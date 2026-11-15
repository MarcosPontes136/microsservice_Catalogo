package com.microsservice.Catalogo.domain.port.input.buscarProduto.buscarPorNome;

import com.microsservice.Catalogo.dto.CatalogoDTO;
import reactor.core.publisher.Mono;

import java.util.List;

public interface IBuscarPorNomeUseCase {
    Mono<List<CatalogoDTO>> buscarPorNome (String nome, Double preco);
}