/*
 * layui引入样式的两种方式:模块化用和全模块用法
 * layui的初始化模块可以理解为，引入这个模块的js文件
 * 值得注意的是：layui的模块之间存在依赖关系，例如引入了table模块，它会layui/lay/moudules/table.js
 * 这个文件引入进来，并且它还会将这个模块所依赖的其他模块自动加入进来，
 * 实际引入的模块有：table、form、jquery、layer、laypage、laytpl、util，7个js文件
 * 所以我仅仅初始化了table模块，却能获得form、jquery、layer的模块对象
 */
//layui初始化表格模组 
layui.use(['table', 'laydate', 'util', 'upload'], function() {
	// 获得模块对象
	var table = layui.table;
	var form = layui.form;
	var $ = layui.jquery;
	var layer = layui.layer;
	var laydate = layui.laydate;
	var util = layui.util;
	var upload = layui.upload;


	//下拉搜索框设置
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



		/*if(flag){
		}
		search_header.css("top",-height);*/
	});
	//----1.用户数据表格渲染------------------------------------------------------------------
	table.render({
		elem: '#bondTable',
		url: '../bond/trade/list', //后期改回获取用户列表的后端程序的url
		method: 'post',
		height: 'full-10',
		where: {}, // 你额外要携带数据，以键值对的方式存入
		toolbar: '#userToolbar', // 开启头部工具栏，并为其绑定左侧模板
		// cellMinWidth: 80, // 全局定义所有常规单元格的最小宽度（默认：60）
		cellMaxWidth: 200,
		page: true, // 开启分页
		limit: 10, // 每页显示的条数
		limits: [5,10, 20, 50, 100], // 每页条数的选择项
		loading: true, // 是否显示加载条(切换分页的时候显示）
		title: '用户表', // 定义 table 的大标题（在文件导出等地方会用到）
		id: 'bondTable', // 设定容器唯一 id
		// 隔行变色
		even: false,
		cols: [
				[{
					title:'序列',
					type: 'numbers',
					fixed:'left', //钉在左侧
				}, // 序号
				{
					type: 'checkbox',
					fixed:'left', //钉在左侧
				}, //复选框
                {
					field:'bondTradeId',
					title:'债券交易Id',
					fixed:'left', //钉在左侧
					hide: true, //一般情况下不显示ID
                    align:'center',
					width: 200
                },
				{
					field: 'bondTradeNo',
					title: '债券交易编号',
					fixed:'left', //钉在左侧
					align: "center",
					width: 200
				},
				{
					field: 'fundId',
					title: '基金Id',
					fixed:'left',
					hide: true,
					align: "center",
					width: 100
				},
				{
					field: 'fundCode',
					title: '基金代码',
					sort: true,
					align: "center",
					width: 160
				},
				{
					field: 'fundName',
					title: '基金名称',
					sort: true,
					align: "center",
					width: 130
				},
				{
					field: 'bondId',
					title: '债券Id',
					sort: true,
					hide: true,
					align: "center",
					width: 130
				},
				{
					field: 'bondCode',
					title: '债券代码',
					sort: true,
					align: "center",
					width: 130
				},
				{
					field: 'bondName',
					title: '债券名称',
                    align: "center",
					width: 140
				},
				{
					field: 'managerId',
					title: '基金经理Id',
					align: "center",
					hide: true,
					width: 130
				},
				{
					field: 'managerName',
					title: '基金经理',
					align: "center",
					width: 130
				},
				{
					field: 'brokerId',
					title: '券商Id',
					sort: true,
					hide: true,
					align: "center",
					width: 150
				},
				{
					field: 'brokerName',
					title: '券商名',
					align: "center",
					width: 150
				},
				{
					field: 'tradeTypeStr',
					title: '交易方式',
					align: "center",
					width: 150
				},
				{
					field: 'tradeFlagStr',
					title: '交易标识',
					align: "center",
					width: 150
				},
				{
					field: 'tradePrice',
					title: '交易价格(单价)',
					align: "center",
					width: 150
				},
				{
					field: 'tradeDateStr',
					title: '交易日期',
					align: "center",
					width: 200
				},
				{
					field: 'share',
					title: '交易数量(份额)',
					align: "center",
					width: 150
				},
				{
					field: 'turnover',
					title: '交易额(总)',
					align: "center",
					width: 120
				},
				{
					field: 'stampTax',
					title: '印花费(国家)',
					align: "center",
					width: 150
				},
				{
					field: 'managementFees',
					title: '征管费(国家)',
					align: "center",
					width: 150
				},
				{
					field: 'transferFee',
					title: '过户费(交易所)',
					align: "center",
					width: 150
				},
				{
					field: 'commission',
					title: '佣金费用(券商)',
					align: "center",
					width: 150
				},
				{
					field: 'brokerage',
					title: '经手费(交易所)',
					align: "center",
					width: 150
				},
				{
					field: 'total',
					title: '总金额',
					align: "center",
					width: 120
				},
				{
					field: 'couponRate',
					title: '债券利息',
					align: "center",
					width: 120
				},
				{
					field: 'description',
					title: '备注',
					align: "center",
					width: 120
				},
				{
					field: 'tradeStatus',
					title: '交易状态',
					align: "center",
					fixed:'right',
					width: 100,
					templet:function (data) {
						if(data.tradeStatus =='1'){
							return '<span style="color: #3acc37">已结算</span>'
						}else if(data.tradeStatus =='0'){
							return '<span style="color: #ff9960">未结算</span>'
						}else {
							return '<span style="color: #ff0800">数据有误</span>'
						}
					}
				}

			]
		]
	});
	//----1.数据表格渲染------------------------------------------------------------------
	
	//----2.时间选择器--------------------------------------------------------------------
	laydate.render({
		elem: '#addExpireDate'
	});

	//时间选择器
	laydate.render({
		elem: '#addValueDate'
	});


	//----3.处理头部条件组合搜素------------------------------------------------------------------
	form.on('submit(SearchBtn)', function(data) {
		//将搜索下列框收回
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
		// 搜索并刷新数据表格
		table.reload('bondTable', {
			url: '../bond/trade/list', //
			where: data.field,
			page: {
				curr: 1 //从第一页开始
			},
			limit: 10
		});
		return false; // 阻止表单跳转。
	});


	//----3.处理表行修改------------------------------------------------------------------
	table.on("tool(bondTableEvent)",function (obj) {
		var data =  obj.data;
		console.log(data);
		if (obj.event == "update"){
            initUpdateUserModal(data);
		}
    });

	// 用户列表头部工具栏添加|修改|删除(逻辑删除)|恢复按钮的事件句柄绑定
	table.on('toolbar(bondTableEvent)', function(obj) {
		//获取当前表格选中状态和选中的一行dom元素
		// checkElem = table.checkStatus(obj.tr);
		// 获取当前表格选中状态和选中的数据
		var checkStatus = table.checkStatus(obj.config.id);
		// console.log(obj);
		// console.log(checkStatus);
		// console.log(checkStatus.data[0].bondId);

		// 区分点击的按钮
		/*obj.update({
			bondCode: "sd"
		});*/
		// 定义一个要删除的所有资源ID的字符串
		let idStr = "";
		switch(obj.event) {
			case 'addUser':
				// 弹出新增模态框
				initAddModal();
				break;
			case 'updateUser':
				// 选择的数据数量
				if(checkStatus.data.length > 1) {
					layer.msg("最多只能修改一行数据哦！！", {
						icon: 4 //图标，可输入范围0~6
					});
				} else if(checkStatus.data.length < 1) {
					layer.msg("请选择要修改的数据哦！！", {
						icon: 4 //图标，可输入范围0~6
					});
				} else if(checkStatus.data[0].usable == '0') {
					layer.msg("该用户被弃用了哦！！", {
						icon: 4 //图标，可输入范围0~6
					});
				} else {
					// 弹出修改模态框，传递当前选中的一行数据过去
					initUpdateUserModal(checkStatus.data[0]);
				}
				break;
			case 'settlements':  //结算
				// 当前选中行的数据
				var data = checkStatus.data;
				//判断是否有选中
				if(checkStatus.data.length < 1) {
					layer.msg("请选择你要结算的数据行！！", {
						icon: 4 //图标，可输入范围0~6
					});
					return;
				}
				// 遍历传递过来的要结算的数据
				for(var i = 0; i < data.length; i++) {
					if(data[i].tradeStatus == '1') {
						layer.msg("所选的数据有已结算的！", {
							icon: 4 // 图标，可输入范围0~6
						});
						return;
					}
					// 拿出用户ID进行拼接
					idStr += data[i].bondTradeId + ",";
				}
				// 截取掉因为拼接产生的多余的一个逗号
				idStr = idStr.substring(0, idStr.length - 1);
				settlements(idStr, '1');
				break;
			case 'restoreRecord':
				// 当前选中行的数据
				var data = checkStatus.data;
				//判断是否有选中
				if(checkStatus.data.length < 1) {
					layer.msg("请选择你要还原的债券！！", {
						icon: 4 //图标，可输入范围0~6
					});
				}
				// 遍历传递过来的要删除的数据
				for(var i = 0; i < data.length; i++) {
					if(data[i].usable == '1') {
						layer.msg("所选债券是可用的，无需修改！", {
							icon: 4 // 图标，可输入范围0~6
						});
						return;
					}
					// 拿出资源ID进行拼接
					idStr += data[i].bondId + ",";
				}
				// 截取掉因为拼接产生的多余的一个逗号
				idStr = idStr.substring(0, idStr.length - 1);
				settlements(idStr, '1');
				break;
		};
	});
	
	// 监听锁定操作
	form.on('switch(usable)', function(obj) {
		settlements(obj.value, this.checked == true ? 'Y' : 'N');
	});

	// 定义弃用或还原的方法
	var settlements = function(bondTradeIds, tradeStatus) {
		// 定义提示信息, 状态
		var msg;
		if(tradeStatus == '1') {
			msg = "结算";
		} else {
			msg = "弃用";
		}
		layer.msg("发送结算请求");
		// 发送异步请求冻结或还原资源
		$.ajax({
			async: false, // 默认为true，false表示同步，如果当前请求没有返回则不执行后续代码
			type: "post",
			url:'../bond/trade/settlements' ,//  /
			data: {
				bondTradeIds:bondTradeIds,
				tradeStatus:tradeStatus
			},
			success: function(data) {
				if(data == "1") {
					layer.msg(msg + "成功！", {
						icon: 1 // 图标，可输入范围0~6
					});
				} else {
					layer.msg(msg + "失败！", {
						icon: 2
					});
				}
				// 刷新数据表格
				table.reload('bondTable', {
					url: '../bond/trade/list'
				});
			}
		});
	};


	// 自定义表单校验
	form.verify({
		pass: [ /^[\S]{6,12}$/, '密码必须6到12位，且不能出现空格'],
		mobile: function(value) {
			var msg;
			$.ajax({
				type: "POST",
				url: '/verifyTelephone',//system/toVerifyUserPhone
				async: false, // 使用同步的方法
				data: {
					'telephone': value
				},
				dataType: 'json',
				success: function(result) {
					if(result.flag) {
						msg = result.msg;
					}
				}
			});
			return msg;
		}
	});
});



