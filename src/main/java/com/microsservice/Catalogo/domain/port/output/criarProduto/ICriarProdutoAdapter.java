package com.microsservice.Catalogo.domain.port.output.criarProduto;

import com.microsservice.Catalogo.domain.model.CatalogoModel;
import reactor.core.publisher.Mono;

public interface ICriarProdutoAdapter {
    Mono<CatalogoModel> salvarProduto (CatalogoModel messageModel);
}