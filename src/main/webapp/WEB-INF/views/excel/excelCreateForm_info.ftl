<html>
<head>
 	<link href="<@url value='platform/css/gjjs-themes/bootstrap/excel.css'/>" rel="stylesheet">
	<script type="text/javascript" src="<@url value="/platform/js/share/excel.js" />"></script>
	<script type="text/javascript">
		$xcp.stopPorcess();
		var contextData = '${context.user}';
		var formName = '${(context.queryFromName)!""}';
		var filePath="${(context.filePath)!""}";
		var validateFile = "${(context.validateFile)!""}";
		$.include(validateFile);
	</script>
</head>
<body style="overflow:auto!important;">
     <div class="bbcx_big display_1" style="" id ="swift">
		<div class="bbcx_top" >
			 <a class="qhtj"  onmouseover="this.className='qhtj_out'"  onmouseout="this.className='qhtj'" href="javascript:void(0);" data-options="iconCls:'icon-reload'" id="statistic">
            	${statics['com.ebills.common.platform.utils.LanguageUtils'].getLanguage('excel.statistic', 'global') }
             </a>
			 <a  class="bbdc" onmouseover="this.className='bbdc_out'" onmouseout="this.className='bbdc'"  href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-print'"  id="export">
              ${statics['com.ebills.common.platform.utils.LanguageUtils'].getLanguage('excel.export', 'global') }
             </a>
			 <a class="bbcx" onmouseover="this.className='bbcx_out'" onmouseout="this.className='bbcx'" href="javascript:void(0);"  class="easyui-linkbutton" data-options="iconCls:'icon-search'" id="query"  >
                 ${statics['com.ebills.common.platform.utils.LanguageUtils'].getLanguage('excel.query', 'global') }
             </a>
             <a class="bbback" onmouseover="this.className='bbback_out'" onmouseout="this.className='bbback'"  href="javascript:void(0);" class="easyui-linkbutton" id="close"  >
                 ${statics['com.ebills.common.platform.utils.LanguageUtils'].getLanguage('excel.back', 'global') }
             </a>
		</div>
		<div id="pp" class=" display_1" style="background:none;border-left:1px solid #ccc;float:right;padding-right:15px;"></div> 
		<div id="pps" class=" display_1" style="background:#efefef;border:1px solid #ccc;float:right;"></div> 
	</div>
	<div  class="bbcx_box display_1" align="center" id="out">
		<div class="" align="center" id="content"></div>
		<div  align="center" id="statisticContent"></div>
	</div>
	<div class="easyui-dialog" title="" id="dialog" data-options="iconCls:'icon-search'" style="overflow:hidden;" flt=true>
		<form id='form' class="commQueryCondWrap">	
		</form>
	</div>
	<div id="win"></div> 
	<div id="message"></div>
</body>
</html>