<%-- 
    Document   : image
    Created on : Apr 11, 2019, 12:02:14 PM
    Author     : Dimitrije Muzur
--%>

<%@page import="java.io.OutputStream"%>
<%@page import="model.Image"%>
<%
    Image img = (Image) request.getAttribute("image");
    
    if(img!= null){
        byte[] imgData = img.getContent();
        response.setContentType("image/jpeg");
        OutputStream os = response.getOutputStream();
        os.write(imgData);
        os.flush();
        os.close();
    }
%>