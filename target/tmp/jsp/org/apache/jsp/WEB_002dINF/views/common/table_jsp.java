package org.apache.jsp.WEB_002dINF.views.common;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class table_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(1);
    _jspx_dependants.add("/WEB-INF/views/common/head.jsp");
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
      out.write("\r\n");
      out.write("<style>\r\n");
      out.write("\ttable.ta_1{width:100%;text-align:center;}\r\n");
      out.write("\ttable.ta_1 td tr{text-align:center;table-layout:fixed;word-break:break-all;}\r\n");
      out.write("</style>\r\n");
      out.write("</head>\r\n");
      out.write("<tbody>\r\n");
      out.write("<div id=\"content\">\r\n");
      out.write("</div>\r\n");
      out.write("  <div class=\"container\" align=\"center\" width=\"1,772.719\">\r\n");
      out.write("  <h1>Excel Converter</h1>\r\n");
      out.write(" <table class=\"ta_1\" width=\"1,772.719\" height=\"auto\" border=\"1\">\r\n");
      out.write("\t\t<tr valign=\"middle\">\r\n");
      out.write("\t\t\t\t<td colspan=\"\" rowspan=\"\" align=\"center\" width=\"148.5\" height=\"17.315199999999997\">\r\n");
      out.write("\t\t\t\t\t机构：\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td colspan=\"5\" rowspan=\"\" align=\"center\" width=\"141.075\" height=\"17.315199999999997\">\r\n");
      out.write("\t\t\t\t\tKOREA EXCHANGE BANK,SEOUL 181,2- KA,ULCHI-RO,CHUNG-KU,,,100 793  SEOUL\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td colspan=\"\" rowspan=\"\" align=\"center\" width=\"\" height=\"\">\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td colspan=\"\" rowspan=\"\" align=\"center\" width=\"\" height=\"\">\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td colspan=\"\" rowspan=\"\" align=\"center\" width=\"\" height=\"\">\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td colspan=\"\" rowspan=\"\" align=\"center\" width=\"\" height=\"\">\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td colspan=\"\" rowspan=\"\" align=\"center\" width=\"\" height=\"\">\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td colspan=\"\" rowspan=\"\" align=\"center\" width=\"\" height=\"\">\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t<tr valign=\"middle\">\r\n");
      out.write("\t\t\t\t<td colspan=\"\" rowspan=\"\" align=\"center\" width=\"148.5\" height=\"59.3664\">\r\n");
      out.write("\t\t\t\t\t流水号\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td colspan=\"\" rowspan=\"\" align=\"center\" width=\"141.075\" height=\"59.3664\">\r\n");
      out.write("\t\t\t\t\t信用证号\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td colspan=\"\" rowspan=\"\" align=\"center\" width=\"155.92499999999998\" height=\"59.3664\">\r\n");
      out.write("\t\t\t\t\t收报行SWIFTCODE\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td colspan=\"\" rowspan=\"\" align=\"center\" width=\"282.15\" height=\"59.3664\">\r\n");
      out.write("\t\t\t\t\t收报行英文名称\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td colspan=\"\" rowspan=\"\" align=\"center\" width=\"133.65\" height=\"59.3664\">\r\n");
      out.write("\t\t\t\t\t申请人编码\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td colspan=\"\" rowspan=\"\" align=\"center\" width=\"282.15\" height=\"59.3664\">\r\n");
      out.write("\t\t\t\t\t申请人英文名称\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td colspan=\"\" rowspan=\"\" align=\"center\" width=\"133.65\" height=\"59.3664\">\r\n");
      out.write("\t\t\t\t\t所属机构\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td colspan=\"\" rowspan=\"\" align=\"center\" width=\"148.5\" height=\"59.3664\">\r\n");
      out.write("\t\t\t\t\t开证日期\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td colspan=\"\" rowspan=\"\" align=\"center\" width=\"133.65\" height=\"59.3664\">\r\n");
      out.write("\t\t\t\t\t信用证金额\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td colspan=\"\" rowspan=\"\" align=\"center\" width=\"148.5\" height=\"59.3664\">\r\n");
      out.write("\t\t\t\t\t最大开证金额\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td colspan=\"\" rowspan=\"\" align=\"center\" width=\"133.65\" height=\"59.3664\">\r\n");
      out.write("\t\t\t\t\t合同号\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td colspan=\"\" rowspan=\"\" align=\"center\" width=\"141.075\" height=\"59.3664\">\r\n");
      out.write("\t\t\t\t\t合同金额\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t<tr valign=\"middle\">\r\n");
      out.write("\t\t\t\t<td colspan=\"\" rowspan=\"\" align=\"center\" width=\"148.5\" height=\"23.4992\">\r\n");
      out.write("\t\t\t\t\t0704040000014108\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td colspan=\"\" rowspan=\"\" align=\"center\" width=\"141.075\" height=\"23.4992\">\r\n");
      out.write("\t\t\t\t\tLC07000900034\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td colspan=\"\" rowspan=\"\" align=\"center\" width=\"155.92499999999998\" height=\"23.4992\">\r\n");
      out.write("\t\t\t\t\tKOEXKRSE\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td colspan=\"\" rowspan=\"\" align=\"center\" width=\"282.15\" height=\"23.4992\">\r\n");
      out.write("\t\t\t\t\tKOREA EXCHANGE BANK,SEOUL 181,2- KA,ULCHI-RO,CHUNG-KU,,,100 793  SEOUL\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td colspan=\"\" rowspan=\"\" align=\"center\" width=\"133.65\" height=\"23.4992\">\r\n");
      out.write("\t\t\t\t\t0000000169\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td colspan=\"\" rowspan=\"\" align=\"center\" width=\"282.15\" height=\"23.4992\">\r\n");
      out.write("\t\t\t\t\tYANTAI ZHICHENG AQUATIC PRODUCT  CO.,LTD DAJIJIA YANTAI ETDZ  SHANDONG CHINA TEL:86-535-6976519 FAX:86-535-6976569\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td colspan=\"\" rowspan=\"\" align=\"center\" width=\"133.65\" height=\"23.4992\">\r\n");
      out.write("\t\t\t\t\t1000000000\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td colspan=\"\" rowspan=\"\" align=\"center\" width=\"148.5\" height=\"23.4992\">\r\n");
      out.write("\t\t\t\t\t2007-04-04 \r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td colspan=\"\" rowspan=\"\" align=\"center\" width=\"133.65\" height=\"23.4992\">\r\n");
      out.write("\t\t\t\t\t399000.000\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td colspan=\"\" rowspan=\"\" align=\"center\" width=\"148.5\" height=\"23.4992\">\r\n");
      out.write("\t\t\t\t\t399000.000\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td colspan=\"\" rowspan=\"\" align=\"center\" width=\"133.65\" height=\"23.4992\">\r\n");
      out.write("\t\t\t\t\t\"无\"\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td colspan=\"\" rowspan=\"\" align=\"center\" width=\"141.075\" height=\"23.4992\">\r\n");
      out.write("\t\t\t\t\t\"贸易\"\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t<tr valign=\"middle\">\r\n");
      out.write("\t\t\t\t<td colspan=\"\" rowspan=\"\" align=\"center\" width=\"148.5\" height=\"23.4992\">\r\n");
      out.write("\t\t\t\t\t0704050000014157\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td colspan=\"\" rowspan=\"\" align=\"center\" width=\"141.075\" height=\"23.4992\">\r\n");
      out.write("\t\t\t\t\tLC07000900037\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td colspan=\"\" rowspan=\"\" align=\"center\" width=\"155.92499999999998\" height=\"23.4992\">\r\n");
      out.write("\t\t\t\t\tFTSBNL2R\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td colspan=\"\" rowspan=\"\" align=\"center\" width=\"282.15\" height=\"23.4992\">\r\n");
      out.write("\t\t\t\t\tFORTIS BANK (NEDERLAND) N.V.,ALL  DUTCH OFFICES BLAAK 555,,,3011 GB  ROTTERDAM\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td colspan=\"\" rowspan=\"\" align=\"center\" width=\"133.65\" height=\"23.4992\">\r\n");
      out.write("\t\t\t\t\t0000000412\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td colspan=\"\" rowspan=\"\" align=\"center\" width=\"282.15\" height=\"23.4992\">\r\n");
      out.write("\t\t\t\t\tYANTAI YOUMEI FOOD CO.,LTD  SHILIANG TOWN,LONGKOU CITY, SHANDONG CHINA\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td colspan=\"\" rowspan=\"\" align=\"center\" width=\"133.65\" height=\"23.4992\">\r\n");
      out.write("\t\t\t\t\t1000000000\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td colspan=\"\" rowspan=\"\" align=\"center\" width=\"148.5\" height=\"23.4992\">\r\n");
      out.write("\t\t\t\t\t2007-04-06 \r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td colspan=\"\" rowspan=\"\" align=\"center\" width=\"133.65\" height=\"23.4992\">\r\n");
      out.write("\t\t\t\t\t540000.000\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td colspan=\"\" rowspan=\"\" align=\"center\" width=\"148.5\" height=\"23.4992\">\r\n");
      out.write("\t\t\t\t\t540000.000\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td colspan=\"\" rowspan=\"\" align=\"center\" width=\"133.65\" height=\"23.4992\">\r\n");
      out.write("\t\t\t\t\t\"无\"\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td colspan=\"\" rowspan=\"\" align=\"center\" width=\"141.075\" height=\"23.4992\">\r\n");
      out.write("\t\t\t\t\t\"贸易\"\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t<tr valign=\"middle\">\r\n");
      out.write("\t\t\t\t<td colspan=\"\" rowspan=\"\" align=\"center\" width=\"148.5\" height=\"23.4992\">\r\n");
      out.write("\t\t\t\t\t0704050000014170\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td colspan=\"\" rowspan=\"\" align=\"center\" width=\"141.075\" height=\"23.4992\">\r\n");
      out.write("\t\t\t\t\tLC07000900026\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td colspan=\"\" rowspan=\"\" align=\"center\" width=\"155.92499999999998\" height=\"23.4992\">\r\n");
      out.write("\t\t\t\t\tFTSBNL2R\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td colspan=\"\" rowspan=\"\" align=\"center\" width=\"282.15\" height=\"23.4992\">\r\n");
      out.write("\t\t\t\t\tFORTIS BANK (NEDERLAND) N.V.,ALL  DUTCH OFFICES BLAAK 555,,,3011 GB  ROTTERDAM\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td colspan=\"\" rowspan=\"\" align=\"center\" width=\"133.65\" height=\"23.4992\">\r\n");
      out.write("\t\t\t\t\t0000000412\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td colspan=\"\" rowspan=\"\" align=\"center\" width=\"282.15\" height=\"23.4992\">\r\n");
      out.write("\t\t\t\t\tYANTAI YOUMEI FOOD CO.,LTD. SHILIANG TOWN,LONGKOU  CITY,SHANDONG CHINA\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td colspan=\"\" rowspan=\"\" align=\"center\" width=\"133.65\" height=\"23.4992\">\r\n");
      out.write("\t\t\t\t\t1000000000\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td colspan=\"\" rowspan=\"\" align=\"center\" width=\"148.5\" height=\"23.4992\">\r\n");
      out.write("\t\t\t\t\t2007-03-23 \r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td colspan=\"\" rowspan=\"\" align=\"center\" width=\"133.65\" height=\"23.4992\">\r\n");
      out.write("\t\t\t\t\t270000.000\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td colspan=\"\" rowspan=\"\" align=\"center\" width=\"148.5\" height=\"23.4992\">\r\n");
      out.write("\t\t\t\t\t270000.000\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td colspan=\"\" rowspan=\"\" align=\"center\" width=\"133.65\" height=\"23.4992\">\r\n");
      out.write("\t\t\t\t\t\"无\"\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td colspan=\"\" rowspan=\"\" align=\"center\" width=\"141.075\" height=\"23.4992\">\r\n");
      out.write("\t\t\t\t\t\"贸易\"\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t<tr valign=\"middle\">\r\n");
      out.write("\t\t\t\t<td colspan=\"\" rowspan=\"\" align=\"center\" width=\"148.5\" height=\"23.4992\">\r\n");
      out.write("\t\t\t\t\t0704060000014232\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td colspan=\"\" rowspan=\"\" align=\"center\" width=\"141.075\" height=\"23.4992\">\r\n");
      out.write("\t\t\t\t\tLC07060700003\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td colspan=\"\" rowspan=\"\" align=\"center\" width=\"155.92499999999998\" height=\"23.4992\">\r\n");
      out.write("\t\t\t\t\tCZNBKRSE\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td colspan=\"\" rowspan=\"\" align=\"center\" width=\"282.15\" height=\"23.4992\">\r\n");
      out.write("\t\t\t\t\tKOOKMIN BANK,(HEAD OFFICE) 9-1, 2-KA,NAMDAEMUN-RO,CHUNG-KU, 100 092 SEOUL\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td colspan=\"\" rowspan=\"\" align=\"center\" width=\"133.65\" height=\"23.4992\">\r\n");
      out.write("\t\t\t\t\t0000000124\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td colspan=\"\" rowspan=\"\" align=\"center\" width=\"282.15\" height=\"23.4992\">\r\n");
      out.write("\t\t\t\t\tYANTAI M AND K FOODS CO.,LTD.  DAJIJIA INDUSTRIAL ZONE  YANTAI ETDZ SHANDONG CHINA\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td colspan=\"\" rowspan=\"\" align=\"center\" width=\"133.65\" height=\"23.4992\">\r\n");
      out.write("\t\t\t\t\t1012000000\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td colspan=\"\" rowspan=\"\" align=\"center\" width=\"148.5\" height=\"23.4992\">\r\n");
      out.write("\t\t\t\t\t2007-04-06 \r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td colspan=\"\" rowspan=\"\" align=\"center\" width=\"133.65\" height=\"23.4992\">\r\n");
      out.write("\t\t\t\t\t273259.800\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td colspan=\"\" rowspan=\"\" align=\"center\" width=\"148.5\" height=\"23.4992\">\r\n");
      out.write("\t\t\t\t\t273259.800\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td colspan=\"\" rowspan=\"\" align=\"center\" width=\"133.65\" height=\"23.4992\">\r\n");
      out.write("\t\t\t\t\t\"无\"\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td colspan=\"\" rowspan=\"\" align=\"center\" width=\"141.075\" height=\"23.4992\">\r\n");
      out.write("\t\t\t\t\t\"贸易\"\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t<tr valign=\"middle\">\r\n");
      out.write("\t\t\t\t<td colspan=\"\" rowspan=\"\" align=\"center\" width=\"148.5\" height=\"22.2624\">\r\n");
      out.write("\t\t\t\t\t统计\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td colspan=\"\" rowspan=\"\" align=\"center\" width=\"141.075\" height=\"22.2624\">\r\n");
      out.write("\t\t\t\t\t2064\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td colspan=\"\" rowspan=\"\" align=\"center\" width=\"155.92499999999998\" height=\"22.2624\">\r\n");
      out.write("\t\t\t\t\t2064\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td colspan=\"\" rowspan=\"\" align=\"center\" width=\"282.15\" height=\"22.2624\">\r\n");
      out.write("\t\t\t\t\t2064\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td colspan=\"\" rowspan=\"\" align=\"center\" width=\"133.65\" height=\"22.2624\">\r\n");
      out.write("\t\t\t\t\t2064\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td colspan=\"\" rowspan=\"\" align=\"center\" width=\"282.15\" height=\"22.2624\">\r\n");
      out.write("\t\t\t\t\t2064\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td colspan=\"\" rowspan=\"\" align=\"center\" width=\"133.65\" height=\"22.2624\">\r\n");
      out.write("\t\t\t\t\t2064\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td colspan=\"\" rowspan=\"\" align=\"center\" width=\"148.5\" height=\"22.2624\">\r\n");
      out.write("\t\t\t\t\t2064\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td colspan=\"\" rowspan=\"\" align=\"center\" width=\"133.65\" height=\"22.2624\">\r\n");
      out.write("\t\t\t\t\t2064\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td colspan=\"\" rowspan=\"\" align=\"center\" width=\"148.5\" height=\"22.2624\">\r\n");
      out.write("\t\t\t\t\t2064\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td colspan=\"\" rowspan=\"\" align=\"center\" width=\"133.65\" height=\"22.2624\">\r\n");
      out.write("\t\t\t\t\t2064\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td colspan=\"\" rowspan=\"\" align=\"center\" width=\"141.075\" height=\"22.2624\">\r\n");
      out.write("\t\t\t\t\t2064\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("</table>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("</tbody>\r\n");
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
