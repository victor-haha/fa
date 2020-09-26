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
	var tId = 'depositTradeTable';
	var table = layui.table;
	var form = layui.form;
	var $ = layui.jquery;
	var layer = layui.layer;
	var laydate = layui.laydate;
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
	});
	//----1.数据表格渲染------------------------------------------------------------------
	function f(a,b) {
	var tableIns=table.render({
		elem: '#'+ a,
		url: '/fa/depositController/findDepositByCondition?tradeStatus=' + b, //后期改回获取用户列表的后端程序的url
		method: 'get',
		where: {}, // 你额外要携带数据，以键值对的方式存入
		toolbar: '#depositToolbar', // 开启头部工具栏，并为其绑定左侧模板
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
					field: 'depositId',
					title: '存款Id',
					unresize: true,
                    hide:true,
					align: "center",
					hide: true  //一般情况下不显示用户ID

				},
				{
					field: 'depositNo',
					title: '存款流水号',
					unresize: true,
                    width:150,
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
					field: 'outAccountId',
					title: '流出账户ID',
					unresize: true,
                    width:130,
					align: "center",
					hide: true  //一般情况下不显示用户ID
				},
				{
					field: 'outAccountNo',
					title: '流出账户账号',
					unresize: true,
					hide:true,
					align: "center",
					hide: true  //一般情况下不显示用户ID
				},
				{
					field: 'outAccountName',
					title: '流出账户名',
					unresize: true,
                    hide: true,
					align: "center",
				},
				{
					field: 'inAccountId',
					title: '流入账户Id',
					unresize: true,
					align: "center",
					hide: true  //一般情况下不显示用户ID
				},
				{
					field: 'inAccountNo',
					title: '流入账户账号',
					unresize: true,
					align: "center",
					hide: true  //一般情况下不显示用户ID
				},
				{
					field: 'inAccountName',
					title: '流入账户名',
					unresize: true,
					align: "center"
				},
				{
					field: 'tradeType',
					title: '交易方式',
					unresize: true,
					align: "center",
					templet: "#tradeStatusTpl2"
				},
				{
					field: 'tradeFlag',
					title: '交易标识',
					unresize: true,
                    align: "center",
					templet: "#tradeStatusTpl3"
				},
				{
					field: 'businessDateStr',
					title: '存款时间',
					width:105,
					unresize: true,
					align: "center"
				},
				{
					field: 'businessType',
					title: '存款类型',
					unresize: true,
					align: "center",
					templet: "#tradeStatusTpl4"
				},
				{
					field: 'money',
					title: '存款金额',
					unresize: true,
					align: "center"
				},
				{
					field: 'interestRate',
					title: '存款利率',
					unresize: true,
					align: "center"
				},
				{
					field: 'endDateStr',
					title: '到期时间',
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
					title: '存款状态',
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
		title: '基金表', // 定义 table 的大标题（在文件导出等地方会用到）
        text:{none:'被抽干了!没东西可以给你了,555!!(ㄒoㄒ)~~'},
		// 隔行变色
		even: false
	})};
	//----1.数据表格渲染------------------------------------------------------------------
	f('depositTradeTable',-1);
	f('depositTradeTable2',1);

	//----2.头部搜索栏基金交易日期时间选择器--------------------------------------------------------------------
	laydate.render({
		elem: '#queryTradeDateStart'
        ,trigger: 'click'
	});

	//头部搜索栏基金交易日期时间选择器
	laydate.render({
		elem: '#queryTradeDateEnd'
        ,trigger: 'click'
	});

	//----3.处理头部条件组合搜素------------------------------------------------------------------
	form.on('submit(fundSearchBtn)', function(data) {
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
	    console.log(data.field);
		//定义局部变量
		let b;
		tId == "depositTradeTable" ? b = -1 : b = 1;
		// 执行后台代码depositTradeTable
		alert(tId);
		table.reload(tId, {
			url: '/fa/depositController/findDepositByCondition?tradeStatus='+ b,
			where: data.field,  // 设定异步数据接口的额外参数，任意设
			page: {
				curr: 1 //从第一页开始
			},
			limit: 10
		});
		return false; // 阻止表单跳转。如果需要表单跳转，去掉这段即可。
	});

	//----3.处理表行修改------------------------------------------------------------------
	// table.on("tool(fundTableEvent)",function (obj) {
	// 	var data =  obj.data;
	// 	console.log(data);
	// 	if (obj.event == "update"){
    //         initUpdatefundModal(data);
	// 	}
    // });

	// 基金交易未结算数据表头部工具栏结算|导入按钮的事件句柄绑定
	table.on('toolbar(depositTableEvent)', function(obj) {
		// 获取当前表格选中状态和选中的数据
		var checkStatus = table.checkStatus(obj.config.id);

		// 区分点击的按钮
		switch(obj.event) {
			case 'upload':
				// 弹出上传模态框
                initUploadModal();
					break;
			case 'settlement':
				// 当前选中行的数据
				var data = checkStatus.data;
				//判断是否有选中
				if(!checkStatus.isAll) {
					layer.msg("请全选你要结算的数据！！", {
						icon: 4 //图标，可输入范围0~6
					});
					return;
				}
				// 定义一个要结算的所有交易数据ID的字符串
				var depositTradeIdStr = "";
				// 遍历传递过来的要结算的数据
				for(var i = 0; i < data.length; i++) {
					if(data[i].tradeStatus == '1') {
						layer.msg("所选数据中有已结算数据！", {
							icon: 1 // 图标，可输入范围0~6
						});
						return;
					}
					// 拿出基金交易数据Id进行拼接
                    depositTradeIdStr += data[i].depositId + ",";
				}
				// 截取掉因为拼接产生的多余的一个逗号
                depositTradeIdStr = depositTradeIdStr.substring(0, depositTradeIdStr.length - 1);
                // 调用修改基金状态请求方法
                updateTradeStatus(depositTradeIdStr, '1');
				break;
			/*case 'restoreRecord':
				// 当前选中行的数据
				var data = checkStatus.data;
				//判断是否有选中
				if(checkStatus.data.length < 1) {
					layer.msg("请选择你要还原的用户！！", {
						icon: 4 //图标，可输入范围0~6
					});
				}
				// 定义一个要删除的所有资源ID的字符串
				var fundIdStr = "";
				// 遍历传递过来的要删除的数据
				for(var i = 0; i < data.length; i++) {
					if(data[i].usable == 'Y') {
						layer.msg("所选用户中有可用的用户！", {
							icon: 1 // 图标，可输入范围0~6
						});
						return;
					}
					// 拿出资源ID进行拼接
					fundIdStr += data[i].fundId + ",";
				}
				// 截取掉因为拼接产生的多余的一个逗号
				fundIdStr = fundIdStr.substring(0, fundIdStr.length - 1);
				frozenORrecoverArchives(fundIdStr,'Y');
				break;*/
		};
	});

    // 定义修改交易状态请求方法
    var updateTradeStatus = function(depositIds,tradeStatus) {
        $.ajax({
            url:"/fa/depositController/depositTradeSettlement",
            type:'post',
            data:{
                depositIds:depositIds,
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
                f('depositTradeTable',-1);
                f('depositTradeTable2',1);
            }
        })

    };


    // 初始化上传模态框
    var initUploadModal = function(data) {
        // 弹出一个页面层
        layer.open({
            type: 1, // 基本层类型0~4，1为页面层
            title: '', // 标题
            skin: "",
            anim: 2, // 弹出动画
            area: ["100%",'100%'], //自适应宽高 只写一个参数就是表示宽度，高度会自适应 // 宽高 只写一个参数就是表示宽度，高度会自适应
            content: $("#upload"), // 文本、html都行
            resize: false, // 是否允许拉伸

            // end: function() { // 弹出层销毁时的回调函数（不论何种方式，只要关闭了就执行）
            //     // 清空表单
            //     $("#addfundForm")[0].reset();
            //     $("#demo1").attr("src", "");
            //     $("#demoText").text("");
            // }
        });

        // 表单赋值
    //     form.val('updateFundForm', {
    //         "fundId": data.fundId,
    //         "fundNo": data.fundNo,
    //         "fundName": data.fundName, //layui.util.toDateString(data.commonStart, 'HH:mm:ss'),
    //         "estDate": data.estDateStr,
    //         "managerName": data.managerName,
    //         "trusteeBank": data.trusteeBank,
    //         "fundScale": data.fundScale,
    //         "nav": data.nav,
    //         "managementFee":data.managementFee,
    //         "trusteeFee": data.trusteeFee,
    //         "billingDays": data.billingDays,
    //         "fundCompanyId": data.fundCompanyId,
    //         "comShortName": data.comShortName,
    //         "description": data.description,
    //     });
    };




     //获取选中下拉列表的值
     var managerIdVal;
    form.on('select', function(data){
        let obj = data.elem;
        if($(data.elem).attr("id") == "updateManagerName")
            managerIdVal = $(obj).children("[value='"+data.value+"']").attr("managerId");
        if($(data.elem).attr("id") == "addManagerName")
            managerIdVal = $(obj).children("[value='"+data.value+"']").attr("managerId");
        console.log(managerIdVal);

        //联动显示基金公司id
        //根据父元素ID判断是否是添加模态框里的选择事件
        if($(data.elem).attr("id") == "addComShortName")
            $("#addFundCompanyId").val($(data.elem).children("[value='"+ data.value+"']").attr("fundCompanyId"));
        //根据父元素ID判断是否是修改模态框里的选择事件
        if($(data.elem).attr("id") == "updateComShortName")
            $("#updateFundCompanyId").val($(data.elem).children("[value='"+ data.value+"']").attr("fundCompanyId"));
        console.log($(data.elem).children("[value='"+ data.value+"']").attr("fundCompanyId"))
    });

	//----4.处理新增用户表单提交--------------------------------------------------------------
	form.on('submit(addFundBtn)',function(data) {
        let addFundForm = form.val("addFundForm");
            // $("#fundCompanyId").val(data.value);

		// 执行后台代码
		$.ajax({
			type:'POST',  //适用RESTful风格的添加： 查询GET、添加POST、更新PUT、删除DELETE
			async:false,
			url:'../saveFund', //后期改为添加用户的后台程序的url
            data:{
			    fundNo:addFundForm.fundNo,
                fundName:addFundForm.fundName,
                fundCompanyId:addFundForm.fundCompanyId,
                trusteeBank:addFundForm.trusteeBank,
                fundScale:addFundForm.fundScale,
                nav:addFundForm.nav,
                trusteeFee:addFundForm.trusteeFee,
                managementFee:addFundForm.managementFee,
                billingDays:addFundForm.billingDays,
                managerId:managerIdVal,
                managerName:addFundForm.managerName,
                estDate:addFundForm.estDate,
                comShortName:addFundForm.comShortName,
                description:addFundForm.description,
            },
            // data:data.field,
			success: function(data) {
			    console.log(data.result)
				// 关闭页面上所有类型的所有弹框
				layer.closeAll();
				if(data.result == 1) {
					layer.msg("添加成功！", {
						icon: 1 // 图标，可输入范围0~6
					});
				} else {
					layer.msg("添加失败！", {
						icon: 1 // 图标，可输入范围0~6
					});
				}
			}
		});

		// 刷新数据表格
		table.reload('fundTable', {
			url: '../findFundByCondition' //后期改为查询用户的后台程序的url
		});
		return false; // 阻止表单跳转。如果需要表单跳转，去掉这段即可。
	});

	//----5.处理修改基金表单提交--------------------------------------------------------------
	form.on('submit(updatefundBtn)', function(data) {
        let updateFundForm = form.val("updateFundForm");
		//执行后台代码
		$.ajax({
			type: 'POST', //适用RESTful风格的添加： 查询GET、添加POST、更新PUT、删除DELETE
			async: false,
			url: '../updateFund',  //后期改为修改用户的后台程序url
			data: {
                fundId:updateFundForm.fundId,
                fundCompanyId:updateFundForm.fundCompanyId,
                trusteeFee:updateFundForm.trusteeFee,
                managementFee:updateFundForm.managementFee,
                billingDays:updateFundForm.billingDays,
                managerId:managerIdVal,
                managerName:updateFundForm.managerName,
                comShortName:updateFundForm.comShortName,
                description:updateFundForm.description,
            },//data.field
			success: function(data) {
				//关闭页面上所有类型的所有弹框
				layer.closeAll();
				if(data == 1) {
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
		table.reload('fundTable', {
			url: '../findFundByCondition' //
		});
		return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
	});






	// 监听锁定操作
	form.on('switch(usable)', function(obj) {
		frozenORrecoverArchives(obj.value, this.checked == true ? 'Y' : 'N');
	});

	// 定义冻结或还原的方法
	var frozenORrecoverArchives = function(fundIdStr, status) {
		// 用户ID
		var fundId = fundIdStr;
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
			url:'../updateFundStatus/' + fundId + "/" + usable,//  /updatefund   '/updatefundStatus/' + fundIds + "/" + usable
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
				table.reload('fundTable', {
					url: '../findFundByCondition'
				});
			}
		});
	};


	// $.ajax({
	// 	url: '../findUserAndFundCompany',//system/org',
	// 	dataType: 'json',
	// 	type: 'get',
	// 	success: function(data) {
	// 		$.each(data.result, function(index) {
    //             var managerId = data.result[index].userId;
    //             var managerName = data.result[index].userName;
	// 			/* var sdSdd = data[index].sdSdd; */
	// 			// 添加模态框基金经理名动态添加下拉列表
	// 			$("#addManagerName").append(
	// 				"<option managerId='" + managerId + "' value='" + managerName + "'>" + managerName +
	// 				"</option>");
    //             // 修改模态框基金经理名动态添加下拉列表
	// 			$("#updateManagerName").append(
	// 				"<option managerId='" + managerId + "' value='" + managerName + "'>" + managerName +
	// 				"</option>");
	// 			// 添加
	// 			$("#addComShortName").append(
	// 				"<option fundCompanyId='" + managerId + "' value='" + managerName + "'>" + managerName +
	// 				"</option>");
	// 			// 修改
	// 			$("#updateComShortName").append(
    //                 "<option fundCompanyId='" + managerId + "' value='" + managerName + "'>" + managerName +
    //                 "</option>");
	// 			form.render();//渲染将option添加进去
	// 		});
	// 	}
	// });

    upload.render({
        elem:'#upload',
        accept: 'file',
        url:"/fa/depositController/upload",
    done: function(res){
        //上传完毕回调
        layer.closeAll();
        alert(res.success);
        layer.msg(res.success);
        // 刷新数据表格
        table.reload('depositTradeTable', {
            url: '/fa/depositController/findDepositByCondition?b=0'
        });
		table.reload('depositTradeTable', {
			url: '/fa/depositController/findDepositByCondition?b=1'
		});
    }
    ,error: function(){
            //请求异常回调
            layer.msg("上传失败,请检查数据格式是否正确");
    },

    });

	// 自定义表单校验
	/*form.verify({
		pass: [ /^[\S]{6,12}$/, '密码必须6到12位，且不能出现空格'],
		mobile: function(value) {
			var msg;
			$.ajax({
				type: "POST",
				url: '../verifyTelephone',//system/toVerifyfundPhone
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
	});*/
});

