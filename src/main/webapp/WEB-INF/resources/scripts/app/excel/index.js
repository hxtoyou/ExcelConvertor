$(document).ready(function() {
	var operateUri = "/excel/generate/html"
	var pageSize=4;
	var pageNum=-1;
	var totalPage=0;
	var fileId = 291;
	var tempId = 270;
	var staticFileId =295;
	var staticTempId=168;
	var defaultUrl = contextPath + operateUri;
	var template = Handlebars.compile($('#content-template').html());
//	console.log("height:",window.height());
	$("#generate").click(function(){
		console.log("cl;icked");
		pageNum++;
		$.getJSON(defaultUrl,{
			"pageNumber": pageNum,
			"pageSize":pageSize,
			"fileId" :fileId,
			"tempId" : tempId
			
		}, function(data) {
			console.log("data:", data.data);
			$("#content").css("width",data.data.result[0].tableWidth);
			$("#bottom").css("width",data.data.result[0].tablewidth);
			$("#out").css("width",data.data.result[0].tablewidth);
		    totalPage = data.data.totalPages;
			var html = template(data.data.result[0]);
//		window.location.href = "/common"
			var value = "当前第"+(data.data.pageNumber+1)+"页"+"共"+data.data.totalPages+"页";
			$("#content").html(html);
			$("#pageTail").html(value);
		});
	});
	$("#back").click(function(){
		console.log("pageNum:",pageNum);
		pageNum=pageNum-1;
		console.log("pageNum:",pageNum);
		$.getJSON(defaultUrl,{
			"pageNumber": pageNum,
			"pageSize":pageSize,
			"fileId" :fileId,
			"tempId" : tempId
		}, function(data) {
			console.log("data:", data.data.result[0]);
		$("#content").css("width",data.data.result[0].tableWidth);
		$("#bottom").css("width",data.data.result[0].tableWidth);
		$("#out").css("width",data.data.result[0].tablewidth);
		totalPage = data.data.totalPages;
		var html = template(data.data.result[0]);
//		window.location.href = "/common"
		var value = "当前第"+(data.data.pageNumber+1)+"页"+"共"+data.data.totalPages+"页";
		$("#content").html(html);
		$("#pageTail").html(value);
			
		});
	});
	$("#first").click(function(){
		$.getJSON(defaultUrl,{
			"pageNumber": 0,
			"pageSize":pageSize,
			"fileId" :fileId,
			"tempId" : tempId
		}, function(data) {
		$("#content").css("width",data.data.result[0].tableWidth);
		$("#bottom").css("width",data.data.result[0].tableWidth);
		$("#out").css("width",data.data.result[0].tablewidth);
		totalPage = data.data.totalPages;
		var html = template(data.data.result[0]);
//		window.location.href = "/common"
		var value = "当前第"+(data.data.pageNumber+1)+"页"+"共"+data.data.totalPages+"页";
		$("#content").html(html);
		$("#pageTail").html(value);
		pageNum=data.data.pageNumber;
			
		});
	});
	$("#last").click(function(){
		$.getJSON(defaultUrl,{
			"pageNumber": totalPage-1,
			"pageSize":pageSize,
			"fileId" :fileId,
			"tempId" : tempId
		}, function(data) {
			console.log("data:", data.data.result[0]);
		$("#content").css("width",data.data.result[0].tableWidth);
		$("#bottom").css("width",data.data.result[0].tableWidth);
		$("#out").css("width",data.data.result[0].tablewidth);
		totalPage = data.data.totalPages;
		var html = template(data.data.result[0]);
//		window.location.href = "/common"
		var value = "当前第"+(data.data.pageNumber+1)+"页"+"共"+data.data.totalPages+"页";
		$("#content").html(html);
		$("#pageTail").html(value);
		pageNum = totalPage-1
		});
	});
	$("#statistic").click(function(){
		if($("#statisticContent").hasClass("display_1")){
			$("#statistic").html("切换台账明细表");
			 $("#statisticContent").removeClass("display_1");
			 $("#content").addClass("display_1");
		}else{
			 $("#statisticContent").addClass("display_1");
			 $("#content").removeClass("display_1");
			 $("#statistic").html("切换统计信息表");
		}
		
		$.getJSON(defaultUrl,{
			"pageNumber": 1,
			"pageSize":5,
			"fileId" :staticFileId,
			"tempId" : staticTempId
		}, function(data) {
			console.log("data:", data.data.result[0]);
			var html = template(data.data.result[0]);
			$("#statisticContent").css("width",data.data.result[0].tableWidth);
//			$("#bottom").css("width",data.data.result[0].tableWidth);
//			$("#out").css("width",data.data.result[0].tablewidth);
		$("#statisticContent").html(html);
		});
	});
});