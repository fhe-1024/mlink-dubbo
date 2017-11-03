var exchange = function() {
			return {
				init : function() {
					console.log(document.body.clientHeight);
					$("#exchange_table").datagrid({
						width:document.body.clientWidth-100,
						height:750,
						nowrap: true,
						autoRowHeight: false,
						striped: true,
						collapsible:true,
						singleSelect : true,
						url:'exchange/getExchangeList',
						sortOrder: 'desc',
						remoteSort: false,
						columns:[[ 
							{field:"fromcurrency",title:"原币种",width:'10%'},
							{field:"tocurrency",title:"目标币种",width:'10%'},
							{field:"exchange",title:"汇率(中国银行)",width:'10%'},
							{field:"createtime",title:"我方获取数据时间",width:'30%'},
							{field:"updatetime",title:"数据实际更新时间",width:'30%'}
						]],
						pagination:true,
						rownumbers:true,
						toolbar:[]
					});
					
				}
	
		};
	}();