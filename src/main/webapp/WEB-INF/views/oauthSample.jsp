<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>통합 로그인 툴 </title>
<script src="./MagicAuthJS/js/magicAuth.js"></script>
	
</head>
<body>
	<h1 align="center"> 통합 로그인 툴 </h1>

	<!-- client 설정하는 화면이 추가되면 좋을거같긴하다. -->
	 
	<div align="center">
 	     <a href="<c:out value='${authAPI}'/>" align="center"> Authorization Code 방식 로그인 요청 </a>
	</div>

	<div align="center">
		<form id="creForm" name="creForm"> 	
			<input type="submit" value="Implicit 방식의  로그인 요청 " align="center" action="requestImplicit"/>
		</form>  
	</div>
	

		<div align="center">
		
			<form:form action="${clientCredentialAPI}"> 	
					<input type="submit" value="Client Credential 방식의  로그인 요청 " align="center"/>
			</form:form>
		
		</div>
		
	
	<div align="center">
		<form:form action="${resourceOwnerAPI}"> 	
			<input type="submit" value="Resource Owner 방식의  AccessToken 발급 요청 " align="center"/>
		</form:form>
	</div>
	
</body>
</html> 