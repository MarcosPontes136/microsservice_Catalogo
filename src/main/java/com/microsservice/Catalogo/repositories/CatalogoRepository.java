package com.microsservice.Catalogo.repositories;

import com.microsservice.Catalogo.domain.model.CatalogoModel;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.util.UUID;

public interface CatalogoRepository  extends ReactiveCrudRepository<CatalogoModel, UUID> {
	
    @Query("""
            SELECT *
            FROM tb_catalogo
            WHERE (:name IS NULL OR LOWER(name) LIKE LOWER(CONCAT('%', :name, '%')))
            AND (:price IS NULL OR price = :price)
    """)
    Flux<CatalogoModel> findByFilters(String name, Double price);
}
