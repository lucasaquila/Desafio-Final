package br.com.projeto.configuracao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.directwebremoting.annotations.AnnotationsConfigurator;
import org.directwebremoting.annotations.DataTransferObject;
import org.directwebremoting.annotations.GlobalFilter;
import org.directwebremoting.annotations.RemoteProxy;
import org.directwebremoting.extend.Configurator;
import org.directwebremoting.extend.DwrConstants;
import org.directwebremoting.spring.DwrClassPathBeanDefinitionScanner;
import org.directwebremoting.spring.DwrController;
import org.directwebremoting.spring.DwrHandlerMapping;
import org.directwebremoting.spring.SpringConfigurator;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import br.com.projeto.controller.HomeController;
import br.com.projeto.controller.UsuarioController;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages ="br.com.projeto")
public class AppWebConfiguration extends WebMvcConfigurerAdapter {
	
	@Bean(name = "jspViewResolver")
	public InternalResourceViewResolver	internalResourceViewResolver() {
		InternalResourceViewResolver resolver =	new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		resolver.setViewClass(JstlView.class);
		return resolver;
	}
	
	@Bean
	public FormattingConversionService mvcConversionService() {
		DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService(true);
		DateFormatterRegistrar registrar = new DateFormatterRegistrar();
		registrar.setFormatter(new DateFormatter("yyyy-MM-dd"));
		registrar.registerFormatters(conversionService);
		return conversionService;
	}
	
	 @Override
	  public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	    registry.addResourceHandler("/assets/**").addResourceLocations("/assets/");
	    registry.addResourceHandler("/WEB-INF/**").addResourceLocations("/WEB-INF/");
	    registry.addResourceHandler("/partials/**").addResourceLocations("/partials/");
	  }
	 
	 @Bean
	 public MailSender mailSender() {
		 JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();
		 javaMailSenderImpl.setHost("smtp.gmail.com");
		 javaMailSenderImpl.setPassword("789amoedo");
		 javaMailSenderImpl.setPort(587);
		 javaMailSenderImpl.setUsername("lucas.aquila90@gmail.com");
		 Properties mailProperties = new Properties();
		 mailProperties.put("mail.smtp.starttls.enable", true);
		 mailProperties.put("mail.smtp.auth", true);
		 mailProperties.put("mail.smtp.starttls.enable", true);
		 javaMailSenderImpl.setJavaMailProperties(mailProperties);
		 return javaMailSenderImpl;
	 }
	
}

