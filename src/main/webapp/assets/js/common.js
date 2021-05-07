
function httpGet(url) {
    return new Promise((resolve, reject) => {
        $.get(url, resolve);
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