<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="user.UserDAO" %> <! 내가 만든 class를 사용해야한다. 앞서 만든 UserDAO를 불러옴.>
<%@ page import ="java.io.PrintWriter" %>
<% request.setCharacterEncoding("UTF-8"); %> <! 건너오는 모든 데이터를 UTF-8로 변환>
<jsp:useBean id="user" class="user.User" scope="page" /> <! 앞서 만든 User라는 class를 javaBeans로서 사용>
<jsp:setProperty name="user" property="userID" /> 
<jsp:setProperty name="user" property="userPassword" /> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>처음 만들어보는 JSP 웹 사이트</title>
</head>
<body>
	<%
		String userID = null;	// 로그인한 회원이 로그인, 회원가입 페이지에 들어가지 못 하도록 만듦
		if (session.getAttribute("userID") !=null) {		// Session을 확인해서 "userID"로 Session이 존재하는 회원들은 userID라는 변수가 자신에게 할당된 Session을 담을 수 있도록 만듦
			userID = (String) session.getAttribute("userID");
		}
		if (userID !=null);	{	// userID가 null값이 아닌 경우
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('이미 로그인이 되어있습니다.')");
			script.println("location.href = 'main.jsp'");
			script.println("</script>");
		}
		UserDAO userDAO = new UserDAO();
		int result = userDAO.login(user.getUserID(), user.getUserPassword()); // 로그인을 시도할 수 있도록 만듬
		if (result == 1) {		// 로그인 성공시
			session.setAttribute("userID", user.getUserID());	// 로그인에 성공한 유저에게 Session을 부여. 이를 통해 로그인 여부를 알 수 있다.
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("location.href = 'main.jsp'");	// 로그인 성공시 main.jsp 페이지로 넘어갈 수 있도록 만든다.
			script.println("</script>");
		}	
		else if (result == 0) {		// 로그인 실패시
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('비밀번호가 틀립니다.')");
			script.println("history.back()");
			script.println("</script>");
		}
		else if (result == -1) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('존재하지 않는 아이디입니다.');");
			script.println("history.back()");
			script.println("</script>");
		}
		else if (result == -2) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('데이터베이스 오류가 발생했습니다.')");
			script.println("history.back()");
			script.println("</script>");
		}
	%>
</body>
</html>

<! 실질적으로 사용자의 로그인 시도를 처리해주는 페이지를 제작>