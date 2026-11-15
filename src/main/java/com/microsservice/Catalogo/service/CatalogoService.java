package com.microsservice.Catalogo.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microsservice.Catalogo.domain.model.CatalogoModel;
import com.microsservice.Catalogo.domain.model.CatalogoModelValidator;
import com.microsservice.Catalogo.repositories.CatalogoRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CatalogoService {

	@Autowired
	private CatalogoRepository catalogoRepository;
	
	@Autowired
	private CatalogoModelValidator catalogoModelValidator;
    
//    public Mono<CatalogoModel> savedProduct(CatalogoModel catalogoModel) throws UnsupportedEncodingException {
//
//    	try {
//
//			log.info("Tentando montar Produto {}", catalogoModel.getName());
//			catalogoModelValidator.validationModel(catalogoModel);
//
//        } catch (ValidationException e) {
//            log.error("Erro de validação: {}", e.getMessage());
//            throw e;
//
//		} catch (Exception e) {
//			log.error("Erro ao criar Produto! {}", e.getMessage());
//			throw e;
//		}
//
//    	log.info("Produto Criado! {" + catalogoModel.getName() + "}");
//		return Mono.just(catalogoRepository.save(catalogoModel));
//	}
    
//    public Mono<List<CatalogoDTO>> getAllProducts() {
//    	try {
//    		log.info("Buscando Produtos!");
//
//    		List<CatalogoModel> catalogoModels = catalogoRepository.findAll();
//
//    		if (catalogoModels.isEmpty()) {
//                log.warn("Nenhum produto encontrado no catálogo.");
//                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum produto encontrado.");
//			}
//
//    		return Mono.just(catalogoModels.stream()
//                            .map(CatalogoConverter::convertToDTO)
//                            .collect(Collectors.toList()));
//
//        } catch (ResponseStatusException ex) {
//            log.error("Erro ao buscar produtos: {}", ex.getMessage());
//            throw ex;
//
//        } catch (Exception ex) {
//            log.error("Erro inesperado ao buscar produtos: {}", ex.getMessage(), ex);
//            throw new RuntimeException("Erro ao buscar produtos. Tente novamente mais tarde.", ex);
//        }
//    }
    
//    public List<CatalogoModel> searchProduct(String name, Double price) {
//    	try {
//
//    		List<CatalogoModel> result = catalogoRepository.findByFilters(name, price);
//
//            if (result.isEmpty()) {
//            	log.warn("Nenhum produto encontrado");
//                throw new NoSuchElementException("Nenhum produto encontrado com: Name: " + name + ", Price: " + price);
//            }
//
//            log.info("Produtos encontrados");
//            return result;
//
//        } catch (NoSuchElementException e) {
//            log.error("Erro ao consultar o catálogo no banco de dados: ", e);
//            throw e;
//
//        } catch (Exception ex) {
//            log.error("Erro inesperado ao realizar a busca: ", ex.getMessage(), ex);
//            throw new RuntimeException("Erro ao realizar a busca. Tente novamente mais tarde.", ex);
//        }
//
//    }
//
//    public void removeProduct(UUID id) {
//    	log.info("Validando Produto! {" + id +"}");
//
//    	try {
//
//            if (!catalogoRepository.existsById(id)) {
//                throw new IllegalArgumentException();
//            }
//
//            catalogoRepository.deleteById(id);
//
//		} catch (IllegalArgumentException e) {
//			log.info("Erro ao remover Produto {}", e.getMessage());
//			throw e;
//		}
//    	log.info("Produto removido por ID! {" + id +"}");
//
//    }
}
