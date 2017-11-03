<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>香港、美国、新加坡、泰国等海外服务器托管/租用-云主机-专线接入-MLINK-全球智能IDC一体化综合服务</title>
<link rel="stylesheet" type="text/css"
	href="resources/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="resources/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="resources/easyui/demo.css">
<script type="text/javascript" src="resources/easyui/jquery.min.js"></script>
<script type="text/javascript"
	src="resources/easyui/jquery.easyui.min.js"></script>
<link rel="shortcut  icon" type="image/x-icon" href="resources/web/images/favicon.ico" media="screen"  />	
<script type="text/javascript" src="resources/manage/mlink_basic.js"></script>	
<script type="text/javascript" src="resources/manage/mlink_facility.js"></script>	
<script type="text/javascript" src="resources/manage/mlink_single.js"></script>
<script type="text/javascript" src="resources/manage/mlink_line.js"></script>	
<script type="text/javascript" src="resources/manage/mlink_consult.js"></script>	
<script type="text/javascript" src="resources/manage/mlink_tip.js"></script>
<script type="text/javascript" src="resources/manage/mlink_exchange.js"></script>
</head>
<body>


	<div id="tabs" class="easyui-tabs" style="width: 100%; min-height:50%;margin:0px;">
		<div title="基本数据" style="padding: 10px">
			<div class="easyui-layout" style="width: 100%; height: 800px;">
				<div data-options="region:'west',split:true" title="mlink-大洲-国家-城市-节点"
					style="width: 200px;">
					<ul class="easyui-tree" id="tt"  >
					</ul>
				</div>
				<div
					data-options="region:'center',title:'Main Title',iconCls:'icon-ok'">
					<table class="easyui-datagrid" id="node_table">
					</table>
				</div>
			</div>
		</div>
		<div title="机房信息" style="padding: 10px" >
			<div class="easyui-layout" style="width: 100%; height: 800px;">
				<div data-options="region:'west',split:true" title="mlink-大洲-国家"
					style="width: 200px;">
					<ul class="easyui-tree" id="facility"  >
					</ul>
				</div>
				<div
					data-options="region:'center',title:'Main Title',iconCls:'icon-ok'">
					<table class="easyui-datagrid" id="facility_table">
					</table>
				</div>
			</div>
		</div>
		<div title="节点信息" style="padding: 10px" >
			<div class="easyui-layout" style="width: 100%; height: 800px;">
				<div data-options="region:'west',split:true" title="mlink-大洲-国家-城市-节点"
					style="width: 200px;">
					<ul class="easyui-tree" id="single"  >
					</ul>
				</div>
				<div
					data-options="region:'center',title:'Main Title',iconCls:'icon-ok'">
					<table class="easyui-datagrid" id="single_table">
					</table>
				</div>
			</div>
		</div>
		
		
		<div title="专线价格详情" style="padding: 10px" >
			<div class="easyui-layout" style="width: 100%; height: 800px;">
				<table class="easyui-datagrid" id="line_table">
				</table>
			</div>
		</div>
		
		<div title="信息咨询" style="padding: 10px" >
			<div class="easyui-layout" style="width: 100%; height: 800px;">
					<table class="easyui-datagrid" id="consult_table">
					</table>
			</div>
		</div>
		<div title="小提示" style="padding: 10px" >
			<div class="easyui-layout" style="width: 100%; height: 800px;">
				<div data-options="region:'west',split:true" title="mlink-大洲-国家"
					style="width: 200px;">
					<ul class="easyui-tree" id="tip"  >
					</ul>
				</div>
				<div
					data-options="region:'center',title:'Main Title',iconCls:'icon-ok'">
					<table class="easyui-datagrid" id="tip_table">
					</table>
				</div>
			</div>
		</div>
		<div title="汇率" style="padding: 10px" >
			<div class="easyui-layout" style="width: 100%; height: 800px;">
				<table class="easyui-datagrid" id="exchange_table">
				</table>
			</div>
		</div>
	</div>
	
	<div id="window" class="easyui-window" title="Basic Window" data-options="iconCls:'icon-save'" style="width:500px;padding:10px;">
		 <form id="levelform">
		 	<input type="hidden" name="levelid" value="">
		 	<input type="hidden" name="levelname" value="">
        <div style="margin-bottom:20px">
            <input class="easyui-textbox" label="名称：" id="level_name" name="level_name" labelPosition="top" style="width:100%;height:52px">
        </div>
        <div style="margin-bottom:20px">
            <input class="easyui-textbox" label="排序：" id="sort" name="sort" labelPosition="top" style="width:100%;height:52px">
        </div>
       
        <div>
            <a id="level_submit"  class="easyui-linkbutton" iconCls="icon-ok" style="width:100%;height:32px">Submit</a>
        </div>
        </form>
	</div>
	
	<div id="facilitywindow" class="easyui-window" title="Basic Window" data-options="iconCls:'icon-save'" style="width:500px;padding:10px;">
		 <form id="facilityform">
		 	<input type="hidden" name="facility_countryid" value=""/>
		 	<input type="hidden" name="facility_id" value=""/>
		 	 <div style="margin-bottom:20px" align="left">
                <select class="easyui-combobox" id="facility_type" name="facility_type" label="类型:" labelPosition="top" style="width:100%;height: 52px;">
                	<option value="0">机房-托管规格-机柜功率-机柜价格</option>
                	<option value="1">(机房)-托管规格-机柜功率-机柜价格</option>
                	<option value="2">(机房-托管规格)-机柜功率-机柜价格</option>
                	<option value="3">(机房-托管规格)-(机柜功率-机柜价格)</option>
            	</select>
            </div>
             <div style="margin-bottom:20px">
	            <input class="easyui-textbox" label="排序：" id="facility_sort" name="facility_sort" labelPosition="top" style="width:100%;height:52px">
	        </div>
	        <div style="margin-bottom:20px">
	            <input class="easyui-textbox" label="机房：" id="facility_name" name="facility_name" labelPosition="top" style="width:100%;height:52px">
	        </div>
	        <div style="margin-bottom:20px">
	            <input class="easyui-textbox"  label="托管规格：" id="facility_standard" name="facility_standard" labelPosition="top" style="width:100%;height:80px" data-options="label:'Message:',multiline:true">
	        </div>
	        <div style="margin-bottom:20px">
	            <input class="easyui-textbox"  label="机柜功率：" id="facility_power" name="facility_power" labelPosition="top" style="width:100%;height:80px" data-options="label:'Message:',multiline:true">
	        </div>
	        <div style="margin-bottom:20px">
	            <input class="easyui-textbox"  label="机柜价格：" id="facility_price" name="facility_price" labelPosition="top" style="width:100%;height:80px" data-options="label:'Message:',multiline:true">
	        </div>
	       
	        <div>
	            <a id="facility_submit" class="easyui-linkbutton" iconCls="icon-ok" style="width:100%;height:32px">Submit</a>
	        </div>
        </form>
	</div>
	
	<div id="singlewindow" class="easyui-window" title="Basic Window" data-options="iconCls:'icon-save'" style="width:500px;padding:10px;">
		 <form id="singleform">
		 	<input type="hidden" name="single_nodeid" value=""/>
		 	<input type="hidden" name="single_id" value="">
	        <div style="margin-bottom:20px">
	            <input class="easyui-textbox" label="总面积：" id="single_area" name="single_area" labelPosition="top" style="width:100%;height:52px">
	        </div>
	        <div style="margin-bottom:20px">
	            <input class="easyui-textbox"  label="服务等级协议：" id="single_protocol" name="single_protocol" labelPosition="top" style="width:100%;height:80px" data-options="label:'Message:',multiline:true">
	        </div>
	        <div style="margin-bottom:20px">
	            <input class="easyui-textbox"  label="电力和冷却：" id="single_electricity" name="single_electricity" labelPosition="top" style="width:100%;height:80px" data-options="label:'Message:',multiline:true">
	        </div>
	        <div style="margin-bottom:20px">
	            <input class="easyui-textbox"  label="等级资质：" id="single_authentication" name="single_authentication" labelPosition="top" style="width:100%;height:80px" data-options="label:'Message:',multiline:true">
	        </div>
	       
	        <div>
	            <a id="single_submit" class="easyui-linkbutton" iconCls="icon-ok" style="width:100%;height:32px">Submit</a>
	        </div>
        </form>
	</div>
	
	<div id="linewindow" class="easyui-window" title="Basic Window" data-options="iconCls:'icon-save'" style="width:500px;padding:10px;">
		 <form id="lineform">
		 	<input type="hidden" name="line_id" value="">
        <div style="margin-bottom:20px">
            <input class="easyui-textbox" label="名称：" id="line_name" name="line_name" labelPosition="top" style="width:100%;height:52px">
        </div>
         <div style="margin-bottom:20px">
            <input class="easyui-textbox" label="价格：" id="line_price" name="line_price" labelPosition="top" style="width:100%;height:52px">
        </div>
        <div style="margin-bottom:20px">
            <input class="easyui-textbox" label="排序：" id="line_sort" name="line_sort" labelPosition="top" style="width:100%;height:52px">
        </div>
       
        <div>
            <a id="line_submit"  class="easyui-linkbutton" iconCls="icon-ok" style="width:100%;height:32px">Submit</a>
        </div>
        </form>
	</div>
	
	<div id="tipwindow" class="easyui-window" title="Basic Window" data-options="iconCls:'icon-save'" style="width:500px;padding:10px;">
		 <form id="tipform">
		 	<input type="hidden" name="tip_countryid" value=""/>
		 	<input type="hidden" name="tip_id" value=""/>
	        <div style="margin-bottom:20px">
	            <input class="easyui-textbox"  label="提示：" id="tip_tip" name="tip_tip" labelPosition="top" style="width:100%;height:80px" data-options="label:'Message:',multiline:true">
	        </div>
	       
	        <div>
	            <a id="tip_submit" class="easyui-linkbutton" iconCls="icon-ok" style="width:100%;height:32px">Submit</a>
	        </div>
        </form>
	</div>
	
	<script type="text/javascript">
	
	 $(function(){
		 basic.init();
		 $("#window").window("close");
		 $("#facilitywindow").window("close");
		 $("#singlewindow").window("close");
		 $("#linewindow").window("close");
		 $("#tipwindow").window("close");
		 
		 $('#tabs').tabs({
			    border:false,
			    onSelect:function(title,index){
			    	if(index=='0'){
			    		basic.init();
			    	}else if(index=='1'){
			    		facility.init();
			    	}else if(index=='2'){
			    		single.init();
			    	}else if(index=='3'){
			    		line.init();
			    	}else if(index=='4'){
			    		consult.init();
			    	}else if(index=='5'){
			    		tip.init();
			    	}else if(index=='6'){
			    		exchange.init();
			    	}
			    }
			});
     });
	 
	 
	 
	</script>
</body>
</html>