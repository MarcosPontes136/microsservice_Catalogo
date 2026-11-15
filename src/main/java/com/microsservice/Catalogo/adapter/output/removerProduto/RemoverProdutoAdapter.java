package com.microsservice.Catalogo.adapter.output.removerProduto;

import com.microsservice.Catalogo.domain.model.CatalogoModelValidator;
import com.microsservice.Catalogo.domain.port.output.removerProduto.IRemoverProdutoAdapter;
import com.microsservice.Catalogo.repositories.CatalogoRepository;
import com.microsservice.Catalogo.response.ResponseApi;
import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class RemoverProdutoAdapter implements IRemoverProdutoAdapter {
    private final CatalogoRepository catalogoRepository;
    @Override
    public Mono<Map<String, Null>> removerProduto(UUID id) {
    	try {
            return catalogoRepository.existsById(id)
                    .flatMap(exists -> {
                        if (!exists) {
                            log.info("[removerProduto] - Produto com id {} não encontrado.", id);
                            return Mono.error(new IllegalArgumentException());
                        }

                        return catalogoRepository.deleteById(id)
                                .then(Mono.fromSupplier(() -> {
                                    Map<String, Null> map = new HashMap<>();
                                    map.put("Prduto removido com sucesso", null);
                                    return map;
                                }));
                    })
                    .doOnError(e -> log.info("[removerProduto] - Erro ao remover Produto {}", e.getMessage()));

		} catch (IllegalArgumentException e) {
			log.info("[removerProduto] - Erro ao remover Produto {}", e.getMessage());
			throw e;
		}
    }
}
