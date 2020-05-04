package br.com.casadocodigo.loja.controllers;

import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.model.CarrinhoCompras;
import br.com.casadocodigo.loja.model.DadosPagamento;

@RequestMapping("/pagamento")
@Controller
public class PagamentoController {
	
	@Autowired
	private CarrinhoCompras carrinho;
	
	@Autowired
	private RestTemplate restTemplate;
	
	//Em um escopo de sessao, nao precisamos receber os itens como parametro
	@RequestMapping(value="/finalizar", method=RequestMethod.POST)
	public Callable<ModelAndView> finalizar(RedirectAttributes model)
	{		
		//queremos enviar os dados para o servidor acessando a url de dentro da aplicacao
		//a classe RestTemplate entao realiza um request para dentro dessa uri
		//tornando o retorno Callable, se torna assincrono e apenas um "usuario" fica aguardando o retorno da url
		//como pe uma classe anonima, vamos usar a sintaxe do java 8 referenciando-se a classes anonimas
		
		return() ->{
			
			String uri = "https://book-payment.herokuapp.com/payment";
			
			//Quanto o tomcat entra aqui, ele fica com uma unica thread esperando que a execucao aconteca, e isso é ruim, pode deixar nossa aplciacao lenta e travada
			//O ideal seria que o servidor se comportasse de maneira assincrona
			try {
				String response = restTemplate.postForObject(uri, new DadosPagamento(carrinho.getTotal()), String.class);
				System.out.println(response);
				model.addFlashAttribute("sucesso", response);
				return new ModelAndView("redirect:/produtos");
				
			}catch(HttpClientErrorException e)
			{
				e.printStackTrace();
				System.out.println();
				model.addFlashAttribute("falha", "Valor maior que o permitido");
				return new ModelAndView("redirect:/produtos");
			}
		};
				
	}
}
