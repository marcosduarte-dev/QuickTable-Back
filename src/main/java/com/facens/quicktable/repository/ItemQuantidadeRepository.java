package com.facens.quicktable.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.facens.quicktable.model.ItemQuantidade;

public interface ItemQuantidadeRepository extends JpaRepository<ItemQuantidade, Long>{
	@Query("SELECT iq FROM ItemQuantidade iq WHERE iq.pedido.id = :pedidoId")
	public List<ItemQuantidade> findAllByIdPedido(@Param("pedidoId") long pedidoId);
}
