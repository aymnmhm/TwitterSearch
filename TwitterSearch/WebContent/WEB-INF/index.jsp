<%@ page pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<label>Enter the "Hash Tag" you want to search.</label>
<form action="./twitter" method="post">
#<input type="text" name="searchTarget" id="tweets">
<input type="submit" value="send">
</form>
</body>
</html>