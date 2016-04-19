import javax.mail.MessagingException;
import javax.sql.DataSource;
import javax.validation.constraints.AssertTrue;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import br.com.projeto.configuracao.AppWebConfiguration;
import br.com.projeto.configuracao.JPAConfiguration;
import br.com.projeto.configuracao.SecurityConfiguration;
import br.com.projeto.entity.ContaBancaria;
import br.com.projeto.entity.TipoUsuario;
import br.com.projeto.entity.Usuario;
import br.com.projeto.repository.UsuarioRepository;
import br.com.projeto.service.UsuarioService;
import br.com.projeto.test.config.DataSourceConfig;
import org.junit.Assert;
import org.mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JPAConfiguration.class}, loader = AnnotationConfigContextLoader.class)
@ActiveProfiles(profiles = "test")
@WebAppConfiguration
public class UsuarioServiceTest{

	@Configuration
	static class ContextConfiguration {
		@Bean
		public UsuarioService usuarioService(){
			UsuarioService usuarioService = new UsuarioService();
			return usuarioService;
		}
	}
	
	private UsuarioService usuarioService;
	
	private UsuarioRepository UsuarioRepository;
	

	
	@BeforeClass
	public static void setUp() {
		System.out.println("-----> SETUP <-----");
	}
	
	@Test
	public void testSave() throws MessagingException {
		System.out.println("Funcionou");
		Usuario usuario = new Usuario();
		usuario.setNome( "Teste Unitário" );
		usuario.setEmail( "abccs@outlook.com" );
		usuario.setPassword( "123456" );
		usuario.setSituacao(true);
		usuario.setTipoUsuario(TipoUsuario.ROLE_USUARIO);
		ResponseEntity<?> response = usuarioService.save(usuario);
		System.out.println("passou no teste");
	}
	
/*	@Test
	public void testGet(){
		Usuario usuario = usuarioService.findById((long)38);
	}*/
	
}
