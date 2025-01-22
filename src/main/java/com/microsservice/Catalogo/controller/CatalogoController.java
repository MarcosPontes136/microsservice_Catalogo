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
import com.microsservice.Catalogo.response.ApiResponse;
import com.microsservice.Catalogo.service.CatalogoService;

import jakarta.validation.constraints.Null;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/catalogo")
public class CatalogoController {
	
	@Autowired
	private CatalogoService catalogoService;
	
	
	@PostMapping(value = "/product")
	public ResponseEntity<ApiResponse<CatalogoModel>> sendingMessage(@RequestBody CatalogoModel messageModel) throws UnsupportedEncodingException {
		CatalogoModel catalogoModel = catalogoService.savedProduct(messageModel);
		ApiResponse<CatalogoModel> response = new ApiResponse<>("Produto criado com sucesso", catalogoModel, true);	
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/products")
	public ResponseEntity<ApiResponse<List<CatalogoDTO>>> getAllMessages() {
		List<CatalogoDTO> products = catalogoService.getAllProducts();
		ApiResponse<List<CatalogoDTO>> response = new ApiResponse<>("Produtos encontrados com sucesso", products, true);	
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
    @GetMapping("/product/name")
    public ResponseEntity<ApiResponse<List<CatalogoModel>>> searchProduct(@RequestParam(required = false) String name, @RequestParam(required = false) Double price) throws UnsupportedEncodingException {
        List<CatalogoModel> products = catalogoService.searchProduct(name, price);
        ApiResponse<List<CatalogoModel>> response = new ApiResponse<>("Produtos encontrados com sucesso", products, true);	
        return ResponseEntity.ok(response);
    }
	
    
    @DeleteMapping("/product/id/{id}")
    public ResponseEntity<ApiResponse<Map<String, Null>>> removeProductId(@PathVariable UUID id) throws UnsupportedEncodingException {
		catalogoService.removeProduct(id);
        ApiResponse<Map<String, Null>> response = new ApiResponse<>("Produto removido com sucesso", null , true);
		return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
