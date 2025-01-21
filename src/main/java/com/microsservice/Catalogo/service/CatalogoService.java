package com.microsservice.Catalogo.service;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microsservice.Catalogo.dto.CatalogoDTO;
import com.microsservice.Catalogo.models.CatalogoModel;
import com.microsservice.Catalogo.repositories.CatalogoRepository;

@Service
public class CatalogoService {

	@Autowired
	private CatalogoRepository catalogoRepository;
	
    private static final Logger logger = LoggerFactory.getLogger(CatalogoRepository.class);
    
    
    public CatalogoModel savedProduct(CatalogoModel catalogoModel) throws UnsupportedEncodingException {
  
    	try {
			logger.info("Tentando montar Produto {}", catalogoModel.getName());
			
			CatalogoDTO catalogoDTO = new CatalogoDTO();
			catalogoDTO.setPrice(catalogoModel.getPrice());
			catalogoDTO.setStatus(catalogoModel.getStatus());
			catalogoDTO.setDiscounted(catalogoModel.getDiscounted());
			catalogoDTO.setDiscount(catalogoModel.getDiscount());
			catalogoDTO.setName(catalogoModel.getName());
 			catalogoDTO.setDescription(catalogoModel.getDescription());
	 		catalogoDTO.setImage(catalogoModel.getImage());
	 		
		} catch (Exception e) {
			logger.info("Erro ao enviar Produto! {}");
			throw e;
		}
    	logger.info("Produto Criado! {}");
		return catalogoRepository.save(catalogoModel);	
	}
    
    public List<CatalogoDTO> getAllProducts() {
    	
    	List<CatalogoModel> catalogoModels = catalogoRepository.findAll();
    	
    	logger.info("Buscando Produtos! {}");

        return catalogoModels.stream()
                .map(catalogoModel -> {
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
                })
                .collect(Collectors.toList()); 
    }
    
    public List<CatalogoModel> searchProduct(String name, Double price) throws UnsupportedEncodingException {	
		return catalogoRepository.findByFilters(name, price);
    }
}
