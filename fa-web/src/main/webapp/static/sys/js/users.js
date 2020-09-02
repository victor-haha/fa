var table = layui.table;
var $ = layui.jquery;
var layer = layui.layer;
var form = layui.form;
//table表格渲染
var searchData = form.val("searchForm");
var tab = table.render({
	elem: '#tb',
	url: '../../../users/list',
	method: 'post',
	where: {userId:searchData.userId,usable:searchData.usable,userName:searchData.userName},
    height: 500,
	page: true,
	toolbar:'#toolbar',
	limit: 10, //页面显示记录数，默认是10
	limits: [10, 20, 50, 100], //可选的页面大小
	cols: [
		[{
			type:'numbers',
			title:'序列',
			fixed:'left'
		}, {
			field: 'userPic',
			title: '头像',
			width: 60,
			fixed: 'left',
			templet:function (data) {
				return "<div><img style='width: 30px;height: 30px;' src="+data.userPic+"'../../..' alt='头像'></div>"
			}
		}, {
			field: 'userName',
			title: '用户名',
			fixed:'left',
			width: 120
		}, {
			field: 'userId',
			title: '用户ID',
            width: 80
		},{
            field: 'password',
            title: '密码'
        },{
            field: 'telephone',
            title: '手机号'
        },{
			field: 'usable',
			title: '是否可用',
            width: 90,
			templet:'#sex'

		}, {
            field: 'createTimeStr',
            title: '创建时间',
            width: 190
        }, {
            field: 'description',
            title: '描述'
        },{
			type: 'right',
			title:'操作', 
			toolbar: '#barDemo',
            fixed: 'right',
			width:80
		}]
	]
});

var usable;
//监听下拉框的值
form.on('select(selectId)', function(data){
	/*console.log(data.elem); //得到select原始DOM对象
	console.log(data.value); //得到被选中的值
	console.log(data.othis); //得到美化后的DOM对象*/
	usable = data.value;
});
//开关监听事件
form.on('switch(sex_f)', function(obj) {

	$.get("../users/usable/" + (obj.elem.checked ? 1 : 0), {userId: obj.value}, function (data) {
		if (data == 1) {
			layer.msg("设置为" + (obj.elem.checked ? "可用" : "禁用"));
		} else {
			layer.msg("操作有误");
		}
	});

	// layer.confirm("确定设置为"+(obj.elem.checked?"可用":"禁用")+"吗？")
	// layer.msg(this.value + ' ' + this.name + '：' + obj.elem.checked);

});
//搜索按钮事件

form.on('submit(searchBtn)',function () {
	  $("#selectId").removeAttr("disabled");
	  searchData = form.val("searchForm");
	  tab.reload({
		  page:1,
		  where:{usable:searchData.usable,userId:searchData.userId,userName:searchData.userName}
	  });
	  return false;
  });
  //监听表头事件
  table.on('toolbar(tb_event)',function () {
	  layer.open({
	  	  title: '添加用户',
		  area: ['700px','500px'],
		  btn: ['提交', '取消', '重置'],
		  content: $("#add").html(),
		  btn1: function () {
	  	  	  layer.msg("确定");
		  },
		  btn2: function () {
	  	  	  layer.close();
		  },
		  btn3: function () {
			  layer.msg("重置");
			  layer.close();
		  }
	  })
  });
  //监听行工具事件
  table.on('tool(tb_event)', function(obj){
    let data = obj.data;
   if(obj.event === 'edit'){
		layer.confirm("", {
			title: "发货",
			width: 600,
			height: 800,
			content:$("#div3").html()
		},function(index){
			var orderStatus = $("#update").val();
			if("1" == data.orderStatus){
				$.post("../../orderController",{biz:"editOrder",orderId:data.orderId,orderStatus:orderStatus},function (data) {
					if(data == "ok"){
						obj.tr.children("[data-field='orderStatus']").children("div").html('<span style="color: #FFD700;">待收货</span>');
						layer.msg("发货成功");
					}else {
						layer.msg("操作失败！");
					}

				});

			}else {
				layer.msg("此订单，您不能进行此操作哦！");
			}
			/*$.post("../../orderController",{biz:"editOrder",orderId:data.orderId,orderStatus:orderStatus},function (data) {
				layer.msg("修改成功");
			});*/

			layer.close(index);
		},function(){
			layer.close();
		});
    }
  });

$(function(){
	//为增加按钮绑定点击事件
	$("#add").click(function(){
		layer.confirm("", {
			title: "编辑",
			width: 600,
			height: 800,
			content:$("#div3").html()
		},function(index){
			layer.close(index);
		},function(index){
			layer.close(index);
		});
	});

	
	/*//为删除按钮绑定点击事件
	$("#delete").click(function() {
		//获取用户选择的行
		var checkStatus = table.checkStatus('category');
		//1.如果用户没有选择行，弹出提示框
		if(checkStatus.data.length == 0) {
			layer.msg("请选择一行进行操作");
			return;
		}
		if(checkStatus.data.length >= 1) {
			//要求客户确认后再删除
			layer.confirm("是否确定删除选择的行", {
				title: "删除",
				icon: 3,
				width: 300,
				height: 400
			}, function(index) { //用户确认后的回调函数
				//关闭询问框
				layer.close(index);
				//发送异步请求删除，现在使用进度条来模拟删除
				layer.confirm("正在删除", {
					title: "进度",
					width: 300,
					height: 400,
					content: $("#div2").html()
				}, function(index) {
					layer.close(index);
				}, function() {
					layer.close(index);
				});
				//重新渲染表格
				tableRender();
			}, function(index) { //用户取消后的回调函数
			});
		}
	});

	//为修改按钮绑定点击事件
	$("#update").click(function() {
		//获取用户选择的行
		var checkStatus = table.checkStatus('category');
                                        		//1.如果用户没有选择行，弹出提示框
		if(checkStatus.data.length == 0) {
			layer.msg("请选择一行进行操作");
			return;
		}
		//2.如果用户选择多余一行，弹出提示框
		if(checkStatus.data.length > 1) {
			layer.msg("只能选择一行进行操作");
			return;
		}
		//3.如果用户选择恰好一行
		if (checkStatus.data.length == 1) {
			//获取选中行的数据
			checkStatus.data;
			//弹出一个修改的模态框或跳转页面，将获取的数据填入到对应的文本框中
			layer.confirm("", {
				title: "编辑",
				width: 600,
				height: 800,
				content:$("#div3").html()
			},function(index){
				layer.close(index);
			},function(){
				layer.close(index);
			});
		}	
	});*/
});

