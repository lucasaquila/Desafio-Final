package br.com.projeto.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.apache.tomcat.util.net.jsse.openssl.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sun.org.apache.xpath.internal.operations.Bool;

import br.com.projeto.entity.Erro;
import br.com.projeto.entity.Usuario;
import br.com.projeto.entity.UsuarioLogado;
import br.com.projeto.repository.UsuarioRepository;
import junit.framework.Assert;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private MailSender mailer;
	
	@Autowired
	private JavaMailSender mailSender; 
	
	public ResponseEntity<?> save(Usuario usuario) throws MessagingException
	{
		String hash = new BCryptPasswordEncoder().encode(usuario.getPassword());
		usuario.setPasswordEdit(usuario.getPassword());
		usuario.setPassword(hash);
		if(repository.existsUsuarioByEmail(usuario.getEmail()))
		{
			Erro erro = new Erro();
			erro.setMensagem("E-mail já cadastrado, insira um outro e-mail.");
			return new ResponseEntity<>(erro,HttpStatus.CONFLICT);
		}
		else
		{
			repository.save(usuario);
			sendNewPurchaseMail(usuario);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}
	
	public List<Usuario> findAll(SecurityContextHolderAwareRequestWrapper request)
	{
		boolean roleAdministrador = request.isUserInRole("ROLE_ADMINISTRADOR");
		if(roleAdministrador == true)
		{
			return repository.findAll();	
		}
		else
		{
			UsuarioLogado user = (UsuarioLogado)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Usuario usuario = repository.findByEmail(user.getUsername());
			List<Usuario> usuarios = new ArrayList<Usuario>();
			usuarios.add(usuario);
			return usuarios;
		}
	}
	
	public Usuario findByEmail(String email) {
		return repository.findByEmail(email);
	}
	
	public Usuario findById(Long id) {
		return repository.findOne(id);
	}
	
	public void deleteUsuario(Long id){
		repository.delete(id);
	}
	
	public void updateUser(Boolean situacao, Long id) {
		repository.changeSituacao(situacao, id);
	}
	
	public Usuario updateUsuario(Usuario usuario) {
		String novoPassword = usuario.getPasswordEdit();
		if(novoPassword != null && !novoPassword.isEmpty()){
			String hash = new BCryptPasswordEncoder().encode(usuario.getPasswordEdit());
			usuario.setPassword(hash);	
		}
		return repository.save(usuario);
	}
	
	private void sendNewPurchaseMail(Usuario usuario) throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setSubject("Bem-vindo ao Sistema Desafio EITS");
		helper.setTo(usuario.getEmail());

		helper.setText("<html><body><h1 style='color:rgb(33,150,243)'><strong>Bem-vindo ao Sistema - Desafio EITS</strong></h1>" 
				+"<h3>Olá " + usuario.getNome() +", seu cadastro foi criado com sucesso.</h3>"
				+"<p>Acesse o sistema com os seguintes dados:<br>"
				+"<strong>E-mail: " + usuario.getEmail() + "</strong><br>"
				+"<strong>Senha: " + usuario.getPasswordEdit() + "</p></strong>"
				+ "</body></html>", true);
		mailSender.send(message);
	}
	
}
