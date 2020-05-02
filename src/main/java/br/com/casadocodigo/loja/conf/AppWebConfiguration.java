package br.com.casadocodigo.loja.conf;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import br.com.casadocodigo.loja.controllers.HomeController;
import br.com.casadocodigo.loja.dao.ProdutoDAO;

@EnableWebMvc // Habilitar o uso do spring mvc
@ComponentScan(basePackageClasses = { HomeController.class, ProdutoDAO.class }) // Queremos que o spring saiba qual é a classe do
																// controller. Queremos que isso seja dinamico -
																// autocomplete
public class AppWebConfiguration {//Classe específica de configuracao para nossa parte web

	@Bean //Diz pro spring que ele vai gerenciar esse metodo
	public InternalResourceViewResolver internalResourceViewResolver() { // Precisamos falar onde está a nossa view
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
		ms.setBasename("/WEB-INF/message");
		ms.setDefaultEncoding("UTF-8");
		ms.setCacheSeconds(1);
		
		return ms;
	}
	
	@Bean
	public FormattingConversionService mvcConversionService() {
		
		DefaultFormattingConversionService cs = new DefaultFormattingConversionService();
		
		DateFormatterRegistrar registrar = new DateFormatterRegistrar();
		registrar.setFormatter(new DateFormatter("dd/MM/yyyy"));
		registrar.registerFormatters(cs);
		
		return cs;
	}

}
