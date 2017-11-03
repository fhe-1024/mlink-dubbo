var consult = function() {
			return {
				init : function() {
					console.log(document.body.clientHeight);
					$("#consult_table").datagrid({
						width:document.body.clientWidth-100,
						height:750,
						nowrap: true,
						autoRowHeight: false,
						striped: true,
						collapsible:true,
						singleSelect : true,
						url:'consult/getConsultList',
						sortOrder: 'desc',
						remoteSort: false,
						columns:[[ 
							{field:"name",title:"称呼",width:'10%'},
							{field:"sex",title:"性别",width:'10%',
								formatter:function(value,row,index){
									if(value=='0'){
										return '男士';
									}else{
										return '女士';
									}
								}
							},
							{field:"phone",title:"联系电话",width:'10%'},
							{field:"position",title:"职位",width:'10%'},
							{field:"company",title:"公司",width:'10%'},
							{field:"text",title:"咨询详情",width:'40%'},
							{field:"createtime",title:"咨询时间",width:'10%'}
						]],
						pagination:true,
						rownumbers:true,
						toolbar:[]
					});
					
				}
	
		};
	}();