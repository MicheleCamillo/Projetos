package br.com.casadocodigo.loja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.dao.ProdutoDAO;
import br.com.casadocodigo.loja.model.Produto;
import br.com.casadocodigo.loja.model.TipoPreco;

@Controller
public class ProdutosController {
	
	@Autowired
	private ProdutoDAO produtoDAO;
	
	@RequestMapping("/produtos/form")
	public ModelAndView from(){
		
		ModelAndView modelAndView = new ModelAndView("produtos/form");//calsse que tem uma propriedade de mandar um objeto do model para a view "produtos/form", declarada no construtor
		modelAndView.addObject("tipos", TipoPreco.values());//estou mandando a lista de enuns do meu model para a variavel tipos da minha view
		
		return modelAndView;
	}
	
	@RequestMapping("/produtos")
	public String grava(Produto produto) {
		System.out.println(produto);
		produtoDAO.gravar(produto);
		
		return "produtos/ok";
	}

}
