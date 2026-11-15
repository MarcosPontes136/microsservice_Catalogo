package com.microsservice.Catalogo.domain.useCase.removerProduto;

import com.microsservice.Catalogo.domain.port.input.removerProduto.IRemoverProdutoUseCase;
import com.microsservice.Catalogo.domain.port.output.removerProduto.IRemoverProdutoAdapter;
import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class RemoverProdutoUseCase implements IRemoverProdutoUseCase {
    private final IRemoverProdutoAdapter iRemoverProdutoAdapter;

    @Override
    public Mono<Map<String, Null>> removerProduto (UUID id) {
        log.info("[removerProduto] - Iniciando fluxo do serviço no useCase.");

        return iRemoverProdutoAdapter.removerProduto(id)
                .filter(produto -> !produto.isEmpty())
                .switchIfEmpty(
                        Mono.error(new ResponseStatusException(
                                        HttpStatus.NOT_FOUND, "[removerProduto] - Nenhum produto removido com sucesso!"
                                )
                        )
                );
    }
}