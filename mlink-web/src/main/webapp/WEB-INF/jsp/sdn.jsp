<%@page language="java" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>跨国IDC/公有云VPC互联-SDN专线- MLINK</title>
<meta name="keywords"
	content="SD-WAN、虚拟专线、公有云互联、数据中心互联、IDC互联、专线接入、阿里云和AWS互联、VPC互联">
<meta name="description"
	content="SDN专线业务提供跨国IDC和云数据中心的端到端快速通道建立、删除和调整，实现阿里云和AWS的无缝连接。自定义带宽和QoS，实时开通、按需付费。咨询热线：4001053626。">
<%
	String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath();
%>
<link href="<%=path %>/resources/web/style/common.css" rel="stylesheet"
	type="text/css">
<link href="<%=path %>/resources/web/style/othercss.css" rel="stylesheet"
	type="text/css">
<link rel="shortcut  icon" type="image/x-icon"
	href="<%=path %>/resources/web/images/favicon.ico" media="screen" />
<script language="javascript" src="<%=path %>/resources/web/js/jquery-1.8.3.min.js"></script>
<script language="javascript" src="<%=path %>/resources/web/js/header.js"></script>
</head>

<body>
	<div class="pageheader">
		<%@include file="head.jsp"%>
		<div class="wholeworldbanner">
			<div class="pagewidth pic_p">
				<img src="<%=path %>/resources/web/images/sdnpic.jpg">
				<div class="intrpage jf">
					<h3>SDN专线服务</h3>
					<p>网络即服务 即时开启您的全球私有网络</p>
				</div>
			</div>
		</div>
	</div>
	<div class="rowblock nopadding">
		<div class="pagewidth sdnintr">
			<h4>业务介绍</h4>
			<div class="pagetext">SDN专线业务将云计算的概念带到广域网的服务中，
				通过专业的硬件云服务或托管服务帮助企业快速拓展 海内外业务。其能够灵活调度和管理全球的SD-WAN 网络，实现 IDC 与
				IDC、云与云以及云与 IDC 之间的 快速连接。</div>

		</div>
	</div>

	<div class="rowblock graybg">
		<div class="pagewidth sd_wbg">
			<h4>SD-WAN 全球网络布局</h4>
			<div class="divrightfloat">
				<img src="<%=path %>/resources/web/images/sd_wbg_c.png">
				<p>已打通北京、上海、香港、洛杉矶各节点之间的网络，目前支持的公有云 包括 AWS、Google Cloud、Windows
					Azure，以及阿里云和腾讯云。 仅需5分钟，便可以打通中美之间云／IDC 之间的连接</p>
			</div>
		</div>
	</div>

	<div class="rowblock">
		<div class="pagewidth">
			<h4>专线价格详情</h4>
			<div class="detailstable">
				<table width="600" border="0" cellspacing="0" cellpadding="0"
					id="sdn">
					<tr>
						<td>美国洛杉矶 - 北京</td>
						<td>1000元/Mbps/月</td>
					</tr>
					<tr>
						<td>上海 - 香港</td>
						<td>200元/Mbps/月</td>
					</tr>
					<tr>
						<td>香港 - 美国洛杉矶</td>
						<td>134元/Mbps/月</td>
					</tr>
				</table>
				<div class="tablemark">
					专线订购说明：<br> 费用按购买节点、带宽以及周期计算，所有款项采取预付方式。<br>
					按天或月在管理平台上自主订购。如需要一年以上的专线服务，需在线下签署合约<br> 注意：以上报价未包含6%的增值税。
				</div>
			</div>
		</div>
	</div>
	<div class="pagebanner">
		<img src="<%=path %>/resources/web/images/pagebanner.jpg">
	</div>
	<div class="rowblock graybg">
		<div class="pagewidth">
			<h4>想要了解更多关于SDN专线的信息，请联系我们。</h4>
			<div class="contactlist">
				<div class="contactblock contacticon01">咨询热线：4001053626</div>
				<div class="contactblock contacticon02">咨询邮箱：sales@mlink.cn</div>
			</div>
		</div>
	</div>
	<div class="rowblock">
		<div class="pagewidth">
			<h4>产品优势</h4>
			<div class="sdwlist">
				<div class="sdwblock">
					<div class="sdwicon sdwicon01"></div>
					<div class="sdwtext">
						即时开通，即用即付<br> 网络专享，按需调动
					</div>
				</div>
				<div class="sdwblock">
					<div class="sdwicon sdwicon02"></div>
					<div class="sdwtext">
						无缝连接阿里云和AWS<br> 遵守国内外法律规范
					</div>
				</div>
				<div class="sdwblock">
					<div class="sdwicon sdwicon03"></div>
					<div class="sdwtext">
						超低延迟和丢包<br> 低价高效，安全稳定
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@include file="foot.jsp"%>
	<script type="text/javascript">
		$.ajax({
			url : '<%=path %>/sdn/getLineList',
			data : {},
			dataType : 'json',
			async : false,
			type : 'get',
			success : function(json) {
				console.log(json);
				if (json.result) {
					var sdnhtml = "";
					//<tr>
					//<td>美国洛杉矶 - 北京</td>
					//<td>1000元/Mbps/月</td>
					//</tr>
					$.each(json.data, function(i, obj) {
						console.log(obj.name);
						sdnhtml += "<tr><td>" + obj.name + "</td><td>"
								+ obj.price + "元/Mbps/月</td><tr>";
					});
					$("#sdn").html(sdnhtml);
				}
			}
		});
	</script>
</body>
</html>
