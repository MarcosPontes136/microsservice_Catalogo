package com.microsservice.Catalogo.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.microsservice.Catalogo.models.CatalogoModel;

public interface CatalogoRepository  extends JpaRepository<CatalogoModel, UUID>{
	
    @Query("SELECT p FROM CatalogoModel p WHERE "
            + "(:name IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND "
            + "(:price IS NULL OR p.price = :price)")
	List<CatalogoModel> findByFilters(@Param("name") String name, @Param("price") Double price);
}
