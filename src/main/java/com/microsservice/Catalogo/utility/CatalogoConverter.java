package com.microsservice.Catalogo.utility;

import com.microsservice.Catalogo.dto.CatalogoDTO;
import com.microsservice.Catalogo.models.CatalogoModel;

public class CatalogoConverter {
	private CatalogoConverter() {
		
	}
	
	public static CatalogoDTO convertToDTO(CatalogoModel catalogoModel) {
	    CatalogoDTO catalogoDTO = new CatalogoDTO();
	    catalogoDTO.setId(catalogoModel.getId());
	    catalogoDTO.setPrice(catalogoModel.getPrice());
	    catalogoDTO.setStatus(catalogoModel.getStatus());
	    catalogoDTO.setDiscounted(catalogoModel.getDiscounted());
	    catalogoDTO.setDiscount(catalogoModel.getDiscount());
	    catalogoDTO.setName(catalogoModel.getName());
	    catalogoDTO.setDescription(catalogoModel.getDescription());
	    catalogoDTO.setImage(catalogoModel.getImage());
	    return catalogoDTO;
	}
}
