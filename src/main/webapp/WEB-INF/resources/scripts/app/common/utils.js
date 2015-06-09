/**
 * 工具类。
 */

	var utils = {};

	/**
	 * 按钮
	 */
	utils.button = {};
	var buttonDisableCSS = 'disabled';

	utils.button.disable = function(ids) {
		$.each(ids, function(key, value) {
			$('#' + value).addClass(buttonDisableCSS);
		});
	};

	utils.button.enable = function(ids) {
		$.each(ids, function(key, value) {
			$('#' + value).removeClass(buttonDisableCSS);
		});
	};

	utils.button.isDisable = function(id) {
		return $('#' + id).hasClass(buttonDisableCSS);
	};

	/**
	 * 弹出框。
	 */
	utils.modal = {};

	utils.modal.show = function(prefix) {
		var $modal = $('#' + prefix + '-modal');
		$modal.modal({
			backdrop : 'static'
		});

		// jquery-ui没有考虑具有marginLeft时left的取值问题
		function getContainerment() {
			var marginLeft = parseInt($modal.css('marginLeft'), 10) || 0, marginTop = parseInt($modal.css('marginTop'), 10) || 0, width = $modal.outerWidth(), height = $modal
					.outerHeight(), wholeWidth = $(document).width(), wholeHeight = $(document).height();

			return [ -marginLeft, -marginTop, wholeWidth - width - marginLeft, wholeHeight - height - marginTop ];
		}

//		$modal.draggable({
//			addClasses : false,
//			handle : '.modal-header',
//			containment : getContainerment()
//		});

		// 完全居中
		var screenWidth = $(window).width(), screenHeight = $(window).height();
		var modalWidth = $modal.width(), modalHeight = $modal.height();
		var modalTop = 0, modalLeft = 0;

		if (screenHeight > modalHeight) {
			modalTop = (screenHeight - modalHeight) / 2;
		}
		if (screenWidth > modalWidth) {
			modalLeft = (screenWidth - modalWidth) / 2;
		}

		$modal.offset({
			top : modalTop + $(document).scrollTop(),
			left : modalLeft
		});

		return $modal;
	};

	utils.modal.hide = function(prefix) {
		$('#' + prefix + '-modal').modal('hide');
	};

	utils.modal.reset = function(prefix) {
		document.getElementById(prefix + '-form').reset();
		$('#' + prefix + '-message-content').html('');
		$('#' + prefix + '-message-alert').hide();
	};

	utils.modal.message = function(prefix, messages) {
		var message = '发生错误，请重试';

		if (messages !== null) {
			$.each(messages, function(key, value) {
				message = value;
			});
		}

		$('#' + prefix + '-message-content').html(message);
		$('#' + prefix + '-message-alert').show();
	};

	utils.modal.showProcess = function(processId) {
		var _html = [ '<div id="' + processId + '-modal" class="modal hide" style="z-index:1056;">', '<div class="modal-header">', '<h5 class="green">',
				'<i class="icon-upload"></i> 上传中请稍候...', '</h5>', '</div>', '<div class="modal-body">', '<div class="row-fluid">',
				'<div class="control-group" id="processParent">', '<label class="control-label" ></label>', '<div class="controls">',
				'<div class="progress progress-striped active span12" >', '<div class="bar" id="' + processId + '" style="width: 1px;"></div>', '</div>',
				'</div>', '</div>', '</div>', '</div>', '<div class="modal-footer">', '<button class="btn btn-small" data-dismiss="modal">', '</button>',
				'</div>', '</div>' ];
		if ($('#process-modal').length === 0) {
			$(_html.join('')).appendTo($("body"));
			$('<div class="modal-backdrop  in" id="' + processId + '-modal-black" style="z-index:1055;"></div>').appendTo($("body"));
		} else {
			$('#' + processId + '-modal').show();
			$('#' + processId + '-modal-black').show();
		}
		// 完全居中
		var $modal = $('#' + processId + '-modal');
		var screenWidth = $(document).width(), screenHeight = $(document).height();
		var modalWidth = $modal.width(), modalHeight = $modal.height();
		var modalTop = 0, modalLeft = 0;

		if (screenHeight > modalHeight) {
			modalTop = (screenHeight - modalHeight) / 2;
		}
		$modal.offset({
			top : modalTop
		});
		this.count = 1;
		var me = this;
		this.process = $('#' + processId);
		this.reset = function() {
			me.count = 1;
			me.process.css("width", 1 + "px");
		};
		this.loopFunc = function() {
			me.count += 2;
			me.process.css("width", me.count + "px");
		};
		this.intervalReference = setInterval(me.loopFunc, 200);
		this.stop = function() {
			clearInterval(me.intervalReference);
			this.reset();
			this.close();
		};
		this.reset();
		this.close = function() {
			$('#' + processId + '-modal').remove();
			$('#' + processId + '-modal-black').remove();
		};
		$('#' + processId + '-modal').show();
	};

	utils.modal.showAlert = function(_message,title,winCode,callBackFunc) {
		var alertCode=winCode?winCode:'';
		if ($("#"+alertCode+"__alertWindow-modal").length <= 0) {
			var _winHtml = [];
			_winHtml.push('<div id="'+alertCode+'__alertWindow-modal" class="modal hide">');
			_winHtml.push('<div class="modal-header">');
			_winHtml.push('<button type="button" class="close" data-dismiss="modal">×</button>');
			_winHtml.push('<h5 class="orange">');
			_winHtml.push('<i class="icon-list"></i> '+(title?title:"提示"));
			_winHtml.push('</h5>');
			_winHtml.push('</div>');
			_winHtml.push('<div class="modal-body">');
			_winHtml.push('<div class="row-fluid">');
			_winHtml.push('<div class="span12" id="'+alertCode+'__message_panel">');
			_winHtml.push('<i class="icon-warning-sign"></i> 提示：确实要关闭本提醒？');
			_winHtml.push('</div>');
			_winHtml.push('<div id="'+alertCode+'__alertWindow-message-alert" class="row-fluid hide">');
			_winHtml.push('<div class="span12">');
			_winHtml.push('<div class="alert alert-error">');
			_winHtml.push('<i class="icon-remove"></i>');
			_winHtml.push('<span id="'+alertCode+'__alertWindow-message-content"></span>');
			_winHtml.push('</div>');
			_winHtml.push('</div>');
			_winHtml.push('</div>');
			_winHtml.push('</div>');
			_winHtml.push('</div>');
			_winHtml.push('<div class="modal-footer">');
			_winHtml.push('<button class="btn btn-small" data-dismiss="modal" id="'+alertCode+'__alertWindow_confirm" >');
			_winHtml.push('<i class="icon-remove"></i> 好的');
			_winHtml.push('</button>');
			_winHtml.push('</div>');
			_winHtml.push('</div>');
			$(_winHtml.join('')).appendTo($('body'));
			if(callBackFunc!==undefined){
				$('#'+alertCode+'__alertWindow_confirm').bind('click',function(){
					callBackFunc();
				});
			}
		}
		utils.modal.show(alertCode+'__alertWindow');
		$('#'+alertCode+'__message_panel').html('<i class="icon-warning-sign"></i> ' + _message );

	};
	utils.modal.showUpload = function(url, callBack, winTitle) {
		var callbackFunc = 'callbackFunc';
		if ($("#__acceptFrame").length <= 0) {
			$('<iframe id="__acceptFrame" name="__acceptFrame" style="display: none"></iframe>').appendTo($('body'));
			var _winHtml = [];
			_winHtml.push('	<div id="__import-modal" class="modal hide" style="z-index:1053;">');
			_winHtml.push('<div class="modal-header">');
			_winHtml.push('<button type="button" class="close" data-dismiss="modal">×</button>');
			_winHtml.push('<h5 class="blue">');
			_winHtml.push('<i class="icon-upload"></i> ' + winTitle);
			_winHtml.push('</h5>');
			_winHtml.push('</div>');
			_winHtml.push('<div class="modal-body">');
			_winHtml.push('<div class="row-fluid">');
			_winHtml.push('<div class="span12">');
			_winHtml.push('<form id="__import-form" class="form-horizontal" action="' + url + '" method="post"');
			_winHtml.push('enctype="multipart/form-data" target="__acceptFrame"');
			_winHtml.push('style="margin-bottom: 0px;">');
			_winHtml.push('<div class="control-group">');
			_winHtml.push('<label class="control-label" for="file">上传文件</label>');
			_winHtml.push('<div class="controls">');
			_winHtml.push('<input id="__import_file" name="file" type="file">');
			_winHtml.push('</div>');
			_winHtml.push('</div><input type="hidden" name="callBackFunction" value="callbackFunc">');
			_winHtml.push('</form>');
			_winHtml.push('</div>');
			_winHtml.push('<div id="__import-message-alert" class="row-fluid hide">');
			_winHtml.push('<div class="span12">');
			_winHtml.push('<div class="alert alert-error">');
			_winHtml.push('<i class="icon-remove"></i> <span id="__import-message-content"></span>');
			_winHtml.push('</div>');
			_winHtml.push('</div>');
			_winHtml.push('</div>');
			_winHtml.push('</div>');
			_winHtml.push('</div>');
			_winHtml.push('<div class="modal-footer">');
			_winHtml.push('<button class="btn btn-small" data-dismiss="modal">');
			_winHtml.push('<i class="icon-remove" id=""></i> 关闭');
			_winHtml.push('</button>');
			_winHtml.push('</div>');
			_winHtml.push('</div>');
			$(_winHtml.join('')).appendTo($('body'));
			$('<div class="modal-backdrop  in" id="__acceptFrame-modal-black" style="z-index:1052;display:none"></div>').appendTo($("body"));
			$('#__import_file').bind('change', function onSelectChange() {
				$('#__import-form').submit();
			});
			$('#__import-form').submit(function _onSubmit() {
				var process = new utils.modal.showProcess('_process');
				window.process = process;
			});
			$('#__acceptFrame').load(function() {
				window.process.stop();
				window.process = null;
			});
			// bind event
			$('#__import-modal').find('button[data-dismiss="modal"]').bind('click', function() {
				$('#__acceptFrame-modal-black').hide();
				$('#__import-modal').hide();
			});
			// css
			var $importModal = $('#__import-modal');
			var screenWidth = $(document).width(), screenHeight = $(document).height();
			var modalWidth = $importModal.width(), modalHeight = $importModal.height();
			var modalTop = 0, modalLeft = 0;

			if (screenHeight > modalHeight) {
				modalTop = (screenHeight - modalHeight) / 2;
			}
			$importModal.offset({
				top : modalTop
			});
		}
		utils.modal.reset('__import');

		$('#__acceptFrame-modal-black').show();
		$('#__import-modal').show();
		var testFunction = function(data) {
			$('#__acceptFrame-modal-black').hide();
			$('#__import-modal').hide();
			callBack(data);
		};
		window.$ = $;
		window[callbackFunc] = testFunction;

	};
	/**
	 * 下拉列表。
	 */
	utils.select = {};

	utils.select.remote = function(ids, url, value, display, blank, blankText) {
		$.get(url, function(data) {
			var html = '';
			if (blank) {
				if (blankText !== undefined) {
					html += '<option value="" class="light-grey">' + blankText + '</option>';
				} else {
					html += '<option value=""></option>';
				}
			}
			$.each(data.data, function(entryIndex, entry) {
				var valueArray = value.split('.');
				if (valueArray.length === 1) {
					html += '<option value="' + entry[value] + '">' + utils.html.encode(entry[display]) + '</option>';
				} else if (valueArray.length === 2) {
					html += '<option value="' + entry[valueArray[0]][valueArray[1]] + '">' + utils.html.encode(entry[display]) + '</option>';
				}
			});

			$.each(ids, function(key, value) {
				$('#' + value).html(html);
			});
		});
	};
	utils.select.remoteMutiField = function(ids, url, value, display, blank, blankText) {
		$.get(url, function(data) {
			var html = '';
			if (blank) {
				if (blankText !== undefined) {
					html += '<option value="">' + blankText + '</option>';
				} else {
					html += '<option value=""></option>';
				}
			}
			$.each(data.data, function(entryIndex, entry) {
				var showText = '';
				var itemName = '';
				for (var i = 0; i < display.length; i++) {
					if (entry[display[i]] && typeof entry[display[i]] === 'object') {
						showText += utils.html.encode(entry[display[i]].itemName);
					} else {
						showText += utils.html.encode(entry[display[i]]);
					}
					showText += "&nbsp;";
				}
				html += '<option value="' + entry[value] + '">' + showText + '</option>';
			});

			$.each(ids, function(key, value) {
				$('#' + value).html(html);
			});
		});
	};
	utils.select.setOption = function(selectId, optionValue) {
		/**
		 * 移除已选择的option，在非IE浏览器可使用： $('#' + id + ' >
		 * option').removeAttr('selected'); 为兼容IE浏览器，只能如此循环替换。。。
		 */
		$.each($('#' + selectId + ' > option'), function(key, value) {
			var option = $(value);
			option.replaceWith('<option value="' + option.attr('value') + '">' + option.text() + '</option>');
		});
		var selectOption = $('#' + selectId + ' > option[value="' + optionValue + '"]');
		selectOption.replaceWith('<option value="' + optionValue + '" selected="selected">' + selectOption.text() + '</option>');
	};

	utils.select.setOptionByName = function(formPrefix, selectName, optionValue) {
		$.each($('#' + formPrefix + '-form select[name="' + selectName + '"] > option'), function(key, value) {
			var option = $(value);
			option.replaceWith('<option value="' + option.attr('value') + '">' + option.text() + '</option>');
		});
		var selectOption = $('#' + formPrefix + '-form select[name="' + selectName + '"] > option[value="' + optionValue + '"]');
		selectOption.replaceWith('<option value="' + optionValue + '" selected="selected">' + selectOption.text() + '</option>');
	};

	/**
	 * 表单
	 */
	utils.form = {};

	utils.form.fill = function(prefix, model) {
		var inputs = $('#' + prefix + '-form').find(':input');

		$.each(inputs, function(key, value) {
			if (value.tagName === 'SELECT') {
				var propertys = $(value).attr('name').replace(/\[/g, '.').replace(/\]/g, '').split('.');

				// 目前只处理1层、2层或3层嵌套的情况
				if (propertys.length === 1) {
					utils.select.setOptionByName(prefix, $(value).attr('name'), model[propertys[0]]);
				} else if (propertys.length === 2) {
					utils.select.setOption($(value).attr('id'), model[propertys[0]][propertys[1]]);
				} else if (propertys.length === 3) {
					utils.select.setOption($(value).attr('id'), model[propertys[0]][propertys[1]][propertys[2]]);
				}
			} else if (value.tagName === 'TEXTAREA') {
				$(value).html(model[$(value).attr('name')]);
			} else {
				// 处理日期控件赋值问题
				if ($(value).attr('type') === 'datetime') {
					$(value).datepicker('update', model[$(value).attr('name')]);
				} else {
					$(value).val(model[$(value).attr('name')]);
				}
			}
		});
	};

	utils.form.serialize = function(prefix) {
		return $('#' + prefix + '-form').serializeObject();
	};

	utils.form.buildParams = function(formId, ignoreEmptyParam) {
		var ignoreParam = true;
		var urlParams = '';

		$.each($('#' + formId).serializeArray(), function(key, value) {
			if (ignoreEmptyParam !== undefined) {
				ignoreParam = ignoreEmptyParam;
			}

			if (ignoreParam) {
				if (value.value !== '') {
					urlParams += '&' + value.name + '=' + value.value;
				}
			} else {
				urlParams += '&' + value.name + '=' + value.value;
			}

		});
		return encodeURI(urlParams);
	};

	utils.form.groupTree = function(treewindow, _treePanel, _triggerName, aimElm) {
		var me = this;
		if ($('#' + treewindow).length === 0) {
			var _html = '<div id="' + treewindow + '" class="menuContent " style="display: none; position: absolute;">';
			_html += '    <ul id="' + _treePanel + '" class="ztree" style="margin-top: 0; width: 160px;"></ul>';
			_html += '   </div>';
			$('body').append($(_html));
		}
		this.beforeClick = function(treeId, treeNode) {
			return true;
		};
		this.onClick = function(e, treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj(_treePanel), nodes = zTree.getSelectedNodes();
			var cityObj = $("#" + aimElm);
			cityObj.val(nodes[0].name);
			cityObj.attr("data-id", nodes[0].id);
		};
		this.setting = {
			view : {
				dblClickExpand : false
			},
			async : {
				enable : true,
				url : contextPath + '/system/groups',
				type : "get",
				dataFilter : function(treeId, parentNode, responseData) {
					return responseData.data[0].groupEntities;
				}
			},
			data : {
				key : {
					children : 'groupEntities'
				}
			},
			callback : {
				beforeClick : me.beforeClick,
				onClick : me.onClick
			}
		};

		this.onKeyDown = function(event) {
			if (!(event.target.id === "menuBtn" || event.target.id === treewindow || $(event.target).parents("#" + treewindow).length > 0)) {
				me.hideTree();
			}
		};
		this.showTree = function() {
			var cityObj = $("#" + aimElm);
			var cityOffset = cityObj.offset();
			$("#" + treewindow).css({
				left : cityOffset.left + "px",
				top : cityOffset.top + cityObj.outerHeight() + "px"
			}).slideDown("fast");
			$("#" + treewindow).css('z-index', 1090);
			$("#" + treewindow).css("background-color", 'white');
			$("#" + treewindow).css("-webkit-box-shadow", '0 3px 7px rgba(0, 0, 0, 0.3)');
			$("#" + treewindow).css("border", '1px solid rgba(0, 0, 0, 0.3)');
			$("body").bind("mousedown", me.onKeyDown);
		};
		this.hideTree = function() {
			$("#" + treewindow).fadeOut("fast");
			$("body").unbind("mousedown", me.onKeyDown);
		};
		var currentTree = $.fn.zTree.init($('#' + _treePanel), me.setting);
		$('#' + _triggerName).bind('click', function() {
			me.showTree();
		});
		return currentTree;
	};

	/**
	 * 日期控件
	 */
	utils.input = {};

	utils.input.date = function(selector, format) {
		$(selector).datepicker({
			autoclose : true,
			format : format === undefined ? 'yyyy-mm-dd' : format
		}).next().on('click', function() {
			$(this).prev().focus();
		});
	};

	/**
	 * 编码
	 */
	utils.html = {};

	utils.html.encode = function(string) {
		return $('<div/>').text(string).html();
	};

	utils.html.decode = function(string) {
		return $('<div/>').html(string).text();
	};

	/**
	 * 树
	 */
	utils.tree = {};

	utils.tree.group = function(id, url, onClick) {
		/**
		 * 处理图标路径
		 */
		function handleIcon(childrens) {
			if (childrens) {
				$.each(childrens, function(index, item) {
					if (item.groupEntities) {
						handleIcon(item.groupEntities);
					}
					switch (item.queryLabel) {
						case 'mine':
							item.icon = resources + '/images/icons/building.png';
							break;
						case 'office':
							item.icon = resources + '/images/icons/monitor.png';
							break;
						case 'team':
							item.icon = resources + '/images/icons/plugin_disabled.png';
							break;
						case 'other':
							item.icon = resources + '/images/icons/house.png';
							break;
						default:
							item.icon = resources + '/images/icons/page_white.png';
							break;
					}
				});
			}
		}

		// 机构树配置
		var groupTreeSetting = {
			view : {
				selectedMulti : false,
				showTitle : false
			},
			async : {
				enable : true,
				url : url,
				type : "get",
				dataFilter : function(treeId, parentNode, responseData) {
					responseData.data.iconOpen = resources + "/images/icons/chart_organisation.png";
					responseData.data.iconClose = resources + "/images/icons/chart_organisation.png";

					handleIcon(responseData.data.groupEntities);

					return responseData.data;
				}
			},
			data : {
				key : {
					children : 'groupEntities'
				}
			},
			callback : {
				onClick : onClick,
				onAsyncSuccess : function(event, treeId, treeNode, msg) {
					var groupTree = $.fn.zTree.getZTreeObj(treeId);
					groupTree.expandAll(true);
				}
			}
		};

		return $.fn.zTree.init($('#' + id), groupTreeSetting);
	};

