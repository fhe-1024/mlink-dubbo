var tip = function() {
			return {
				init : function() {
					$("#tip_table").datagrid({
						width:document.body.clientWidth-300,
						height:750,
						nowrap: true,
						autoRowHeight: false,
						striped: true,
						collapsible:true,
						singleSelect : true,
						url:'tip/getTipList',
						sortOrder: 'desc',
						remoteSort: false,
						columns:[[
							{field:"tip",title:"小提示",width:'100%'}
						]],
						pagination:true,
						rownumbers:true,
						toolbar:[{
							id:'btnedt',
							text:'添加提示',
							iconCls:'icon-edit',
							handler:function(){
								var node = $('#tip').tree('getSelected');
								if (node){
										console.log(node.text+":"+node.id+":"+node.level);
										if("country"==node.level){
											$("#tipwindow").window("open");
											$("input[name='tip_countryid']").val(node.id);
											$("#tip_submit").unbind().bind().click(function(){
												tip.addTip();
											});
										}else{
											$.messager.show({
								                title:'警告',
								                msg:':)',
								                showType:'show'
								            });
										}
								}
							}
						},
						{
							id:'btnedt',
							text:'修改详情',
							iconCls:'icon-cut',
							handler:function(){
								var row = $('#tip_table').datagrid('getSelected');
								if (row){
									$("#tipwindow").window("open");
									$("input[name='tip_id']").val(row.id);
									$("#tip_tip").textbox('setValue',row.tip);
									$("#tip_submit").unbind().bind().click(function(){
										tip.updateTip();
									});
									
								}
							}
						},
						{
							id:'btnedt',
							text:'删除详情',
							iconCls:'icon-remove',
							handler:function(){
								var row = $('#tip_table').datagrid('getSelected');
								if (row){
									if(confirm("确认删除吗?")){
										$.ajax({
											url : 'tip/deleteTip',
											data : {"id":row.id},
											dataType : 'json',
											async : false,
											type : 'post',
											success : function(json) {
												if(json.result){
													var node = $('#tip').tree('getSelected');
													if (node){
														$("#tip_table").datagrid("reload",{"level":node.level,"id":node.id});
													}
													$.messager.show({
										                title:'小提示',
										                msg:'删除成功',
										                showType:'show'
										            });
												}
											}
										});
									}
								}
							}
						}]
					});
					
					$("#tip").tree({
						url:"tip/getTreeMenu",
						method:"get",
						animate:"true",
						lines:true,
			            onClick:function(node){  
			                console.log(node.id+":"+node.level);
			                $("#tip_table").datagrid("reload",{"level":node.level,"id":node.id});
			            }  
			        });  
				},
				
				addTip:function(){
					$.ajax({
						url : 'tip/saveTip',
						data : $('#tipform').serialize(),
						dataType : 'json',
						async : false,
						type : 'post',
						success : function(json) {
							if(json.result){
								var node = $('#tip').tree('getSelected');
								if(node){
									$.messager.show({
						                title:'小提示',
						                msg:'添加成功',
						                showType:'show'
						            });
									//重新加载表格
									$("#tip_table").datagrid("reload",{"level":node.level,"id":node.id});
									//树形菜单加上
									console.log(json.id);
									//关闭窗口
									$("#tipwindow").window("close");
								}
							}else{
								$.messager.show({
					                title:'小提示',
					                msg:'添加失败',
					                showType:'show'
					            });
							}
						}
					});
			},
			updateTip:function(){
				$.ajax({
					url : 'tip/updateTip',
					data : $('#tipform').serialize(),
					dataType : 'json',
					async : false,
					type : 'post',
					success : function(json) {
						if(json.result){
							var node = $('#tip').tree('getSelected');
							if(node){
								$.messager.show({
					                title:'小提示',
					                msg:'修改成功',
					                showType:'show'
					            });
								//重新加载表格
								$("#tip_table").datagrid("reload",{"level":node.level,"id":node.id});
								//树形菜单加上
								console.log(json.id);
								//关闭窗口
								$("#tipwindow").window("close");
							}
						}else{
							$.messager.show({
				                title:'小提示',
				                msg:'修改失败',
				                showType:'show'
				            });
						}
					}
				});
			}
	
	
		};
	}();