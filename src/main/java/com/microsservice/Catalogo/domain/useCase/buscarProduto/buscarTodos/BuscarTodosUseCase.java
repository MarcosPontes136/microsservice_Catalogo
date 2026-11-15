package com.microsservice.Catalogo.domain.useCase.buscarProduto.buscarTodos;

import com.microsservice.Catalogo.domain.port.input.buscarProduto.buscarTodos.IBuscarTodosUseCase;
import com.microsservice.Catalogo.domain.port.output.buscarProduto.buscarTodos.IBuscarTodosAdapter;
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
public class BuscarTodosUseCase implements IBuscarTodosUseCase {
    private final IBuscarTodosAdapter iBuscarTodosAdapter;

    @Override
    public Mono<List<CatalogoDTO>> buscarTodos() {
        log.info("[buscarTodos] - Iniciando fluxo do serviço no useCase.");

        return iBuscarTodosAdapter.buscarTodos()
                .filter(produtos -> produtos != null && !produtos.isEmpty())
                .switchIfEmpty(
                        Mono.error(new ResponseStatusException(
                                        HttpStatus.NOT_FOUND, "[buscarTodos] - Nenhum produto encontrado"
                                )
                        )
                );
    }
}
