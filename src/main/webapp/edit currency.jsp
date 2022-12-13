<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="pageTitle" value="Edit currency" scope="application" />
<t:wrapper>


	<h1>Edit currency</h1>


	<div class="row">
		<form class="col s12" method="post" action="/currency">
			<input type="hidden" name="code" value="${dto.code}" />
			<div class="row">
				<div class="input-field col s6">
					<input name="name" type="text"  value="${dto.name}" class="validate" > <label
						for="name">Name</label>
				</div>
			</div>
			<div class="row">
				<div class="input-field col s6">
					<input name="code" type="text"  value="${dto.code}" class="validate" > <label
						for="code">Code</label>
				</div>
			</div>
			<div class="row">
				<div class="input-field col s6">
					<a href="currency.jsp" class="waves-effect pink btn">Back</a>
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
