<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${title}</title>
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/style.css" />" />
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
    </head>
    <body>
        <div class="wraper">
        <header>
            <div class="header-row">
                <div class="logo">
                    <a href="<c:url value="/" />">
                        WebShop
                    </a>
                </div>
                <div class="search">
                    <form action="<c:url value="/search"/>" method="POST">
                        <input class="search-field" type="text" name="search" id ="search" placeholder="Pretraga"/>
                    </form>
                </div>
                        
                <div class="cart">
                    <a href="<c:url value="/cart"/>">
                        <table>
                            <tr>                                
                                <td>Proizvoda u korpi: <span id="totalItems">${suser.cart.size()}</span></td>
                                <td rowspan="2" style="font-size: 2rem;"><i class="fas fa-shopping-cart"></i></td>
                            </tr>
                            <tr>
                                <td>
                                    <span id="totalCost">${suser.getTotalCost()}</span>&nbsp;RSD
                                </td>
                            </tr>
                        </table>
                    </a>
                </div>
                
            </div>
            <nav>
                <ul>
                    <li>
                        <a href="<c:url value="/"/>">Proizovdi</a>
                    </li>                    
                    <li>
                        <a href="<c:url value="/users"/>">Korisnici</a>
                    </li>
                    <li>
                        <a href="<c:url value="/orders"/>">Narudzbine</a>
                    </li>
                    <li>
                        <a href="<c:url value="/about"/>">O meni</a>
                    </li>
                    <c:if test="${not empty suser.name}">
                        <li>
                            <a style="background-color: darkorange" href="<c:url value="/add_product.htm"/>">Novi Proizovd</a>
                        </li>
                    </c:if>
                </ul>
                    
                <div class="loginWidget">
                    
                    <c:if test="${empty suser.name}">
                    <a href="<c:url value="/login" />">Prijava</a>
                    <a href="<c:url value="/registration" />">Registracija</a>
                    </c:if>
                    <c:if test="${not empty suser.name}">
                        Pozdrav, ${suser.name}
                    <a href="<c:url value="/logout" />">Odjava</a>
                    </c:if>
                </div>
            </nav>            
        </header>
        <main class="wrap">
            <c:if test="${not empty error}">
                <div class="error">
                    ${error}
                    <span onclick ="this.parentNode.style.display = 'none';" style='font-size:1em; float:right;'>&#10006;</span>                
                </div>
            </c:if>
            <c:if test="${not empty message}">
                <div class="message">
                    ${message}
                    <span onclick ="this.parentNode.style.display = 'none';" style='font-size:1em; float:right;'>&#10006;</span>
                </div>
            </c:if>


       
