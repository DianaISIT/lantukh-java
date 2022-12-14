<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="pageTitle" value="Currency_Rate" scope="application" />
<t:wrapper>


	<h1>Currency Rate</h1>


	<div class="row">
		<form class="col s12" method="post" action="/currencyRate">
			<div class="col s6">
					<label for="currencyFromCode">Currency From Code</label> 
					<select name="currencyFromCode" class="browser-default" required>
						<option value="">--select currencyFromCode--</option>
						<c:forEach items="${allCurrencyes}" var="currency">
							<option value="${currency.code}" <c:if test="${currency.code eq dto.currencyFromCode}">selected="selected"</c:if>>${currency.code}</option>
						</c:forEach>
					</select>
				</div>
			<div class="row">
				<div class="input-field col s12">
					<label for="currencyToCode">Currency To Code</label> 
					<select name="currencyToCode" class="browser-default" required>
						<option value="">--select currencyToCode--</option>
						<c:forEach items="${allCurrencyes}" var="currency">
							<option value="${currency.code}" <c:if test="${currency.code eq dto.currencyToCode}">selected="selected"</c:if>>${currency.code}</option>
						</c:forEach>
					</select>
				</div>

			<div class="row">
				<div class="input-field col s12">
					<input name="valuePurchase" type="text" value="${dto.valuePurchase}" class="validate"> <label
						for="valuePurchase">Purchase</label>
				</div>
				</div>
				<div class="row">
				<div class="input-field col s12">
					<input name="valuePokypka" type="text" value="${dto.valuePokypka}" class="validate"> <label
						for="valuePokypka">Pokypka</label>
				</div>
			</div>
			<div class="row">
				<div class="input-field col s6">
					<a href="currency Rate.jsp" class="waves-effect pink btn">Back</a>
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
