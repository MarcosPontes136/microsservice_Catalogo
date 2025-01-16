package com.microsservice.Catalogo.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microsservice.Catalogo.dto.CatalogoDTO;
import com.microsservice.Catalogo.models.CatalogoModel;
import com.microsservice.Catalogo.service.CatalogoService;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/catalogo")
public class CatalogoController {
	
	@Autowired
	private CatalogoService catalogoService;
	
	
	@PostMapping(value = "/product")
	public ResponseEntity<CatalogoModel> sendingMessage(@RequestBody CatalogoModel messageModel) throws UnsupportedEncodingException {
		CatalogoModel catalogoModel = catalogoService.savedProduct(messageModel);
		return new ResponseEntity<>(catalogoModel, HttpStatus.CREATED);
	}

	@GetMapping(value = "/products")
	public ResponseEntity<List<CatalogoDTO>> getAllMessages() {
		List<CatalogoDTO> products = catalogoService.getAllProducts();
		return new ResponseEntity<>(products, HttpStatus.OK);
	}
	
    @GetMapping("/product/name")
    public ResponseEntity<List<CatalogoModel>> searchProduct(@RequestParam(required = false) String name, @RequestParam(required = false) Double price) throws UnsupportedEncodingException {
        List<CatalogoModel> products = catalogoService.searchProduct(name, price);
        return ResponseEntity.ok(products);
    }
	
}
