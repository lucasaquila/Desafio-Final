import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import br.com.projeto.repository.UsuarioRepository;
import br.com.projeto.service.UsuarioService;

@Configuration
public class AppConfig {
	@Bean
	public UsuarioService getUsuarioService() {
		return new UsuarioService();
	}
	

}
