/*
 * layui引入样式的两种方式:模块化用和全模块用法
 * layui的初始化模块可以理解为，引入这个模块的js文件
 * 值得注意的是：layui的模块之间存在依赖关系，例如引入了table模块，它会layui/lay/moudules/table.js
 * 这个文件引入进来，并且它还会将这个模块所依赖的其他模块自动加入进来，
 * 实际引入的模块有：table、form、jquery、layer、laypage、laytpl、util，7个js文件
 * 所以我仅仅初始化了table模块，却能获得form、jquery、layer的模块对象
 */
//layui初始化表格模组
layui.use(['table', 'laydate', 'upload','element'], function() {
	// 获得模块对象
	var table = layui.table;
	var form = layui.form;
	var $ = layui.jquery;
	var layer = layui.layer;
	var laydate = layui.laydate;
	var upload = layui.upload;
	//定义下拉搜索框方法
	function xia() {
		var hideBtn = $("#hideBtn");
		var flag = false;
		var search_header = $("#search-header");
		search_header.css("top",-search_header.height());
		hideBtn.click(function () {
			let height = search_header.height();
			if(flag){
				search_header.animate({
					"top":-height
				},"fast","linear");
				flag = false;
			}else {
				search_header.animate({
					"top":0
				},"fast","linear");
				flag = true;
			}
		});
	}
	//调用下拉框方法
	xia();
	//定义数据表格渲染方法
	function f(a,b) {
		var tableIns=table.render({
			elem: '#'+ a,
			url: '/fa/depositInterestAccrualController/findSecuritiesInventoryByCondition?tradeStatus=' + b, //后期改回获取用户列表的后端程序的url
			method: 'get',
			where: {}, // 你额外要携带数据，以键值对的方式存入
			toolbar: '#depositInterestAccrual', // 开启头部工具栏，并为其绑定左侧模板
			cellMinWidth: 80, // 全局定义所有常规单元格的最小宽度（默认：60）
			height: 'full-10',
			cols: [
				[{
					type: 'numbers'
				}, // 序号
					{
						type: 'checkbox'
					}, //复选框
					{
						field: 'securitiesInventoryId',
						title: '证劵库存Id',
						unresize: true,
						hide:true,
						align: "center",
						hide: true  //一般情况下不显示用户ID

					},
					{
						field: 'sechuritiesInventoryNo',
						title: '证劵库存编号',
						unresize: true,
						width:150,
						align: "center"
					},
					{
						field: 'securitiesId',
						title: '证券Id',
						unresize: true,
						align: "center",
						hide: true  //一般情况下不显示用户ID
					},
					{
						field: 'securitiesNo',
						title: '证券编号',
						unresize: true,
						align: "center",
						hide: true  //一般情况下不显示用户ID
					},
					{
						field: 'securitiesName',
						title: '证券名',
						unresize: true,
						align: "center"
					},
					{
						field: 'fundId',
						title: '基金Id',
						unresize: true,
						hide:true,
						align: "center",
						hide: true  //一般情况下不显示用户ID
					},
					{
						field: 'fundNo',
						title: '基金代码',
						unresize: true,
						align: "center",
					},
					{
						field: 'fundName',
						title: '基金名',
						width:160,
						unresize: true,
						align: "center",
					},
					{
						field: 'accountId',
						title: '账户ID',
						unresize: true,
						width:130,
						align: "center",
						hide: true  //一般情况下不显示用户ID
					},
					{
						field: 'accountNo',
						title: '账户账号',
						unresize: true,
						hide:true,
						align: "center",
						hide: true  //一般情况下不显示用户ID
					},
					{
						field: 'accountName',
						title: '账户名',
						unresize: true,
						hide: true,
						align: "center",
					},
					{
						field: 'price',
						title: '单位成本',
						unresize: true,
						align: "center",
					},
					{
						field: 'share',
						title: '持有份额',
						unresize: true,
						align: "center",
					},
					{
						field: 'turnover',
						title: '总金额',
						width:105,
						unresize: true,
						align: "center"
					},
					{
						field: 'securitiesType',
						title: '证劵类型',
						unresize: true,
						align: "center",
						templet: "#tradeStatusTpl4"
					},
					{
						field: 'statisticalDate',
						title: '统计日期',
						unresize: true,
						align: "center"
					},
					{
						field: 'description',
						title: '描述',
						unresize: true,
						align: "center"
					},
					{
						field: 'tradeStatus',
						title: '状态',
						unresize: true,
						align: "center",
						//fixed:'right',
						templet: '#tradeStatusTpl'
					}
				]
			],
			page: true, // 开启分页
			limit: 10, // 每页显示的条数
			limits: [10, 20, 50, 100], // 每页条数的选择项
			loading: true, // 是否显示加载条(切换分页的时候显示）
			title: '存款计息', // 定义 table 的大标题（在文件导出等地方会用到）
			text:{none:'被抽干了!没东西可以给你了,555!!(ㄒoㄒ)~~'},
			// 隔行变色
			even: false
		})};
	//调用渲染表格方法
	f("depositInterestAccrualTable1",0);
	f("depositInterestAccrualTable2",1);

	// 基金交易未结算数据表头部工具栏结算|导入按钮的事件句柄绑定
	table.on('toolbar(depositInterestAccrualTableEvent)', function(obj) {
		// 获取当前表格选中状态和选中的数据
		var checkStatus = table.checkStatus(obj.config.id);
		// 区分点击的按钮
		switch(obj.event) {
			case 'settlement':
				// 当前选中行的数据
				var data = checkStatus.data;
				//判断是否有全选中
				if(!checkStatus.isAll) {
					layer.msg("请全选你要结算的数据！！", {
						icon: 4 //图标，可输入范围0~6
					});
					return;
				}
				// 定义一个要计息的所有存款数据ID的字符串
				var depositInterestAccrualIdStr = "";
				// 遍历传递过来的要计息的数据
				for(var i = 0; i < data.length; i++) {
					if(data[i].tradeStatus == '1') {
						layer.msg("所选数据中有已计息数据！", {
							icon: 1 // 图标，可输入范围0~6
						});
						return;
					}
					// 拿出存款数据Id进行拼接
					depositInterestAccrualIdStr += data[i].securitiesInventoryId + ",";
				}
				// 截取掉因为拼接产生的多余的一个逗号
				depositInterestAccrualIdStr = depositInterestAccrualIdStr.substring(0, depositInterestAccrualIdStr.length - 1);
				// 调用修改基金状态请求方法
				updateTradeStatus(depositInterestAccrualIdStr, '1');
				break;
		};
	});

	// 定义修改交易状态请求方法
	var updateTradeStatus = function(securitiesInventoryIds,tradeStatus) {
		$.ajax({
			url:"/fa/depositInterestAccrualController/depositInterestAccrual",
			type:'post',
			data:{
				securitiesInventoryIds:securitiesInventoryIds,
				tradeStatus:tradeStatus
			},
			success:function (data) {
				console.log(data);
				if(data) {
					layer.msg("结算成功！", {
						icon: 1 // 图标，可输入范围0~6
					});
				} else {
					layer.msg("结算失败！", {
						icon: 1 // 图标，可输入范围0~6
					});
				}
				f("depositInterestAccrualTable1",0);
				f("depositInterestAccrualTable2",1)
			}
		})
	};
});

