<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="partial/header.jsp" />

<table>
    <tr>
        <th>ID</th>
        <th>Ime</th>
        <th>Korisnicko ime</th>
        <c:if test="${not empty suser.name}">
            <th>Kontrole</th>
        </c:if>
    </tr>
    <c:forEach var="user" items="${users}">
        <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.username}</td>
            <c:if test="${not empty suser.name}">
            <td>
                <a class="editBtn" href="<c:url value="user/edit/${user.id}" />">Izmeni</a>
                <a class="delBtn"  href="<c:url value="user/delete/${user.id}" />">Obrisi</a>                
            </td>
            </c:if>
        </tr>
    </c:forEach>
</table>

<jsp:include page="\partial\footer.jsp"/>
