
function httpGet(url, data) {
    return new Promise((resolve, reject) => {
        $.get(url, data, resolve);
    });
}
function httpPost(url, param) {
    return new Promise((resolve, reject) => {
        $.post(url, param, res=>{
            if(res.code && res.code === 2000){
                resolve(res)
            }else{
                reject(res)
            }
        });
    });
}

// 用户操作模块
const USER = function (){
    // 注销操作
    const logout = function (){
        httpGet("Api/User/logout").then(r => {
            console.log(r)
            if(r.code === 2000){
                Swal.fire({
                    title: '注销成功！',
                    icon: 'success',
                    html: '2秒后返回首页',
                    showCloseButton: false,
                    showCancelButton: false,
                    focusConfirm: false
                })
                setTimeout(()=>{
                    location.href = ""
                }, 2000)
            }
        })
    }

    // 登录操作
    const login = function (){
        Swal.fire({
            title: '请稍等...',
            icon: 'info',
            html:
                '登录中...',
            showCloseButton: false,
            showCancelButton: false,
            focusConfirm: false
        })
        // 发送 POST 请求
        httpPost('Api/User/login', {
            username: uinfo.username.value,
            password: uinfo.password.value
        })
            .then(function (response) {
                console.log(response);

                const request = getRequest();
                let redirection = request.redirect || "user/info.jsp"
                Swal.fire({
                    title: '登录成功！',
                    icon: 'success',
                    html: '准备跳转至' + redirection,
                    showCloseButton: false,
                    showCancelButton: false,
                    focusConfirm: false
                })

                location.href = redirection;
            })
            .catch(function (error) {
                console.log("错误", error);

                Swal.fire({
                    title: '登录失败！',
                    icon: 'error',
                    html: '<span style="color: red">' + error.msg + '</span>',
                    showCloseButton: false,
                    showCancelButton: false,
                    focusConfirm: false,
                    timer: 5000
                })
            });
    }
    return {
        logout:logout,
        login:login
    }
}();

function getRequest() {
    let url = location.search; //获取url中"?"符后的字串
    let theRequest = new Object();
    if (url.indexOf("?") != -1) {
        let str = url.substr(1);
        let strs = str.split("&");
        for(let i = 0; i < strs.length; i ++) {
            theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);
        }
    }
    return theRequest;
}