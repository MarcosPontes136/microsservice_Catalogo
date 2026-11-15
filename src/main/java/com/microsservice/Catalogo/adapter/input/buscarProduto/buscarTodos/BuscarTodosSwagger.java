package com.microsservice.Catalogo.adapter.input.buscarProduto.buscarTodos;

import com.microsservice.Catalogo.dto.CatalogoDTO;
import com.microsservice.Catalogo.response.ResponseApi;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import java.util.List;

public interface BuscarTodosSwagger {

    @Operation(summary = "Get all Products", description = "Endpoint to search all products in the catalog")
    @ApiResponses(value = {
             @ApiResponse(responseCode = "200", description = "Search completed successfully",
                    content = @Content(
                            mediaType = "application/json",schema = @Schema(
                                    implementation = CatalogoDTO.class
                    ))),
            @ApiResponse(responseCode = "400", description = "Request error"),
            @ApiResponse(responseCode = "500", description = "Internal server erro"),
    })
    public Mono<ResponseEntity<ResponseApi<List<CatalogoDTO>>>> buscarTodos();
}