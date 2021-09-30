/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.71
 * Generated at: 2021-09-29 03:42:28 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.io.PrintWriter;
import bbs.BbsDAO;
import bbs.Bbs;
import java.util.ArrayList;

public final class bbs_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("java.io.PrintWriter");
    _jspx_imports_classes.add("bbs.Bbs");
    _jspx_imports_classes.add("bbs.BbsDAO");
    _jspx_imports_classes.add("java.util.ArrayList");
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSP들은 오직 GET, POST 또는 HEAD 메소드만을 허용합니다. Jasper는 OPTIONS 메소드 또한 허용합니다.");
      return;
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("	<!-- 게시판의 목록을 출력하기 위해 필요 -->\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("<meta name=\"viewport\" content=\"width=device-width\", initial-scale=\"1\" >  <!-- 반응형 웹에 사용하는 메타태그 -->\r\n");
      out.write("<link rel=\"stylesheet\" href=\"css/bootstrap.css\"> <!-- 참조  -->\r\n");
      out.write("<title>처음 만들어보는 JSP 웹 사이트 게시판</title>\r\n");
      out.write("<!-- 아래는 게시판 제목을 디자인하는 부분, a 태그가 달린것들은 검은색으로, 밑줄 없음 -->\r\n");
      out.write("<style type=\"text/css\">\r\n");
      out.write("	a, a:hover {\r\n");
      out.write("		color: #000000;	\r\n");
      out.write("		text-decoration: none;\r\n");
      out.write("	}\r\n");
      out.write("</style>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");

    String userID = null; // 로그인이 된 사람들은 로그인정보를 담을 수 있도록한다
    if (session.getAttribute("userID") != null) {
        userID = (String)session.getAttribute("userID");
    }
	// 아래는 현재 게시판이 몇번째 페이지인지 알려줄 수 있도록 하기 위해 만듦
    int pageNumber = 1;	// 기본 페이지
    if (request.getParameter("pageNumber") != null)	{
    	pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
    }

      out.write("\r\n");
      out.write("    <nav class =\"navbar navbar-default\">\r\n");
      out.write("        <div class=\"navbar-header\"> <!-- 홈페이지의 로고 -->\r\n");
      out.write("            <button type=\"button\" class=\"navbar-toggle collapsed\"\r\n");
      out.write("                data-toggle=\"collapse\" data-target=\"#bs-example-navbar-collapse-1\"\r\n");
      out.write("                aria-expand=\"false\">\r\n");
      out.write("                <span class =\"icon-bar\"></span> <!-- 줄였을때 옆에 짝대기 -->\r\n");
      out.write("                <span class =\"icon-bar\"></span>\r\n");
      out.write("                <span class =\"icon-bar\"></span>\r\n");
      out.write("            </button>\r\n");
      out.write("            <a class =\"navbar-brand\" href=\"main.jsp\">처음 만들어보는 JSP 웹 사이트 게시판</a>\r\n");
      out.write("        </div>\r\n");
      out.write("        <div class=\"collapse navbar-collapse\" id=\"bs-example-navbar-collapse-1\">\r\n");
      out.write("            <ul class=\"nav navbar-nav\">\r\n");
      out.write("                <li><a href=\"main.jsp\">메인</a></li>\r\n");
      out.write("                <li class=\"active\"><a href=\"bbs.jsp\">게시판</a></li>\r\n");
      out.write("            </ul>\r\n");
      out.write("            ");

            // 접속하기는 로그인이 되어있지 않은 경우만 나오게한다
                if(userID == null)
                {
            
      out.write("\r\n");
      out.write("            <ul class=\"nav navbar-nav navbar-right\">\r\n");
      out.write("                <li class=\"dropdown\">\r\n");
      out.write("                <a href=\"#\" class = \"dropdown-toggle\"\r\n");
      out.write("                    data-toggle=\"dropdown\" role =\"button\" aria-haspopup=\"true\"\r\n");
      out.write("                    aria-expanded=\"false\">접속하기<span class=\"caret\"></span></a>\r\n");
      out.write("                    <ul class=\"dropdown-menu\">\r\n");
      out.write("                        <li><a href=\"login.jsp\">로그인</a></li>\r\n");
      out.write("                        <li><a href=\"join.jsp\">회원가입</a></li>                    \r\n");
      out.write("                    </ul>\r\n");
      out.write("                </li>\r\n");
      out.write("            </ul>\r\n");
      out.write("            ");

            // 로그인이 되어있는 사람만 볼수 있는 화면
                } else {
            
      out.write("\r\n");
      out.write("            <ul class=\"nav navbar-nav navbar-right\">\r\n");
      out.write("                <li class=\"dropdown\">\r\n");
      out.write("                <a href=\"#\" class = \"dropdown-toggle\"\r\n");
      out.write("                    data-toggle=\"dropdown\" role =\"button\" aria-haspopup=\"true\"\r\n");
      out.write("                    aria-expanded=\"false\">회원관리<span class=\"caret\"></span></a>\r\n");
      out.write("                    <ul class=\"dropdown-menu\">\r\n");
      out.write("                        <li><a href=\"logoutAction.jsp\">로그아웃</a></li>\r\n");
      out.write("                    </ul>\r\n");
      out.write("                </li>\r\n");
      out.write("            </ul>\r\n");
      out.write("            ");

                }
            
      out.write("\r\n");
      out.write("        </div>\r\n");
      out.write("    </nav>\r\n");
      out.write(" 	\r\n");
      out.write(" 	<!-- 게시판은 기본적으로 작성한 글 순서대로 보여지는 하나의 테이블 형태. 그러므로 하나의 테이블을 만들어주는 방식으로 디자인 -->\r\n");
      out.write(" 	<div class=\"container\">\r\n");
      out.write(" 		<div class\"row>\r\n");
      out.write(" 			<table class=\"table table-striped\" style=\"text-align: center; border: 1px solid #dddddd\"> <!-- 홀수, 짝수가 서로 번갈아가며 색상이 다르게 나타난다 -->\r\n");
      out.write(" 				<thead>			<!-- table의 제목, 각각의 속성들을 알려줌 -->\r\n");
      out.write(" 					<tr>\r\n");
      out.write(" 						<th style=\"background-color: #eeeeee; text-align: center;\">번호</th>\r\n");
      out.write(" 						<th style=\"background-color: #eeeeee; text-align: center;\">제목</th>\r\n");
      out.write(" 						<th style=\"background-color: #eeeeee; text-align: center;\">작성자</th>\r\n");
      out.write(" 						<th style=\"background-color: #eeeeee; text-align: center;\">작성일</th>\r\n");
      out.write(" 					</tr>\r\n");
      out.write(" 				</thead>\r\n");
      out.write(" 				<tbody>\r\n");
      out.write(" 					");
	// 실제로 게시글이 출력되는 부분
 						BbsDAO bbsDAO = new BbsDAO();
 						ArrayList<Bbs> list = bbsDAO.getList(pageNumber);	// 현재의 페이지에서 가져온 게시글 목록임
 						for(int i = 0; i < list.size(); i++) {		// 위에서 가져온 목록들을 list size까지 하나씩 출력
 					
      out.write("	<!-- 이 아래는 출력된 list에서 각각의 게시글에 대한 정보가 출력 -->\r\n");
      out.write(" 					<tr>	<!-- 현재 게시글에 대한 정보를 가져올 수 있도록 만듦 -->\r\n");
      out.write(" 						<td>");
      out.print(list.get(i).getBbsID() );
      out.write("</td>	<!-- 데이터들을 DB에서 가져와서 그대로 보여주는 것 -->\r\n");
      out.write(" 						<td><a href=\"view.jsp?bbsID=");
      out.print(list.get(i).getBbsID());
      out.write('"');
      out.write('>');
      out.print(list.get(i).getBbsTitle() );
      out.write("</a></td>	<!-- 게시글 제목을 누르면 게시글 내용을 보여주는 페이지로 넘어가는 링크 첨부 -->\r\n");
      out.write(" 						<td>");
      out.print(list.get(i).getUserID() );
      out.write("</td>\r\n");
      out.write(" 						<td>");
      out.print(list.get(i).getBbsDate().substring(0, 11) + list.get(i).getBbsDate().substring(11, 13) + "시" + list.get(i).getBbsDate().substring(14, 16) + "분" );
      out.write("</td>	\r\n");
      out.write(" 						<!-- 윗줄 Date 부분, DB에서 넘어오는 날짜시간 그대로가 아니라 내가 편집해서 출력한다. -->\r\n");
      out.write(" 					</tr> 					\r\n");
      out.write(" 					");
		
 						}
 					
      out.write("\r\n");
      out.write(" 				</tbody>\r\n");
      out.write(" 			</table>\r\n");
      out.write(" 			<!-- 아래는 게시판에서 '다음 페이지, 이전 페이지'를 구현 -->\r\n");
      out.write(" 			");

 				if(pageNumber !=1) {
 			
      out.write("\r\n");
      out.write(" 				<a href=\"bbs.jsp?pageNumber=");
      out.print(pageNumber - 1 );
      out.write("\" class=\"btn btn-success btn-arrow-left\">이전</a>\r\n");
      out.write(" 			");
		
 				} if(bbsDAO.nextPage(pageNumber + 1)) {	// 다음 페이지가 존재한다면
			
      out.write("\r\n");
      out.write(" 					<a href=\"bbs.jsp?pageNumber=");
      out.print(pageNumber + 1 );
      out.write("\" class=\"btn btn-success btn-arrow-left\">다음</a>\r\n");
      out.write(" 			");

 				}
 			
      out.write("\r\n");
      out.write(" 			<a href=\"write.jsp\" class=\"btn btn-primary pull-right\">글쓰기</a>\r\n");
      out.write(" 		</div>\r\n");
      out.write(" 	</div>\r\n");
      out.write("    <script src=\"https://code.jquery.com/jquery-3.1.1.min.js\"></script>\r\n");
      out.write("    <script src=\"js/bootstrap.js\"></script>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
