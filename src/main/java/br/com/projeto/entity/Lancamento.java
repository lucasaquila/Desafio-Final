package br.com.projeto.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;

import org.hibernate.envers.Audited;
import org.springframework.data.jpa.domain.AbstractPersistable;
import com.sun.istack.internal.NotNull;
import com.sun.javafx.scene.control.skin.FXVK.Type;

@Entity
@Table(name = "lancamento")
@Audited
public class Lancamento extends AbstractPersistable<Long>{
	
	private String observacao;
	
	@Digits(integer=10, fraction=2)	
	@Column(nullable = false)
	private BigDecimal valor;
	
	@ManyToOne
	@JoinColumn(columnDefinition="integer", name="id_contabancaria", nullable = false)
	private ContaBancaria contaBancaria;
	
	@Enumerated(EnumType.STRING)
	private TipoLancamento tipoLancamento;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Calendar data;
	
	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public TipoLancamento getTipoLancamento() {
		return tipoLancamento;
	}

	public void setTipoLancamento(TipoLancamento tipoLancamento) {
		this.tipoLancamento = tipoLancamento;
	}
	
	@Override
	protected void setId(Long id) {
		super.setId(id);
	}

	public ContaBancaria getContaBancaria() {
		return contaBancaria;
	}

	public void setContaBancaria(ContaBancaria contaBancaria) {
		this.contaBancaria = contaBancaria;
	}

}
