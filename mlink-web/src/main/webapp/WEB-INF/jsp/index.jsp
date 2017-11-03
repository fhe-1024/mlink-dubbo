<%@page language="java" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>海外服务器托管/租用-云主机-云/IDC专线接入-MLINK-全球智能IDC一体化综合服务</title>
<meta name="keywords"
	content="全球IDC机房、全球IDC服务器、IDC服务提供商、海外服务器租用、海外服务器托管、带宽租用">
<meta name="description"
	content="MLINK致力于为国内企业提供香港、美国、新加坡、泰国等海内外服务器托管/租用、云主机、云/IDC专线接入以及防DDoS攻击等产品服务。跨国经验，质优价低，快速部署，7*24小时中英文客服支持。咨询热线：4001053626。">
<%
	String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath();
%>
<link href="<%=path %>/resources/web/style/common.css" rel="stylesheet"
	type="text/css">
<link href="<%=path %>/resources/web/style/index.css" rel="stylesheet"
	type="text/css">
<link rel="shortcut  icon" type="image/x-icon"
	href="<%=path %>/resources/web/images/favicon.ico" media="screen" />
<script language="javascript" src="<%=path %>/resources/web/js/jquery-1.8.3.min.js"></script>
<script language="javascript" src="<%=path %>/resources/web/js/header.js"></script>
<script>
	$(function() {
		$(".businesstypelist_b").hover(function() {
			$(this).css('background-color', '#f4f4f4');
		}, function() {
			$(this).css('background-color', '');
		})

		//首页幻灯片
		var $imgrolls = $(".bannerblock");
		var len = $imgrolls.length;
		var index = 0;
		var adTimer = null;
		$imgrolls.mouseover(function() {
			index = $imgrolls.index(this);
			showImg(index);
		}).eq(0).mouseover();
		//滑入 停止动画，滑出开始动画.
		$('.bannerlist').hover(function() {
			if (adTimer) {
				clearInterval(adTimer);
			}
		}, function() {
			adTimer = setInterval(function() {
				showImg(index);
				index++;
				if (index == len) {
					index = 0;
				}
			}, 5000);
		}).trigger("mouseleave");

	});

	function showImg(index) {
		var $bannerdot = $(".bannerdot");
		var $rolllist = $bannerdot.find(".bannerdotblock");
		$(".bannerlist").find(".bannerblock").eq(index).stop(true, true)
				.fadeIn(1500).siblings().fadeOut(1000);
		$rolllist.removeClass("active").css("opacity", "0.3").eq(index)
				.addClass("active").css("opacity", "1");
	}
</script>
</head>

