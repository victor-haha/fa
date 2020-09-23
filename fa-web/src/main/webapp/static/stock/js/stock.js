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



//----1.用户数据表格渲染------------------------------------------------------------------
	table.render({
		elem: '#stockTable',
		url: '../stock/findAllStock', //后期改回获取用户列表的后端程序的url
		method: 'get',
		where: {}, // 你额外要携带数据，以键值对的方式存入
		toolbar: '#stockToolbar',
		cellMinWidth: 80, // 全局定义所有常规单元格的最小宽度（默认：60）
		cols: [
				[{
					type: 'numbers'
				}, // 序号
				{
					type: 'checkbox'
				}, //复选框
				{
					field: 'stockId',
					title: '股票Id',
					unresize: true,
					hide: true, //一般情况下不显示股票ID
					align: "center"
				},
				{
					field: 'stockCode',
					title: '股票代码',
					unresize: true,
					align: "center"
				},
				{
					field: 'stockShortName',
					title: '股票简称',
					unresize: true,
					align: "center"
				},
				{
					field: 'issueDate',
					title: '上市日期',
					align: "center",
					templet: function(data) {
						return formatChinaDate(data);
					}

				},
				{
					field: 'issuer',
					title: '发行人',
					unresize: true,
					align: "center"
				},
				{
					field: 'plate',
					title: '所属板块',
					unresize: true,
					align: "center",
				},
				{
					field: 'industry',
					title: '所属行业',
					unresize: true,
					align: "center",
				},
				{
					field: 'exchange',
					title: '交易所',
					unresize: true,
					align: "center",
				},
				{
					field: 'usable',
					title: '股票状态',
					unresize: true,
					templet: '#checkboxTpl',
					align: "center"
				},
				{
					field: 'description',
					title: '备注',
					unresize: true,
                    hide:true,
					align: "center",
				}
			]
		],
		page: true, // 开启分页
		limit: 10, // 每页显示的条数
		limits: [10, 20, 50, 100], // 每页条数的选择项
		loading: true, // 是否显示加载条(切换分页的时候显示）
		title: '股票表', // 定义 table 的大标题（在文件导出等地方会用到）
		id: 'stockTable', // 设定容器唯一 id
		// 隔行变色
		even: false
	});
	//----1.用户数据表格渲染------------------------------------------------------------------
	
	//----2.添加股票上市日期选择器--------------------------------------------------------------------
	laydate.render({
		elem: '#addStockHiredate'
		,type: 'date'
		,format: 'yyyy-MM-dd'
	});


	//----2.修改股票上市日期选择器--------------------------------------------------------------------
	laydate.render({
		elem: '#updateStockHiredate'
		,type: 'date'
		,format: 'yyyy-MM-dd'
	});

	//修改用户入职时间选择器
	laydate.render({
		elem: '#updatefundHiredate'
	});


	//----3.处理头部条件组合搜素------------------------------------------------------------------
	form.on('submit(stockSearchBtn)', function(data) {
		// 执行后台代码
		table.reload('stockTable', {
			url: '/findStockByCondition',
			where: { // 设定异步数据接口的额外参数，任意设
				stockCode: $("#queryStockCode").val(),
				stockShortName: $("#queryStockShortName").val(),
				plate: $("#queryPlate").val(),
				industry: $("#queryIndustry").val(),
				exchange: $("#queryExchange").val(),
				queryusable: $("#usable").val(),
			},
			page: {
				curr: 1 //从第一页开始
			},
			limit: 10
		});
		return false; // 阻止表单跳转。如果需要表单跳转，去掉这段即可。
	});
	//----3.处理表行修改------------------------------------------------------------------
	table.on("tool(stockTableEvent)",function (obj) {
		var data =  obj.data;

		if (obj.event == "update"){
            initUpdateStockModal(data);
		}
    });

	//修改日期格式（年，月，日）
	function formatChinaDate(data) {
		var oIssueDate = data.issueDate;
		var oDate = new Date(oIssueDate);
		var sIssueDate = oDate.getFullYear() + "年" + (oDate.getMonth() + 1) + "月" + oDate.getDate() + "日";
		return sIssueDate;
	}

	//修改日期格式（yyyy-MM-dd）
	function formatDate(date) {
		var d = new Date(date),
			month = '' + (d.getMonth() + 1),
			day = '' + d.getDate(),
			year = d.getFullYear();

		if (month.length < 2) month = '0' + month;
		if (day.length < 2) day = '0' + day;

		return [year, month, day].join('-');
	}

	// 用户列表头部工具栏添加|修改|删除(逻辑删除)|恢复按钮的事件句柄绑定
	table.on('toolbar(stockTableEvent)', function(obj) {
		// 获取当前表格选中状态和选中的数据
		var checkStatus = table.checkStatus(obj.config.id);
		// 区分点击的按钮
		switch(obj.event) {
			case 'addStock':
				// 弹出新增模态框
				initAddStockModal();
				break;
			case 'updateStock':
				// 选择的数据数量
				if(checkStatus.data.length > 1) {
					layer.msg("最多只能修改一行数据哦！！", {
						icon: 0 //图标，可输入范围0~6
					});
				} else if(checkStatus.data.length < 1) {
					layer.msg("请选择要修改的数据哦！！", {
						icon: 3 //图标，可输入范围0~6
					});
				} else if(checkStatus.data[0].usable == 'N') {
					layer.msg("该用户被禁用了哦！！", {
						icon: 4 //图标，可输入范围0~6
					});
				} else {
					// 弹出修改模态框，传递当前选中的一行数据过去
					initUpdateStockModal(checkStatus.data[0]);
				}
				break;
			case 'frozenRecord':
				// 当前选中行的数据
				var data = checkStatus.data;
				//判断是否有选中
				if(checkStatus.data.length < 1) {
					layer.msg("请选择你要冻结的股票！！", {
						icon: 4 //图标，可输入范围0~6
					});
				}
				// 定义一个要删除的所有资源ID的字符串
				var stockIdStr = "";
				// 遍历传递过来的要删除的数据
				for(var i = 0; i < data.length; i++) {
					if(data[i].usable == 'N') {
						layer.msg("所选用户中有被冻结的用户！", {
							icon: 1 // 图标，可输入范围0~6jues
						});
						return;
					}
					// 拿出用户ID进行拼接
					stockIdStr += data[i].stockCode + ",";
				}
				// 截取掉因为拼接产生的多余的一个逗号
				stockIdStr = stockIdStr.substring(0, stockIdStr.length - 1);
				frozenORrecoverArchives(stockIdStr, 'N');
				break;
			case 'restoreRecord':
				// 当前选中行的数据
				var data = checkStatus.data;
				//判断是否有选中
				if(checkStatus.data.length < 1) {
					layer.msg("请选择你要还原的用户！！", {
						icon: 4 //图标，可输入范围0~6
					});
				}
				// 定义一个要删除的所有资源ID的字符串
				var stockIdStr = "";
				// 遍历传递过来的要删除的数据
				for(var i = 0; i < data.length; i++) {
					if(data[i].usable == 'Y') {
						layer.msg("所选用户中有可用的用户！", {
							icon: 1 // 图标，可输入范围0~6
						});
						return;
					}
					// 拿出资源ID进行拼接
					stockIdStr += data[i].stockCode + ",";
				}
				// 截取掉因为拼接产生的多余的一个逗号
				stockIdStr = stockIdStr.substring(0, stockIdStr.length - 1);
				frozenORrecoverArchives(stockIdStr, 'Y');
				break;
		};
	});
	
	
	
	
	
	//----4.处理新增用户表单提交--------------------------------------------------------------
	form.on('submit(addStockBtn)', function(data) {
		// 执行后台代码
		$.ajax({
			type: 'POST',  //适用RESTful风格的添加： 查询GET、添加POST、更新PUT、删除DELETE 
			async: false,
			url: '/addStock', //后期改为添加用户的后台程序的url
			data: data.field,
			success: function(data) {
				console.log(data);
				// 关闭页面上所有类型的所有弹框
				layer.closeAll();
				if(data == 1) {
					layer.msg("添加成功！", {
						icon: 1 // 图标，可输入范围0~6
					});
				} else {
					layer.msg("添加失败！", {
						icon: 1 // 图标，可输入范围0~6
					});
				}
				//关闭添加模态框

			}
		});
		// 刷新数据表格
		table.reload('stockTable', {
			url: '/findStockByCondition' //后期改为查询用户的后台程序的url
		});
		return false; // 阻止表单跳转。如果需要表单跳转，去掉这段即可。
	});

	//----5.处理修改用户表单提交--------------------------------------------------------------
	form.on('submit(updateStockBtn)', function(data) {
		//执行后台代码
		$.ajax({
			type: 'POST', //适用RESTful风格的添加： 查询GET、添加POST、更新PUT、删除DELETE
			async: false,
			url: '/updateStock',  //后期改为修改用户的后台程序url
			data: data.field,//data.field
			success: function(data) {
			    console.log(data)
				//关闭页面上所有类型的所有弹框
				layer.closeAll();
				if(data.result == 1) {
					layer.msg("修改成功！", {
						icon: 1 //图标，可输入范围0~6
					});
				} else {
					layer.msg("修改失败！", {
						icon: 1 //图标，可输入范围0~6
					});
				}
			}
		});
		//刷新数据表格
		table.reload('stockTable', {
			url: '/findStockByCondition' //
		});
		return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
	});

	
	
	// 监听锁定操作
	form.on('switch(usable)', function(obj) {
		frozenORrecoverArchives(obj.value, this.checked == true ? 'Y' : 'N');
	});

	// 定义冻结或还原的方法
	var frozenORrecoverArchives = function(stockIdStr, status) {
		// 用户ID
		var stockId = stockIdStr;
		// 定义提示信息, 状态
		var msg, usable;
		if(status == 'Y') {
			msg = "还原", usable = 'Y';
		} else {
			msg = "禁用", usable = 'N';
		}
		// 发送异步请求冻结或还原资源
		$.ajax({
			async: false, // 默认为true，false表示同步，如果当前请求没有返回则不执行后续代码
			type: "DELETE",
			url:'/updateStockStatus/' + stockId + "/" + usable,//  /updatefund   '/updatefundStatus/' + fundIds + "/" + usable
			data: {
			    // fundId:fundId,
                // usable:usable,
				// _method: 'DELETE'
			},
			datatype: 'json',
			success: function(data) {
				if(data.result == "1") {
					layer.msg(msg + "成功！", {
						icon: 1 // 图标，可输入范围0~6
					});
				} else {
					layer.msg(msg + "失败！", {
						icon: 2
					});
				}
				// 刷新数据表格
				table.reload('stockTable', {
					url: '/findStockByCondition'
				});
			}
		});
	};
	// var hiddeDisable = function(){
	//
    // }

	// 初始化新增模态框
	var initAddStockModal = function() {
		// 弹出一个页面层
		layer.open({
			type: 1, // 基本层类型0~4，1为页面层
			title: '添加股票', // 标题
			skin: "layui-layer-molv",
			anim: 2, // 弹出动画
			area: ["680px"], //自适应宽高 只写一个参数就是表示宽度，高度会自适应 // 宽高 只写一个参数就是表示宽度，高度会自适应
			content: $("#addStockModal"), // 文本、html都行
			resize: false, // 是否允许拉伸
			end: function() { // 弹出层销毁时的回调函数（不论何种方式，只要关闭了就执行）
				// 清空表单
				/*$("#addfundForm")[0].reset();
				$("#demo1").attr("src", "");
				$("#demoText").text("");*/
			}
		});

	}

	// 初始化修改模态框
	var initUpdateStockModal = function(data) {
		// 弹出一个页面层
		layer.open({
			type: 1, // 基本层类型0~4，1为页面层
			title: '修改股票', // 标题
			skin: "layui-layer-molv",
			anim: 2, // 弹出动画
			area: ['680px'], //自适应宽高 只写一个参数就是表示宽度，高度会自适应 // 宽高 只写一个参数就是表示宽度，高度会自适应
			content: $("#updateStockModal"), // 文本、html都行
			resize: false, // 是否允许拉伸
			end: function() { // 弹出层销毁时的回调函数（不论何种方式，只要关闭了就执行）
				// 清空表单
				$("#addStockForm").reset();
				/*$("#demo1").attr("src", "");
				$("#demoText").text("");*/
			}
		});





		// 表单赋值
		form.val('updateStockForm', {
			"stockCode": data.stockCode,
			"stockShortName": data.stockShortName, //layui.util.toDateString(data.commonStart, 'HH:mm:ss'),
			"issueDate": formatDate(data.issueDate),
			"issuer": data.issuer,
			// "orgId": data.orgId,
			// "orgName": data.orgName,
			"plate": data.plate,
			// "fundBirthdate": data.fundBirthdate,
			// "fundHiredate": data.fundHiredate,
			"industry": data.industry,
			"exchange": data.exchange,
			// "headImage": data.file,
		});
	};

	// $.ajax({
	// 	url: '/findfundByConditionuuuu',//system/org',
	// 	dataType: 'json',
	// 	type: 'post',
	// 	success: function(data) {
	// 		$.each(data, function(index) {
	// 			var orgName = data[index].orgName;
	// 			var orgId = data[index].orgId;
	// 			/* var sdSdd = data[index].sdSdd; */
	// 			// 头部的搜索
	// 			$("#queryOrgName").append(
	// 				"<option value='" + orgName + "'>" + orgName +
	// 				"</option>");
	// 			// 添加
	// 			$("#addOrgName").append(
	// 				"<option value='" + orgId + "'>" + orgName +
	// 				"</option>");
	// 			// 修改
	// 			$("#updateOrgName").append(
	// 				"<option value='" + orgId + "'>" + orgName +
	// 				"</option>");
	// 			// form.render()渲染将option添加进去
	// 			form.render();
	// 		});
	// 	}
	// });

	// 自定义表单校验
	form.verify({
		stockCode: [ /^[\S]{6}$/, '股票代码必须是6位，且不能出现空格'],
		stockShortName: [ /^[\S]{1,}$/, '股票简称不能为空！'],
		issueDate: [ /^[\S]/, '上市日期不能为空！'],
		issuer: [ /^[\S]{1,}$/, '发行人不能为空！'],
		plate: [ /\S/, '所属板块不能为空！'],
		industry: [/\S/, '所属行业不能为空！'],

		mobile: function(value) {
			var msg;
			$.ajax({
				type: "POST",
				url: '/verifyTelephone',//system/toVerifyfundPhone
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

