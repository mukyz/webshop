<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="partial/header.jsp" />

<h1>${title}</h1>

<h3>Korisnik:${order.user.name}</h3>

<table>
    <tr>
        <th>ID Proizvoda</th>
        <th>Naziv Proizvoda</th>
        <th>Kolicina</th>
        <th>Jedinicna cena</th>
        <th>Ukupno</th>
    </tr>
    <c:forEach var="item" items="${order.orderItems}">
        <tr>
            <td>${item.productItem.id}</td>
            <td>${item.productItem.name}</td>
            <td>${item.quantity}</td>    
            <td>${item.productItem.price}</td> 
            <td>${item.productItem.price * item.quantity}</td> 
        </tr>
    </c:forEach>
    <tr>
        <td colspan="3">
            
        </td>
        <td>
            <h3>Ukupna cena</h3>
        </td>
        <td>
            <h3>${order.getTotalPrice()}</h3>
        </td>
    </tr>
</table>


<jsp:include page="\partial\footer.jsp"/>
