package com.microsservice.Catalogo.models;

import org.springframework.stereotype.Component;

import jakarta.validation.ValidationException;

@Component
public class CatalogoModelValidator {
	
	private void validField(Object field, String fieldName) {
	    if (field == null) {
	        throw new ValidationException("O campo '" + fieldName + "' é obrigatório e não pode ser nulo.");
	    }
	    if (field instanceof String && ((String) field).isEmpty()) {
	        throw new ValidationException("O campo '" + fieldName + "' é obrigatório e não pode ser vazio.");
	    }
	}
	
	public void validationModel (CatalogoModel catalogoModel) {
		validField(catalogoModel.getName(), "name");
		validField(catalogoModel.getDescription(), "description");
	    validField(catalogoModel.getPrice(), "price");
	    validField(catalogoModel.getStatus(), "status");
	    validField(catalogoModel.getImage(), "image");
	}
}
