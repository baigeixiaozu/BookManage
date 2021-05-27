
$(function() {
    $("#btn").click(function () {
        var bookname=[];
        $("#books li").each(function(i){
            bookname[i]=$(this).text();
        });
        var bookcount=[];
        $("#books button").each(function (i){
         bookcount[i]=$(this).val();
        })
        $.ajax({
            type: "post",
            url: "/BookManage/MarketingSystemServlet",
            data: {"bookName":bookname, "bookCount":bookcount},
            dataType: "text",
            success: function (data) {
                alert("提交成功!"+"\r\n"+data);

            },
            error: function () {
                alert("提交失败,请重试或者检查您的环境");
            }
        })
    })
})

