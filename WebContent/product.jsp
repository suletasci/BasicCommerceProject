<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<html>
 <% int userId=Integer.parseInt(request.getParameter("userId")); %>
 <head><title>Products form</title></head>
 <body>
      <h4>product Page</h4>
      <c:forEach var="tempProduct" items="${PRODUCTS}">
      
          <c:url var="tempLink" value="UserServlets">
             <c:param name="command" value="PRODUCTDETAILS" />
             <c:param name="productId" value="${tempProduct.getId()}"></c:param>
             <c:param name="userId" value="${userId}"></c:param>
          </c:url> 
        
      
      
        <a href="${tempLink}"> ${tempProduct.getName()}</a> <br>
      </c:forEach>
      
      
 
 
 </body>


</html>