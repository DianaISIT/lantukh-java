<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="pageTitle" value="Edit" scope="application" />
<t:wrapper>


	<h1>Edit client</h1>


	<div class="row">
		<form class="col s12" method="post" action="/client">
			<input type="hidden" name="id" value="${dto.id}" />
			<div class="row">
				<div class="input-field col s6">
					<input name="firstName" type="text"  value="${dto.firstName}" class="validate" > <label
						for="firstName">FirstName</label>
				</div>
			</div>

			<div class="row">
				<div class="input-field col s12">
					<input name="lastName" type="text" value="${dto.lastName}" class="validate"> <label
						for="lastName">LastName</label>
				</div>
			</div>
			<div class="row">
				<div class="input-field col s12">
					<input name="patronymic" type="text" value="${dto.patronymic}" class="validate"> <label
						for="patronymic">Middle Name</label>
				</div>
			</div>
			<div class="row">
				<div class="input-field col s12">
					<input name="passport" type="text" value="${dto.passport}" class="validate"> <label
						for="passport">Passport</label>
				</div>
			</div>
			<div class="row">
				<div class="input-field col s6">
					<a href="client.jsp" class="waves-effect pink btn">Back</a>
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
