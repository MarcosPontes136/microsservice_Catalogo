package com.microsservice.Catalogo.controllerTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.microsservice.Catalogo.controller.CatalogoController;
import com.microsservice.Catalogo.dto.CatalogoDTO;
import com.microsservice.Catalogo.models.CatalogoModel;
import com.microsservice.Catalogo.response.ResponseApi;
import com.microsservice.Catalogo.service.CatalogoService;

import jakarta.validation.constraints.Null;

@SpringBootTest
public class CatalogoControllerTests {
	
    @Mock
    private CatalogoService catalogoService;

    @InjectMocks
    private CatalogoController catalogoController;

    private CatalogoModel mockCatalogoModel;
    
    private List<CatalogoDTO> mockCatalogoDTOList;
    
    private List<CatalogoModel> mockCatalogoModelList;
    
    private ResponseApi<CatalogoModel> expectedResponse;
    
    private ResponseApi<List<CatalogoDTO>> expectedResponseGet;
    
    private ResponseApi<List<CatalogoModel>> expectedResponseGetNamePrice;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        
        mockCatalogoModel = new CatalogoModel();
        
        mockCatalogoDTOList = Arrays.asList(new CatalogoDTO(), new CatalogoDTO());
        mockCatalogoModelList = Arrays.asList(new CatalogoModel(), new CatalogoModel());

        expectedResponse = new ResponseApi<>("Produto criado com sucesso", mockCatalogoModel, true);       
        expectedResponseGetNamePrice = new ResponseApi<>("Produtos encontrados com sucesso", mockCatalogoModelList, true); 
        expectedResponseGet = new ResponseApi<>("Produtos encontrados com sucesso", mockCatalogoDTOList, true);
    }

    @Test
    void testCreateProduct() throws UnsupportedEncodingException {

        when(catalogoService.savedProduct(any(CatalogoModel.class))).thenReturn(mockCatalogoModel);


        ResponseEntity<ResponseApi<CatalogoModel>> response = catalogoController.createProduct(mockCatalogoModel);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());

        verify(catalogoService, times(1)).savedProduct(any(CatalogoModel.class));
    }
    
    @Test
    void testGetAllProducts() {
    	when(catalogoService.getAllProducts()).thenReturn(mockCatalogoDTOList);
    	
    	ResponseEntity<ResponseApi<List<CatalogoDTO>>> response = catalogoController.getAllProducts();
    	
        assertNotNull(response);      
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponseGet, response.getBody());
        verify(catalogoService, times(1)).getAllProducts();
    }
    
    @Test
    void testSearchProduct() throws UnsupportedEncodingException {
        when(catalogoService.searchProduct(eq("Cafe"), eq(123.0))).thenReturn(mockCatalogoModelList);

        ResponseEntity<ResponseApi<List<CatalogoModel>>> response = catalogoController.searchProduct("Cafe", 123.0);
        
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponseGetNamePrice, response.getBody());
        verify(catalogoService, times(1)).searchProduct("Cafe", 123.0);
    }
    
    @Test
    void testRemoveProductId() throws UnsupportedEncodingException {
    	
    	UUID id = UUID.randomUUID();
    	
    	doNothing().when(catalogoService).removeProduct(id);
    	
    	ResponseEntity<ResponseApi<Map<String, Null>>> response = catalogoController.removeProductId(id);

        assertNotNull(response);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        ResponseApi<Map<String, Null>> body = response.getBody();
        assertNotNull(body);
        assertEquals("Produto removido com sucesso", body.getMessage());  // Verificar a mensagem
        assertTrue(body.isSuccess());
        assertNull(body.getData());
        verify(catalogoService, times(1)).removeProduct(id);
    }
    
}
