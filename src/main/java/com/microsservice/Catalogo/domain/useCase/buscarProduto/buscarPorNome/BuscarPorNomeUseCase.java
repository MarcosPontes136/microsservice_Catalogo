package com.microsservice.Catalogo.domain.useCase.buscarProduto.buscarPorNome;

import com.microsservice.Catalogo.domain.port.input.buscarProduto.buscarPorNome.IBuscarPorNomeUseCase;
import com.microsservice.Catalogo.domain.port.output.buscarProduto.buscarPorNome.IBuscarPorNomeAdapter;
import com.microsservice.Catalogo.dto.CatalogoDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class BuscarPorNomeUseCase implements IBuscarPorNomeUseCase {
        private final IBuscarPorNomeAdapter iBuscarPorNomeAdapter;

    @Override
    public Mono<List<CatalogoDTO>> buscarPorNome(String nome, Double preco) {
            log.info("[buscarPorNome] - Iniciando fluxo do serviço no useCase.");

        return iBuscarPorNomeAdapter.buscarPorNome(nome, preco)
                        .filter(produto -> produto.getFirst() != null && !produto.isEmpty())
                .switchIfEmpty(
                        Mono.error(new ResponseStatusException(
                                                        HttpStatus.NOT_FOUND,
                                                        "[buscarPorNome] - Nenhum produto encontrado"
                                )
                        )
                );
    }
}
