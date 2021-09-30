<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="bbs.BbsDAO" %>
<%@ page import="java.io.PrintWriter" %> <!-- 자바스크립트 문장사용 -->
<% request.setCharacterEncoding("UTF-8"); %> <!-- 건너오는 모든 파일을 UTF-8로 -->
<jsp:useBean id="bbs" class="bbs.Bbs" scope="page"/>
<jsp:setProperty name="bbs" property="bbsTitle" />
<jsp:setProperty name="bbs" property="bbsContent" />
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
        } else {
            if(bbs.getBbsTitle() == null || bbs.getBbsContent() == null) {	// 제목 or 내용에 어떤 정보를 입력하지 않았다면 if절 실행
    			    PrintWriter script = response.getWriter();
    			    script.println("<script>");
    			    script.println("alert('입력이 안된 사항이 있습니다.')");
     			 	script.println("history.back()");
   				    script.println("</script>");
    			} else {
				    BbsDAO BbsDAO = new BbsDAO();
				    int result = BbsDAO.write(bbs.getBbsTitle(), userID, bbs.getBbsContent());	// write 함수를 작동시켜 실제로 게시글 작성을 가능하게 만듦
	    			if(result == -1){
		   				PrintWriter script = response.getWriter(); //하나의 스크립트 문장을 넣을 수 있도록.
		  			  	script.println("<script>");
		   			 	script.println("alert('글쓰기에 실패했습니다.')");
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
