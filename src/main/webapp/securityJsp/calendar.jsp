<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>我的日历</title>
<jsp:include page="/inc.jsp"></jsp:include>
<script>
	$(document).ready(function() {
		$('#personal-calendar').fullCalendar({
			header : {
				left : 'prev,next today',
				center : 'title',
				right : 'month,agendaWeek,agendaDay'
			},
			defaultDate : new Date(),
			editable : false,
			eventLimit : true,
			height : 480,
			events : {
				url : '/calendar/getAll',
				error : function() {
					alert("失败了  ");
					$('#script-warning').show();
				},
				success : function(data) {
				}
			},
			loading : function(bool) {
				$('#loading').toggle(bool);
			},
			selectable : true,
			selectHelper : true,
			dayClick : function(date, allDay, jsEvent, view) {
				$('#personal-new-div').dialog({
					title : '新增提醒事项',
					width : 450,
					cache : false,
					closed : false,
					modal : true,
					buttons: [{
						text:'提交',
						iconCls:'icon-ok',
						handler:function(){
							submitForm();
						}
					},{
						text:'重置',
						iconCls:'icon-clear',
						handler:function(){
							clearForm();
						}
					}]
				});
				//初始化控件
				$('#personal-new-start').attr("onchange","WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'"
					+ new Date(date).format('yyyy-MM-dd 00:00:00')
					+ "',maxDate:'#F{$dp.$D(\\'personal-new-end\\')||\\'"
					+ new Date(date).format('yyyy-MM-dd 23:59:59')
					+ "\\'}'})");
				$('#personal-new-start').val(new Date(date).format('yyyy-MM-dd 08:00:00'));
				//$('#personal-new-start').val(date._d.format("yyyy-MM-dd hh:mm:ss"));
			},
			eventClick : function(event) {
				$.ajax({
					type : "POST",
					url : '/calendar/view',
					data : {
						id : +event.id
					},
					error : function() {
						alert("error");
					},
					success : function(data) {
						if (data != null) {
							$('#personal-view-div').dialog({
								title : '查看提醒事项',
								width : 320,
								cache : false,
								closed : false,
								modal : true,
								buttons: [{
									text:'删除',
									iconCls:'icon-remove',
									handler:function(){
										deleteTips();
									}
								},{
									text:'关闭',
									iconCls:'icon-cancel',
									handler:function(){
										closeView();
									}
								}]
							});

							$('#personal-view-id').val(data.id);
							$('#personal-view-title').val(data.title);
							$('#personal-view-start').val(data.start);
							$('#personal-view-end').val(data.end);
							$('#personal-view-details').val(data.details);

						}
					}
				});
			}
		});

		$("#personal-new-backgroundColor").change( function() {
			$(this).attr("style",$(this).find("option:selected").attr("style"));
		});
		
		$("#personal-new-textColor").change( function() {
			$(this).attr("style",$(this).find("option:selected").attr("style"));
		});

	});

	function submitForm() {
		$.ajax({
			type : "POST",
			url : '/calendar/add',
			data : $('#personal-new-form').serialize(),// 你的formid
			error : function() {
				alert("error");
			},
			success : function(data) {
				if (data != null) {
					$('#personal-calendar').fullCalendar('refetchEvents');
					$('#personal-new-div').dialog('close', true);
				}
			}
		});
	}
	
	function clearForm() {
		$('#personal-new-form').form('clear');
	}
	
	function closeView() {
		$('#personal-view-div').dialog('close', true);
	}
	
	function deleteTips() {
		$.messager.confirm("操作提示", "您确定要执行操作吗？", function(data) {
			if (data) {
				$.ajax({
					type : "POST",
					url : '/calendar/delete',
					data : {
						id : $('#personal-view-id').val()
					},
					error : function() {
						alert("error");
					},
					success : function(data) {
						if (data != null && data != '') {
							$.messager.alert('提示', '删除成功！ ', 'info');
							$('#personal-calendar').fullCalendar('refetchEvents');
							$('#personal-view-div').dialog('close', true);
						}else{
							alert("删除失败 ");
						}
					}
				});
			}
		});
	}
