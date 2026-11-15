package com.microsservice.Catalogo.adapter.output.buscarProduto.buscarPorNome;

import com.microsservice.Catalogo.domain.port.output.buscarProduto.buscarPorNome.IBuscarPorNomeAdapter;
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
public class BuscarPorNomeAdapter implements IBuscarPorNomeAdapter {
    private final CatalogoRepository catalogoRepository;

    @Override
    public Mono<List<CatalogoDTO>> buscarPorNome(String nome, Double preco) {
        try {
            log.info("[buscarPorNome] - Buscando Produto por nome {} ", nome);

            return catalogoRepository.findByFilters(nome, preco)
                    .map(CatalogoConverter::convertToDTO)
                    .collectList()
                    .doOnError(ex ->
                            log.error("Erro ao buscar produto {}", nome, ex)
                    );

        } catch (ResponseStatusException ex) {
            log.error("Erro ao buscar produto: {}", ex.getMessage());
            throw ex;

        } catch (Exception ex) {
            log.error("Erro inesperado ao buscar produtos: {}", ex.getMessage(), ex);
            throw new RuntimeException("Erro ao buscar produtos. Tente novamente mais tarde.", ex);
        }
    }
}
