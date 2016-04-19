package br.com.projeto.entity;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class UsuarioLogado extends User {
	
	private Usuario usuario;

	public UsuarioLogado(Usuario usuario) {
		super(
				usuario.getEmail(), 
				usuario.getPassword(),
				AuthorityUtils.createAuthorityList(usuario.getTipoUsuario().toString()));
		this.usuario = usuario;
	}
	
	public Long getId(){
		return usuario.getId();
	}
	
	public TipoUsuario getTipoUsuario(){
		return usuario.getTipoUsuario();
	}

}
