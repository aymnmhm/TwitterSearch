<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<%
List<String> searchResult = (List<String>)request.getAttribute("result");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
for(int i =0; i<searchResult.size(); i++) {%>
<div class="resultOutput">
<%=searchResult.get(i) %>
</div>
<%} %>
</body>
<a href="TwitterToolHome.jsp">
Back To Query Screen</a>
</html>