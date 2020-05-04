package br.com.casadocodigo.loja.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION) // Estamos informando ao spring que podemos ter mais de uma sessao -
													// uma instancia dessa classe por usuario
public class CarrinhoCompras implements Serializable {
//É interessante usar o implemets serializable toda vez que temos um scope_session, porque o objeto é guardado em arquivo, e quando o usuario vai utilizar novamente a sessão, o arquivo é retornado 
	private Map<CarrinhoItem, Integer> itens = new LinkedHashMap<CarrinhoItem, Integer>();

	public Collection<CarrinhoItem> getItens() {
		return itens.keySet();
	}

	public void add(CarrinhoItem item) {
		itens.put(item, getQuantidade(item) + 1);
	}

	// Com o generate hash, a gente garante que não vai haver repetição
	// Dessa forma, se o item nao existe no carrinho, vamos adiciona-lo
	// caso exista, vamos retorná-lo
	
	public Integer getQuantidade(CarrinhoItem item) {
		if (!itens.containsKey(item)) {
			itens.put(item, 0);
		}
		return itens.get(item);
	}

	public int getQuantidade() {
		// Expressao lambda
		return itens.values().stream().reduce(0, (proximo, acumulador) -> proximo + acumulador);
	}

	public BigDecimal getTotal(CarrinhoItem item) {
		return item.getTotal(getQuantidade(item));
	}

	public BigDecimal getTotal() {
		BigDecimal total = BigDecimal.ZERO;
		for(CarrinhoItem item : itens.keySet()) {
			total = total.add(getTotal(item));
		}
		return total;
	}
}
