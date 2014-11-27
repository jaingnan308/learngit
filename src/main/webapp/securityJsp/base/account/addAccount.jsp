<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
%>
<%
	String id = request.getParameter("id");
	if (id == null) {
		id = "";
	}
%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../../inc.jsp"></jsp:include>
<script type="text/javascript">
	var submitNow = function($dialog, $grid, $pjq) {
		var url;
		if ($(':input[name="data.id"]').val().length > 0) {
			url = sy.contextPath + '/base/syuser!update.sy';
		} else {
			url = sy.contextPath + '/account/add';
		}
		$.post(url, sy.serializeObject($('form')), function(result) {
			parent.sy.progressBar('close');//关闭上传进度条

			if (result.success) {
				$pjq.messager.alert('提示', result.msg, 'info');
				$grid.datagrid('load');
				$dialog.dialog('destroy');
			} else {
				$pjq.messager.alert('提示', result.msg, 'error');
			}
		}, 'json');
	};
	var submitForm = function($dialog, $grid, $pjq) {
		if ($('form').form('validate')) {
				submitNow($dialog, $grid, $pjq);
		}
	};
	$(function() {
		if ($(':input[name="data.id"]').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post(sy.contextPath + '/base/syuser!getById.sy', {
				id : $(':input[name="data.id"]').val()
			}, function(result) {
				if (result.id != undefined) {
					$('form').form('load', {
						'data.id' : result.id,
						'data.name' : result.name,
						'data.loginname' : result.loginname,
						'data.sex' : result.sex,
						'data.age' : result.age,
						'data.photo' : result.photo
					});
					if (result.photo) {
						$('#photo').attr('src', sy.contextPath + result.photo);
					}
				}
				parent.$.messager.progress('close');
			}, 'json');
		}
	});
</script>
</head>
<body>
	<form method="post" class="form">
		<fieldset>
			<legend>账单基本信息</legend>
			<table class="table" style="width: 100%;">
				<tr>
					<td><input name="data.id" value="<%=id%>"  type="hidden"/></td>
				</tr>
				<tr>
					<th>账单持有人</th>
					<td><input name="data.userId" /></td>
				</tr>
				<tr><td></td><td></td></tr>
				<tr>
					<th>资金流向</th>
					<td><select class="easyui-combobox" name="data.direction" data-options="panelHeight:'auto',editable:false" style="width: 155px;">
							<option value="1">收入</option>
							<option value="0">支出</option>
					</select></td>
				</tr>
				<tr><td></td><td></td></tr>
				<tr>
					<th>资金类型</th>
					<td><select class="easyui-combobox" name="data.financeType" data-options="panelHeight:'auto',editable:false" style="width: 155px;">
							<option value="1">餐费</option>
							<option value="4">交通</option>
							<option value="2">礼金</option>
					</select></td>
				</tr>
				<tr><td></td></tr>
				<tr>
					<th>金额</th>
					<td><input name="data.money" /></td>
				</tr>
				<tr><td></td></tr>
				<tr>
					<th>备注</th>
					<td><input name="data.remark" /></td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>