<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
	<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Livros de java, android, iPhone, Ruby, PHP e muito mais -
	Casa do Código</title>
</head>
<body>
<!-- utilizamos essa escrita estanha para qu, se a classe do produtos controller for alterada em algum pacote, garantimos que aqui eará sempre atualizado -->
	<form:form action="${s:mvcUrl('PC#gravar').build() }" method="post" commandName="produto">
		<div>
			<label>Título</label> 
			<form:input path="titulo"/>
			<form:errors path="titulo" />
		</div>

		<div>
			<label>Descrição</label>
			<form:textarea rows="10" cols="20" path="descricao"/>
			<form:errors path="descricao" />
		</div>

		<div>
			<label>Páginas</label>
			<form:input path="paginas"/>
			<form:errors path="paginas" />
		</div>
		<div>
			<label>Data de Lançamento</label>
			<form:input path="dataLancamento"/>
			<form:errors path="dataLancamento"/>
		</div>
		
		<c:forEach items="${tipos}" var="tipoPreco" varStatus="status"> 
		<div>		<%-- para cada item que estamos recebendo na variavel tipo, salvamos na variavel tipoPreco --%>
		<label>${tipoPreco}</label>											<%-- legenda = string armazenada em tipoPreco --%>
		<form:input path="precos[${status.index}].valor"/>				<%-- esse input é do tipo texto e retorna uma variavel que estaremos mandando para o BD --%>
																				<%-- estamos mandando um array, então temos que passa o indice --%>
		<form:hidden path="precos[${status.index}].tipo" value="${tipoPreco}"/>
		</div>  
		</c:forEach>
		
		<button type="submit">Cadastrar</button>
	</form:form>

</body>
</html>