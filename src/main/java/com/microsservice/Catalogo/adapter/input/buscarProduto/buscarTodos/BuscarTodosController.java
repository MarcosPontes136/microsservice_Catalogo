package com.microsservice.Catalogo.adapter.input.buscarProduto.buscarTodos;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microsservice.Catalogo.domain.port.input.buscarProduto.buscarTodos.IBuscarTodosUseCase;
import com.microsservice.Catalogo.dto.CatalogoDTO;
import com.microsservice.Catalogo.response.ResponseApi;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@RestController
public class BuscarTodosController implements BuscarTodosSwagger {

        private final IBuscarTodosUseCase iBuscarTodosUseCase;

        @Override
        @GetMapping("/produtos")
        public Mono<ResponseEntity<ResponseApi<List<CatalogoDTO>>>> buscarTodos() {
                log.info("[buscarTodos] - Iniciando busca de todos os produtos no catálogo");

                return iBuscarTodosUseCase.buscarTodos()
                                .map(produtos -> ResponseEntity.ok(
                                                new ResponseApi<>(
                                                                "[buscarTodos] - Produtos encontrados com sucesso",
                                                                produtos,
                                                                true
                                                )
                                ));
        }
}
