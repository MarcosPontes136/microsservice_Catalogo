package com.microsservice.Catalogo.adapter.input.criarProduto;

import com.microsservice.Catalogo.domain.model.CatalogoModel;
import com.microsservice.Catalogo.response.ResponseApi;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;

import java.io.UnsupportedEncodingException;

public interface CriarProdutoSwagger {

        @Operation(summary = "Create a new Product", description = "Endpoint to create a new product in the catalog")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "201", description = "Create with success"),
                        @ApiResponse(responseCode = "400", description = "Request error"),
                        @ApiResponse(responseCode = "422", description = "Required field"),
                        @ApiResponse(responseCode = "500", description = "Internal server erro"),
        })
        public Mono<ResponseEntity<ResponseApi<CatalogoModel>>> criarProduto(
                        @RequestBody CatalogoModel messageModel) throws UnsupportedEncodingException;
}
