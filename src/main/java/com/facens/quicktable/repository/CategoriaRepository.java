package com.facens.quicktable.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.facens.quicktable.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
