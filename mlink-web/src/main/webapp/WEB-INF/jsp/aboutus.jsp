<%@page language="java" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>香港、美国、新加坡、泰国等海外服务器托管/租用-云主机-专线接入-MLINK-全球智能IDC一体化综合服务</title>
<%
	String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath();
%>
<link href="<%=path%>/resources/web/style/common.css" rel="stylesheet"
	type="text/css">
<link href="<%=path%>/resources/web/style/othercss.css" rel="stylesheet"
	type="text/css">
<link rel="shortcut  icon" type="image/x-icon" href="<%=path%>/resources/web/images/favicon.ico" media="screen"  />	
<script language="javascript" src="<%=path%>/resources/web/js/jquery-1.8.3.min.js"></script>
<script language="javascript" src="<%=path%>/resources/web/js/header.js"></script>
<script language="javascript">
	$(function() {
		$(".sexy").find(".sexyblcok").click(function() {
			$(this).addClass("active").siblings().removeClass("active");
		})
	});
</script>
</head>

<body>
	<div class="pageheader">
		<%@include file="head.jsp"%>
		<div class="wholeworldbanner">
			<div class="pagewidth pic_p">
				<img src="<%=path%>/resources/web/images/aboutpic.jpg">
				<div class="intrpage jf">
					<h3>关于我们</h3>
					<p>
						我们为企业的海外扩张提供全面的工程和技术解决方案，包括传统主机托管和<br>
						数据中心服务、云计算和IT网络服务。我们有一批经验丰富的专家团队，能够<br>
						协助您一起应对激烈的市场挑战，帮助您在海外目标市场实现快速成长。
					</p>
				</div>
			</div>
		</div>
	</div>
	<div class="rowblock">
		<div class="pagewidth">
			<div class="renthostdiv">
				<div class="renthostblock Fcolumn">
					<h5>咨询&amp;架构</h5>
					<img src="<%=path%>/resources/web/images/aboutpicblockpic01.jpg">
				</div>
				<div class="renthostblock Fcolumn">
					<h5>采购&amp;物流</h5>
					<img src="<%=path%>/resources/web/images/aboutpicblockpic02.jpg">
				</div>
				<div class="renthostblock Fcolumn">
					<h5>实施&amp;管理</h5>
					<img src="<%=path%>/resources/web/images/aboutpicblockpic03.jpg">
				</div>
				<div class="renthostblock Fcolumn">
					<h5>优化&amp;规模</h5>
					<img src="<%=path%>/resources/web/images/aboutpicblockpic04.jpg">
				</div>
			</div>


		</div>
	</div>

	<div class="rowblock graybg">
		<div class="pagewidth">
			<h4>我们的宗旨</h4>
			<div class="aboutlist">
				<div class="aboutlistblock">
					<div class="aboutblockicon abicon01"></div>
					<h5>理解</h5>
					<p>
						每一个方案都是独一无二的<br> 为企业量身打造最正确的解决方案。
					</p>
				</div>

				<div class="aboutlistblock">
					<div class="aboutblockicon abicon02"></div>
					<h5>响应</h5>
					<p>
						全球7*24小时<br>无间断的运作和响应。
					</p>
				</div>

				<div class="aboutlistblock">
					<div class="aboutblockicon abicon03"></div>
					<h5>效率</h5>
					<p>
						我们拥有提供最优解决方案的<br>基础设施、见解和专业知识。
					</p>
				</div>

				<div class="aboutlistblock">
					<div class="aboutblockicon abicon04"></div>
					<h5>弹性</h5>
					<p>
						我们让事情尽可能变得简单，<br>竭尽全力满足您的技术和商业需求。
					</p>
				</div>

			</div>
		</div>
	</div>

	<div class="rowblock">
		<div class="pagewidth">
			<h4>联系我们</h4>
			<div class="lineh">
				<em>您在了解我们产品和服务的过程如遇问题，可以通过以下几种方式与我们联系,我们将及时为您处理</em>
			</div>
			<div class="contactlist">
				<div class="contactblock contacticon01">咨询热线：4001053626</div>
				<div class="contactblock contacticon02">咨询邮箱：sales@mlink.cn</div>
			</div>
		</div>
	</div>
	<div class="rowblock graybg">
		<div class="pagewidth">
			<h4>信息咨询申请表</h4>
			<form action="" method="get">
				<div class="halfrow">
					<span>称呼</span><input name="name" type="text"><em>必填</em>
				</div>
				<div class="halfrow">
					<span>性别</span>
					<div class="sexy">
						<div class="sexyblcok active">先生</div>
						<div class="sexyblcok">女士</div>
					</div>
				</div>
				<div class="halfrow">
					<span>联系电话</span><input name="phone" type="text"><em>必填</em>
				</div>
				<div class="halfrow">
					<span>职位</span><input name="position" type="text">
				</div>
				<div class="row">
					<span>公司</span><input name="company" type="text">
				</div>
				<div class="row">
					<span>咨询详情</span>
					<textarea name="text" cols="" rows=""></textarea>
				</div>
				<div class="row">
					<button type="button" class="tjbtn">提 交</button>
					<button type="button" class="ptbtn">重 置</button>
				</div>
			</form>
		</div>
	</div>
	<%@include file="foot.jsp"%>
	<script type="text/javascript">
	$(function() {
		$(".tjbtn").click(function() {
			var name=$("input[name='name']").val();
			var sexhtml=$("div.sexy div.active").html();
			var phone=$("input[name='phone']").val();
			var position=$("input[name='position']").val();
			var company=$("input[name='company']").val();
			var text=$("textarea[name='text']").val();
			console.log("name"+name);
			console.log("sexhtml"+sexhtml);
			console.log("phone"+phone);
			console.log("position"+position);
			console.log("company"+company);
			console.log("text"+text);
			if(name==''){
				alert('请填写称呼');
				$("input[name='name']").focus();
				return;
			}else if(phone==''){
				alert('请填写联系电话');
				$("input[name='phone']").focus();
				return;
			}
			var sex='0';
			if(sexhtml=='女士'){
				sex='1';
			}
			$.ajax({
				url : '<%=path%>/aboutus/saveConsult',
				data : {'name':name,'sex':sex,'phone':phone,'position':position,'company':company,'text':text},
				dataType : 'json',
				async : false,
				type : 'post',
				success : function(json) {
					if(json.result){
						alert('信息提交成功，我们会尽快与您联系.');
					}else{
						alert('信息提交失败,请刷新后重试');
					}
				}
			});
		});
		$(".ptbtn").click(function() {
			$("input[name='name']").val('');
			$("div.sexy div:eq(0)").addClass("active").siblings().removeClass("active");
			$("input[name='phone']").val('');
			$("input[name='position']").val('');
			$("input[name='company']").val('');
			$("textarea[name='text']").val('');
		});
	});
	</script>
</body>
</html>
