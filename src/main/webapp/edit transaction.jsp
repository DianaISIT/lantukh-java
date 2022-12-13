<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="pageTitle" value="Edit" scope="application" />
<t:wrapper>


	<h1>Edit Transaction</h1>


	<div class="row">
		<form class="col s12" method="post" action="/transaction">
			<input type="hidden" name="id" value="${dto.id}" />
			<div class="col s6">
					<label for="clientId">Client ID</label> 
					<select name="clientId" class="browser-default" required>
						<option value="">--select client--</option>
						<c:forEach items="${allClients}" var="client">
							<option value="${client.id}" <c:if test="${client.id eq dto.clientId}">selected="selected"</c:if>>${client.firstName}</option>
						</c:forEach>
					</select>
				</div>
			<div class="row">
				<div class="input-field col s12">
					<label for="currencyCodeFrom">currencyCodeFrom</label> 
					<select name="currencyCodeFrom" class="browser-default" required>
						<option value="">--select currencyCodeFrom--</option>
						<c:forEach items="${allCurrencyes}" var="currency">
							<option value="${currency.code}" <c:if test="${currency.code eq dto.currencyCodeFrom}">selected="selected"</c:if>>${currency.code}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="row">
				<div class="input-field col s12">
					<label for="currencyCodeTo">currencyCodeFrom</label> 
					<select name="currencyCodeTo" class="browser-default" required>
						<option value="">--select currencyCodeFrom--</option>
						<c:forEach items="${allCurrencyes}" var="currency">
							<option value="${currency.code}" <c:if test="${currency.code eq dto.currencyCodeTo}">selected="selected"</c:if>>${currency.code}</option>
						</c:forEach>
					</select>
				</div>
			</div>

			<div class="row">
				<div class="input-field col s12">
					<input name="amount" type="text" value="${dto.amount}" class="validate"> <label
						for="amount">Amount</label>
				</div>
				</div>
				<div class="row">
				<div class="input-field col s12">
					<input name="created" type="text" value="${dto.created}" class="validate"> <label
						for="created">Date</label>
				</div>
				</div>
				<div class="row">
				<div class="input-field col s12">
					<input name="result" type="text" value="${dto.result}" class="validate"> <label
						for="result">Result</label>
				</div>
			</div>
			<div class="row">
				<div class="input-field col s6">
					<a href="transaction.jsp" class="waves-effect pink btn">Back</a>
				</div>
				<div class="input-field col s6">
					<button class="btn waves-effect waves-light" type="submit">
						<i class="material-icons left">save</i>save
					</button>
				</div>
			</div>
		</form>
	</div>


</t:wrapper>
