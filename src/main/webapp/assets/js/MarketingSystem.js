window.onload = function () {
    var header = document.getElementsByClassName("box");
    var Nav = header[0].getElementsByTagName("div");
    var container = document.getElementsByClassName("box1");
    var show = container[0].getElementsByClassName("item");
    for (var i = 0; i < Nav.length; i++) {
        Nav[i].index = i;
        Nav[i].onclick = function () {
            for (let i = 0; i < Nav.length; i++) {
                Nav[i].className = '';
                show[i].style.display = "none";
            }
            show[this.index].style.display = "block"
        }
        for (var m = 1; m < Nav.length; m++) {
            Nav[m].className = '';
            show[m].style.display = "none";
        }
    }
    var Btn=document.getElementById("btn");
    Btn.onclick=function (){
        confirm("确定提交？");
    }
}