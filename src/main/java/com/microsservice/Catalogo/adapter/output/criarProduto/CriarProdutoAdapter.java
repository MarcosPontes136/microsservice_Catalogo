package com.microsservice.Catalogo.adapter.output.criarProduto;

import com.microsservice.Catalogo.domain.port.output.criarProduto.ICriarProdutoAdapter;
import com.microsservice.Catalogo.domain.model.CatalogoModel;
import com.microsservice.Catalogo.domain.model.CatalogoModelValidator;
import com.microsservice.Catalogo.repositories.CatalogoRepository;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@Service
public class CriarProdutoAdapter implements ICriarProdutoAdapter {
    private final CatalogoRepository catalogoRepository;
    private final CatalogoModelValidator catalogoModelValidator;
    @Override
    public Mono<CatalogoModel> salvarProduto (CatalogoModel catalogoModel) {
        try {

            log.info("[criarProduto] - Tentando montar Produto: {}", catalogoModel.getName());
            catalogoModelValidator.validationModel(catalogoModel);

        } catch (ValidationException e) {
            log.error("Erro de validação: {}", e.getMessage());
            throw e;

        } catch (Exception e) {
            log.error("Erro ao criar Produto! {}", e.getMessage());
            throw e;
        }

        log.info("Produto Criado! {" + catalogoModel.getName() + "}");
        return catalogoRepository.save(catalogoModel);
    }
}
