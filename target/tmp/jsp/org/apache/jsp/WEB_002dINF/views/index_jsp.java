package org.apache.jsp.WEB_002dINF.views;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(2);
    _jspx_dependants.add("/WEB-INF/views/common/head.jsp");
    _jspx_dependants.add("/WEB-INF/views/common/template.jsp");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_set_var_value_nobody;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_c_set_var_value_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_c_set_var_value_nobody.release();
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

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
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE HTML>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<title>报表系统</title>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      if (_jspx_meth_c_set_0(_jspx_page_context))
        return;
      out.write('\r');
      out.write('\n');
      if (_jspx_meth_c_set_1(_jspx_page_context))
        return;
      out.write('\r');
      out.write('\n');
      if (_jspx_meth_c_set_2(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n");
      out.write("<!-- <meta http-equiv=\"X-UA-Compatible\" content=\"IE=Edge\"> -->\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<link rel=\"shortcut icon\" type=\"image/x-icon\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${resources}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/favicon.ico\">\r\n");
      out.write("\r\n");
      out.write("<link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${resources}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/scripts/app/common/bootstrap/css/bootstrap-responsive.css\" rel=\"stylesheet\">\r\n");
      out.write("<link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${resources}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/scripts/app/common/bootstrap/css/bootstrap-responsive.min.css\" rel=\"stylesheet\">\r\n");
      out.write("<link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${resources}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/scripts/app/common/bootstrap/css/bootstrap.css\" rel=\"stylesheet\">\r\n");
      out.write("<link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${resources}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/scripts/app/common/bootstrap/css/bootstrap.min.css\" rel=\"stylesheet\">\r\n");
      out.write("<link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${resources}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/styles/ui/zTreeStyle/zTreeStyle.css\" rel=\"stylesheet\">\r\n");
      out.write("<link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${resources}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/styles/ui/jquery.autocomplete.css\" rel=\"stylesheet\">\r\n");
      out.write("<link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${resources}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/styles/ui/select2.css\" rel=\"stylesheet\">\r\n");
      out.write("\r\n");
      out.write("<link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${resources}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/styles/main.css\" rel=\"stylesheet\">\r\n");
      out.write("<!--[if IE 7]>\r\n");
      out.write("<link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${resources}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/styles/ui/font-awesome-ie7.min.css\" rel=\"stylesheet\"/>\r\n");
      out.write("<![endif]-->\r\n");
      out.write("<!--[if lte IE 8]>\r\n");
      out.write("<link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${resources}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/ui/ace-ie.min.css\" rel=\"stylesheet\"/>\r\n");
      out.write("<![endif]-->\r\n");
      out.write("\r\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${resources}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/scripts/app/common/date.js\" type=\"text/javascript\"></script>\r\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${resources}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/scripts/app/common/jquery-1.7.2.min.js\" type=\"text/javascript\"></script>\r\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${resources}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/scripts/app/common/serialize-object.js\" type=\"text/javascript\"></script>\r\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${resources}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/scripts/app/common/bootstrap/js/bootstrap.js\" type=\"text/javascript\"></script>\r\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${resources}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/scripts/app/common/bootstrap/js/bootstrap.min.js\" type=\"text/javascript\"></script>\r\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${resources}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/scripts/app/common/utils.js\" type=\"text/javascript\"></script>\r\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${resources}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/scripts/app/common/handlebars-v3.0.3.js\" type=\"text/javascript\"></script>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\tvar contextPath = \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\";\r\n");
      out.write("\tvar resources = \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${resources}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\";\r\n");
      out.write("\tvar requestURI = \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${requestURI}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\";\r\n");
      out.write("</script>\r\n");
      out.write('\r');
      out.write('\n');
      out.write("\r\n");
      out.write("\r\n");
      out.write("<script id=\"content-template\" type=\"text/x-handlebars-template\">\r\n");
      out.write("  \t\t<h1>Excel Converter{{result}}</h1>\r\n");
      out.write(" \t\t\t<table class=\"ta_1\" width=\"{{tableWidth}}\" height=\"auto\" border=\"1\">\r\n");
      out.write("\t\t\t\t{{#each htmlRowStructure}}\r\n");
      out.write("\t\t\t\t\t<tr valign=\"middle\">\r\n");
      out.write("\t\t\t\t\t\t{{#if htmlTdStructure}}\r\n");
      out.write("\t\t\t\t\t\t\t{{#each htmlTdStructure}}\r\n");
      out.write("\t\t\t\t\t\t\t\t<td colspan=\"{{colspan}}\" rowspan=\"{{rowspan}}\" align=\"center\" width=\"{{width}}\" height=\"{{height}}\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t{{#if value}}\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t{{value}}\r\n");
      out.write("\t\t\t\t\t\t\t\t\t{{/if}}\r\n");
      out.write("\t\t\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t\t{{/each}}\r\n");
      out.write("\t\t\t\t\t\t{{/if}}\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t{{/each}}\r\n");
      out.write("\t\t\t</table>\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("\t");
      out.write(">\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write(" <div class=\"container\" align=\"center\">\r\n");
      out.write(" <h1>THE BEGINING!</h1>\r\n");
      out.write("\t<form>\r\n");
      out.write("\t  <fieldset>\r\n");
      out.write("\t    <legend>Legend</legend>\r\n");
      out.write("\t  </fieldset>\r\n");
      out.write("\t</form>\r\n");
      out.write("\t<button id=\"create\" class=\"btn btn-large btn-primary\">\r\n");
      out.write("\t\t<i class=\"icon-eject\"></i>文件上传\r\n");
      out.write("\t</button>\r\n");
      out.write("\t<button id=\"generate\" class=\"btn btn-large btn-primary\">\r\n");
      out.write("\t\t生成表格\r\n");
      out.write("\t</button>\r\n");
      out.write("\t<div id=\"create-modal\" class=\"modal modal-md hide\">\r\n");
      out.write("\t\t<div class=\"modal-header\">\r\n");
      out.write("\t\t\t<button type=\"button\" class=\"close\" data-dismiss=\"modal\">×</button>\r\n");
      out.write("\t\t\t<h5 class=\"green\">\r\n");
      out.write("\t\t\t\t<i class=\"icon-plus-sign-alt\"></i> 添加文件\r\n");
      out.write("\t\t\t</h5>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div class=\"modal-body\">\r\n");
      out.write("\t\t\t<div class=\"row-fluid\">\r\n");
      out.write("\t\t\t\t<div class=\"span12\">\r\n");
      out.write("\t\t\t\t\t<form id=\"create-form\" class=\"form-horizontal\"\r\n");
      out.write("\t\t\t\t\t\tstyle=\"margin-bottom: 0px;\">\r\n");
      out.write("\t\t\t\t\t\t<div class=\"control-group\">\r\n");
      out.write("\t\t\t\t\t\t\t<label class=\"control-label \" for=\"documentName\">文档名称</label>\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"controls\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<input id=\"create_documentName\" name=\"documentName\" type=\"text\">\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t<div class=\"control-group\">\r\n");
      out.write("\t\t\t\t\t\t\t<label class=\"control-label \" for=\"keyWord\">查询关键字</label>\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"controls\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<input id=\"create_keyWord\" name=\"keyWord\" type=\"text\">\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t<div class=\"control-group\">\r\n");
      out.write("\t\t\t\t\t\t\t<label class=\"control-label \" for=\"securityLevel\">开放等级</label>\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"controls\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<select id=\"create_security_level\" name=\"securityLevel\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<option value=\"3\" title=\"所有人可见\">公开(所有人可见)</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<option value=\"2\" title=\"同组织内可见\">保护(同组织内可见)</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<option value=\"1\" title=\"仅自己可见\">私有(仅自己可见)</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t</select>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t<div class=\"control-group\" style=\"display: none;\">\r\n");
      out.write("\t\t\t\t\t\t\t<label class=\"control-label\" for=\"credential\">附件</label>\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"controls\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<input id=\"create_attachment\" readonly name=\"attachment\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\ttype=\"text\" class=\"span10\" style=\"width: 350px;\"> <input\r\n");
      out.write("\t\t\t\t\t\t\t\t\tvalue=\"删除\" type=\"button\" id=\"create-file-delete\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\tclass=\"btn btn-small btn-success span2\" style=\"width: 48px;\">\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t<input type=\"hidden\" name=\"folderId\" id=\"create_folderId\">\r\n");
      out.write("\t\t\t\t\t</form>\r\n");
      out.write("\t\t\t\t\t<form id=\"create-file-form\" action=\"/simpleupload\"\r\n");
      out.write("\t\t\t\t\t\tclass=\"form-horizontal\" method=\"post\"\r\n");
      out.write("\t\t\t\t\t\tenctype=\"multipart/form-data\" target=\"acceptFrame\">\r\n");
      out.write("\t\t\t\t\t\t<div class=\"control-group\">\r\n");
      out.write("\t\t\t\t\t\t\t<label class=\"control-label\" for=\"credential\">附件</label>\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"controls\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<input name=\"file\" id=\"file\" type=\"file\">\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t</form>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div id=\"create-message-alert\" class=\"row-fluid hide\">\r\n");
      out.write("\t\t\t\t\t<div class=\"span12\">\r\n");
      out.write("\t\t\t\t\t\t<div class=\"alert alert-error\">\r\n");
      out.write("\t\t\t\t\t\t\t<i class=\"icon-remove\"></i> <span id=\"create-message-content\"></span>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div class=\"modal-footer\">\r\n");
      out.write("\t\t\t<button id=\"create-save\" class=\"btn btn-small btn-success\">\r\n");
      out.write("\t\t\t\t<i class=\"icon-ok\"></i> 确定\r\n");
      out.write("\t\t\t</button>\r\n");
      out.write("\t\t\t<button class=\"btn btn-small\" data-dismiss=\"modal\">\r\n");
      out.write("\t\t\t\t<i class=\"icon-remove\"></i> 取消\r\n");
      out.write("\t\t\t</button>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div  id=\"plan-table\"></div>\r\n");
      out.write("</div>\r\n");
      out.write("<iframe name=\"acceptFrame\" border=\"1\" frameborder=\"1\" width=\"100\"\r\n");
      out.write("height=\"100\" style=\"display: none\"></iframe>\r\n");
      out.write("</body>\r\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${resources}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/scripts/app/fileupload/index.js\" type=\"text/javascript\"></script>\r\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${resources}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/scripts/app/excel/index.js\" type=\"text/javascript\"></script>\r\n");
      out.write("</html>\r\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_c_set_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_set_0 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _jspx_tagPool_c_set_var_value_nobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_set_0.setPageContext(_jspx_page_context);
    _jspx_th_c_set_0.setParent(null);
    _jspx_th_c_set_0.setVar("contextPath");
    _jspx_th_c_set_0.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.request.contextPath}", java.lang.Object.class, (PageContext)_jspx_page_context, null));
    int _jspx_eval_c_set_0 = _jspx_th_c_set_0.doStartTag();
    if (_jspx_th_c_set_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_set_var_value_nobody.reuse(_jspx_th_c_set_0);
      return true;
    }
    _jspx_tagPool_c_set_var_value_nobody.reuse(_jspx_th_c_set_0);
    return false;
  }

  private boolean _jspx_meth_c_set_1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_set_1 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _jspx_tagPool_c_set_var_value_nobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_set_1.setPageContext(_jspx_page_context);
    _jspx_th_c_set_1.setParent(null);
    _jspx_th_c_set_1.setVar("resources");
    _jspx_th_c_set_1.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.request.contextPath}/static-resources", java.lang.Object.class, (PageContext)_jspx_page_context, null));
    int _jspx_eval_c_set_1 = _jspx_th_c_set_1.doStartTag();
    if (_jspx_th_c_set_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_set_var_value_nobody.reuse(_jspx_th_c_set_1);
      return true;
    }
    _jspx_tagPool_c_set_var_value_nobody.reuse(_jspx_th_c_set_1);
    return false;
  }

  private boolean _jspx_meth_c_set_2(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_set_2 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _jspx_tagPool_c_set_var_value_nobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_set_2.setPageContext(_jspx_page_context);
    _jspx_th_c_set_2.setParent(null);
    _jspx_th_c_set_2.setVar("requestURI");
    _jspx_th_c_set_2.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.request.requestURI}", java.lang.Object.class, (PageContext)_jspx_page_context, null));
    int _jspx_eval_c_set_2 = _jspx_th_c_set_2.doStartTag();
    if (_jspx_th_c_set_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_set_var_value_nobody.reuse(_jspx_th_c_set_2);
      return true;
    }
    _jspx_tagPool_c_set_var_value_nobody.reuse(_jspx_th_c_set_2);
    return false;
  }
}
