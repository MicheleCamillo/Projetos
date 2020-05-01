<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Livros de java, android, iPhone, Ruby, PHP e muito mais -
	Casa do C�digo</title>
</head>
<body>
	<form action="/casadocodigo/produtos" method="post">
		<div>
			<label>T�tulo</label> <input type="text" name="titulo">
		</div>

		<div>
			<label>Descri��o</label>
			<textarea rows="10" cols="20" name="descricao"></textarea>
		</div>

		<div>
			<label>P�ginas</label> <input type="text" name="paginas">
		</div>
		
		<c:forEach items="${tipos}" var="tipoPreco" varStatus="status"> 
		<div>		<%-- para cada item que estamos recebendo na variavel tipo, salvamos na variavel tipoPreco --%>
		<label>${tipoPreco}</label>											<%-- legenda = string armazenada em tipoPreco --%>
		<input type="text" name="precos[${status.index}].valor"/>				<%-- esse input � do tipo texto e retorna uma variavel que estaremos mandando para o BD --%>
																				<%-- estamos mandando um array, ent�o temos que passa o indice --%>
		<input type="hidden" name="precos[${status.index}].tipo" value="${tipoPreco}"/>
		</div>  
		</c:forEach>
		
		<button type="submit">Cadastrar</button>
	</form>

</body>
</html>