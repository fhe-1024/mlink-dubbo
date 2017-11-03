<%@page language="java" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>全球机房-香港/台湾/美国/新加坡/泰国/印度/越南/英国/德国服务器托管- MLINK</title>
<meta name="keywords"
	content="海外服务器托管、香港机房托管、美国机房、新加坡机房、泰国机房、越南机房">
<meta name="description"
	content="MLINK提供全球50+的顶级数据中心资源的机房托管和带宽租用服务，覆盖亚洲、美洲、欧洲和大洋洲。运营商中立，1.4Tpbs总出口带宽，7*24小时技术支持。咨询热线：4001053626。">
<%
	String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath();
%>
<link href="<%=path %>/resources/web/style/common.css" rel="stylesheet"
	type="text/css">
<link href="<%=path %>/resources/web/style/othercss.css" rel="stylesheet"
	type="text/css">
<link rel="shortcut  icon" type="image/x-icon" href="<%=path %>/resources/web/images/favicon.ico" media="screen"  />	
<script language="javascript" src="<%=path %>/resources/web/js/jquery-1.8.3.min.js"></script>
<script language="javascript" src="<%=path %>/resources/web/js/header.js"></script>
<script language="javascript" src="<%=path %>/resources/web/js/pagejs.js"></script>
</head>

