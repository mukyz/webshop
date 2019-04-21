<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="\partial\header.jsp"/>

<c:if test="${not empty product.images}" var="edit" /> 


<h1>${title}</h1>

<div class="edit_product_form">
    <c:url value="${actionUrl}" var="action"/>

    <form:form style="flex-grow:5" enctype="multipart/form-data" action="${action}" method="post" commandName="product" >
    <div class="field">
        <form:label path="name">Naziv Proizovda</form:label>
        <form:errors path="name" cssClass="error"></form:errors>
        <form:input path="name" />
        
    </div>
    
    <div class="field">
        <form:label path="price">Cena</form:label>
        <form:errors path="price" cssClass="error"></form:errors>
        <form:input path="price" />
        
    </div>

    <div class="field">
        <form:label path="description">Opis proizovda</form:label>
        <form:errors path="description" cssClass="error"/>
        <form:textarea path="description" />        
    </div>
    
    <div class="field">
        <form:label path="quantity">Kolicina</form:label>
        <form:errors path="quantity" cssClass="error"></form:errors>
        <form:input path="quantity" />        
    </div>

    <div class="field">
        <input type="file" name="upFiles" accept="image/*" multiple />
    </div>
    
    <div class="field">
        <input type="submit" value="Sacuvaj"/>
    </div>
</form:form>


<c:if test="${edit}">      
    <div class="edit_product_images">
        <c:forEach var="image" items="${product.images}" >
            <div>
            <img style="width:60%; height:auto; "src="<c:url value="/image/" />${image.imageId}" alt="${image.filename}"/>
            <button class="delBtn" onclick="ajax('<c:url value="/image/delete/" />${image.imageId}')">Obrisi</button>
            </div>
        </c:forEach>
    </div>    
</c:if>
</div>
        
<jsp:include page="\partial\footer.jsp"/>

<script>
    var images = document.getElementsByTagName("IMG");
    
    function ajax(url){
        var xmlHttp;
        try
        {  //check Opera, FireFox and Safari
            xmlHttp=new XMLHttpRequest();  
        } catch (e){  // check IE
            try { 
                xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");    
            } catch (e) {
                try {  
                    xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");  
                } catch (e) { // error occurred
                    alert("Initialisation not completed"); return false; 
                }
            }
        }    
        
        xmlHttp.onreadystatechange = function(){
            if(xmlHttp.readyState == 4){
                for(var i=0; i < images.length; i++){
                    if(images[i].alt == xmlHttp.responseText){
                        images[i].parentNode.style.display = 'none';
                    }
                }
            }
        }
        
        xmlHttp.open("GET", url, true);
        xmlHttp.send()
    }
    
</script>
