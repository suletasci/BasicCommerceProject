
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<html>

 <head><title>Products Detail</title></head>
 <% int userId=Integer.parseInt(request.getParameter("userId")); %>
 
 <body>
      <h4>Product Details</h4>
        
        
        <c:url var="addBox" value="UserServlets">
             <c:param name="command" value="ADDBOX" />
             <c:param name="productId" value="${productDetail.getId()}"></c:param>
             <c:param name="userId" value="${userId}"></c:param>
        </c:url> 
        
        <c:url var="goProduct" value="UserServlets">
             <c:param name="command" value="LISTPRODUCTS" />
             <c:param name="productId" value="${productDetail.getId()}"></c:param>
             <c:param name="userId" value="${userId}"></c:param>
        </c:url> 


         <c:url var="getBox" value="UserServlets">
             <c:param name="command" value="LISTBOX" />
             <c:param name="userId" value="${userId}"></c:param>
             <c:param name="productId" value="${productDetail.getId()}"></c:param>
        </c:url> 
     
         
         <span><strong>Product Id = </strong></span>${productDetail.getId()} <br>
         <span><strong>Product Name = </strong></span> ${productDetail.getName()} <br>
          <span><strong>Product Price = </strong></span>${productDetail.getPrice()} <br>
          <span><strong>Product StockNumber = </strong></span>${productDetail.getStockNumber()} <br>
          <img alt="${productDetail.getName()}" src="${productDetail.getImage()}"><br>
          
          <a href="${goProduct}">back to Products</a>
          
           <a href="${addBox}">Add To Box</a>
           <a href="${getBox}">Go To Box</a>
  
      
      
 
 
 </body>


</html>

















