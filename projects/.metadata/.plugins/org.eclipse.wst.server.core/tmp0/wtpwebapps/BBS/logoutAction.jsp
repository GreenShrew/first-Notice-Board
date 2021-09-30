<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>처음 만들어보는 JSP 웹 사이트 게시판</title>
</head>
<body>
	<%
		session.invalidate();//접속한 회원의 세션을 빼앗음
	%>
	<script>
		location.href = 'main.jsp';
	</script>

</body>
</html>