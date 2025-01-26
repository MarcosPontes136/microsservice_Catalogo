package com.microsservice.Catalogo.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microsservice.Catalogo.dto.CatalogoDTO;
import com.microsservice.Catalogo.models.CatalogoModel;
import com.microsservice.Catalogo.response.ResponseApi;
import com.microsservice.Catalogo.service.CatalogoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.*;
import jakarta.validation.constraints.Null;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/catalogo")
public class CatalogoController {
	
	@Autowired
	private CatalogoService catalogoService;
	
	@PostMapping(value = "/product")
	@Operation(summary = "Create a new Product", description = "Endpoint to create a new product in the catalog")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Create with success"),
			@ApiResponse(responseCode = "422", description = "Required field"),
			@ApiResponse(responseCode = "400", description = "Request error"),
			@ApiResponse(responseCode = "500", description = "Internal server erro"),
	})
	public ResponseEntity<ResponseApi<CatalogoModel>> createProduct(@RequestBody CatalogoModel messageModel) throws UnsupportedEncodingException {
		CatalogoModel catalogoModel = catalogoService.savedProduct(messageModel);
		ResponseApi<CatalogoModel> response = new ResponseApi<>("Produto criado com sucesso", catalogoModel, true);	
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	
	@GetMapping(value = "/products")
	@Operation(summary = "Get all Products", description = "Endpoint to search all products in the catalog")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Search completed successfully"),
			@ApiResponse(responseCode = "400", description = "Request error"),
			@ApiResponse(responseCode = "500", description = "Internal server erro"),
	})
	public ResponseEntity<ResponseApi<List<CatalogoDTO>>> getAllProducts() {
		List<CatalogoDTO> products = catalogoService.getAllProducts();
		ResponseApi<List<CatalogoDTO>> response = new ResponseApi<>("Produtos encontrados com sucesso", products, true);	
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
    @GetMapping("/product/name")
	@Operation(summary = "Get by name and price", description = "Endpoint to search by name and price the catalog")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Search completed successfully"),
			@ApiResponse(responseCode = "404", description = "No products found with the information provided"),
			@ApiResponse(responseCode = "400", description = "Request error"),
			@ApiResponse(responseCode = "500", description = "Internal server erro"),
	})
    public ResponseEntity<ResponseApi<List<CatalogoModel>>> searchProduct(@RequestParam(required = false) String name, @RequestParam(required = false) Double price) throws UnsupportedEncodingException {
        List<CatalogoModel> products = catalogoService.searchProduct(name, price);
        ResponseApi<List<CatalogoModel>> response = new ResponseApi<>("Produtos encontrados com sucesso", products, true);	
        return ResponseEntity.ok(response);
    }
	
    
    @DeleteMapping("/product/id/{id}")
	@Operation(summary = "Delete by UD", description = "Endpoint to delete by ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successfully deleted"),
			@ApiResponse(responseCode = "422", description = "Product with invalid ID"),
			@ApiResponse(responseCode = "400", description = "Request error"),
			@ApiResponse(responseCode = "500", description = "Internal server erro"),
	})
    public ResponseEntity<ResponseApi<Map<String, Null>>> removeProductId(@PathVariable UUID id) throws UnsupportedEncodingException {
		catalogoService.removeProduct(id);
        ResponseApi<Map<String, Null>> response = new ResponseApi<>("Produto removido com sucesso", null , true);
		return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
