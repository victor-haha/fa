function exit() {
    $.get("../userController",{biz:"exit"},function (data) {
        if(data == "ok"){
            location.href="content/login.html";
            layer.msg("成功退出");
        }
    })
}
$(function () {
    $.get("../userController",{biz:"relogin"},function (data) {
        if(data.userId.length != 0){
            $("[login='N']").css("display","none");
            $("[login='Y']").css("display","inline-block");
            $("#userName").html(data.nickname);
        }else{
            $("[login='N']").css("display","inline-block");

            $("[login='Y']").css("display","none");
        }
    });

});