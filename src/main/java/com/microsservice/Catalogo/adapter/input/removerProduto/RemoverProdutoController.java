package com.microsservice.Catalogo.adapter.input.removerProduto;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.microsservice.Catalogo.domain.port.input.removerProduto.IRemoverProdutoUseCase;
import com.microsservice.Catalogo.response.ResponseApi;

import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@RestController
public class RemoverProdutoController implements RemoverProdutoSwagger {
        private final IRemoverProdutoUseCase iRemoverProdutoUseCase;

        @Override
        @DeleteMapping(value = "/produtos/id/{id}")
        public Mono<ResponseEntity<ResponseApi<Map<String, Null>>>> removerProduto(
                        @PathVariable UUID id) throws UnsupportedEncodingException {
                log.info("[removerProduto] - Iniciando remoção de um produto no catálogo");

                return iRemoverProdutoUseCase.removerProduto(id)
                                .map(produto -> ResponseEntity.ok(
                                                new ResponseApi<>(
                                                                "[removerProduto] - Produto removido com sucesso!",
                                                                produto,
                                                                true
                                                )
                                ));
        }
}
