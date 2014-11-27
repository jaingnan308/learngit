<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../../inc.jsp"></jsp:include>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress({
			text : '数据加载中....'
		});

		$('#container').highcharts({
			exporting : {
				filename : '消费项目统计表'
			},
			chart : {
				plotBackgroundColor : null,
				plotBorderWidth : null,
				plotShadow : false
			},
			title : {
				text : '消费项目统计表 '
			},
			tooltip : {
				pointFormat : '{series.name}: <b>{point.y}</b>'
			},
			plotOptions : {
				pie : {
					allowPointSelect : true,
					cursor : 'pointer',
					dataLabels : {
						enabled : true,
						color : '#000000',
						connectorColor : '#000000',
						formatter : function() {
							//return '<b>' + this.point.name + '</b>统计 ：' + this.y + ' 元 ';
							  return '<b>'+ this.point.name +'</b>: '+ Highcharts.numberFormat(this.y, 0, ',')+'元 '+
							  '(' + Highcharts.numberFormat(this.percentage, 2) +'%'+')';
						}
					}
				}
			},
			series : [ {
				type : 'pie',
				name : '金额  ',
				data : []
			} ]
		});

		$.post(sy.contextPath + '/account/chart', function(result) {
			var trs = '';
			$.each(result, function(index, item) {
				trs += sy.formatString('<tr><td>{0}</td><td>{1}</td></tr>', item.name, item.y);
			});
			$('table tr td table').append(trs);

			var chart = $('#container').highcharts();
			chart.series[0].setData(result);
			chart.series[0].name = '我想要的结果 ';
			chart.title.text = '我想要的统计表 ';
			parent.$.messager.progress('close');
		}, 'json');

	});
</script>
</head>
<body>
	<table style="width: 100%; height: 100%">
		<tr>
			<td style="width: 60%"><div id="container"></div></td>
			<td valign="top">
				<table class="table" style="margin-left: 20px;">
					<tr>
						<th>消费项目</th>
						<th>金额</th>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</body>
</html>