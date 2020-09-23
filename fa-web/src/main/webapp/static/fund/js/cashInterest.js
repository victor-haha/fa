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

        //----1.基金交易数据表格渲染------------------------------------------------------------------
        table.render({
            elem: '#cashInterestTable',
            url: '../calcRevenue/cashInventoryList', //后期改回获取用户列表的后端程序的url
            method: 'post',
            height: 'full-50',
            where: {}, // 你额外要携带数据，以键值对的方式存入
            toolbar: '#cashInterestToolbar', // 开启头部工具栏，并为其绑定左侧模板
            page: true, // 开启分页
            limit: 10, // 每页显示的条数
            limits: [5,10, 20, 50, 100], // 每页条数的选择项
            loading: true, // 是否显示加载条(切换分页的时候显示）
            title: '现金计息表', // 定义 table 的大标题（在文件导出等地方会用到）
            id: 'cashInterestTable', // 设定容器唯一 id
            defaultToolbar: [{
                title: '搜索'
                ,layEvent: 'LAYTABLE_SEARCH'
                ,icon: 'layui-icon-search'
            },'filter', 'exports', 'print' ],
            // 隔行变色
            even: false,
            cols: [
                [{
                    title:'序列',
                    type: 'numbers'
                }, // 序号
                    {
                        type: 'checkbox'
                    }, //复选框
                    {
                        field:'cashInventoryId',
                        title:'现金库存Id',
                        hide: true, //一般情况下不显示ID
                        align:'center',
                        width: 200
                    },
                    {
                        field: 'cashInventoryNo',
                        title: '现金库存编号',
                        // fixed:'left', //钉在左侧
                        align: "center",
                        width: 260
                    },
                    {
                        field: 'fundId',
                        title: '基金Id',
                        hide: true,
                        align: "center",
                        width: 100
                    },
                    {
                        field: 'fundNo',
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
                        width: 200
                    },
                    {
                        field: 'accountId',
                        title: '账户Id',
                        sort: true,
                        hide: true,
                        align: "center",
                        width: 130
                    },
                    {
                        field: 'accountNo',
                        title: '账号',
                        sort: true,
                        align: "center",
                        width: 180
                    },
                    {
                        field: 'accountName',
                        title: '账户名',
                        align: "center",
                        width: 190
                    },
                    {
                        field: 'cashBalance',
                        title: '现金余额',
                        align: "center",
                        sort: true,
                        width: 130
                    },
                    {
                        field: 'statisticalDateStr',
                        title: '统计日期',
                        align: "center",
                        sort:true,
                        width: 130
                    },
                    {
                        field: 'description',
                        title: '描述',
                        align: "center",
                        hide: true,
                        width: 150
                    }

                ]
            ]
        });
        //----1.基金交易数据表格渲染------------------------------------------------------------------




        //----2.头部搜索栏基金交易日期时间选择器--------------------------------------------------------------------


        //----3.处理表行修改------------------------------------------------------------------
        // table.on("tool(fundTableEvent)",function (obj) {
        // 	var data =  obj.data;
        // 	console.log(data);
        // 	if (obj.event == "update"){
        //         initUpdatefundModal(data);
        // 	}
        // });


        var ltips=layer.tips("为你定制的搜索^_^","[title='搜索']",{tips:[2,'#1211115e'],time:3000,shadeClose:true});
        // 现金计息表头部工具栏计息|搜索按钮的事件句柄绑定
        table.on('toolbar(cashInterestEvent)', function(obj) {
            // 获取当前表格选中状态和选中的数据
            var checkStatus = table.checkStatus(obj.config.id);
            // 区分点击的按钮
            switch(obj.event) {
                case 'LAYTABLE_SEARCH':
                    laytablesearch();
                    layer.close(ltips);
                    break;
                case 'interest':
                    // 当前选中行的数据
                    var data = checkStatus.data;
                    //判断是否有选中
                    if(checkStatus.data.length < 1) {
                        layer.msg("请选择你要计息的数据！！", {
                            icon: 4 //图标，可输入范围0~6
                        });
                        return;
                    }
                    // 定义一个要现金计息的所有现金库存id的字符串
                    var cashInventoryIdStr = "";
                    // 遍历传递过来的要计息的数据
                    for(var i = 0; i < data.length; i++) {
                        // 拿出基金库存id进行拼接
                        cashInventoryIdStr += data[i].cashInventoryId + ",";
                    }
                    // 截取掉因为拼接产生的多余的一个逗号
                    cashInventoryIdStr = cashInventoryIdStr.substring(0, cashInventoryIdStr.length - 1);
                    // 调用向后台发起现金计息的请求方法
                    cashInterest(cashInventoryIdStr);
                    break;
            };
        });

        // 定义向后台发起现金计息的请求方法
        var cashInterest = function(cashInventoryIds) {
            $.ajax({
                url:"../calcRevenue/cashInterest",
                type:'post',
                data:{
                    cashInventoryIds:cashInventoryIds,
                },
                success:function (data) {
                    if(data == 1) {
                        layer.msg("计息成功！", {
                            icon: 1 // 图标，可输入范围0~6
                        });
                    } else {
                        layer.msg("计息失败！", {
                            icon: 1 // 图标，可输入范围0~6
                        });
                    }
                    table.reload('cashInterestTable',{
                        url:"../calcRevenue/cashInventoryList"
                    });
                }
            })

        };









    });
});
