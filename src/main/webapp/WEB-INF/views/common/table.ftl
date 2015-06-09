<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<title>报表系统</title>
<%@ include file="head.jsp"%>
<style>
	table.ta_1{width:100%;text-align:center;}
	table.ta_1 td tr{text-align:center;table-layout:fixed;word-break:break-all;}
</style>
</head>
<tbody>
<div id="content">
</div>
 <#if root??>
 <div class="container" align="center" width="${root.tableWidth}">
  <h1>Excel Converter</h1>
 <table class="ta_1" width="${root.tableWidth}" height="auto" border="1">
	<#list root.htmlRowStructure as item>
		<tr valign="middle">
			<#if item.htmlTdStructure??>
				<#list item.htmlTdStructure as itemTd>
				<td colspan="${itemTd.colspan!}" rowspan="${itemTd.rowspan!}" align="center" width="${itemTd.width!}" height="${itemTd.height!}">
				<#if itemTd.value??>
					${itemTd.value}
				</#if>
				</td>
				</#list>
			</#if>
		</tr>
	</#list>
</table>
</div>

</#if>
</tbody>
</html>
