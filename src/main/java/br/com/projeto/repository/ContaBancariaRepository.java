package br.com.projeto.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.projeto.entity.ContaBancaria;

public interface ContaBancariaRepository extends JpaRepository<ContaBancaria, Long>{

	@Modifying(clearAutomatically = true)
	@Query("update ContaBancaria c set c.saldo =:saldo where c.id =:id")
	void inserirSaldoInicial(@Param("id") Long id, @Param("saldo") BigDecimal saldo);
	
	@Query("Select c from ContaBancaria c where c.usuario.id = :id")
	List<ContaBancaria> findByUsuario(@Param("id") Long id);
	
    @Query("SELECT CASE WHEN COUNT(l) > 0 THEN true ELSE false END FROM Lancamento l WHERE l.contaBancaria.id = :id")
    Boolean existeLancamento(@Param("id") Long id);
	
    @Query("Select c from ContaBancaria c where c.id = :id AND c.usuario.id = :idUsuario")
    ContaBancaria findOneByUser(@Param("idUsuario") Long idUsuario, @Param("id") Long id);
    
}
