<%@page language="java" pageEncoding="utf-8"%>
<div class="footer">
	<div class="pagewidth">
		
		<div class="footer_b">
			<em>备案号：</em>京ICP备10054122号-10
		</div>
		<div class="footer_b">
			<em>客服热线：</em>4001053626
		</div>
		<div class="footer_b">
			<em>咨询邮箱：</em>sales@mlink.cn
		</div>
		<div class="footer_b rightfloat">
			<em>官网二维码：</em><img src="<%=request.getContextPath() %>/resources/web/images/web_qr.png">
		</div>
	</div>
</div>
<script>
(function(){
    var bp = document.createElement('script');
    var curProtocol = window.location.protocol.split(':')[0];
    if (curProtocol === 'https'){
   bp.src = 'https://zz.bdstatic.com/linksubmit/push.js';
  }
  else{
  bp.src = 'http://push.zhanzhang.baidu.com/push.js';
  }
    var s = document.getElementsByTagName("script")[0];
    s.parentNode.insertBefore(bp, s);
})();
</script>