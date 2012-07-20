<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>

<html lang="en">
    
<c:import url="../../../header.jsp"/>


    <div class="container">
     <div class="span6">
     		<div class="page-header">
        <h1>${assunto}<small>, Comente sobre.</small></h1>
     </div>
     
     <div class="span6">
     	<form class="well" action="<c:url value="/${assunto}/comentario"/>" >
		  <label>Comentário</label>
		  <textarea class="input-xxlarge" name="comentario.texto" id="textarea" rows="5"></textarea>
			 	                       
           	<div class="input-prepend">
			  <span class="add-on"><i class="icon-envelope"></i></span><input class="span2" name="comentario.email" type="text" placeholder="Email">
        	  <button type="submit" class="btn">Enviar</button>
        	</div>
       	    
             <input type="hidden" name="comentario.assunto" value="${assunto}"/>
		
		</form>  
		
		  <c:forEach var="error" items="${errors}">
				<div class="alert alert-error span4 fade in">
			  		<button class="close" data-dismiss="alert">x</button>		
			  		${error.message}		  
			  	</div>				    
			</c:forEach>				
     </div>
  </div>

	<div class="span5">
		<div class="page-header">
	        <h1><small>Comentários</small></h1>
	     </div>
	     
	     <c:forEach var="post" items="${posts}">
		     <div class="span5 well" >
		     	<blockquote>${post.texto}</blockquote><br /> 
		     	_______________________________<br />
			    Comentário por: <strong>${post.email}</strong> 
			    <h6><fmt:formatDate value="${post.data.time}" pattern="dd/MM/yyyy HH:mm" /></h6>
			    <br />
			    
			    <input name="star${post.id}" type="radio" class="star"/>
				<input name="star${post.id}" type="radio" class="star"/>
				<input name="star${post.id}" type="radio" class="star"/>
				<input name="star${post.id}" type="radio" class="star"/>
				<input name="star${post.id}" type="radio" class="star"/>	    
		     </div>				
		</c:forEach>
		</div>  
</div>
		               
<c:import url="../../../footer.jsp"/>
</html>