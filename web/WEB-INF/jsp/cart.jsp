<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="partial/header.jsp" />

<h1>${title}</h1>

<table>
    <tr>
        <th>ID Proizvoda</th>
        <th>Naziv Proizvoda</th>
        <th>Kolicina</th>
        <th>Jedinicna cena</th>
        <th>Ukupno</th>
        <th>Kontrole</th>
    </tr>
    <c:forEach var="item" items="${suser.cart}">
        <tr>
            <td>${item.key.id}</td>
            <td>${item.key.name}</td>
            <td>${item.value}</td>    
            <td>${item.key.price}</td> 
            <td>${item.key.price * item.value}</td> 
            <td>
                <a class="delBtn"  href="<c:url value="/cart/remove/${item.key.id}" />">Ukloni</a>                
            </td>
        </tr>
    </c:forEach>
    <tr>
        <td colspan="4">
            
        </td>
        <td>
            <h3>Ukupna cena</h3>
        </td>
        <td>
            <h3>${suser.getTotalCost()}</h3>
        </td>
    </tr>
    <c:if test="${not empty suser.cart}">
    <tr>
        <td colspan="5"></td>
        <td>
            <c:if test="${not empty suser.name}">
            
                <a class="editBtn"  href="<c:url value="/cart/order" />">Naruci</a>
            </c:if>
            
            <c:if test="${empty suser.name}">
                Morate biti prijavljeni da biste narucili proizvode iz korpe.
            </c:if>
        </td>
    </tr>
    </c:if>
    
</table>

<jsp:include page="\partial\footer.jsp"/>
