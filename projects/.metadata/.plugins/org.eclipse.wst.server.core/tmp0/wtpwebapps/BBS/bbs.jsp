<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="bbs.BbsDAO" %>
<%@ page import="bbs.Bbs" %>
<%@ page import="java.util.ArrayList" %>	<!-- 게시판의 목록을 출력하기 위해 필요 -->
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width", initial-scale="1" >  <!-- 반응형 웹에 사용하는 메타태그 -->
<link rel="stylesheet" href="css/bootstrap.css"> <!-- 참조  -->
<link rel="stylesheet" href="css/custom.css">
<title>처음 만들어보는 JSP 웹 사이트 게시판</title>
<!-- 아래는 게시판 제목을 디자인하는 부분, a 태그가 달린것들은 검은색으로, 밑줄 없음 -->
<style type="text/css">
	a, a:hover {
		color: #000000;	
		text-decoration: none;
	}
</style>
</head>
<body>
<%
    String userID = null; // 로그인이 된 사람들은 로그인정보를 담을 수 있도록한다
    if (session.getAttribute("userID") != null) {
        userID = (String)session.getAttribute("userID");
    }
	// 아래는 현재 게시판이 몇번째 페이지인지 알려줄 수 있도록 하기 위해 만듦
    int pageNumber = 1;	// 기본 페이지
    if (request.getParameter("pageNumber") != null)	{
    	pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
    }
%>
    <nav class ="navbar navbar-default">
        <div class="navbar-header"> <!-- 홈페이지의 로고 -->
            <button type="button" class="navbar-toggle collapsed"
                data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
                aria-expand="false">
                <span class ="icon-bar"></span> <!-- 줄였을때 옆에 짝대기 -->
                <span class ="icon-bar"></span>
                <span class ="icon-bar"></span>
            </button>
            <a class ="navbar-brand" href="main.jsp">처음 만들어보는 JSP 웹 사이트 게시판</a>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a href="main.jsp">메인</a></li>
                <li class="active"><a href="bbs.jsp">게시판</a></li>
            </ul>
            <%
            // 접속하기는 로그인이 되어있지 않은 경우만 나오게한다
                if(userID == null)
                {
            %>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                <a href="#" class = "dropdown-toggle"
                    data-toggle="dropdown" role ="button" aria-haspopup="true"
                    aria-expanded="false">접속하기<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="login.jsp">로그인</a></li>
                        <li><a href="join.jsp">회원가입</a></li>                    
                    </ul>
                </li>
            </ul>
            <%
            // 로그인이 되어있는 사람만 볼수 있는 화면
                } else {
            %>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                <a href="#" class = "dropdown-toggle"
                    data-toggle="dropdown" role ="button" aria-haspopup="true"
                    aria-expanded="false">회원관리<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="logoutAction.jsp">로그아웃</a></li>
                    </ul>
                </li>
            </ul>
            <%
                }
            %>
        </div>
    </nav>
 	
 	<!-- 게시판은 기본적으로 작성한 글 순서대로 보여지는 하나의 테이블 형태. 그러므로 하나의 테이블을 만들어주는 방식으로 디자인 -->
 	<div class="container">
 		<div class"row>
 			<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd"> <!-- 홀수, 짝수가 서로 번갈아가며 색상이 다르게 나타난다 -->
 				<thead>			<!-- table의 제목, 각각의 속성들을 알려줌 -->
 					<tr>
 						<th style="background-color: #eeeeee; text-align: center;">번호</th>
 						<th style="background-color: #eeeeee; text-align: center;">제목</th>
 						<th style="background-color: #eeeeee; text-align: center;">작성자</th>
 						<th style="background-color: #eeeeee; text-align: center;">작성일</th>
 					</tr>
 				</thead>
 				<tbody>
 					<%	// 실제로 게시글이 출력되는 부분
 						BbsDAO bbsDAO = new BbsDAO();
 						ArrayList<Bbs> list = bbsDAO.getList(pageNumber);	// 현재의 페이지에서 가져온 게시글 목록임
 						for(int i = 0; i < list.size(); i++) {		// 위에서 가져온 목록들을 list size까지 하나씩 출력
 					%>	<!-- 이 아래는 출력된 list에서 각각의 게시글에 대한 정보가 출력 -->
 					<tr>	<!-- 현재 게시글에 대한 정보를 가져올 수 있도록 만듦 -->
 						<td><%=list.get(i).getBbsID() %></td>	<!-- 데이터들을 DB에서 가져와서 그대로 보여주는 것 -->
 						<td><a href="view.jsp?bbsID=<%=list.get(i).getBbsID()%>"><%=list.get(i).getBbsTitle().replaceAll(" ","&nbsp;").replaceAll("<","&lt;").replaceAll(">", "&gt;").replaceAll("\n","<br>") %></a></td>	<!-- 게시글 제목을 누르면 게시글 내용을 보여주는 페이지로 넘어가는 링크 첨부 -->
 						<td><%=list.get(i).getUserID() %></td>
 						<td><%=list.get(i).getBbsDate().substring(0, 11) + list.get(i).getBbsDate().substring(11, 13) + "시" + list.get(i).getBbsDate().substring(14, 16) + "분" %></td>	
 						<!-- 윗줄 Date 부분, DB에서 넘어오는 날짜시간 그대로가 아니라 내가 편집해서 출력한다. -->
 					</tr> 					
 					<%		
 						}
 					%>
 				</tbody>
 			</table>
 			<!-- 아래는 게시판에서 '다음 페이지, 이전 페이지'를 구현 -->
 			<%
 				if(pageNumber !=1) {
 			%>
 				<a href="bbs.jsp?pageNumber=<%=pageNumber - 1 %>" class="btn btn-success btn-arrow-left">이전</a>
 			<%		
 				} if(bbsDAO.nextPage(pageNumber + 1)) {	// 다음 페이지가 존재한다면
			%>
 					<a href="bbs.jsp?pageNumber=<%=pageNumber + 1 %>" class="btn btn-success btn-arrow-left">다음</a>
 			<%
 				}
 			%>
 			<a href="write.jsp" class="btn btn-primary pull-right">글쓰기</a>
 		</div>
 	</div>
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script src="js/bootstrap.js"></script>
</body>
</html>
