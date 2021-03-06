layui.use(['layer'], function () {
    var layer = layui.layer;

    //获得当前用户名，在左侧导航栏显示
    $.ajax({
        type: 'get'
        , url: 'login'
        , data: {}
        , success: function (user) {
            if (user == '' || user == 'null') {
                layer.msg('登陆过期，请重新登录', {
                    shade: 0.8
                    , offset: '200px'
                    , time: 0 //不自动关闭
                    , btn: '重新登录'
                    , yes: function () {
                        window.location.href = 'login.jsp'
                    }
                });
            } else
                $('#user').append(user);
        }
    });
    $('#user').click(function () {
        $('#logout').toggle();
    });
    //登出请求
    $('#logout').click(function () {
        layer.msg('确定要退出登录吗？', {
            time: 0 //不自动关闭
            , shade: 0.8
            , offset: '200px'
            , btn: ['确定', '返回']
            , yes: function () {
                $.ajax({
                    type: 'get'
                    , url: 'logout'
                    , data: {}
                    , success: function () {
                        window.location.href = 'login.jsp';
                    }
                });
            }
        });

    });

});


