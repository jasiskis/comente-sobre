<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<html lang="en">
    
<c:import url="../../../header.jsp"/>


       <div class="container">
			<div class="page-header">
		        <h1>${assunto}<small>, Comente sobre.</small></h1>
		     </div>
		     
		     <div class="span6">
		     	<form class="well" action="<c:url value="/${assunto}/comentario"/>" >
				  <label>Comentário</label>
				  <textarea class="input-xxlarge" name="comentario.texto" id="textarea" rows="5"></textarea>
				  				  
				  <input name="comentario.email" type="text" class="input-xlarge" placeholder="Email">
				  <input type="hidden" name="comentario.assunto" value="${assunto}"/>
				  <button type="submit" class="btn">Enviar</button>
				</form>
		     </div>
		     <div class="span6">
		     	<c:forEach var="post" items="${posts}">
				   ${post.texto}  <br /> 
				    ${post.email}<br />
				</c:forEach>
		     </div>
		     
		</div>
               
<c:import url="../../../footer.jsp"/>
</html>