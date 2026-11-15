package com.microsservice.Catalogo.adapter.input.buscarProduto.buscarPorNome;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microsservice.Catalogo.domain.port.input.buscarProduto.buscarPorNome.IBuscarPorNomeUseCase;
import com.microsservice.Catalogo.dto.CatalogoDTO;
import com.microsservice.Catalogo.response.ResponseApi;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@RestController
public class BuscarPorNomeController implements BuscarPorNomeSwagger {

        private final IBuscarPorNomeUseCase iBuscarPorNomeUseCase;

        @Override
        @GetMapping("/produtos/nome")
        public Mono<ResponseEntity<ResponseApi<List<CatalogoDTO>>>> buscarPorNome(
                        @RequestParam(required = false) String nome,
                        @RequestParam(required = false) Double preco) throws UnsupportedEncodingException {
                log.info("[buscarPorNome] - Iniciando busca de todos os produtos no catálogo");

                return iBuscarPorNomeUseCase.buscarPorNome(nome, preco)
                                .map(produtos -> ResponseEntity.ok(
                                                new ResponseApi<>(
                                                                "[buscarPorNome] - Produtos encontrados com sucesso",
                                                                produtos,
                                                                true
                                                )
                                ));
        }
}
