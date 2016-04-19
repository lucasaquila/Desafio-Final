package br.com.projeto.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.projeto.entity.Banco;
import br.com.projeto.entity.ContaBancaria;
import br.com.projeto.entity.TipoUsuario;
import br.com.projeto.entity.Usuario;
import br.com.projeto.entity.UsuarioLogado;
import br.com.projeto.service.ContaBancariaService;
import br.com.projeto.service.UsuarioService;

@Controller
@Transactional
@RestController
@RequestMapping("/contaBancaria")
public class ContaBancariaController {

	@Autowired
	private ContaBancariaService contaBancariaService;

	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView modelAndView = new ModelAndView("contaBancaria/list");
		return modelAndView;
	}

	@PreAuthorize("hasRole('ROLE_ADMINISTRADOR')")
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public ModelAndView form(){
		ModelAndView modelAndView =	new ModelAndView("contaBancaria/form");
		return modelAndView;
	}

	@RequestMapping(value = "/detalhes", method = RequestMethod.GET)
	public ModelAndView detalhes(){
		ModelAndView modelAndView =	new ModelAndView("contaBancaria/detalhes");
		return modelAndView;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMINISTRADOR')")
	@RequestMapping(value = "/saldoInicial", method = RequestMethod.GET)
	public ModelAndView saldoInicial(){
		ModelAndView modelAndView =	new ModelAndView("contaBancaria/saldoInicial");
		return modelAndView;
	}
	
	@RequestMapping("/getContasBancariasByRole")
	public List<ContaBancaria> getContasBancarias(SecurityContextHolderAwareRequestWrapper request){
		List<ContaBancaria> contasBancarias = contaBancariaService.findAll(request);
		return contasBancarias;
	}

	@RequestMapping("/getContasBancarias")
	public List<ContaBancaria> getContasBancarias(){
		List<ContaBancaria> contasBancarias = contaBancariaService.findAll();
		return contasBancarias;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ContaBancaria getContaBancaria(@PathVariable("id") long id, SecurityContextHolderAwareRequestWrapper request) {
		ContaBancaria contaBancaria = contaBancariaService.findById(id, request);
		return contaBancaria;
	}

	@RequestMapping("/getBancos")
	public List<Banco> getBancos()
	{
		List<Banco> banco = Arrays.asList(Banco.values());
		return banco;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<?> save(@Valid @RequestBody ContaBancaria contaBancaria, BindingResult result){
		if (result.hasErrors()) {
			List<String> mensagens = new ArrayList<>();
			List<FieldError> errors = result.getFieldErrors();
			for (FieldError error : errors ) {
				String mensagem = error.getField() + " - " + error.getDefaultMessage();
				mensagens.add(mensagem);
			}
			return new ResponseEntity<>(mensagens,HttpStatus.CONFLICT);
		}
		return contaBancariaService.save(contaBancaria);
	}

	@PreAuthorize("hasRole('ROLE_ADMINISTRADOR')")
	@RequestMapping(value = "/saldoInicial/{id}", method = RequestMethod.PUT)
	public BigDecimal saldoInicial(@PathVariable("id") long id, @RequestBody BigDecimal saldo) {
		contaBancariaService.inserirSaldoInicial(id, saldo);
		return saldo;
	}

	@PreAuthorize("hasRole('ROLE_ADMINISTRADOR')")
	@RequestMapping(value = "/editar/{id}", method = RequestMethod.PUT)
	public ContaBancaria editar(@PathVariable("id") long id, @RequestBody ContaBancaria contaBancaria) {
		UsuarioLogado user = (UsuarioLogado)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String tipo = user.getTipoUsuario().toString();
		contaBancariaService.updateContaBancaria(contaBancaria);
		return contaBancaria;
	}

	@PreAuthorize("hasRole('ROLE_ADMINISTRADOR')")
	@RequestMapping(value = "/excluir/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?>  excluir(@PathVariable("id") long id){
		return contaBancariaService.deleteContaBancaria(id);
	}

}
