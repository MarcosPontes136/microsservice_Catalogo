package com.microsservice.Catalogo.domain.port.input.CriarProduto;

import com.microsservice.Catalogo.domain.model.CatalogoModel;
import reactor.core.publisher.Mono;

public interface ICriarProdutoUseCase {
    Mono<CatalogoModel> salvarProduto (CatalogoModel messageModel);
}