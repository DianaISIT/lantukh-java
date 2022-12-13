<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<c:set var="pageTitle" value="t" scope="application" />
<c:set var="pageUrl" value="/transaction" scope="page" />
<t:wrapper>

	<h1>Transaction</h1>
	<table>
		<thead>
			<tr>
				<th><mytaglib:sort-link pageUrl="${pageUrl}" column="id">DB ID</mytaglib:sort-link></th>
				<th>Client</th>
				<th>Currency Code From</th>
				<th>Currency Code To</th>
				<th><mytaglib:sort-link pageUrl="${pageUrl}" column="amount">Amount</mytaglib:sort-link></th>
				<th><mytaglib:sort-link pageUrl="${pageUrl}" column="created">Created</mytaglib:sort-link></th>
				<th><mytaglib:sort-link pageUrl="${pageUrl}" column="result">Result</mytaglib:sort-link></th>
			</tr>
		</thead>
		<tbody>

			<c:forEach var="entity" items="${list}" varStatus="loopCounter">
				<tr>
					<td><c:out value="${entity.id}" /></td>
					<td><c:out value="${entity.clientId}" /></td>
					<td><c:out value="${entity.created}" /></td>
					<td><c:out value="${entity.currencyCodeFrom}" /></td>
					<td><c:out value="${entity.currencyCodeTo}" /></td>
					<td><c:out value="${entity.amount}" /></td>
					<td><c:out value="${entity.result}" /></td>
					<td><a
						class="btn-small btn-floating waves-effect waves-light blue"
						title="редактировать" href="/transaction?view=edit&id=${entity.id}"><i
							class="material-icons">edit</i></a><a
						class="btn-small btn-floating waves-effect waves-light red"
						title="удалить" onclick="sendHTTPDelete('/transaction?id=${entity.id}')"><i
							class="material-icons">delete</i></a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<div class="row">

		<div class="col s1 offset-s5">
			<a class="btn-floating btn-large waves-effect yellow"
				href="/transaction?view=edit"><i class="material-icons">add</i></a>
		</div>
	</div>
		<t:paging />
</t:wrapper>

