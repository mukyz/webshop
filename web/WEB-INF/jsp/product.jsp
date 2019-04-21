<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="\partial\header.jsp"/>

<div class="container">  
<div class="product-images">
    <c:if test="${not empty product.images}">
    <c:forEach var="item" items="${product.images}">
        <div class="slide">
            <a href="<c:url value="/image/" />${item.imageId}">
                <img src="<c:url value="/image/" />${item.imageId}"/>
            </a>
        </div>
    </c:forEach>
    
    <a class="prev" onclick="nextSlide(-1)">&#10094;</a>
    <a class="next" onclick="nextSlide(1)">&#10095;</a>
    </c:if>
    <c:if test="${empty product.images}">
        <img src="<c:url value="/images/placeholder.png" />" />
    </c:if>
    
    
    
</div>
<div class="product-details">
    <div class="product-title">
        ${product.name}
    </div>
    <div class="element-title">Cena:</div>
    <div class="product-price">
        ${product.price}
    </div>
    
    <form action="javascript:void(0)">        
        <input type="number" id="quantity" name="quantity" min="1" max="${product.quantity}" value="1" <c:if test="${product.quantity == 0}">disabled</c:if>/>
        <input type="hidden" id="prodId" name="prodId" value="${product.id}"  />
        <button id="cartButton" onclick="ajax()" <c:if test="${product.quantity == 0}">disabled</c:if>>U korpu</button>
        <span style="margin-left: 0.5em; font-size: 0.8em; color:rgba(0,0,0,0.8);">Trenutno na stanju <span id="stock">${product.quantity}</span></span>        
    </form>
</div>
</div>
<div class="container">
    <div class="element-title">Opis:</div>
    <div class="product-description">
        ${product.description}
    </div>
</div>
<jsp:include page="\partial\footer.jsp"/>

<script>
    var sliderIndex = 0;
    showSlide(sliderIndex);
    
    function nextSlide(step){
        showSlide(sliderIndex +=step);
    }
    
    function showSlide(index){
        var slides = document.getElementsByClassName("slide");
        
        if(index > slides.length-1) {
            sliderIndex = 0;
        }
        
        if(index < 0){
            sliderIndex = slides.length-1;
        }
        
        for(var i = 0; i< slides.length; i++){
            slides[i].style.display = "none";
        }
        
        slides[sliderIndex].style.display = "block";     
    }
    
    function ajax(){
        var xmlHttp;     
        var button = document.getElementById("cartButton");
        
        try{  //check Opera, FireFox and Safari
              xmlHttp=new XMLHttpRequest();  
        }
        catch (e){  // check IE
            try {
                xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");    
            } catch (e){    
                try {      
                    xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");      
                } catch (e) { //error occured       
                    alert("Initialisation not completed"); return false; 
                }    
            }  
        }
        
        xmlHttp.onreadystatechange = function(){
            if(xmlHttp.readyState == 4){
                var response = xmlHttp.responseText.split("#");
                
                var stock = document.getElementById("stock");
                var totalItems = document.getElementById("totalItems");
                var totalCost = document.getElementById("totalCost");
                stock.innerHTML = response[0];
                totalItems.innerHTML = response[1];
                totalCost.innerHTML = response[2];
                button.innerHTML = "U korpu";
            }
        }
               
        
        var quantity = document.getElementById("quantity").value;
        var prodId = document.getElementById("prodId").value;
        
        urlEncodedData = '?quantity='+quantity+'&prodId='+prodId;       
        
        xmlHttp.open("GET","<c:url value="/add_to_cart"/>"+urlEncodedData,true);
        xmlHttp.send();
        
        var button = document.getElementById("cartButton");
        button.innerHTML = '<div class="loader"></div>'
    }
    
    
</script>
