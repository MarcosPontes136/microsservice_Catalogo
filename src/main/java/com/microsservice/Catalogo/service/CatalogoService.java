package com.microsservice.Catalogo.service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.microsservice.Catalogo.dto.CatalogoDTO;
import com.microsservice.Catalogo.exceptions.NoProductsFoundException;
import com.microsservice.Catalogo.models.CatalogoModel;
import com.microsservice.Catalogo.models.CatalogoModelValidator;
import com.microsservice.Catalogo.repositories.CatalogoRepository;
import com.microsservice.Catalogo.utility.CatalogoConverter;

import jakarta.validation.ValidationException;

@Service
public class CatalogoService {

	@Autowired
	private CatalogoRepository catalogoRepository;
	
    private static final Logger logger = LoggerFactory.getLogger(CatalogoRepository.class);
    
    
    public CatalogoModel savedProduct(CatalogoModel catalogoModel) throws UnsupportedEncodingException {
  
    	try {
			logger.info("Tentando montar Produto {}", catalogoModel.getName());
			
			new CatalogoModelValidator().validationModel(catalogoModel);
				
			CatalogoDTO catalogoDTO = CatalogoConverter.convertToDTO(catalogoModel);
	 			
        } catch (ValidationException e) {
            logger.error("Erro de validação: {}", e.getMessage());
            throw e;
            
		} catch (Exception e) {
			logger.error("Erro ao criar Produto! {}", e.getMessage());
			throw e;
		}
    	
    	logger.info("Produto Criado! {" + catalogoModel.getId() + "}");
		return catalogoRepository.save(catalogoModel);	
	}
    
    public List<CatalogoDTO> getAllProducts() {
    	try {
    		logger.info("Buscando Produtos!"); 
    		
    		List<CatalogoModel> catalogoModels = catalogoRepository.findAll();
    		
    		if (catalogoModels.isEmpty()) {
                logger.warn("Nenhum produto encontrado no catálogo.");
                throw new NoProductsFoundException("Nenhum produto encontrado.");
			}
    		
    		return catalogoModels.stream()
    				.map(CatalogoConverter::convertToDTO)
    				.collect(Collectors.toList());
    		
        } catch (NoProductsFoundException ex) {
            logger.error("Erro ao buscar produtos: {}", ex.getMessage());
            throw ex;

        } catch (Exception ex) {
            logger.error("Erro inesperado ao buscar produtos: {}", ex.getMessage(), ex);
            throw new RuntimeException("Erro ao buscar produtos. Tente novamente mais tarde.", ex);
        }
    }
    
    public List<CatalogoModel> searchProduct(String name, Double price) {	
    	try {
    		return catalogoRepository.findByFilters(name, price);	
        } catch (DataAccessException e) {
            logger.error("Erro ao consultar o catálogo no banco de dados: ", e);
            throw new RuntimeException("Erro ao consultar o catálogo. Tente novamente mais tarde.", e);
            
        } catch (Exception ex) {	
            logger.error("Erro inesperado ao realizar a busca: ", ex.getMessage(), ex);
            throw new RuntimeException("Erro ao realizar a busca. Tente novamente mais tarde.", ex);
        }
    }
    
    public void removeProduct(UUID id) {
    	logger.info("Validando Produto! {" + id +"}");
    	
    	try {
    		
            if (!catalogoRepository.existsById(id)) {
                throw new IllegalArgumentException();
            }  
            
            catalogoRepository.deleteById(id);
            
		} catch (IllegalArgumentException e) {
			logger.info("Erro ao remover Produto {}", e.getMessage());
			throw e;
		}
    	logger.info("Produto removido por ID! {" + id +"}");

    }
}
