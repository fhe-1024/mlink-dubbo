<%@page language="java" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>海外云主机-美国云主机租用- MLINK</title>
<meta name="keywords"
	content="云服务器、云主机、BGP多线、按需付费、服务器短期租用、主机短期租用、快速开通">
<meta name="description"
	content="MLINK为企业提供短期的海外云主机租用服务。企业能够在线自主开通和关闭主机业务，通过虚拟网络控制台（VNC）远程控制服务器的运行。按需付费、灵活管理。咨询热线：4001053626。">
<%
	String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath();
%>
<link rel="shortcut  icon" type="image/x-icon" href="<%=path%>/resources/web/images/favicon.ico" media="screen"  />					
<link href="<%=path%>/resources/web/style/common.css" rel="stylesheet" type="text/css">
<link href="<%=path%>/resources/web/style/othercss.css" rel="stylesheet" type="text/css">
<script language="javascript" src="<%=path%>/resources/web/js/jquery-1.8.3.min.js"></script>
<script language="javascript" src="<%=path%>/resources/web/js/header.js"></script>
</head>

<body>
	<div class="pageheader">
		<%@include file="head.jsp" %>
		<div class="wholeworldbanner">
			<div class="pagewidth pic_p">
				<img src="<%=path%>/resources/web/images/crentpic.jpg">
				<div class="intrpage jf">
					<h3>云主机租用</h3>
					<p>简单而强大的云部署</p>
				</div>
			</div>
		</div>
	</div>
	<div class="rowblock">
		<div class="pagewidth">
			<div class="renthostdiv">
				<div class="renthostblock">
					<h5>快速部署 10分钟即可开通</h5>
					<img src="<%=path%>/resources/web/images/Crenthostpic01.jpg">
				</div>
				<div class="renthostblock">
					<h5>免合约 适合短期租用 无最低消费门槛</h5>
					<img src="<%=path%>/resources/web/images/Crenthostpic02.jpg">
				</div>
				<div class="renthostblock">
					<h5>可扩展 1-100VM 自助选择</h5>
					<img src="<%=path%>/resources/web/images/Crenthostpic03.jpg">
				</div>
			</div>


		</div>
	</div>
	<div class="rowblock graybg">
		<div class="pagewidth">
			<h4>服务</h4>
			<div class="crentservicelist">
				<div class="crentserviceblock">
					<div class="crentserviceicon csicon01"></div>
					<h5>SSD硬盘选择</h5>
				</div>
				<div class="crentserviceblock">
					<div class="crentserviceicon csicon02"></div>
					<h5>负载均衡</h5>
				</div>

				<div class="crentserviceblock">
					<div class="crentserviceicon csicon03"></div>
					<h5>防火墙</h5>
				</div>

				<div class="crentserviceblock">
					<div class="crentserviceicon csicon04"></div>
					<h5>BGP多线接入</h5>
				</div>

				<div class="crentserviceblock">
					<div class="crentserviceicon csicon05"></div>
					<h5>海内外资源</h5>
				</div>

				<div class="crentserviceblock">
					<div class="crentserviceicon csicon06"></div>
					<h5>全面安全防护</h5>
				</div>

			</div>
		</div>
	</div>

	<div class="rowblock">
		<div class="pagewidth">
			<h4>想要了解更多关于云主机租用业务信息，请联系我们。</h4>
			<div class="contactlist">
				<div class="contactblock contacticon01">咨询热线：4001053626</div>
				<div class="contactblock contacticon02">咨询邮箱：sales@mlink.cn</div>
			</div>
		</div>
	</div>
	<%@include file="foot.jsp" %>
</body>
</html>
