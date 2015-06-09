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
	<#if root??>
		<#list root as item>
		  <div class="control-group span4.1 text-right">
		    <label class="control-label" for="inputEmail">${item.name!}</label>
		    <div class="controls">
		      <input type="text" id="${item.varName!}" name="${item.varName!}" placeholder="${item.name!}">
		    </div>
		  </div>
	  </#list>
	 </#if>
	</form>
	
</div>
<iframe name="acceptFrame" border="1" frameborder="1" width="100"
height="100" style="display: none"></iframe>
</body>
</html>
