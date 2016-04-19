package br.com.projeto.configuracao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;

import br.com.projeto.service.UsuarioLogadoDetailService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UsuarioLogadoDetailService service;
	
    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("lucas-aquila@outlook.com").password("123456").roles("ADMINISTRADOR");
        auth.inMemoryAuthentication().withUser("usuario@usuario.com").password("123456").roles("USUARIO");
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/webjars/**").permitAll()
			.antMatchers("/assets/**").permitAll()
			.antMatchers("/WEB-INF/**").permitAll()
			.antMatchers("/css/**").permitAll()
			.antMatchers("/js/**").permitAll()
			.antMatchers("/jspl/**").permitAll()
			.antMatchers("/usuario/form").hasRole("ADMINISTRADOR")
			.antMatchers("/contaBancaria/form").hasRole("ADMINISTRADOR")
			.antMatchers("/usuario/").hasRole("USUARIO")
			.antMatchers("/usuario/").hasRole("ADMINISTRADOR")
			.anyRequest().authenticated()
		.and()
			.formLogin()
			.loginPage("/login")
			.loginProcessingUrl("/j_spring_security_check")
			.usernameParameter("username")
			.passwordParameter("password")
			.permitAll()
			.defaultSuccessUrl("/#/usuario", true)
			.failureUrl("/login?error=true")
		.and()
			.exceptionHandling().accessDeniedPage("/denied")
		.and()
		.logout().logoutSuccessUrl("/login?logout=true")
		.and()
			.addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(service).passwordEncoder(new BCryptPasswordEncoder());
	}
	
}
