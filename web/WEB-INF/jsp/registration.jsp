<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="\partial\header.jsp"/>


<h1>${title}</h1>

    <c:url value="${actionUrl}" var="action"/>

<form:form action="${action}" method="post" commandName="user" >
    <div class="field">
        <form:label path="name">Ime</form:label>
        <form:errors path="name" cssClass="error" />
        <form:input path="name" />
        
    </div>

    <div class="field">
        <form:label path="username">Korisnicko ime</form:label>
        <form:errors path="username" cssClass="error"/>
        <form:input path="username" />        
    </div>
    
    <div class="field">
        <form:label path="password">Lozinka</form:label>
        <form:errors path="password" cssClass="error" />
        <form:password path="password" />        
    </div>

    <div class="field">
        <input type="submit" value="Sacuvaj"/>
    </div>
</form:form>

<jsp:include page="\partial\footer.jsp"/>
