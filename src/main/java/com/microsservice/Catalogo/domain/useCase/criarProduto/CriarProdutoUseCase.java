package com.microsservice.Catalogo.domain.useCase.criarProduto;

import com.microsservice.Catalogo.domain.port.input.CriarProduto.ICriarProdutoUseCase;
import com.microsservice.Catalogo.domain.port.output.criarProduto.ICriarProdutoAdapter;
import com.microsservice.Catalogo.domain.model.CatalogoModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class CriarProdutoUseCase implements ICriarProdutoUseCase {
    private final ICriarProdutoAdapter iCriarProdutoAdapter;

    @Override
    public Mono<CatalogoModel> salvarProduto(CatalogoModel messageModel) {
        log.info("[criarProduto] - Iniciando fluxo do serviço no useCase.");

        return iCriarProdutoAdapter.salvarProduto(messageModel)
                .filter(produto -> produto.getId() != null && !produto.getName().isEmpty())
                .switchIfEmpty(
                        Mono.error(new ResponseStatusException(
                                        HttpStatus.NOT_FOUND, "[criarProduto] - Nenhum produto criado com sucesso!"
                                )
                        )
                );
    }
}