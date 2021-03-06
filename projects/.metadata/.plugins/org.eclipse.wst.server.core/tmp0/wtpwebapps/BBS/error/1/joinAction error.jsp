<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="user.UserDAO" %> <! 내가 만든 class를 사용해야한다. 앞서 만든 UserDAO를 불러옴.>
<%@ page import ="java.io.PrintWriter" %>
<% request.setCharacterEncoding("UTF-8"); %> <! 건너오는 모든 데이터를 UTF-8로 변환>
<jsp:useBean id="user" class="user.User" scope="page" /> <! 앞서 만든 User라는 class를 javaBeans로서 사용>
<jsp:setProperty name="user" property="userID" />
<jsp:setProperty name="user" property="userPassword" />
<jsp:setProperty name="user" property="userName" />
<jsp:setProperty name="user" property="userGender" />
<jsp:setProperty name="user" property="userEmail" />
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
		if (user.getUserID() == null || user.getUserPassword() == null || user.getUserName() == null || user.getUserGender() == null || user.getUserEmail() == null) {		// 회원가입 창에서 아무것도 안 써 넣으면 아이디 비번 이름 이메일이 Null이 된다. 이를 방지함. ||는 '혹은' 을 의미.
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('입력이 안 된 사항이 있습니다.')");
			script.println("history.back()");
			script.println("</script>");
		} else {
			UserDAO userDAO = new UserDAO();
			int result = userDAO.join(user);
			if (result == -1) {		// 데이터 베이스 오류시. 이 경우에 이 오류를 일으킬 이유는 이미 있는 아이디를 사용했을 때 뿐이다.
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('이미 존재하는 아이디입니다.')");
				script.println("history.back()");
				script.println("</script>");
			}	
			else {
				session.setAttribute("userID", user.getUserID());
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("location.href = 'main.jsp'");
				script.println("</script>");
			}
		}

	%>
</body>
</html>

<! join 함수를 이용해 실제로 데이터베이스에 해당 유저 정보가 등록이 될 것이다.>