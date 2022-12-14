<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<c:set var="pageTitle" value="currency" scope="application" />
<c:set var="pageUrl" value="/currency" scope="page" />
<t:wrapper>

	<h1>Currency</h1>
	<table>
		<thead>
			<tr>
                <th><mytaglib:sort-link pageUrl="${pageUrl}" column="code">DB ID</mytaglib:sort-link></th>
				<th><mytaglib:sort-link pageUrl="${pageUrl}" column="name">First name</mytaglib:sort-link></th>
				
			</tr>
		</thead>
		<tbody>

			<c:forEach var="entity" items="${list}" varStatus="loopCounter">
				<tr>
					<td><c:out value="${entity.code}" /></td>
					<td><c:out value="${entity.name}" /></td>
					<td><a
						class="btn-small btn-floating waves-effect waves-light blue"
						title="редактировать" href="/currency?view=edit&id=${entity.code}"><i
							class="material-icons">edit</i></a><a
						class="btn-small btn-floating waves-effect waves-light red"
						title="удалить" onclick="sendHTTPDelete('/currency?code=${entity.code}')"><i
							class="material-icons">delete</i></a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<div class="row">

		<div class="col s1 offset-s5">
			<a class="btn-floating btn-large waves-effect yellow"
				href="/currency?view=edit"><i class="material-icons">add</i></a>
		</div>
	</div>
		<t:paging />
</t:wrapper>