<body>
	<!--------首页banner--------->
	<div class="banner">
		<!--------导航--------->
		<%@include file="head.jsp"%>
		<div class="bannerlist  pagewidth">
			<div class="bannerblock bannerpic01">
				<div class="bannerblockcontent">
					<div class="bannertext">
						全球网络无缝连接<br>一体化综合服务
					</div>
				</div>
			</div>
			<div class="bannerblock bannerpic02">
				<div class="bannerblockcontent">
					<div class="bannertext">全球互联 灵活部署</div>
				</div>
			</div>
			<div class="bannerblock bannerpic03" style="display: block">
				<div class="bannerblockcontent">
					<div class="bannertext">体验卓越 共创未来</div>
				</div>
			</div>



		</div>
		<div class="bannerdot">
			<div class="bannerdotblock active"></div>
			<div class="bannerdotblock"></div>
			<div class="bannerdotblock"></div>
		</div>
		<div class="intrtext">丰富的海内外顶级数据中心资源为企业网络的高速互联提供高端专业的数据传输和高速接入服务。通过一站式的综合解决方案，企业能够实现全球业务的快速扩张和稳定运作。</div>
	</div>
	<!------业务体系------->
	<div class="businesstype">
		<div class="pagewidth">
			<h1>业务体系</h1>
			<div class="businesstypelist">
				<div class="businesstypelist_b"
					onclick="javascript:window.location.href='wholeworld/index'">
					<div class="businesstypelist_b_p b_b_p_icon01"></div>
					<div class="businesstypelist_b_t">
						<h3>主机托管</h3>
						<div class="businesstypelist_b_t_list">
							<em>全球节点覆盖</em> <em>按需规划定制</em> <em>设备高效部署</em>
						</div>
					</div>
				</div>
				<div class="businesstypelist_b add_w"
					onclick="javascript:window.location.href='renthost/index'">
					<div class="businesstypelist_b_p b_b_p_icon02"></div>
					<div class="businesstypelist_b_t">
						<h3>主机租用</h3>
						<div class="businesstypelist_b_t_list">
							<em>国内外知名设备厂商</em> <em>定制化的租用服务</em> <em>解决企业采购烦恼</em>
						</div>
					</div>
				</div>
				<div class="businesstypelist_b"
					onclick="javascript:window.location.href='cloudrent/index'">
					<div class="businesstypelist_b_p b_b_p_icon03"></div>
					<div class="businesstypelist_b_t">
						<h3>云主机</h3>
						<div class="businesstypelist_b_t_list">
							<em>即时开通</em> <em>自主操作</em> <em>按需付费</em>
						</div>
					</div>
				</div>
				<div class="businesstypelist_b"
					onclick="javascript:window.location.href='wholeworld/index'">
					<div class="businesstypelist_b_p b_b_p_icon04"></div>
					<div class="businesstypelist_b_t">
						<h3>带宽租用</h3>
						<div class="businesstypelist_b_t_list">
							<em>1.4T总带宽容量</em> <em>质优价更低</em> <em>核心骨干网多线接入</em>
						</div>
					</div>
				</div>
				<div class="businesstypelist_b add_w"
					onclick="javascript:window.location.href='sdn/index'">
					<div class="businesstypelist_b_p b_b_p_icon05"></div>
					<div class="businesstypelist_b_t">
						<h3>SDN专线</h3>
						<div class="businesstypelist_b_t_list">
							<em>即时开通，即用即付 </em><em>无缝连接阿里云和AWS</em> <em>低价高效，安全稳定</em>
						</div>
					</div>
				</div>
				<div class="businesstypelist_b"
					onclick="javascript:window.location.href='ddos/index'">
					<div class="businesstypelist_b_p b_b_p_icon06"></div>
					<div class="businesstypelist_b_t">
						<h3>防DDoS 攻击</h3>
						<div class="businesstypelist_b_t_list">
							<em>全面智能防护 </em><em>最高的准确率</em> <em>灵活部署和扩展</em>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--------产品优势------->
	<div class="index_advantage">
		<div class="pagewidth">
			<h1>产品优势</h1>
			<div class="index_advantage_list">
				<div class="index_advantage_list_b">
					<div class="index_a_l_b_icon index_a_l_b_icon01"></div>
					<h3>更高级的数据中心</h3>
					<div class="index_a_l_b_t">
						全球50+顶级数据中心<br> 运营商网络中立<br> 海量带宽，高速低 价<br>
						安全可靠、灵活部署
					</div>
				</div>
				<div class="index_advantage_list_b">
					<div class="index_a_l_b_icon index_a_l_b_icon02"></div>
					<h3>更全面的增值服务</h3>
					<div class="index_a_l_b_t">
						数据备份和迁移<br> 系统管理和网络监控<br> 负载均衡和DNS解析服务<br>

						常规维护和故障排除
					</div>
				</div>
				<div class="index_advantage_list_b">
					<div class="index_a_l_b_icon index_a_l_b_icon03"></div>
					<h3>更完善的全球服务</h3>
					<div class="index_a_l_b_t">
						7*24小时中英文客服<br> 海内外运营中心全天驻场<br> 本地化专业服务团队
					</div>
				</div>
				<div class="index_advantage_list_b">
					<div class="index_a_l_b_icon index_a_l_b_icon04"></div>
					<h3>更强大的设备部署</h3>
					<div class="index_a_l_b_t">
						按需采购安装<br> 高度定制方案<br> 快速响应和配置
					</div>
				</div>
			</div>

		</div>
	</div>
	<%@include file="foot.jsp"%>
</body>
</html>
