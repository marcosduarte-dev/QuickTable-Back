package com.facens.quicktable.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.facens.quicktable.enums.StatusPedido;
import com.facens.quicktable.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{
	@Query("SELECT p FROM pedido p WHERE p.nome_cliente = :nomeCliente AND p.reserva.data_fechamento IS NULL")
	public List<Pedido> getAllByNomeCliente(@Param("nomeCliente") String nomeCliente);
	
	@Query("SELECT p FROM pedido p WHERE p.reserva.id = :idReserva")
	public List<Pedido> getAllByIdReserva(@Param("idReserva") Long idReserva);
	
	@Query(nativeQuery=true, value ="SELECT p.* FROM pedido p WHERE p.status = '0'")
	public List<Pedido> getAllPedidosStatus(@Param("status") StatusPedido status);
}
