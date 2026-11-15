package com.microsservice.Catalogo.adapter.input.removerProduto;

import com.microsservice.Catalogo.domain.model.CatalogoModel;
import com.microsservice.Catalogo.response.ResponseApi;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.Null;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.UUID;

public interface RemoverProdutoSwagger {

    @Operation(summary = "Delete by UD", description = "Endpoint to delete by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted",
                    content = @Content(
                            mediaType = "application/json",schema = @Schema(
                            implementation = CatalogoModel.class
                    ))),
            @ApiResponse(responseCode = "422", description = "Product with invalid ID"),
			@ApiResponse(responseCode = "400", description = "Request error"),
			@ApiResponse(responseCode = "500", description = "Internal server erro"),
    })
    public Mono<ResponseEntity<ResponseApi<Map<String, Null>>>> removerProduto(
            @PathVariable UUID id
    ) throws UnsupportedEncodingException;
}
