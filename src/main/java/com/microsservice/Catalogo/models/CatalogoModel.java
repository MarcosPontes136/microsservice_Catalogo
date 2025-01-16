package com.microsservice.Catalogo.models;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Product")
public class CatalogoModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private UUID id;
	
	@Column(name = "PRICE")
	private Double price;
	
	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "DISCOUNTED")
	private String discounted;
	
	@Column(name = "DISCOUNT")
	private Double discount;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Lob
	@Column(name = "IMAGE", columnDefinition = "MEDIUMBLOB")
	private byte[] image;
}
