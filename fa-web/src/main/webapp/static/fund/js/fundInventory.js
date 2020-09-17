$(function(){

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
	var element = layui.element;

	//----1.基金库存数据表格渲染------------------------------------------------------------------
	var tablIns=table.render({
		elem: '#fundInventoryTable',
		url: '../list', //后期改回获取用户列表的后端程序的url
		method: 'get',
		where: {}, // 你额外要携带数据，以键值对的方式存入
		toolbar: '#fundToolbar', // 开启头部工具栏，并为其绑定左侧模板
		cellMinWidth: 80, // 全局定义所有常规单元格的最小宽度（默认：60）
		height:'full',
		cols: [
				[{
					type: 'numbers'
				}, // 序号
			 	{
					field: 'fundInventoryId',
					title: '基金库存Id',
					// unresize: true,
                    hide:true,
                    // width:'20%',
					align: "center"

				},
				{
					field: 'fundInventoryNo',
					title: '基金库存编号',
					// unresize: true,
                    width:'23%',
					align: "center"
				},
				{
					field: 'fundId',
					title: '基金Id',
					// unresize: true,
                    hide:true,
                    // width:'30%',
					align: "center"
				},
				{
					field: 'fundNo',
					title: '基金代码',
					// unresize: true,
                    // width:'15%',
					align: "center",
				},
				{
					field: 'fundName',
					title: '基金名',
                    // width:'15%',
					// unresize: true,
					align: "center"

				},
				{
					field: 'share',
					title: '份额',
					// unresize: true,
                    // width:'13%',
					align: "center",
				},
				{
					field: 'balance',
					title: '余额',
					// unresize: true,
                    // width:'15%',
					align: "center"
				},
				{
					field: 'statisticalDateStr',
					title: '统计日期',
					// unresize: true,
                    // width:'15%',
					sort:true,
					align: "center"
				},
				{
					field: 'description',
					title: '描述',
                    // width:'10%',
                    hide:true,
					// unresize: true,
					align: "center"
				}
			]
		],
        defaultToolbar: [{
            title: '搜索'
            ,layEvent: 'LAYTABLE_SEARCH'
            ,icon: 'layui-icon-search'
        },'filter', 'exports', 'print' ],
		page: true, // 开启分页
		limit: 10, // 每页显示的条数
		limits: [10, 20, 50, 100], // 每页条数的选择项
		loading: true, // 是否显示加载条(切换分页的时候显示）
		title: '基金库存表', // 定义 table 的大标题（在文件导出等地方会用到）
        text:{none:'被抽干了!没什么东西可以给你了,555!!(ㄒoㄒ)~~'},
		// 隔行变色
		even: false,
		done:function () {
			resize();
        }
	});
	//----1.基金库存数据表格渲染------------------------------------------------------------------


	//----2.头部搜索栏基金库存统计日期选择器--------------------------------------------------------------------
	laydate.render({
		elem: '#queryStatisticalDateStart'
        ,trigger: 'click'
	});

	laydate.render({
		elem: '#queryStatisticalDateEnd'
        ,trigger: 'click'
	});

	//----3.处理头部条件组合搜素------------------------------------------------------------------
	form.on('submit(fundInventorySearchBtn)', function(data) {
        // 执行后台代码
		table.reload('fundInventoryTable', {
			url: '../list',
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

    window.onresize=function(){
        resize();
    }

    var ltips=layer.tips("为你定制的搜索^_^","[title='搜索']",{tips:[2,'#1211115e'],time:3000,shadeClose:true});
	// 基金交易未结算数据表头部工具栏结算|导入按钮的事件句柄绑定
	table.on('toolbar(fundInventoryTableEvent)', function(obj) {
		// 获取当前表格选中状态和选中的数据
		var checkStatus = table.checkStatus(obj.config.id);
		// 区分点击的按钮
		switch(obj.event) {
            case 'LAYTABLE_SEARCH':
                laytablesearch();
                layer.close(ltips);
                break;
		};
	});




    function resize(){
        // $("#dataTable .layui-table-fixed thead tr").css("height",$("#dataTable thead tr").height() + "px");
        // $(".layui-table-body:last table").css("height", $(".layui-table-main")[0].clientHeight+"px")
        $("#dataTable  .layui-table-fixed thead tr").css("height",$("#dataTable thead tr").height() + "px");
        $("#dataTable2  .layui-table-fixed thead tr").css("height",$("#dataTable2 thead tr").height() + "px");
		$.each($(".layui-table-body:eq(1) table tr"),function (index,data) {
			$(data).css("height",$(".layui-table-main:first tr").get(index).clientHeight+"px")
        })
		$.each($(".layui-table-body:last table tr"),function (index,data) {
			$(data).css("height",$(".layui-table-main:last tr").get(index).clientHeight+"px")
        })
    }
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
        });

    };


    element.on('tab',function (data) {
        tId = data.elem.find(".layui-show table").attr("id")
        table.resize(tId);
        resize();
    });

    //渲染上传模态框
    upload.render({
        elem:'#upload',
        accept: 'file',
        url:"../upload",
    done: function(res){
        //上传完毕回调
        layer.closeAll();
        layer.msg(res.success);
        // 刷新数据表格
        table.reload('fundTradeTable', {
            url: '../findAllFundTrade?b=0'
        });
        table.reload('fundTradeTable2', {
            url: '../findAllFundTrade?b=1'
        });
        resize()
    }
    ,error: function(){
            //请求异常回调
            layer.msg("上传失败,请检查数据格式是否正确");
    },
    acceptMime:Lay_xls_xlsx,
    exts: 'xls|excel|xlsx',
    });

	// 自定义表单校验
	// form.verify({
	// 	pass: [ /^[\S]{6,12}$/, '密码必须6到12位，且不能出现空格'],
	// 	mobile: function(value) {
	// 		var msg;
	// 		$.ajax({
	// 			type: "POST",
	// 			url: '../verifyTelephone',//system/toVerifyfundPhone
	// 			async: false, // 使用同步的方法
	// 			data: {
	// 				'telephone': value
	// 			},
	// 			dataType: 'json',
	// 			success: function(result) {
	// 				if(result.flag) {
	// 					msg = result.msg;
	// 				}
	// 			}
	// 		});
	// 		return msg;
	// 	}
	// });
    // function laytablesearch(){search.stop().slideToggle(1500);}



});
});
