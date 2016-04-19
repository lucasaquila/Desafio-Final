package br.com.projeto.entity;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.security.core.GrantedAuthority;

public enum TipoUsuario{
	ROLE_USUARIO, 
	ROLE_ADMINISTRADOR;
}


