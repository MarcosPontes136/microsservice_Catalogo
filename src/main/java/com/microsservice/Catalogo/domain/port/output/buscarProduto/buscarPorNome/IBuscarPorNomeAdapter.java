package com.microsservice.Catalogo.domain.port.output.buscarProduto.buscarPorNome;

import com.microsservice.Catalogo.dto.CatalogoDTO;
import reactor.core.publisher.Mono;

import java.util.List;

public interface IBuscarPorNomeAdapter {
    Mono<List<CatalogoDTO>> buscarPorNome (String nome, Double preco);
}
