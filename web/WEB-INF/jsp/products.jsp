<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="\partial\header.jsp"/>
        <h1>${title}</h1>    
        
        <div class="products">
            <c:if test="${empty products}">Trenutno ne postoje proizvodi u prodavnici</c:if>
            <c:forEach var="item"  items="${products}" varStatus="i">
                <jsp:include page="\partial\product.jsp" flush="false">
                    <jsp:param name="index" value="${i.index}" />
                </jsp:include>         
            </c:forEach>
        </div>        
<jsp:include page="\partial\footer.jsp"/>
