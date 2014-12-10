<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../../inc.jsp"></jsp:include>
<style> 
td{text-align:center} 
</style> 
<script type="text/javascript">
window.onload = function(){  
	function selectFinanceType(newVal){
		$('#finance_type').combobox({
			 url:sy.contextPath + '/user/moneyType?parentId='+newVal,
			 valueField:'value',
			 textField:'key',
			 onLoadSuccess: function () { //加载完成后,设置选中第一项
                var val = $(this).combobox("getData");
                for (var item in val[0]) {
                    if (item == "value") {
                        $(this).combobox("select", val[0][item]);
                    }
                }
             }
		});
	};
	
	 $("#account_direction").combobox({
		 onChange: function (newVal,oldVal) {
			 selectFinanceType(newVal);
		 }
	});

	//初始化数据
	$(function() {
		selectFinanceType($('#account_direction').val());
		$('#add_account_moneytime').val(new Date().format('yyyy-MM-dd'));
	});
};
var submitForm = function($dialog, $grid, $pjq) {
	console.log($('#account_add_form'));
	if ($('form').form('validate')) {
		$.ajax({
			type : "POST",
			url : '/account/add',
			data : $('#account_add_form').serialize(),// 你的formid
			//data : sy.serializeObject($('form')),// 你的formid
			error : function() {
				alert("error");
			},
			success : function(data) {
				alert("data="+data);
				if (data != null && data != "") {
					$pjq.messager.alert('提示', "添加账单成功!", 'info');
					$grid.datagrid('load');
					$dialog.dialog('destroy');
				}else{
					$pjq.messager.alert('提示', "添加账单失败,请稍候再试!", 'info');
				}
			}
		});
	};
};
function setImagePreview() {  
    var docObj=document.getElementById("doc");  
    var imgObjPreview=document.getElementById("preview");  
    if(docObj.files && docObj.files[0]){  
        //火狐下，直接设img属性  
        imgObjPreview.style.display = 'block';  
        imgObjPreview.style.width = '460px';  
        imgObjPreview.style.height = '245px';                      
        //imgObjPreview.src = docObj.files[0].getAsDataURL();  
          
        //火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式    
        imgObjPreview.src = window.URL.createObjectURL(docObj.files[0]);  
    }else{  
        //IE下，使用滤镜  
        docObj.select();  
        var imgSrc = document.selection.createRange().text;  
        var localImagId = document.getElementById("localImag");  
        //必须设置初始大小  
        localImagId.style.width = "300px";  
        localImagId.style.height = "120px";  
        //图片异常的捕捉，防止用户修改后缀来伪造图片  
        try{  
            localImagId.style.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";  
            localImagId.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;  
        }catch(e){  
            alert("您上传的图片格式不正确，请重新选择!");  
            return false;  
        }  
        imgObjPreview.style.display = 'none';  
        document.selection.empty();  
    }  
    return true;  
} 
</script>
</head>
<body>
	<form method="post" class="form" id="account_add_form">
		<fieldset>
			<legend>账单基本信息</legend>
			<table class="table" style="width: 100%;"  cellpadding=”2″>
				<tr>
					<td><input name="id"  type="hidden"/></td>
				</tr>
				<tr>
					<th>账单持有人</th>
					<td><input name="userId" /></td>
				</tr>
				<tr><td></td><td></td></tr>
				<tr>
					<th>资金流向</th>
					<td><select id="account_direction" class="easyui-combobox" name="direction" data-options="panelHeight:'auto',editable:false" style="width: 155px;">
							<option value="1">收入</option>
							<option value="2">支出</option>
					</select></td>
				</tr>
				<tr><td></td><td></td></tr>
				<tr>
					<th>资金类型</th>
					<td><input id = "finance_type" class="easyui-combobox" name="financeType" 
					data-options="valueField:'value',textField:'key',panelHeight:'auto',editable:false" style="width: 155px;" /></td>
					
				</tr>
				<tr><td></td></tr>
				<tr>
					<th>金额</th>
					<td><input name="money" class="easyui-numberbox" min="1" max="9999999.99" precision="2" 
						invalidMessage="必须填写1-9999999.99之间的数字!" missingMessage="必须填写1-9999999.99之间的数字!" data-options="required:true" /></td>
				</tr>
				<tr><td></td></tr>
				<tr>
					<th>收入/支出时间</th>
					<td><input id="add_account_moneytime" name="moneyTime" class="Wdate" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" readonly="readonly" style="width: 152px;" /></td>
				</tr>
				<tr>
					<th>备注</th>
					<td><textarea class="easyui-validatebox" name="remark" validType="length[2,80]" invalidMessage="不能小于2或超过80个字符!" missingMessage="备注不能为空"  style="heigth:120px; width: 155px;" data-options="required:true"></textarea></td>
				</tr>
				<tr>
					<th>图片</th>
				 <th style="text-align:left"><input type=file name="doc" id="doc" onchange="javascript:setImagePreview();" /></th> 
					<!-- <th><input type="file" name="file" /></th> <td><input type="submit" value="Submit" /></td> -->
				</tr>
				<tr>
			    <!-- <div id="localImag"><img id="preview" width=-1 height=-1 style="diplay:none" /></div>   -->
			    <td colspan="2" style="img-align:center" >
			    	<img id="preview" width=-1 height=-1 /></td>
			    </tr>
			</table>
		</fieldset>
	</form>
</body>
</html>