<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="bbs.Bbs" %>
<%@ page import="bbs.BbsDAO" %>
<%@ page import="java.io.PrintWriter" %> <!-- 자바스크립트 문장사용 -->
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>처음 만들어보는 JSP 웹 사이트 게시판</title>
</head>
<body>
    <%
        String userID = null;
        if (session.getAttribute("userID") != null ) {
            userID = (String) session.getAttribute("userID");
        }
        if(userID == null) {	// 글 쓰기는 이미 로그인 되어있어야 가능함
            PrintWriter script = response.getWriter();
            script.println("<script>");
            script.println("alert('로그인을 하세요.')");
            script.println("location.href = 'login.jsp'");
            script.println("</script>");
        }
        int bbsID = 0;
        if (request.getParameter("bbsID") != null) {
        	bbsID = Integer.parseInt(request.getParameter("bbsID"));
        }
        if (bbsID == 0) {
            PrintWriter script = response.getWriter();
            script.println("<script>");
            script.println("alert('유효하지 않은 글 입니다.')");
            script.println("location.href = 'bbs.jsp'");
            script.println("</script>");
        }
        Bbs bbs = new BbsDAO().getBbs(bbsID);
        if (!userID.equals(bbs.getUserID())) {		// 수정하려는 사람과 게시판 작성자가 동일하지 않을 시 아랫문구 출력
            PrintWriter script = response.getWriter();
            script.println("<script>");
            script.println("alert('권한이 없습니다.')");
            script.println("location.href = 'login.jsp'");
            script.println("</script>");
        } else {
            if(request.getParameter("bbsTitle") == null || request.getParameter("bbsContent") == null
            		|| request.getParameter("bbsTitle").equals("") || request.getParameter("bbsContent").equals("")) {	// 제목 or 내용에 하나라도 어떤 정보를 입력하지 않았거나 null값인 경우 if절 실행
    			    PrintWriter script = response.getWriter();
    			    script.println("<script>");
    			    script.println("alert('입력이 안된 사항이 있습니다.')");
     			 	script.println("history.back()");
   				    script.println("</script>");
    			} else {
				    BbsDAO BbsDAO = new BbsDAO();
				    int result = BbsDAO.update(bbsID, request.getParameter("bbsTitle"), request.getParameter("bbsContent"));
	    			if(result == -1){
		   				PrintWriter script = response.getWriter(); //하나의 스크립트 문장을 넣을 수 있도록.
		  			  	script.println("<script>");
		   			 	script.println("alert('글 수정에 실패했습니다.')");
	      			    script.println("history.back()");
	      		     	script.println("</script>");
     			   }
			       	else {
			            PrintWriter script = response.getWriter();
			            script.println("<script>");
			            script.println("location.href= 'bbs.jsp'");
			            script.println("</script>");
				}
  		  }
        }

    %>
</body>
</html> 