<body>
	<div class="pageheader">
		<%@include file="head.jsp" %>
		<div class="wholeworldbanner">
			<div class="pagewidth pic_p">
				<img src="<%=path %>/resources/web/images/abroadpic.jpg">
				<div class="intrpage jf">
					<h3>
						<em><%=request.getAttribute("countryname") %></em> 机房托管服务
					</h3>
				</div>
			</div>
		</div>
	</div>
	<div class="rowblock">
		<div class="pagewidth">
			<h4><%=request.getAttribute("countryname") %>机房信息</h4>

			<div class="sel_jfdiv">
				<div class="sel_jf_row" id="machine">
					
					<div class="clearfloat"></div>
				</div>
				
				<div class="sel_jf_row" style="display: none;">
					<a href="#" class="moresel"><span>香港01</span>
						<div class="localdiv">
							<div class="localblock">香港01</div>
							<div class="localblock">香港02</div>
						</div></a> <a href="#">台湾</a>
				</div>
				<div class="clearfloat"></div>
				
			</div>
			<div class="jf_info">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<th scope="col"><span class="tableicon t_icon01"></span>总面积</th>
						<th scope="col"><span class="tableicon t_icon02"></span>服务等级协议(SLA)
						</th>
						<th scope="col"><span class="tableicon t_icon03"></span>电力和冷却</th>
						<th scope="col"><span class="tableicon t_icon04"></span>等级资质</th>
					</tr>
					<tr id="singleentity">
					</tr>
				</table>

			</div>
		</div>
	</div>
	<div class="rowblock graybg" id="mlink_head">
		<div class="pagewidth">
			<h4><%=request.getAttribute("countryname") %>机房托管价格详情</h4>
			<div class="detailstable">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" id="mlink">
					
				</table>
				<div class="tablemark">以上所有报价均为税前价格，在此基础上需加收6%的增值税。</div>

			</div>
		</div>
	</div>
	<div class="pagebanner">
		<img src="<%=path %>/resources/web/images/pagebanner.jpg">
	</div>
	<div class="rowblock">
		<div class="pagewidth">
			<h4>想要了解更多地区的机房托管价格信息，请联系我们。</h4>
			<div class="contactlist">
				<div class="contactblock contacticon01">咨询热线：4001053626</div>
				<div class="contactblock contacticon02">咨询邮箱：sales@mlink.cn</div>
			</div>
		</div>
	</div>
	<%@include file="foot.jsp" %>
	<script type="text/javascript">
			
	 var machine = function() {
		return {
			inittable : function() {
				 $.ajax({
						url : '<%=path %>/machine/getFacilityList',
						data : {"countryid":"<%=request.getAttribute("countryid")%>"},
						dataType : 'json',
						async : false,
						type : 'get',
						success : function(json) {
							console.log(json.result);
							if(json.result){
								console.log(json.data);
								var facility="";
								
								facility+="<tr>";
									facility+="<th scope='col'>机房</th>";
									facility+="<th scope='col'>托管规格</th>";
									facility+="<th scope='col'>机柜功率</th>";
									facility+="<th scope='col'>机柜价格(元/月)</th>";
									facility+="</tr>";
								
								$.each(json.data,function(i, obj) {
									console.log(obj.type);
									var length=obj.size;
									if("0"===obj.type){
										$.each(obj.data,function(i,obj){
											console.log(obj.power)
											var option="<tr><td>"+obj.name+"</td><td>"+obj.standard+"</td><td>"+obj.power+"</td><td>"+obj.price+"</td></tr>";
											facility+=option;
										});
									}else if("1"===obj.type){
										$.each(obj.data,function(i,obj){
											console.log(obj.power)
											var option="";
											if(i==0){
												option="<tr><td rowspan="+length+">"+obj.name+"</td><td>"+obj.standard+"</td><td>"+obj.power+"</td><td>"+obj.price+"</td></tr>";
											}else{
												option="<tr><td>"+obj.standard+"</td><td>"+obj.power+"</td><td>"+obj.price+"</td></tr>";
											}
											facility+=option;
										});
									}else if("2"===obj.type){
										$.each(obj.data,function(i,obj){
											console.log(obj.power)
											var option="";
											if(i==0){
												option="<tr><td colspan='2' rowspan="+length+">"+obj.name+"</td><td>"+obj.power+"</td><td>"+obj.price+"</td></tr>";
											}else{
												option="<tr><td>"+obj.power+"</td><td>"+obj.price+"</td></tr>";
											}
											facility+=option;
										});
									}else if("3"===obj.type){
										$.each(obj.data,function(i,obj){
											console.log(obj.power)
											var option="<tr><td colspan='2'>"+obj.name+"</td><td colspan='2'>"+obj.power+"</td></tr>";
											facility+=option;
										});
									}
								});
								
								$("#mlink").html(facility);
							}else{
								$("#mlink_head").hide();
							}
						}
					});
			},
			
			inittip:function(){
				$.ajax({
					url : '<%=path %>/machine/getTipEntity',
					data : {"countryid":"<%=request.getAttribute("countryid")%>"},
					dataType : 'json',
					async : false,
					type : 'get',
					success : function(json) {
						console.log(json);
						if(json.result){
							$(".tablemark").html(json.tip.tip.replace(new RegExp("\r\n","gm"),"<br>"));
						}else{
							$(".tablemark").html("");
						}
					}
			 });	
			},
			
			initmachine:function(){
				 $.ajax({
						url : '<%=path %>/machine/getNodeList',
						data : {"countryid":"<%=request.getAttribute("countryid")%>"},
						dataType : 'json',
						async : false,
						type : 'get',
						success : function(json) {
							console.log(json);
							if(json.result){
								var machinehtml="";
								$.each(json.ret,function(i, obj) {
									console.log(obj.name);
									if(obj.size==1){
										$.each(obj.node,function(j, obj) {
											console.log(obj.name);
											machinehtml+="<a href='#' id='"+obj.id+"'>"+obj.name+"</a>";
										}); 
									}else if(obj.size>1){
										$.each(obj.node,function(j, obj) {
											console.log(obj.name);
											if(j==0){
												machinehtml+="<a href='#' class='moresel' id='"+obj.id+"'><span>"+obj.name+"</span>";
												machinehtml+="<div class='localdiv'>";
											}
											machinehtml+="<div class='localblock' id='"+obj.id+"'>"+obj.name+"</div>";
										}); 
										machinehtml+="</div></a>";
									}
								});
								machinehtml+="<div class='clearfloat'></div>"
								$("#machine").html(machinehtml);
							}
						}
				 });	
			},
			initsingle:function(nodeid){
				 $.ajax({
						url : '<%=path %>/machine/getSingleEntity',
						data : {"nodeid":nodeid},
						dataType : 'json',
						async : false,
						type : 'get',
						success : function(json) {
							console.log(json);
							if(json.result){
								$("#singleentity").html("<td>"+json.single.area.replace(new RegExp("\r\n","gm"),"<br>")+"</td><td>"+json.single.protocol.replace(new RegExp("\r\n","gm"),"<br>")+"</td><td>"+json.single.electricity.replace(new RegExp("\r\n","gm"),"<br>")+"</td><td>"+json.single.authentication.replace(new RegExp("\r\n","gm"),"<br>")+"</td>");
							}else{
								$("#singleentity").html("");
							}
						}
				 });	
			}
				 
			};
		}();	
	
	</script>
</body>
</html>
