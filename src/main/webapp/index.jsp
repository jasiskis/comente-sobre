<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<html lang="en">
    
<c:import url="header.jsp"/>

       <div class="container">
           <div class="row">
           <div class="hero-unit">
		  <h1>Diga o que Pensa!</h1>
		  <p>Comente sobre o que lhe interessa e compartilhe seus pensamentos com o Mundo!</p>
		  <p><h5>É simples, pesquise abaixo o assunto e começe a comentar</h5></p>
		  		
		  	<input id="assunto" type="text" class="input-xxlarge search-query" placeholder="Pesquisar Assunto!" style="font-size: 35px; height: 38px;">
		    <a id="buscar" class="btn btn-primary btn-large">Buscar</a>     
		    <br />
		    <div id="msg">
		    	
		    </div>			    				
		</div>
               
<c:import url="footer.jsp"/>
</html>