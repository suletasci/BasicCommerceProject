<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<html>

 <head><title>Box page</title></head>
 <% String productId=(String )request.getAttribute("productId");
    int userId=(int )request.getAttribute("userId");
 %>
 
 <body>
      <h4>Box Page</h4>
      <c:forEach var="box" items="${box_list}">
      
           ${box.getName()}-- ${box.getUserId()} <br>
      </c:forEach>
      
     <c:url var="tempBox" value="UserServlets">
             <c:param name="command" value="BUY" />
             <c:param name="productId" value="${productId}"></c:param>
             <c:param name="userId" value="${userId}"></c:param>
      </c:url> 
      
      <a href="${tempBox}">BUY</a>
      
      
 
 
 </body>


</html>