<%@page language="java" pageEncoding="utf-8"%>
<div class="header">
	<div class="headermain pagewidth">
		<div class="logo">
			<img src="<%=request.getContextPath() %>/resources/web/images/logo.png">
		</div>
		<div class="meun">
			<ul>
				<li><a href="<%=request.getContextPath() %>/index"><span>首页</span></a></li>
				<li><a href="javascript:void();"><span>主机托管/租用</span></a>
					<ol>
						<li><a href="<%=request.getContextPath() %>/wholeworld/index">主机托管</a></li>
						<li><a href="<%=request.getContextPath() %>/renthost/index">主机租用</a></li>
						<li><a href="<%=request.getContextPath() %>/cloudrent/index">云主机</a></li>
						<li><a href="<%=request.getContextPath() %>/wholeworld/index">带宽租用</a></li>
						<li><a href="<%=request.getContextPath() %>/ddos/index">防DDoS攻击</a></li>
					</ol></li>
				<li><a href="<%=request.getContextPath() %>/sdn/index"><span>SDN专线</span></a></li>
				<li><a href="<%=request.getContextPath() %>/aboutus/index"><span>关于我们</span></a></li>
			</ul>
		</div>
	</div>
</div>