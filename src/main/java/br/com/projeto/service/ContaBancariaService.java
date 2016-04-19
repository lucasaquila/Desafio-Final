package br.com.projeto.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Service;

import br.com.projeto.entity.ContaBancaria;
import br.com.projeto.entity.Erro;
import br.com.projeto.entity.Usuario;
import br.com.projeto.entity.UsuarioLogado;
import br.com.projeto.repository.ContaBancariaRepository;

@Service
public class ContaBancariaService {
	
	@Autowired
	private ContaBancariaRepository repository;
	
	public ContaBancaria findById(Long id, SecurityContextHolderAwareRequestWrapper request)
	{
		boolean roleAdministrador = request.isUserInRole("ROLE_ADMINISTRADOR");
		if(roleAdministrador == true)
		{
			return repository.findOne(id);	
		}
		else
		{
			UsuarioLogado user = (UsuarioLogado)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			return repository.findOneByUser(user.getId(), id);
		}
	}
	
	public List<ContaBancaria> findAll(){
		return repository.findAll();	
	}

	public List<ContaBancaria> findAll(SecurityContextHolderAwareRequestWrapper request){
		
		boolean roleAdministrador = request.isUserInRole("ROLE_ADMINISTRADOR");
		if(roleAdministrador == true)
		{
			return repository.findAll();	
		}
		else
		{
			UsuarioLogado user = (UsuarioLogado)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			return repository.findByUsuario(user.getId());
		}
	}
	
	public ResponseEntity<?> save(ContaBancaria contaBancaria){
		try {
			repository.save(contaBancaria);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
		}
	}
	
	public void inserirSaldoInicial(Long id, BigDecimal saldo) {
		ContaBancaria conta = this.repository.findOne(id);
		if (conta.getSaldo().compareTo(BigDecimal.ZERO) <= 0)
		{
			repository.inserirSaldoInicial(id, saldo);
		}
	}
	
	public ContaBancaria updateContaBancaria(ContaBancaria contaBancaria) {
		return repository.save(contaBancaria);
	}
	
	public ResponseEntity<?> deleteContaBancaria(Long id){
		Boolean existeLancamento = repository.existeLancamento(id);
		if(existeLancamento == true)
		{
			Erro erro = new Erro();
			erro.setMensagem("Impossível excluir esta conta, pois ela já possui lançamentos.");
			return new ResponseEntity<>(erro,HttpStatus.CONFLICT);
		}
		else
		{
			repository.delete(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}
}
