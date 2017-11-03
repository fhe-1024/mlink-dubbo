	 var basic = function() {
			return {
				init : function() {
					console.log(document.body.clientHeight);
					$("#node_table").datagrid({
						width:document.body.clientWidth-300,
						height:750,
						nowrap: true,
						autoRowHeight: false,
						striped: true,
						collapsible:true,
						singleSelect : true,
						url:'tree/getNodeList',
						sortOrder: 'desc',
						remoteSort: false,
						columns:[[ 
							{field:"name",title:"名称",width:'20%'},
							{field:"sort",title:"排序",width:'20%'},
							{field:"description",title:"描述",width:'60%'}
						]],
						pagination:true,
						rownumbers:true,
						toolbar:[{
							id:'btnedt',
							text:'添加节点',
							iconCls:'icon-edit',
							handler:function(){
								var node = $('#tt').tree('getSelected');
								if (node){
										console.log(node.text+":"+node.id+":"+node.level);
										if("node"!=node.level){
											$("#window").window("open");
											$("input[name='levelid']").val(node.id);
											$("input[name='levelname']").val(node.level);
											$("#level_submit").unbind().bind().click(function(){
												basic.addLevel();
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
							text:'修改节点',
							iconCls:'icon-cut',
							handler:function(){
								var node = $('#tt').tree('getSelected');
								if (node){
									console.log(node.text+":"+node.id+":"+node.level);
									$("#window").window("open");
									$("input[name='levelid']").val(node.id);
									$("input[name='levelname']").val(node.level);
									$("#level_name").textbox('setValue',node.text);
									$("#sort").textbox('setValue',node.sort);
									
									$("#level_submit").unbind().bind().click(function(){
										basic.updateLevel();
									});
								}
							}
						},
						{
							id:'btnedt',
							text:'删除节点',
							iconCls:'icon-remove',
							handler:function(){
								var node = $('#tt').tree('getSelected');
								if (node){
										console.log(node.text+":"+node.id+":"+node.level);
										
										if(confirm("确认删除当前节点及其子节点吗?")){
											$.ajax({
												url : 'tree/deleteLevel',
												data : {"nodelevel":node.level,"nodeid":node.id},
												dataType : 'json',
												async : false,
												type : 'post',
												success : function(json) {
													if(json.result){
														var node = $('#tt').tree('getSelected');
														if(node){
																$('#tt').tree('remove',node.target);
																$('#node_table').datagrid('reload',{});
																$.messager.show({
													                title:'小提示',
													                msg:'删除成功',
													                showType:'show'
													            });
														}		
													}
												}
											});
										}
										
										
								}		
							}
						}]
					});
					
					$("#tt").tree({
						url:"tree/getTreeMenu",
						method:"get",
						animate:"true",
						lines:true,
			            onClick:function(node){  
			                console.log(node.id+":"+node.level);
			                $("#node_table").datagrid("reload",{"level":node.level,"id":node.id});
			            }  
			        });  
				},
	
				addLevel:function(){
					$.ajax({
						url : 'tree/saveLevel',
						data : $('#levelform').serialize(),
						dataType : 'json',
						async : false,
						type : 'post',
						success : function(json) {
							if(json.result){
								var node = $('#tt').tree('getSelected');
								if(node){
									$.messager.show({
						                title:'小提示',
						                msg:'添加成功',
						                showType:'show'
						            });
									//重新加载表格
									$("#node_table").datagrid("reload",{"level":node.level,"id":node.id});
									//树形菜单加上
									console.log(json.id);
									var nodes=[{
										"id":json.id,
										"text":$("input[name='level_name']").val(),
										"level":json.level
									}];
									$('#tt').tree('append', {
										parent:node.target,
										data:nodes
									});
									//关闭窗口
									$("#window").window("close");
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
			updateLevel:function(){
				$.ajax({
					url : 'tree/updateLevel',
					data : $('#levelform').serialize(),
					dataType : 'json',
					async : false,
					type : 'post',
					success : function(json) {
						if(json.result){
							var node = $('#tt').tree('getSelected');
							if(node){
								$.messager.show({
					                title:'小提示',
					                msg:'修改成功',
					                showType:'show'
					            });
								//树形菜单加上
								 $('#tt').tree('reload');
								console.log(json.id);
								//关闭窗口
								$("#window").window("close");
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