package br.com.projeto.service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.render.ResponseStateManager;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.projeto.entity.ContaBancaria;
import br.com.projeto.entity.Erro;
import br.com.projeto.entity.Lancamento;
import br.com.projeto.entity.Transferencia;
import br.com.projeto.entity.Usuario;
import br.com.projeto.entity.UsuarioLogado;
import br.com.projeto.repository.ContaBancariaRepository;
import br.com.projeto.repository.LancamentoRepository;
import junit.framework.Assert;

@Service
public class LancamentoService {

	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Autowired 
	private ContaBancariaRepository contaBancariaRepository; 
	
	@Autowired
	private JavaMailSender mailSender; 
	
	
	public Lancamento findById(Long id, SecurityContextHolderAwareRequestWrapper request)
	{
		boolean roleAdministrador = request.isUserInRole("ROLE_ADMINISTRADOR");
		if(roleAdministrador == true)
		{
			return lancamentoRepository.findOne(id);	
		}
		else
		{
			UsuarioLogado user = (UsuarioLogado)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

			return lancamentoRepository.findOneByUser(user.getId(), id);
		}
		
	}
	
	public List<Lancamento> findByDate(Calendar dataInicio, Calendar dataFim, SecurityContextHolderAwareRequestWrapper request){
		
		boolean roleAdministrador = request.isUserInRole("ROLE_ADMINISTRADOR");
		if(roleAdministrador == true)
		{
			return lancamentoRepository.findByData(dataInicio, dataFim);	
		}
		else
		{
			UsuarioLogado user = (UsuarioLogado)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			return lancamentoRepository.findByDataUsuario(dataInicio, dataFim, user.getId());
		}
	}
	
	public Lancamento efetuarDeposito(Lancamento lancamento){
		Long id = lancamento.getContaBancaria().getId();
		ContaBancaria contaBancaria = contaBancariaRepository.findOne(id);
		BigDecimal saldoAtual = contaBancaria.getSaldo().add(lancamento.getValor());
		contaBancaria.setSaldo(saldoAtual);
		contaBancariaRepository.save(contaBancaria);
		return lancamentoRepository.save(lancamento);
	}
	
	public ResponseEntity<?> efetuarSaque(Lancamento lancamento) throws MessagingException{
		Long id = lancamento.getContaBancaria().getId();
		ContaBancaria contaBancaria = contaBancariaRepository.findOne(id);
		BigDecimal saldoAtual = contaBancaria.getSaldo().subtract(lancamento.getValor());
		if(saldoAtual.signum() == -1){
			mailNotificaSaldoNegativo(contaBancaria);
	    }
		
		contaBancaria.setSaldo(saldoAtual);
		contaBancariaRepository.save(contaBancaria);
		lancamentoRepository.save(lancamento);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	public ResponseEntity<?> efetuarTransferencia(Transferencia transferencia){
		try {
			efetuarDeposito(transferencia.getEntrada());
			efetuarSaque(transferencia.getSaida());
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e);
		}
	}

	private void mailNotificaSaldoNegativo(ContaBancaria contaBancaria) throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setSubject("Notificação de Saldo Negativo");
		helper.setTo(contaBancaria.getUsuario().getEmail());
		String valor = String.format("{0:C}", contaBancaria.getSaldo());
		helper.setText("<html><body><h1 style='color:rgb(33,150,243)'><strong>Notificação de Saldo Negativo - Sistema Desafio EITS</strong></h1>" 
				+"<h3>Olá " + contaBancaria.getUsuario().getNome() +", sua conta bancária está com o saldo negativo.</h3>"
				+"<p><strong>Dados da Conta Bancária:</strong><br>"
				+"Nr. Conta: " + contaBancaria.getNumero() + "<br>"
				+"Banco: " + contaBancaria.getBanco() + "<br>"
				+"Agência: " + contaBancaria.getAgencia() + "<br>"
				+"Saldo: " + contaBancaria.getSaldo() + "</p>"
				+"</body></html>", true);
		mailSender.send(message);
	}
	
	



	
}
