var facility = function() {
			return {
				init : function() {
					$("#facility_table").datagrid({
						width:document.body.clientWidth-300,
						height:750,
						nowrap: true,
						autoRowHeight: false,
						striped: true,
						collapsible:true,
						singleSelect : true,
						url:'facility/getFacilityList',
						sortOrder: 'desc',
						remoteSort: false,
						columns:[[ 
							{field:"type",title:"类型",width:'15%',
								formatter:function(value,row,index){
									if(value == '0'){
										return '机房-托管规格-机柜功率-机柜价格';
									}else if(value == '1'){
										return '(机房)-托管规格-机柜功率-机柜价格';
									}else if(value == '2'){
										return '(机房-托管规格)-机柜功率-机柜价格';
									}else if(value == '3'){
										return '(机房-托管规格)-(机柜功率-机柜价格)';
									}			
								}		
							},   
							{field:"name",title:"机房名称",width:'15%'},
							{field:"standard",title:"托管规格",width:'15%'},
							{field:"power",title:"功率",width:'25%'},
							{field:"price",title:"机柜价格",width:'25%'},
							{field:"sort",title:"排序",width:'5%'}
						]],
						pagination:true,
						rownumbers:true,
						toolbar:[{
							id:'btnedt',
							text:'添加详情',
							iconCls:'icon-edit',
							handler:function(){
								var node = $('#facility').tree('getSelected');
								if (node){
										console.log(node.text+":"+node.id+":"+node.level);
										if("country"==node.level){
											$("#facilitywindow").window("open");
											$("input[name='facility_countryid']").val(node.id);
											$("#facility_submit").unbind().bind().click(function(){
												facility.addFacility();
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
								var row = $('#facility_table').datagrid('getSelected');
								if (row){
									$("#facilitywindow").window("open");
									$("input[name='facility_id']").val(row.id);
									$('#facility_type').combobox('setValue', row.type);
									$("#facility_sort").textbox('setValue',row.sort);
									$("#facility_name").textbox('setValue',row.name);
									$("#facility_standard").textbox('setValue',row.standard);
									$("#facility_power").textbox('setValue',row.power);
									$("#facility_price").textbox('setValue',row.price);
									$("#facility_submit").unbind().bind().click(function(){
										facility.updateFacility();
									});
									
								}
							}
						},
						{
							id:'btnedt',
							text:'删除详情',
							iconCls:'icon-remove',
							handler:function(){
								var row = $('#facility_table').datagrid('getSelected');
								if (row){
									if(confirm("确认删除吗?")){
										$.ajax({
											url : 'facility/deleteFacility',
											data : {"id":row.id},
											dataType : 'json',
											async : false,
											type : 'post',
											success : function(json) {
												if(json.result){
													var node = $('#facility').tree('getSelected');
													if (node){
														$("#facility_table").datagrid("reload",{"level":node.level,"id":node.id});
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
					
					$("#facility").tree({
						url:"facility/getTreeMenu",
						method:"get",
						animate:"true",
						lines:true,
			            onClick:function(node){  
			                console.log(node.id+":"+node.level);
			                $("#facility_table").datagrid("reload",{"level":node.level,"id":node.id});
			            }  
			        });  
				},
				
				addFacility:function(){
					$.ajax({
						url : 'facility/saveFacility',
						data : $('#facilityform').serialize(),
						dataType : 'json',
						async : false,
						type : 'post',
						success : function(json) {
							if(json.result){
								var node = $('#facility').tree('getSelected');
								if(node){
									$.messager.show({
						                title:'小提示',
						                msg:'添加成功',
						                showType:'show'
						            });
									//重新加载表格
									$("#facility_table").datagrid("reload",{"level":node.level,"id":node.id});
									//树形菜单加上
									console.log(json.id);
									//关闭窗口
									$("#facilitywindow").window("close");
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
			updateFacility:function(){
				$.ajax({
					url : 'facility/updateFacility',
					data : $('#facilityform').serialize(),
					dataType : 'json',
					async : false,
					type : 'post',
					success : function(json) {
						if(json.result){
							var node = $('#facility').tree('getSelected');
							if(node){
								$.messager.show({
					                title:'小提示',
					                msg:'修改成功',
					                showType:'show'
					            });
								//重新加载表格
								$("#facility_table").datagrid("reload",{"level":node.level,"id":node.id});
								//树形菜单加上
								console.log(json.id);
								//关闭窗口
								$("#facilitywindow").window("close");
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