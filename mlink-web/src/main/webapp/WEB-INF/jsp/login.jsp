<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Basic PasswordBox - jQuery EasyUI Demo</title>
<link rel="stylesheet" type="text/css"
	href="resources/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="resources/easyui/themes/icon.css">
<link rel="shortcut  icon" type="image/x-icon" href="resources/web/images/favicon.ico" media="screen"  />	
<link rel="stylesheet" type="text/css" href="resources/easyui/demo.css">
<script type="text/javascript" src="resources/easyui/jquery.min.js"></script>
<script type="text/javascript"
	src="resources/easyui/jquery.easyui.min.js"></script>
</head>
<body>

	<div style="margin: 15% 0;"></div>
	<div class="easyui-panel" data-options="style:{margin:'0 auto'}"
		style="width: 400px; padding: 50px 60px">
		<form id="loginform" action="">
			<div style="margin-bottom: 20px">
				<input class="easyui-textbox" prompt="Username" name="username"
					iconWidth="28" style="width: 100%; height: 34px; padding: 10px;">
			</div>
			<div style="margin-bottom: 20px">
				<input class="easyui-passwordbox" prompt="Password" name="password"
					iconWidth="28" style="width: 100%; height: 34px; padding: 10px">
			</div>
			<div>
				<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
					style="width: 100%; height: 32px">Register</a>
			</div>
		</form>
	</div>
	<script type="text/javascript">
		$(function() {
			$(".easyui-linkbutton").click(function() {
				$.ajax({
					url : 'login/manage',
					data : $('#loginform').serialize(),
					dataType : 'json',
					async : false,
					type : 'post',
					success : function(json) {
						if (json.result) {
							window.location.href = "manage";
						}else{
							$.messager.show({
				                title:'tips',
				                msg:'please check your username or password!',
				                showType:'show'
				            });
						}
					}
				});
			});
		});
	</script>
</body>
</html>