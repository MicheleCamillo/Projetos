package br.com.casadocodigo.loja.conf;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class ServletSpringMVC extends AbstractAnnotationConfigDispatcherServletInitializer { //essa classe que estamos herdando já inicializa o servlet do spring

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() { //Queremos que o spring conheca a classe de configuração do nosso projeto web
		return new Class[] {AppWebConfiguration.class, JPAConfiguration.class};
	}

	@Override
	protected String[] getServletMappings() {//Queremos que o spring mapeie a partir do / pra frente
			return new String[] {"/"};
	} 
	
}
