<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<title>报表系统</title>
<%@ include file="../common/head.jsp"%>
<%@ include file="../common/template.jsp" %>
<style>
	table.ta_1{width:100%;text-align:center;}
	table.ta_1 td tr{text-align:center;table-layout:fixed;}
	div.display_1{
	display:none;}
</style>
</head>
<tbody>
<div style="float:left;">
<button  class="btn btn-large" id="statistic">
		切换统计表
</button>
</div>
<div class="container" align="center" id="out">
<div class="container" align="center" id="content">
</div>
<div class="container display_1" align="center" id="statisticContent">
</div>
<div class="container" align="center" id="bottom">
<button id="first" class="btn btn-large">
		第一页
</button>
<button id="back" class="btn btn-large">
		上一页
</button>
<button id="generate" class="btn btn-large">
		下一页
</button>
<button id="last" class="btn btn-large">
		最后一页
</button>
<p id="pageTail"></p>
</div>
</div>

</tbody>
<script src="${resources}/scripts/app/excel/index.js" type="text/javascript"></script>
</html>
