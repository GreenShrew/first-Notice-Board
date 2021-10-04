/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.71
 * Generated at: 2021-10-04 01:26:15 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.io.PrintWriter;
import bbs.Bbs;
import bbs.BbsDAO;

public final class update_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("<meta name=\"viewport\" content=\"width=device-width\", initial-scale=\"1\" >  <!-- 반응형 웹에 사용하는 메타태그 -->\r\n");
      out.write("<link rel=\"stylesheet\" href=\"css/bootstrap.css\"> <!-- 참조  -->\r\n");
      out.write("<title>처음 만들어보는 JSP 웹 사이트 게시판</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");

    String userID = null; // 로그인이 된 사람들은 로그인정보를 담을 수 있도록한다
    if (session.getAttribute("userID") != null)
    {
        userID = (String)session.getAttribute("userID");
    }
    
    if (userID == null)	{
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
      out.write("        </div>\r\n");
      out.write("    </nav>\r\n");
      out.write(" 	\r\n");
      out.write(" 	<!-- 게시판은 기본적으로 작성한 글 순서대로 보여지는 하나의 테이블 형태. 그러므로 하나의 테이블을 만들어주는 방식으로 디자인 -->\r\n");
      out.write("    <div class=\"container\">\r\n");
      out.write("        <div class=\"row\">\r\n");
      out.write("        <form method=\"post\" action=\"updateAction.jsp?bbsID=");
      out.print( bbsID );
      out.write("\">\r\n");
      out.write("            <table class=\"table table-striped\" style=\"text-align:center; border:1px solid #dddddd\">		<!-- striped는 홀짝홀짝 색상이 다르게 나오도록 만들어줌 -->\r\n");
      out.write("                <thead>\r\n");
      out.write("                    <tr>\r\n");
      out.write("                        <th colspan=\"2\" style=\"background-color:#eeeeee; text-align:center;\">게시판 글 수정 양식</th>\r\n");
      out.write("                    </tr>\r\n");
      out.write("                </thead>\r\n");
      out.write("                <tbody>\r\n");
      out.write("                    <tr>\r\n");
      out.write("                    <td><input type=\"text\" class=\"form-control\" placeholder=\"글 제목\"  name=\"bbsTitle\" maxlength=\"50\" value=\"");
      out.print( bbs.getBbsTitle() );
      out.write("\"></td>\r\n");
      out.write("                    </tr>\r\n");
      out.write("                    <tr>\r\n");
      out.write("                    <td><textarea class=\"form-control\" placeholder=\"글 내용\"  name=\"bbsContent\" maxlength=\"2048\" style=\"height:350px\" >");
      out.print( bbs.getBbsContent() );
      out.write("</textarea></td>\r\n");
      out.write("                    </tr>\r\n");
      out.write("                </tbody>\r\n");
      out.write("            </table>\r\n");
      out.write("                <input type=\"submit\"  class=\"btn btn-primary pull-right\" value=\"글수정\">\r\n");
      out.write("            </form>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
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
