package br.com.projeto.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.projeto.entity.Usuario;
import br.com.projeto.entity.UsuarioLogado;

@Service
public class UsuarioLogadoDetailService implements UserDetailsService{

	private static final Logger LOG = Logger.getLogger(UsuarioLogadoDetailService.class);

	@Autowired
	private UsuarioService service;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Usuario usuario = new Usuario();

		try {
			usuario = service.findByEmail(username);
			LOG.info("Usuário encontrado: (" + username + ").");
		} catch (Exception e) {
			LOG.error("Usuário NÃO encontrado: (" + username + ").");
			throw new UsernameNotFoundException("Usuário " + username + " não encontrado");
		}
		return new UsuarioLogado(usuario);
	}

}
