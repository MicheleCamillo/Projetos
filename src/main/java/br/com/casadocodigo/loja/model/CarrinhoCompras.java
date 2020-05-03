package br.com.casadocodigo.loja.model;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value=WebApplicationContext.SCOPE_SESSION) //Estamos informando ao spring que podemos ter mais de uma sessao - uma instancia dessa classe por usuario
public class CarrinhoCompras {

	private Map<CarrinhoItem, Integer> itens = new LinkedHashMap<CarrinhoItem, Integer>();
	
	public void add(CarrinhoItem item) {
		itens.put(item, getQuantidade(item)+1);
	}

	//Com o generate hash, a gente garante que não vai haver repetição
	//Dessa forma, se o item nao existe no carrinho, vamos adiciona-lo
	//caso exista, vamos retorná-lo
	private int getQuantidade(CarrinhoItem item) {
		if(!itens.containsKey(item))
		{
			itens.put(item, 0);
		}
		return itens.get(item);
	}
	
	public int getQuantidade() {
		//Expressao lambda
		return itens.values().stream().reduce(0, (proximo, acumulador) -> proximo + acumulador);
	}
	
}
