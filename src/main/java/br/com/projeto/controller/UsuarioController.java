package br.com.projeto.controller;


import java.util.List;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.projeto.entity.TipoUsuario;
import br.com.projeto.entity.Usuario;
import br.com.projeto.service.UsuarioService;


@Controller
@Transactional
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public ModelAndView form(@ModelAttribute("usuario") Usuario usuario){
		ModelAndView modelAndView =	new ModelAndView("usuario/form");
		modelAndView.addObject("tipos", TipoUsuario.values());
		return modelAndView;
	}

	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView modelAndView = new ModelAndView("usuario/list");
		return modelAndView;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMINISTRADOR')")    
	@RequestMapping(value = "/editar", method = RequestMethod.GET)
	public ModelAndView alterar(){
		ModelAndView modelAndView =	new ModelAndView("usuario/editar");
		return modelAndView;
	}

	@RequestMapping("/listagem")
	public List<Usuario> getUsuarios(SecurityContextHolderAwareRequestWrapper request){
		List<Usuario> usuarios =usuarioService.findAll(request);
		return usuarios;
	}
		
	@PreAuthorize("hasRole('ROLE_ADMINISTRADOR')")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<?> save(@RequestBody Usuario usuario) throws MessagingException{
        return usuarioService.save(usuario);
	}

	@PreAuthorize("hasRole('ROLE_ADMINISTRADOR')")
    @RequestMapping(value = "/alteraSituacao/{id}", method = RequestMethod.PUT)
    public Usuario updateUsuario(@PathVariable("id") long id, @RequestBody Usuario usuario) {
        usuarioService.updateUser(usuario.getSituacao(), id);
        return usuario;
    }
	
	@PreAuthorize("hasRole('ROLE_ADMINISTRADOR')")
    @RequestMapping(value = "/editar/{id}", method = RequestMethod.PUT)
    public Usuario editar(@PathVariable("id") long id, @RequestBody Usuario usuario) {
        usuarioService.updateUsuario(usuario);
        return usuario;
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Usuario getUsuario(@PathVariable("id") long id) {
        Usuario usuario = usuarioService.findById(id);
        return usuario;
    }
    
    @PreAuthorize("hasRole('ROLE_ADMINISTRADOR')")
	@RequestMapping(value = "/excluirUsuario/{id}", method = RequestMethod.DELETE)
	public Usuario excluir(@PathVariable("id") long id){
		Usuario usuario = usuarioService.findById(id);
		usuarioService.deleteUsuario(id);
		return usuario;
	}
	
}
