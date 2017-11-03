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
				<img src="<%=path %>/resources/web/images/pagepic01.jpg">
				<div class="intrpage">
					<h3>主机托管服务</h3>
					<p>全球50+的顶级数据中心，覆盖亚洲、美洲、欧洲及大洋洲。全球带宽总容量
						高达1.4Tbps，并根据企业业务需求随时做好扩容准备。海内外机房智能BGP 多线介入，高速稳定，独享/共享任选。</p>
				</div>
			</div>
		</div>
	</div>
	<div class="rowblock">
		<div class="pagewidth">
			<h4>全球网络布局</h4>
			<div class="h_txt">50+顶级数据中心，1.4Tbps</div>
			<div class="map_w">
				<img src="<%=path %>/resources/web/images/worldmappic.jpg">
				<div class="map_w_t">200+与国际运营商的互联互通 | 7*24洛杉矶与上海网络运营中心</div>
			</div>
		</div>
	</div>
	<div class="rowblock graybg">
		<div class="pagewidth">
			<h4>全球机房选择</h4>
			<div class="selcitytable">
				<table width="100%" border="0" cellspacing="0" cellpadding="0"
					id="mtable">
					<tr>
						<th scope="row">亚 洲 区 域</th>
						<td><a href="#">中国</a><a href="#">新加坡</a><a href="#">泰国</a><a
							href="#">阿联酋</a><a href="#">柬埔寨</a><a href="#">印度</a><a href="#">日本</a><a
							href="#">马来西亚</a><a href="#">菲律宾</a><a href="#">韩国</a></td>
					</tr>
					<tr>
						<th scope="row">美 洲 区 域</th>
						<td><a href="#">美国</a><a href="#">加拿大</a><a href="#">巴西</a></td>
					</tr>
					<tr>
						<th scope="row">欧 洲 区 域</th>
						<td><a href="#">德国</a><a href="#">法国</a><a href="#">英国</a><a
							href="#">意大利</a><a href="#">俄罗斯</a></td>
					</tr>
					<tr>
						<th scope="row">大 洋 洲 区 域</th>
						<td><a href="#">澳大利亚</a><a href="#">新西兰</a></td>
					</tr>
				</table>
			</div>

		</div>
	</div>
	<div class="rowblock">
		<div class="pagewidth">
			<h4>我们可以为您做什么？</h4>
			<div class="p_text">我们帮助企业在竞争激烈的全球市场中实现网络一体化的战略部署。由于每个企业的需求都是独一无二的，我们能够为客户量身打造端到端的敏捷主机托管服务，这样企业就可以更好地专
				注于自己的核心业务。</div>
			<div class="p_text">
				<strong>我们的主机托管业务主要分为空间租用和带宽租用两部分。</strong>
			</div>
			<div class="detailstable">
				<table border="0" cellpadding="0" cellspacing="0">
					<tr>
						<th width="80" scope="col"><span class="bluetxt">空间租用</span></th>
						<th width="750" scope="col">详情</th>
						<th width="300" scope="col">目标客户</th>
					</tr>
					<tr>
						<td width="80">机位出租</td>
						<td width="750">将整个机柜拆分成独立的机位租用给客户，客户最小的租用单位为 1U（高度为 44.45mm）</td>
						<td width="300">中小企业门户、小型网站</td>
					</tr>
					<tr>
						<td width="80">机柜出租</td>
						<td width="750">以整机柜(42U)为单位出租给客户</td>
						<td width="300">托管设备较多的大客户</td>
					</tr>
				</table>

				<table border="0" cellspacing="0" cellpadding="0">
					<tr>
						<th width="80" scope="col"><span class="bluetxt">带宽租用</span></th>
						<th width="750" scope="col">详情</th>
						<th width="300" scope="col">目标客户</th>
					</tr>
					<tr>
						<td width="80">共享带宽</td>
						<td width="750">提供 100 Mbps 的共享带宽，相同网段的客户共享一个上联端口，通过 100 Mbps
							入口接入</td>
						<td width="300">带宽使用量较小的中小企业门户</td>
					</tr>
					<tr>
						<td width="80">独享宽带</td>
						<td width="750">提供 1 Mbps~10 Gbps 的独享带宽，客户独享一个上联端口，为 100 Mbps
							以内的提供电缆接入， 100 Mbps 以上的客户提供电缆或光纤接入</td>
						<td width="300">对网络响应要求高，带宽峰值高的客户，以视频、电子商务类、 网络游戏类客户为主</td>
					</tr>
				</table>

			</div>
		</div>
	</div>
	<div class="rowblock graybg">
		<div class="pagewidth">
			<h4>为什么选择我们？</h4>
			<div class="rowlineblocklist">
				<div class="rowlineblock">
					<div class="rowlineblock_icon lineblock_icon01"></div>
					<div class="rowlineblock_t">专业</div>
					<div class="rowlineblock_p">
						广阔的专业知识和人际资源<br> 丰富的全球部署见解和经验<br> 专业的技术团队
					</div>
				</div>
				<div class="rowlineblock">
					<div class="rowlineblock_icon lineblock_icon02"></div>
					<div class="rowlineblock_t">敏捷</div>
					<div class="rowlineblock_p">
						高度定制化的敏捷服务<br> 快速部署和响应<br> 创造性的智能解决方案
					</div>
				</div>
				<div class="rowlineblock noline">
					<div class="rowlineblock_icon lineblock_icon03"></div>
					<div class="rowlineblock_t">服务</div>
					<div class="rowlineblock_p">
						本地化的服务<br> 24*7全天候支持<br> 专业的技术实现和支持团队
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@include file="foot.jsp"%>
	<script type="text/javascript">
		$(function() {
			$.ajax({
				url : '<%=path %>/wholeworld/getTreeMenu',
				data : {},
				dataType : 'json',
				async : false,
				type : 'get',
				success : function(json) {
					console.log(json.result);
					if (json.result) {
						var html = "";
						$.each(json.data, function(i, obj) {
							var area = "<tr>";
							area += "<th scope='row'>" + obj.text + "</th>";
							var option = "";
							console.log(obj.text);
							$.each(json.data[i].children, function(i, obj) {
								console.log(obj.text);
								option += "<a href='<%=path %>/machine/index/"+obj.id+"'>"
										+ obj.text + "</a>";
							});
							area += "<td>" + option + "</td></tr>";
							html += area;
						});
						console.log(html);
						$("#mtable").html(html);
					}
				}
			});
		});
	</script>
</body>
</html>