</script>
<style>

</style>
</head>
<body >
	<div id='personal-calendar'></div>
	<!-- 新增弹窗 start -->
	<div id="personal-new-div" class="easyui-dialog" style="width: 500px;" data-options="closed:true">
		<div style="padding: 10px 60px 20px 60px">
			<form id="personal-new-form">
				<table cellpadding="5">
					<tr>
						<td>标题:</td>
						<td><input id="personal-new-title" type="text" name="title" style="width: 200px" /></td>
					</tr>
					<tr>
						<td>开始时间:</td>
						<td>
							<input id="personal-new-start" class="Wdate" type="text" name="start"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" style="width: 200px; height: 20px;" />
						</td>
					</tr>
					<tr>
						<td>结束时间:</td>
						<td>
							<input id="personal-new-end" class="Wdate" type="text" name="end"
								onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'personal-new-start\')}'})"
								style="width: 200px; height: 20px;" />
						</td>
					</tr>
					<tr>
						<td>背景颜色:</td>
						<td>
							<select id="personal-new-backgroundColor" name="backgroundColor" style="width: 200px; background: #3a87ad;">
								<option style="width: 200px; background: #3a87ad" value="#3a87ad">&nbsp;</option>
								<option style="width: 200px; background: #0d7813" value="#0d7813">&nbsp;</option>
								<option style="width: 200px; background: #f2a640" value="#f2a640">&nbsp;</option>
								<option style="width: 200px; background: #b373b3" value="#b373b3">&nbsp;</option>
								<option style="width: 200px; background: #f2a640" value="#f2a640">&nbsp;</option>
								<option style="width: 200px; background: #668cb3" value="#668cb3">&nbsp;</option>
								<option style="width: 200px; background: #28754e" value="#28754e">&nbsp;</option>
								<option style="width: 200px; background: #8c66d9" value="#8c66d9">&nbsp;</option>
						</select></td>
					</tr>
					<tr>
						<td>字体颜色:</td>
						<td>
							<select id="personal-add-textColor" class="" name="textColor" style="width: 200px; background: #3a87ad;">
								<option style="width: 200px; background: #3a87ad" value="#3a87ad">&nbsp;</option>
								<option style="width: 200px; background: #0d7813" value="#0d7813">&nbsp;</option>
								<option style="width: 200px; background: #f2a640" value="#f2a640">&nbsp;</option>
								<option style="width: 200px; background: #b373b3" value="#b373b3">&nbsp;</option>
								<option style="width: 200px; background: #f2a640" value="#f2a640">&nbsp;</option>
								<option style="width: 200px; background: #668cb3" value="#668cb3">&nbsp;</option>
								<option style="width: 200px; background: #28754e" value="#28754e">&nbsp;</option>
								<option style="width: 200px; background: #8c66d9" value="#8c66d9">&nbsp;</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>备注:</td>
						<td><textarea id="personal-add-details" name="details" rows="3" style="height: 60px; width: 200px"></textarea></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<!-- 新增弹窗 end -->
	
	<!-- 查看窗口 start -->
	<div id="personal-view-div" class="easyui-dialog" data-options="closed:true">
		<table cellpadding="5">
			<tr>
				<td>标题:</td>
				<td>
					<input id="personal-view-id" type="hidden" name="id" />
					<input id="personal-view-title" type="text" name="title" style="width: 200px" readonly="readonly" /></td>
			</tr>
			<tr>
				<td>开始时间:</td>
				<td><input id="personal-view-start" class="Wdate" type="text" name="start" style="width: 200px; height: 20px;" readonly="readonly" /></td>
			</tr>
			<tr>
				<td>结束时间:</td>
				<td><input id="personal-view-end" class="Wdate" type="text" name="end" style="width: 200px; height: 20px;" readonly="readonly" /></td>
			</tr>
			<tr>
				<td>备注:</td>
				<td><textarea id="personal-view-details" name="details" rows="3" style="height: 60px; width: 200px"></textarea></td>
			</tr>
		</table>
	</div>
	<!-- 查看窗口 end -->
</body>
</html>

