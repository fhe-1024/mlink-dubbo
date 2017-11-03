var line = function() {
			return {
				init : function() {
					console.log(document.body.clientHeight);
					$("#line_table").datagrid({
						width:document.body.clientWidth-100,
						height:750,
						nowrap: true,
						autoRowHeight: false,
						striped: true,
						collapsible:true,
						singleSelect : true,
						url:'line/getLineList',
						sortOrder: 'desc',
						remoteSort: false,
						columns:[[ 
							{field:"name",title:"名称",width:'20%'},
							{field:"price",title:"价格",width:'20%'},
							{field:"sort",title:"排序",width:'60%'}
						]],
						pagination:true,
						rownumbers:true,
						toolbar:[{
							id:'btnedt',
							text:'添加专线详情',
							iconCls:'icon-edit',
							handler:function(){
								$("#linewindow").window("open");
								$("#line_submit").unbind().bind().click(function(){
									line.addLine();
								});
							}
						},
						{
							id:'btnedt',
							text:'修改专线详情',
							iconCls:'icon-cut',
							handler:function(){
								var row = $('#line_table').datagrid('getSelected');
								if(row){
									$("#linewindow").window("open");
									$("input[name='line_id']").val(row.id);
									
									$("#line_name").textbox('setValue',row.name);
									$("#line_price").textbox('setValue',row.price);
									$("#line_sort").textbox('setValue',row.sort);
									
									$("#line_submit").unbind().bind().click(function(){
										line.updateLine();
									});
									
								}
							}
						},
						{
							id:'btnedt',
							text:'删除专线详情',
							iconCls:'icon-remove',
							handler:function(){
								var row = $('#line_table').datagrid('getSelected');
								if (row){
									if(confirm("确认删除吗?")){
										$.ajax({
											url : 'line/deleteLine',
											data : {"id":row.id},
											dataType : 'json',
											async : false,
											type : 'post',
											success : function(json) {
												if(json.result){
													$("#line_table").datagrid("reload",{});
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
					
				},
				addLine:function(){
					$.ajax({
						url : 'line/saveLine',
						data : $('#lineform').serialize(),
						dataType : 'json',
						async : false,
						type : 'post',
						success : function(json) {
							if(json.result){
								
									$.messager.show({
						                title:'小提示',
						                msg:'添加成功',
						                showType:'show'
						            });
									//重新加载表格
									$("#line_table").datagrid("reload",{});
									//树形菜单加上
									console.log(json.id);
								
									//关闭窗口
									$("#linewindow").window("close");
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
			updateLine:function(){
				$.ajax({
					url : 'line/updateLine',
					data : $('#lineform').serialize(),
					dataType : 'json',
					async : false,
					type : 'post',
					success : function(json) {
						if(json.result){
								$.messager.show({
					                title:'小提示',
					                msg:'修改成功',
					                showType:'show'
					            });
								//重新加载表格
								$("#line_table").datagrid("reload",{});
							
								//关闭窗口
								$("#linewindow").window("close");
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