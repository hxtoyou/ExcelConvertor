<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<title>基础信息 - 山西王庄煤业数字矿山综合管理平台</title>
<%@ include file="../../common/head.jsp"%>
</head>
<body class="navbar-fixed">
	<div class="main-container container-fluid">
		<div class="main-content">
			<div class="page-content">
				<div class="row-fluid">
					<div class="span9 hide">
						<div class="tabbable">
							<ul class="nav nav-tabs">
								<li class="active">
									<a data-toggle="tab" href="#tab1">分站信息</a>
								</li>
								<li>
									<a data-toggle="tab" href="#tab2">测点信息</a>
								</li>
							</ul>
							<div class="tab-content" style="padding: 10px">
								<div id="tab1" class="tab-pane active">
									<div class="nav-query" style="position: inherit;">
										<form id="query-form1" class="form-inline" onsubmit="return false;" style="margin-bottom: 10px"></form>
									</div>
									<div id="grid1" class="row-fluid"></div>
								</div>
								<div id="tab2" class="tab-pane">
									<div class="nav-query" style="position: inherit;">
										<form id="query-form2" class="form-inline" onsubmit="return false;" style="margin-bottom: 10px">
											<select id="monitorSensorType2" name="monitorSensorType" style="width: 100px">
											</select>
										</form>
									</div>
									<div id="grid2" class="row-fluid"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	</script>
</body>
</html>
