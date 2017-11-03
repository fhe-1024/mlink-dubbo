<%@page language="java" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>海外服务器-香港/台湾/美国/新加坡/泰国/印度/越南/英国/德国服务器租用- MLINK</title>
<meta name="keywords"
	content="国外主机租用、香港主机租用、台湾主机租用、美国主机租用、新加坡主机租用、越南主机租用">
<meta name="description"
	content="MLINK为国内企业提供定制化的全球服务器租用服务。根据客户需求，MLINK采购DELL、华为、超微等合作厂商资源，设备稳定、高性价比。咨询热线：4001053626。">
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
</head>

<body>
	<div class="pageheader">
		<%@include file="head.jsp" %>
		<div class="wholeworldbanner">
			<div class="pagewidth pic_p">
				<img src="<%=path %>/resources/web/images/rentpic.jpg">
				<div class="intrpage jf">
					<h3>主机租用</h3>
					<p>当您没有自己的服务器、网络设备等硬件时，我们可以提供服务器租用业务。
						根据您的需求，我们采购符合要求的硬件设备，并以租用的形式提供给您使用。</p>
				</div>
			</div>
		</div>
	</div>
	<div class="rowblock">
		<div class="pagewidth">
			<div class="renthostdiv">
				<div class="renthostblock">
					<h5>DELL、华为、超微等国内外知名设备厂商</h5>
					<img src="<%=path %>/resources/web/images/renthostpic01.jpg">
				</div>
				<div class="renthostblock">
					<h5>稳定、优质的硬件设备</h5>
					<img src="<%=path %>/resources/web/images/renthostpic02.jpg">
				</div>
				<div class="renthostblock">
					<h5>按需定制，解决烦恼</h5>
					<img src="<%=path %>/resources/web/images/renthostpic03.jpg">
				</div>
			</div>


		</div>
	</div>


	<div class="rowblock graybg">
		<div class="pagewidth">
			<h4>想要了解更多关于主机租用业务信息，请联系我们。</h4>
			<div class="contactlist">
				<div class="contactblock contacticon01">咨询热线：4001053626</div>
				<div class="contactblock contacticon02">咨询邮箱：sales@mlink.cn</div>
			</div>
		</div>
	</div>
	<%@include file="foot.jsp" %>
</body>
</html>
