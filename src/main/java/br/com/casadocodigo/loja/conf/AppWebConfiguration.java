package br.com.casadocodigo.loja.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
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

}
