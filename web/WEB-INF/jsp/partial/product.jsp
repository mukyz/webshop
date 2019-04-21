<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="column product">
    <a href="<c:url value="/product/"/>${products[param.index].id}">
     
        <c:if test="${products[param.index].images.size() == 0 }">  
            <img src="<c:url value="/images/placeholder.png" />" />
        </c:if>
        <c:forEach var="image" items="${products[param.index].images}" end="0">
            <img src="<c:url value="/image/" />${image.imageId}"/>
        </c:forEach>
    
    <h3>${products[param.index].name}</h3>
    </a>
    <div class="price">${products[param.index].price}</div> 
    
    <c:if test="${not empty suser.name}" >
    <div class="controls">
        <a class="editBtn" href="<c:url value="product/edit/"/>${products[param.index].id}">Izmeni</a>
        <a class="delBtn" href="<c:url value="product/delete/"/>${products[param.index].id}">Obrisi</a>        
    </div>
    </c:if>
</div>
