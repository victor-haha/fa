//layui初始化表格模组
layui.use(['laydate', 'util'],function () {
    var $ = layui.jquery;


    $("#imgRefresh").click(function () {
        $(this).attr("src","../../loginCodeServlet?"+ new Date().getTime());
    });


    $("#submit").submit(function () {
        var userName = $("#userName").val();
        var password = $("#password").val();
        var vercode = $("#vercode").val();
        $.get("../../userController",{biz:"login",userName:userName,password:password,vercode:vercode},function (data) {
            if(data.flag){//登录成功
                $("#msg").html("");
                window.location.href="../admin.html";
            }else { //登录失败
                $("#msg").html(data.msg);
                $("#imgRefresh").attr("src","../../loginCodeServlet?"+ new Date().getTime());

            }
        });
        return false;
    });

    $("#userName").blur(function () {
        if(!checkuser()){
            $("#usermsg").html("用户名格式错误（8—20字符，非空格）");
        }else {
            $("#usermsg").html("");
        }

    });
    $("#password").blur(function () {
        if(!password()){
            $("#pwdmsg").html("密码格式错误（8—20字符，非空格）");
        }else {
            $("#pwdmsg").html("");
        }
    });
    //验证用户名
    function checkuser() {
        //获取表单用户名
        var username = $("#userName").val();
        //定义正则
        var reg_username = /^\w{8,20}$/;
        //3.判断，给出提示信息
        var flag = reg_username.test(username);
        return flag;
    }
    //验证密码
    function password(){
        //获取表单用户名
        var password = $("#password").val();
        //定义正则
        var reg_password = /^\w{8,20}$/;
        //3.判断，给出提示信息
        var flag = reg_password.test(password);
        return flag;
    }
});