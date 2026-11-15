package com.microsservice.Catalogo.domain.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.util.UUID;

@Data
@Table("Product")
public class CatalogoModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column("ID")
	private UUID id;

	@Column("PRICE")
	private Double price;

	@Column("STATUS")
	private String status;

	@Column("DISCOUNTED")
	private String discounted;

	@Column("DISCOUNT")
	private Double discount;

	@Column("NAME")
	private String name;

	@Column("DESCRIPTION")
	private String description;

	@Column("IMAGE")
	private byte[] image;
}
