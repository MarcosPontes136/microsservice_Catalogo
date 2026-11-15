package com.microsservice.Catalogo.adapter.output.buscarProduto.buscarTodos;

import com.microsservice.Catalogo.domain.port.output.buscarProduto.buscarTodos.IBuscarTodosAdapter;
import com.microsservice.Catalogo.dto.CatalogoDTO;
import com.microsservice.Catalogo.repositories.CatalogoRepository;
import com.microsservice.Catalogo.utility.CatalogoConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class BuscarTodosAdapter implements IBuscarTodosAdapter {
    private final CatalogoRepository catalogoRepository;

    @Override
    public Mono<List<CatalogoDTO>> buscarTodos() {
        try {
            log.info("[buscarTodos] - Buscando todos os Produtos!");

            return catalogoRepository.findAll()
                    .map(CatalogoConverter::convertToDTO)
                    .collectList()
                    .doOnError(ex ->
                            log.error("Erro ao buscar todos!", ex)
                    );

        } catch (ResponseStatusException ex) {
            log.error("Erro ao buscar produtos: {}", ex.getMessage());
            throw ex;

        } catch (Exception ex) {
            log.error("Erro inesperado ao buscar produtos: {}", ex.getMessage(), ex);
            throw new RuntimeException("Erro ao buscar produtos. Tente novamente mais tarde.", ex);
        }
    }
}
