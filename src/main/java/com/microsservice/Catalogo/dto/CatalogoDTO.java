package com.microsservice.Catalogo.dto;

import java.util.UUID;

import com.microsservice.Catalogo.models.CatalogoModel;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CatalogoDTO {
	
	private UUID id;
	
	@NotBlank
	private Double price;
	
	@NotBlank
	private String status;
	
	@NotBlank
	private String discounted;
	
	@NotBlank
	private Double discount;
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String description;
	
	@NotBlank
	private byte[] image;
	
	
	public UUID id() {
		return id;
	}

}
