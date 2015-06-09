<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script id="content-template" type="text/x-handlebars-template">
  		<h1>Excel Converter{{result}}</h1>
 			<table class="ta_1" width="{{tableWidth}}" height="auto" border="1">
				{{#each htmlRowStructure}}
					<tr valign="middle">
						{{#if htmlTdStructure}}
							{{#each htmlTdStructure}}
								<td colspan="{{colspan}}" rowspan="{{rowspan}}" align="center" width="{{width}}" height="{{height}}">
									{{#if value}}
										{{value}}
									{{/if}}
								</td>
							{{/each}}
						{{/if}}
					</tr>
				{{/each}}
			</table>
</script>

	