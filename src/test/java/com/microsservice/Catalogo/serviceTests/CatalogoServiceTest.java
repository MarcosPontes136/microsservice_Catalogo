package com.microsservice.Catalogo.serviceTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.microsservice.Catalogo.dto.CatalogoDTO;
import com.microsservice.Catalogo.exceptions.NoProductsFoundException;
import com.microsservice.Catalogo.models.CatalogoModel;
import com.microsservice.Catalogo.models.CatalogoModelValidator;
import com.microsservice.Catalogo.repositories.CatalogoRepository;
import com.microsservice.Catalogo.service.CatalogoService;

import jakarta.validation.ValidationException;

@SpringBootTest
public class CatalogoServiceTest {	
	
	@InjectMocks
    private CatalogoService catalogoService;
    
    @Mock
    private CatalogoModelValidator catalogoModelValidator;
     
    @Mock
    private CatalogoRepository catalogoRepository;
    
    private CatalogoModel catalogoModel;
    
    

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        catalogoModel = new CatalogoModel();
        catalogoModel.setId(UUID.randomUUID());
        catalogoModel.setPrice(100.0);
        catalogoModel.setStatus("ATIVO");
        catalogoModel.setDiscounted("discounted");
        catalogoModel.setDiscount(0.1);
        catalogoModel.setName("Produto Teste");
        catalogoModel.setDescription("Descrição do Produto");
        catalogoModel.setImage("imagem.jpg".getBytes());
        
        
        List<CatalogoDTO> mockCatalogoDTOList = Arrays.asList(new CatalogoDTO(), new CatalogoDTO());
    }
    
    @Test
    void testSavedProductValid() throws UnsupportedEncodingException {
    	when(catalogoRepository.save(catalogoModel)).thenReturn(catalogoModel); 	
    	doNothing().when(catalogoModelValidator).validationModel(catalogoModel);
    	
    	CatalogoModel result = catalogoService.savedProduct(catalogoModel);
    	
    	assertNotNull(result);
    	assertEquals("Produto Teste", result.getName());
    	verify(catalogoRepository, times(1)).save(catalogoModel);
    	verify(catalogoModelValidator, times(1)).validationModel(result);	
    }
    
    @Test
    void testSavedProductValidationException() throws UnsupportedEncodingException {
    	doThrow(new ValidationException("Erro de validação")).when(catalogoModelValidator).validationModel(catalogoModel);
    	
        ValidationException thrown = assertThrows(ValidationException.class, () -> {
            catalogoService.savedProduct(catalogoModel);
        });

        assertEquals("Erro de validação", thrown.getMessage());
    }
    
    @Test
    void testGetAllProducts() {
    	when(catalogoRepository.findAll()).thenReturn(Arrays.asList(catalogoModel));
    	
    	List<CatalogoDTO> response = catalogoService.getAllProducts();
    	
        assertNotNull(response);      
        assertEquals(1, response.size());
        assertEquals("Produto Teste", response.get(0).getName());
        verify(catalogoRepository, times(1)).findAll();
    }
    
    @Test
    void testGetAllProductsNoProductsFoundException() {
    	when(catalogoRepository.findAll()).thenReturn(Arrays.asList());
    	
        NoProductsFoundException thrown = assertThrows(NoProductsFoundException.class, () -> {
            catalogoService.getAllProducts();
        });
    	
        assertEquals("Nenhum produto encontrado.", thrown.getMessage());
        verify(catalogoRepository, times(1)).findAll();
    }
    
    @Test
    void testSearchProduct() {
    	when(catalogoRepository.findByFilters("Produto Teste", 100.0)).thenReturn(Arrays.asList(catalogoModel));
    	
    	List<CatalogoModel> response = catalogoService.searchProduct("Produto Teste", 100.0);
    	
    	assertNotNull(response);
    	assertEquals(1, response.size());
    	assertEquals("Produto Teste", response.get(0).getName());
    	
    	verify(catalogoRepository, times(1)).findByFilters("Produto Teste", 100.0);
    }
    
    @Test
    void testSearchProductNoProductsFoundException() {
    	when(catalogoRepository.findByFilters("Produto Teste", 100.0)).thenReturn(Arrays.asList());
    	
        NoSuchElementException thrown = assertThrows(NoSuchElementException.class, () -> {
            catalogoService.searchProduct("Produto Inexistente", 100.0);
        });
    	
        assertEquals("Nenhum produto encontrado com: Name: Produto Inexistente, Price: 100.0", thrown.getMessage());

    	
    	verify(catalogoRepository, times(1)).findByFilters("Produto Inexistente", 100.0);
    }
    
    @Test
    void testRemoveProduct() {
    	
    	UUID productId = UUID.randomUUID(); 
    	
    	when(catalogoRepository.existsById(productId)).thenReturn(true);
    	doNothing().when(catalogoRepository).deleteById(productId);
    	
    	catalogoService.removeProduct(productId);
    	
    	verify(catalogoRepository, times(1)).deleteById(productId);
    	verify(catalogoRepository, times(1)).existsById(productId);
    }
    
    @Test
    void testRemoveProductNoProductsFoundException() {
    	
    	UUID productId = UUID.randomUUID(); 
    	
    	when(catalogoRepository.existsById(productId)).thenReturn(false);
    	
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            catalogoService.removeProduct(productId);
        });
    	
        assertNotNull(thrown);
        
        verify(catalogoRepository, times(1)).existsById(productId);
    	verify(catalogoRepository, times(0)).deleteById(productId);
    }

}
