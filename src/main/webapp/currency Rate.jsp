<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<c:set var="pageTitle" value="t" scope="application" />
<c:set var="pageUrl" value="/currencyRate" scope="page" />
<t:wrapper>

	<h1>Currency Rate</h1>
	<table>
		<thead>
			<tr>
				<th>Currency From Code</th>
				<th>Currency To Code</th>
				<th><mytaglib:sort-link pageUrl="${pageUrl}" column="value_purchase">Purchase</mytaglib:sort-link></th>
				<th><mytaglib:sort-link pageUrl="${pageUrl}" column="value_pokypka">Pokypka</mytaglib:sort-link></th>
			</tr>
		</thead>
		<tbody>

			<c:forEach var="entity" items="${list}" varStatus="loopCounter">
				<tr>
					<td><c:out value="${entity.currencyFromCode}" /></td>
					<td><c:out value="${entity.currencyToCode}" /></td>
					<td><c:out value="${entity.valuePurchase}" /></td>
					<td><c:out value="${entity.valuePokypka}" /></td>
					<td><a
						class="btn-small btn-floating waves-effect waves-light blue"
						title="редактировать" href="/currencyRate?view=edit&idFrom=${entity.currencyFromCode}&idTo=${entity.currencyToCode}"><i
							class="material-icons">edit</i></a><a
						class="btn-small btn-floating waves-effect waves-light red"
						title="удалить" onclick="sendHTTPDelete('/currencyRate?idFrom=${entity.currencyFromCode}&idTo=${entity.currencyToCode}')"><i
							class="material-icons">delete</i></a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<div class="row">

		<div class="col s1 offset-s5">
			<a class="btn-floating btn-large waves-effect yellow"
				href="/currencyRate?view=edit"><i class="material-icons">add</i></a>
		</div>
	</div>
		<t:paging />
</t:wrapper>

