<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<title>报表系统</title>
<%@ include file="common/head.jsp"%>
<%@ include file="common/template.jsp" %>>
</head>
<body>
 <div class="container" align="center">
 <h1>THE BEGINING!</h1>
	<form>
	  <fieldset>
	    <legend>Legend</legend>
	  </fieldset>
	</form>
	<button id="create" class="btn btn-large btn-primary">
		<i class="icon-eject"></i>文件上传
	</button>
	<button id="generate" class="btn btn-large btn-primary">
		生成表格
	</button>
	<div id="create-modal" class="modal modal-md hide">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal">×</button>
			<h5 class="green">
				<i class="icon-plus-sign-alt"></i> 添加文件
			</h5>
		</div>
		<div class="modal-body">
			<div class="row-fluid">
				<div class="span12">
					<form id="create-form" class="form-horizontal"
						style="margin-bottom: 0px;">
						<div class="control-group">
							<label class="control-label " for="documentName">文档名称</label>
							<div class="controls">
								<input id="create_documentName" name="documentName" type="text">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label " for="keyWord">查询关键字</label>
							<div class="controls">
								<input id="create_keyWord" name="keyWord" type="text">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label " for="securityLevel">开放等级</label>
							<div class="controls">
								<select id="create_security_level" name="securityLevel">
									<option value="3" title="所有人可见">公开(所有人可见)</option>
									<option value="2" title="同组织内可见">保护(同组织内可见)</option>
									<option value="1" title="仅自己可见">私有(仅自己可见)</option>
								</select>
							</div>
						</div>
						<div class="control-group" style="display: none;">
							<label class="control-label" for="credential">附件</label>
							<div class="controls">
								<input id="create_attachment" readonly name="attachment"
									type="text" class="span10" style="width: 350px;"> <input
									value="删除" type="button" id="create-file-delete"
									class="btn btn-small btn-success span2" style="width: 48px;">
							</div>
						</div>
						<input type="hidden" name="folderId" id="create_folderId">
					</form>
					<form id="create-file-form" action="/simpleupload"
						class="form-horizontal" method="post"
						enctype="multipart/form-data" target="acceptFrame">
						<div class="control-group">
							<label class="control-label" for="credential">附件</label>
							<div class="controls">
								<input name="file" id="file" type="file">
							</div>
						</div>
					</form>
				</div>
				<div id="create-message-alert" class="row-fluid hide">
					<div class="span12">
						<div class="alert alert-error">
							<i class="icon-remove"></i> <span id="create-message-content"></span>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="modal-footer">
			<button id="create-save" class="btn btn-small btn-success">
				<i class="icon-ok"></i> 确定
			</button>
			<button class="btn btn-small" data-dismiss="modal">
				<i class="icon-remove"></i> 取消
			</button>
		</div>
	</div>
	<div  id="plan-table"></div>
</div>
<iframe name="acceptFrame" border="1" frameborder="1" width="100"
height="100" style="display: none"></iframe>
</body>
<script src="${resources}/scripts/app/fileupload/index.js" type="text/javascript"></script>
<script src="${resources}/scripts/app/excel/index.js" type="text/javascript"></script>
</html>
