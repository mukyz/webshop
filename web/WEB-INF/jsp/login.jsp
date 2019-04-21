<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<jsp:include page="partial/header.jsp" />

<form:form action="login" method="post" modelAttribute="user" >
    <div class="field">
        <form:label path="username">Korisnicko ime</form:label> 
        <form:input path="username" />
    </div>
    
    <div class="field">
        <form:label path="password">Lozinka</form:label>
        <form:password  path="password" />        
    </div>
    
    <div class="field">
        <input type="submit" value="Log in"/>
    </div>
</form:form>
        

<jsp:include page="\partial\footer.jsp"/>
