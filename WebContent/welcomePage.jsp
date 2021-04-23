<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<head><title>Welcome Page</title></head>
<% 
	String name=(String)request.getAttribute("name"); 
	int userId=(int)request.getAttribute("userId"); 
%>

<body>



    <h3>welcome   
    
       <c:url var="listProduct" value="UserServlets">
       <c:param name="command" value="LISTPRODUCTS"></c:param>
        <c:param name="userId" value="${userId}" ></c:param>
       </c:url>
       
       <c:url var="goBox" value="UserServlets">
        <c:param name="command" value="LISTBOX" />
        <c:param name="userId" value="${userId}" ></c:param>
       </c:url>
       <c:url var="logout" value="UserServlets">
        <c:param name="command" value="LOGOUT" />
       </c:url>
    
       <% 
         
          out.print(name+" "+userId);

        %></h3>
        
         <a  href="${listProduct}">PRODUCT PAGE</a> <br>
         <a  href="${goBox}">MY BOX</a> <br>
          <a  href="${logout}">LOGOUT</a>


</body>


</html>