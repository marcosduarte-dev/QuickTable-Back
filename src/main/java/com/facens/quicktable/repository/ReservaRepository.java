package com.facens.quicktable.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.facens.quicktable.model.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Long>{
	@Query("SELECT iq FROM Reserva iq WHERE iq.mesa.id = :mesaId AND iq.data_fechamento IS NULL ORDER BY iq.data_abertura DESC")
	public List<Reserva> findAllByIdMesa(@Param("mesaId") long mesaId);
}
