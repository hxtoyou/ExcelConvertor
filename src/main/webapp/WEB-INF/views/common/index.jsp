<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<title>报表系统</title>
<%@ include file="head.jsp"%>
</head>
<body>
 <div class="container" align="center">
 <h1>THE BEGINING!</h1>
 <br>
  <br>
   <br>
    <br>
     <br>
      <br>
       <br>
        <br>
 	
	<form class="form-horizontal">
			  <div class="control-group span4.1 text-right">
		    <label class="control-label" for="inputEmail">开证日期</label>
		    <div class="controls">
		      <input type="text" id="issueDate" name="issueDate" placeholder="开证日期">
		    </div>
		  </div>
		  <div class="control-group span4.1 text-right">
		    <label class="control-label" for="inputEmail">开证金额</label>
		    <div class="controls">
		      <input type="text" id="issueAmt" name="issueAmt" placeholder="开证金额">
		    </div>
		  </div>
		  <div class="control-group span4.1 text-right">
		    <label class="control-label" for="inputEmail">信用证号码</label>
		    <div class="controls">
		      <input type="text" id="LCNO" name="LCNO" placeholder="信用证号码">
		    </div>
		  </div>
		  <div class="control-group span4.1 text-right">
		    <label class="control-label" for="inputEmail">机构编号</label>
		    <div class="controls">
		      <input type="text" id="OrgNo" name="OrgNo" placeholder="机构编号">
		    </div>
		  </div>
		  <div class="control-group span4.1 text-right">
		    <label class="control-label" for="inputEmail">机构名称</label>
		    <div class="controls">
		      <input type="text" id="OrgName" name="OrgName" placeholder="机构名称">
		    </div>
		  </div>
	</form>
	
</div>
<iframe name="acceptFrame" border="1" frameborder="1" width="100"
height="100" style="display: none"></iframe>
</body>
</html>
