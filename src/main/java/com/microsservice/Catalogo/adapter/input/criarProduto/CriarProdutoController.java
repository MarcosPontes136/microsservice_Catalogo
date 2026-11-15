package com.microsservice.Catalogo.adapter.input.criarProduto;

import java.io.UnsupportedEncodingException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.microsservice.Catalogo.domain.model.CatalogoModel;
import com.microsservice.Catalogo.domain.port.input.CriarProduto.ICriarProdutoUseCase;
import com.microsservice.Catalogo.response.ResponseApi;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@RestController
public class CriarProdutoController implements CriarProdutoSwagger {
        private final ICriarProdutoUseCase iCriarProdutoUseCase;

        @Override
        @PostMapping(value = "/produtos/criar")
        public Mono<ResponseEntity<ResponseApi<CatalogoModel>>> criarProduto(
                        @RequestBody CatalogoModel messageModel) throws UnsupportedEncodingException {
                log.info("[criarProduto] - Iniciando criação de um produto no catálogo");

                return iCriarProdutoUseCase.salvarProduto(messageModel)
                                .map(produto -> ResponseEntity.ok(
                                        new ResponseApi<>(
                                                "[criarProduto] - Produto criado com sucesso!",
                                                produto,
                                                true
                                        )
                                ));
        }
}
