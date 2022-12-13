<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<c:set var="pageTitle" value="client" scope="application" />
<c:set var="pageUrl" value="/client" scope="page" />
<t:wrapper>

	<h1>Client</h1>
	<table>
		<thead>
			<tr>

				<th><mytaglib:sort-link pageUrl="${pageUrl}" column="id">DB ID</mytaglib:sort-link></th>
				<th><mytaglib:sort-link pageUrl="${pageUrl}" column="first_name">First name</mytaglib:sort-link></th>
				<th><mytaglib:sort-link pageUrl="${pageUrl}" column="last_name">Last name</mytaglib:sort-link></th>
				<th><mytaglib:sort-link pageUrl="${pageUrl}" column="patronymic">Patronymic</mytaglib:sort-link></th>
				<th><mytaglib:sort-link pageUrl="${pageUrl}" column="passport ">Passport</mytaglib:sort-link></th>
				
			</tr>
		</thead>
		<tbody>

			<c:forEach var="entity" items="${list}" varStatus="loopCounter">
				<tr>
					<td><c:out value="${entity.id}" /></td>
					<td><c:out value="${entity.firstName}" /></td>
					<td><c:out value="${entity.lastName}" /></td>
					<td><c:out value="${entity.patronymic}" /></td>
					<td><c:out value="${entity.passport}" /></td>
					<td><a
						class="btn-small btn-floating waves-effect waves-light blue"
						title="редактировать" href="/client?view=edit&id=${entity.id}"><i
							class="material-icons">edit</i></a><a
						class="btn-small btn-floating waves-effect waves-light red"
						title="удалить" onclick="sendHTTPDelete('/client?id=${entity.id}')"><i
							class="material-icons">delete</i></a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<div class="row">

		<div class="col s1 offset-s5">
			<a class="btn-floating btn-large waves-effect yellow"
				href="/client?view=edit"><i class="material-icons">add</i></a>
		</div>
	</div>
		<t:paging />
</t:wrapper>

