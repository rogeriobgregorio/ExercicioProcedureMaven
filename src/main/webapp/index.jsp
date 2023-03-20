<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="./css/styles.css">
<title>Exercicio Procedure Maven</title>
</head>
<body>
	<div align="center" class="container">
		<form action="cliente" method="post">
			<p class="title">
				<b>Cliente</b>
			</p>
			<table>
				<tr>
					<td colspan="3">
						<input class="input_data" type="text" 
							id="cpf" name="cpf" placeholder="CPF"
							value="<c:out value="${cliente.cpf }"></c:out>">
					</td>
					<td>
						<input type="submit" id="botao" name="botao" value="Buscar">
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<input class="input_data" type="text" 
							id="nome" name="nome" placeholder="Nome"
							value="<c:out value="${cliente.nome }"></c:out>">
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<input class="input_data" type="text" 
							id="email" name="email" placeholder="E-mail"
							value="<c:out value="${cliente.email }"></c:out>">
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<input class="input_data" type="number" 
							min="0" step="0.01" id="limiteCredito"
							name="limiteCredito" placeholder="Limite de Crédito"
							value="<c:out value="${cliente.limiteCredito }"></c:out>">
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<input class="input_data" type="date" 
							id="dataNascimento"	name="dataNascimento" placeholder="Data de Nascimento"
							value="<c:out value="${cliente.dataNascimento }"></c:out>">
					</td>
				</tr>
				<tr>
					<td>
						<input type="submit" id="botao" name="botao" value="Inserir">
					</td>
					<td>
						<input type="submit" id="botao" name="botao" value="Atualizar">
					</td>
					<td>
						<input type="submit" id="botao" name="botao" value="Excluir">
					</td>
					<td>
						<input type="submit" id="botao" name="botao" value="Listar">
					</td>
				</tr>
			</table>
		</form>
	</div>
	<br />
	<br />
	<div align="center">
		<h2><c:out value="${erro }" /></h2>
	</div>
	<div align="center">
		<h3><c:out value="${saida }" /></h3>
	</div>
	<br />
	<br />
	<div align="center">
		<c:if test="${not empty clientes }">
			<table class="table_round">
				<thead>
					<tr>
						<th><b>Cpf</b></th>
						<th><b>Nome</b></th>
						<th><b>E-mail</b></th>
						<th><b>Limite de Crédito</b></th>
						<th><b>Data de Nascimento</b></th>
					</tr>	
				</thead>
				<tbody>
					<c:forEach items="${clientes }" var="c">
						<tr>
							<td><c:out value="${c.cpf }"></c:out></td>
							<td><c:out value="${c.nome }"></c:out></td>
							<td><c:out value="${c.email }"></c:out></td>
							<td><c:out value="${c.limiteCredito }"></c:out></td>
							<td><c:out value="${c.dataNascimento }"></c:out></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</div>
</body>
</html>