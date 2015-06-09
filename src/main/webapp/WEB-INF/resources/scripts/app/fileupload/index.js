	$('#create').click(function() {
		if ($('#create').hasClass('disabled')) {
			return;
		}
//		Utils.modal.reset('create');
		utils.modal.show('create');
		$('#create-file-delete').trigger('click');
	});
	// 文件上传
	$('#file').bind('change', function() {
		var val = $('#file').val();
		var postfix = val.substring(val.lastIndexOf(".") + 1).toLowerCase();
		var types = ['doc', 'docx', 'ppt', 'pptx', 'xls', 'xlsx', 'txt', 'pdf', 'odt', 'ods', 'odp', 'odg', 'wps', 'htm', 'html','dwg','dxf','cad','rar','zip'];
		if ($.inArray(postfix, types) < 0) {
			utils.modal.showAlert('不支持当前格式文件上传', '提示', 'typeAlert');
			return;
		}
		if ($('#file').val() !== '') {
			$('#create-file-form').submit();
			var process = new utils.modal.showProcess('process');
			window.process = process;
		}
	});
	// 保存
	$('#create-save').click(function() {
		if ($('#create-save').hasClass('disabled')) {
			return;
		}

		var object = utils.form.serialize('create');
		// 验证
		if (object.documentName === '') {
			utils.modal.message('create', [ '请输入文档名称' ]);
			return;
		}
		if (object.attachment === '') {
			utils.modal.message('create', [ '请添加附件' ]);
			return;
		}
		var attachment = {
			id : $('#create_attachment').attr('data-id'),
			simpleName : object.documentName
		};

		delete object.filePath;
		object.attachment = attachment;
		$.post('/spmi/document/documents',{"documentName":object.documentName,"keyWord":object.keyWord,"attachmentId":object.attachment.id,"securityLevel":object.securityLevel}, function(data) {
			if (data.success) {
				grid.refresh();
				utils.modal.hide('create');
			} else {
				utils.modal.message('create', data.errors);
			}
		});
	});
	// 配置表格列
	var fields = [ {
		header : '附件',
		name : 'attachment',
		width : 300,
		render : function(v) {
			var name = v.simpleName;
			var html=null;
			if(v.swfPath){
				html = '<a href="javascript:void(0);" doc_id=' + v.id + ' title=' + name + '>' + name.substring(0, 20) + '</a>&nbsp;&nbsp;';
			}else{
				html=v.simpleName;
			}
			html += '<a href="' + v.filePath + '" target="_blank" class="pull-right">下载</a>';
			return html;
		}
	}, {
		header : '开放程度',
		name : 'securityLevel',
		render : function(v) {
			return securityLevel[v.toString()];
		}
	}, {
		header : '创建人',
		name : 'createBy',
		width : 80
	}, {
		header : '创建时间',
		name : 'createTime',
		width : 145
	}, {
		header : '更新人',
		name : 'updateBy'
	} ];

	// 计算表格高度和行数
	var gridHeight = $(window).height() - ($('.navbar').height() + $('.page-toolbar').height() + $('.page-header').height() + 100);
	var pageSize = Math.floor(gridHeight / 21);

	// 配置表格
	var defaultUrl = contextPath + '/spmi/document/documents?orderBy=id&order=desc&pageSize=' + pageSize;
//	var grid = new Grid({
//		parentNode : '#plan-table',
//		url : defaultUrl,
//		model : {
//			fields : fields,
//			needOrder : true,
//			orderWidth : 50,
//			height : gridHeight
//		},
//		onClick : function(target, data) {
//			changeButtonsStatus(this.selected, data);
//		},
//		onLoaded : function() {
//			changeButtonsStatus();
//		}
//	}).render();

	// 文件上传回调
	var callBack = function(data) {
		if (data.success) {
			var attachment = $('#create_attachment');
			var docName = $('#create_documentName');
			if (docName.val() === '') {
				docName.val(data.data.simpleName);
			}
			attachment.val(data.data.simpleName);
			attachment.attr('data-id', data.data.id);
			$('#create-file-form').hide();
			attachment.parent().parent().show();
			$('#create-save').removeClass('disabled');
		} else {
			alert(data.data);
		}
		window.process.stop();
		window.process = null;
	};
	window.callBack = callBack;