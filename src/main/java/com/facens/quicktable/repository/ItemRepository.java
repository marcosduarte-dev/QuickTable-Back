package com.facens.quicktable.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.facens.quicktable.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long>{

}
