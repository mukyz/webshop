<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="partial/header.jsp" />
<h1>${title}</h1>
<table>
    <tr>
        <th>ID</th>
        <th>Korisnik</th>
        <th>Br. Proizvoda</th>
        <th>Ukupna Cena</th>
        <th>Kontrole</th>
    </tr>
    <c:forEach var="order" items="${orders}">
        <tr>
            <td>${order.id}</td>
            <td>${order.user.name}</td>
            <td>${order.orderItems.size()}</td>
            <td>${order.getTotalPrice()}</td>
            <td>
                <a class="editBtn" href="<c:url value="/order/${order.id}" />">Detalji</a>                               
            </td>
        </tr>
    </c:forEach>
</table>

<jsp:include page="\partial\footer.jsp"/>
