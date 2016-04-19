package br.com.projeto.repository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.transaction.Transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.projeto.entity.ContaBancaria;
import br.com.projeto.entity.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>{

	@Query("Select l "
			+ "from Lancamento l "
			+ "where (l.data >= :dataInicio OR cast(:dataInicio, date) = null) and "
			+ "(l.data <= :dataFim OR cast(:dataFim, date) = null)")
	List<Lancamento> findByData(@Param("dataInicio") Calendar dataInicio,@Param("dataFim") Calendar dataFim);
	
	@Query("Select l "
			+ "from Lancamento l "
			+ "where "
			+ "(l.data >= :dataInicio OR cast(:dataInicio, date) = null) and "
			+ "(l.data <= :dataFim OR cast(:dataFim, date) = null) and"
			+ "(l.contaBancaria.usuario.id = :id)")
	List<Lancamento> findByDataUsuario(@Param("dataInicio") Calendar dataInicio,@Param("dataFim") Calendar dataFim, @Param("id") Long id);
	
	@Query("Select l from Lancamento l where l.id = :id AND l.contaBancaria.usuario.id = :idUsuario")
    Lancamento findOneByUser(@Param("idUsuario") Long idUsuario, @Param("id") Long id);
}
