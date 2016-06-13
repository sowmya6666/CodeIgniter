<%-- 
    Document   : StudentInput.jsp
    Created on : 7 Jun, 2016, 4:30:07 PM
    Author     : sowmya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>

        <form action="/WebServiceApplication/studentRedirectServlet" method="post">
            <textarea  name="mytextarea"  type="text" rows="5" cols="50">
            </textarea>
            <input type="submit" value="submit" name="submitform"  />
        </form>
    </body>
</html>
