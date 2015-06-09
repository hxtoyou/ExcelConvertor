<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="resources" value="${pageContext.request.contextPath}/static-resources" />
<c:set var="requestURI" value="${pageContext.request.requestURI}" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- <meta http-equiv="X-UA-Compatible" content="IE=Edge"> -->
<meta charset="UTF-8">
<link rel="shortcut icon" type="image/x-icon" href="${resources}/favicon.ico">
<%-- <link href="${resources}/app.css" rel="stylesheet"> --%>
<link href="${resources}/scripts/app/common/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
<link href="${resources}/scripts/app/common/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet">
<link href="${resources}/scripts/app/common/bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="${resources}/scripts/app/common/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="${resources}/styles/ui/zTreeStyle/zTreeStyle.css" rel="stylesheet">
<link href="${resources}/styles/ui/jquery.autocomplete.css" rel="stylesheet">
<link href="${resources}/styles/ui/select2.css" rel="stylesheet">
<%-- <link href="${resources}/styles/ui/select2-bootstrap.css" rel="stylesheet"> --%>
<link href="${resources}/styles/main.css" rel="stylesheet">
<!--[if IE 7]>
<link href="${resources}/styles/ui/font-awesome-ie7.min.css" rel="stylesheet"/>
<![endif]-->
<!--[if lte IE 8]>
<link href="${resources}/ui/ace-ie.min.css" rel="stylesheet"/>
<![endif]-->
<%-- <script src="${resources}/app.js"></script> --%>
<script src="${resources}/scripts/app/common/date.js" type="text/javascript"></script>
<script src="${resources}/scripts/app/common/jquery-1.7.2.min.js" type="text/javascript"></script>
<script src="${resources}/scripts/app/common/serialize-object.js" type="text/javascript"></script>
<script src="${resources}/scripts/app/common/bootstrap/js/bootstrap.js" type="text/javascript"></script>
<script src="${resources}/scripts/app/common/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${resources}/scripts/app/common/utils.js" type="text/javascript"></script>
<script src="${resources}/scripts/app/common/handlebars-v3.0.3.js" type="text/javascript"></script>
<script type="text/javascript">
	var contextPath = "${contextPath}";
	var resources = "${resources}";
	var requestURI = "${requestURI}";
</script>
