<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder"%>
<%@ page import="java.util.Enumeration"%>

<%@ page import="com.nimbusds.jwt.JWT"%>
<%@ page import="com.nimbusds.jwt.JWTParser" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<!DOCTYPE html>
<html>
<head>
<title>MagicAuth SessionView</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<style type="text/css">
#content {
	width: 100%;
}

#content table {
	width: 100%;
	border: 1px;
	table-layout: fixed;
}

#content table thead {
	text-align: center;
	background: #B3CDEE;
}

#content table td {
	word-break:break-all;
	padding: .1em;
	border-right: 1px solid #CCC;
	border-bottom: 1px solid #CCC;
}


#token {
	width: 100%;
}

#token table {
	width: 100%;
	border: 1px;
	table-layout: fixed;
}

#token table thead {
	text-align: center;
	background: #B3CDEE;
}

#token table td {
	word-break:break-all;
	padding: .1em;
	border-right: 1px solid #CCC;
	border-bottom: 1px solid #CCC;
}


#dynamicConent {
	width: 100%;
}

#dynamicConent table {
	width: 100%;
	border: 1px;
	table-layout: fixed;
}

#dynamicConent table thead {
	text-align: center;
	background: #B3CDEE;
}

#dynamicConent table td {
	word-break:break-all;
	padding: .1em;
	border-right: 1px solid #CCC;
	border-bottom: 1px solid #CCC;
}



form table {
	width: 50%;
	border: 1px;
}

form table thead {
	text-align: center;
	background: #B3CDEE;
}

form table td {
	padding: .1em;
	border-right: 1px solid #CCC;
	border-bottom: 1px solid #CCC;
}
</style>
<script type="text/javascript">

	var flag = false;

	var access_token  = "";
	var refresh_token = "";
	const tokenType   = "Bearer";
	
	
	function onLoadInit(){
		access_token 	= $("#accessToken").val();
		refresh_token	= $("#refreshToken").val();
		
	}
	function showLayer() {
		if (!flag) {
			document.getElementById("sysinfo_lay").style.display = "block";
			document.getElementById("showLayer").value = "환경 정보 감추기";
		} else {
			document.getElementById("sysinfo_lay").style.display = "none";
			document.getElementById("showLayer").value = "환경 정보 보기";
		}
		flag = !flag;
	}

	function Hello(){
		alert("hello");
	}
	
	function getUser() {

		var allData = "accessToken=" + access_token + "&tokenType=" + tokenType;

		$.ajax({
			type : "post",
			url : "resource.jsp",
			data : allData,
			success : function(args) {
				$("#UserPage").html(args);
			},

			error : function(e) {
				alert(e.responseText);
			}
		});
	}
	function getRefreshAccessToken(){
		var refreshToken = "refreshToken="+refresh_token;
		
		$.ajax({
			type: "post",
			url : "getRefreshToken.jsp",
			data : refreshToken,
			success : function ( args ){
				$("#RefreshResultPage").html(args);

				
				var jsonObj = JSON.parse(args.trim());
				//console.log ( jsonObj);
				
				access_token 	= jsonObj.access_token;
				refresh_token 	= jsonObj.refresh_token;
				$("#access_token").html(jsonObj.access_token );
				$("#refresh_token").html(jsonObj.refresh_token );
				$("#grant_type").html(jsonObj.grant_type );
				$("#scope").html(jsonObj.scope );
				$("#token_type").html(jsonObj.token_type );
				$("#expires_in").html(jsonObj.expires_in );

			},
			error: function (e){
				alert(e.responseText);
			}
			
		});
	}
</script>
</head>
<body onload="onLoadInit()">
	<h1 align="center">MagicAuth</h1>
	<div id="content">
		<table>
			<thead>
				<tr>
					<td width="20%">Header Variable</td>
					<td>Header Value</td>
				</tr>
			</thead>
			<tbody>
		
				<%
					Enumeration<?> eh = request.getHeaderNames();
					while (eh.hasMoreElements()) {
						String skey = (String) eh.nextElement();
						out.println("<tr>");
						out.println("<td>" + skey + "</td>");
						out.println("<td>" + request.getHeader(skey) + "</td>");
						out.println("</tr>");
					}	
				%>
			</tbody>
		</table>
		<br>
	</div>
	
	<div id="token">
		<table>
			<thead>
				<tr>
					<td width="20%">Session Variable</td>
					<td>Session Value</td>
				</tr>
			</thead>
			<tbody>
					
				<%
					
					Enumeration<?> em = session.getAttributeNames();
					while (em.hasMoreElements()) {
						String skey = (String) em.nextElement();
						out.println("<tr>");
						out.println("<td>" + skey + "</td>");
						out.println("<td id= " + skey + ">" + session.getAttribute(skey) + "</td>");
						out.println("</tr>");
						
					}
					
					
				%>

			</tbody>
			
		
		</table>
		</div>

		<br>

		<!-- 리소스 서버 연동 샘플 -->		
		<div id="dynamicConent">
		
		<input id="accessToken" type="hidden" value="<%=session.getAttribute("access_token")%>"/>
		<input id="tokenType" type="hidden" value="Bearer"/>
			<table>
				<thead>
					<tr>
						<td width="20%">Access Token</td>
						<td><input type="button" onclick="getUser();return false;" value="AccessToken으로 리소스 서버에서 사용자정보 가져오기" /></td>
					</tr>
				</thead>
				<tbody>
				<tr>
				<td width="20%">Response Body</td>
				<td><div id="UserPage"></div></td>
				</tr>
				</tbody>
			</table>
		
		
		<c:choose>
			<c:when test="${not empty sessionScope.refresh_token }">
				
				<input id="refreshToken" type="hidden" value="<%=session.getAttribute("refresh_token") %>"/>
				<table>
					<thead>
						<tr>
							<td width="20%">Refrsh Access Token</td>
							<td><input type="button" onClick="getRefreshAccessToken();return false" value="Refresh Token을 이용하여 생성 "/>
							
						</tr>
						
					</thead>
					
						<tr>
							<td width="20%">Access Token 재 생성 결과. </td>
							<td><div id="RefreshResultPage"></div></td>
						</tr>
					
				</table>		
			</c:when>		
		</c:choose>
		</div>
		
		<br>
		<br>
		<!-- Sys info-->
		<input type="button" id="showLayer" name="showLayer" value="환경 정보 보기" style="width: 150px; margin-left: 2px;"
			onclick="showLayer();return false;" />
		<div id="sysinfo_lay" style="display: none;">
			<table>
				<thead>
					<tr>
						<td width="20%">Sys pName</td>
						<td>Value</td>
					</tr>
				</thead>
				<tbody>
					<%
						Enumeration<?> es = System.getProperties().propertyNames();
						while (es.hasMoreElements()) {
							String skey = (String) es.nextElement();
							out.println("<tr>");
							out.println("<td>" + skey + "</td>");
							out.println("<td>" + System.getProperty(skey) + "</td>");
							out.println("</tr>");
						}
					%>
				</tbody>
			</table>
		</div>
	
</body>
</html>
